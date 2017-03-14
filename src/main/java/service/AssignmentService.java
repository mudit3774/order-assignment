package service;

import com.google.inject.Inject;
import model.DeliveryBoy;
import model.Order;
import org.apache.log4j.Logger;
import org.jblas.DoubleMatrix;
import util.assignmentStrategies.AssignmentStrategy;
import util.assignmentStrategies.HungarianAssignment;

import java.util.*;
import java.util.stream.Collectors;

public class AssignmentService {

	final static Logger logger = Logger.getLogger(AssignmentService.class);
	private FeatureCalculatorService featureCalculatorService;
	private DeliveryBoyService deliveryBoyService;
	private StashOrderService stashOrderService;
	private AssignmentStrategy assignmentStrategy;

	@Inject
	public AssignmentService(FeatureCalculatorService featureCalculatorService,
	                         DeliveryBoyService deliveryBoyService,
	                         StashOrderService stashOrderService,
	                         HungarianAssignment hungarianAssignment) {
		this.featureCalculatorService = featureCalculatorService;
		this.deliveryBoyService = deliveryBoyService;
		this.stashOrderService = stashOrderService;
		this.assignmentStrategy = hungarianAssignment;
	}

	public Map<UUID, UUID> assign(Long cityID, Long areaID){
		Map<Order, List<DeliveryBoy>> possibleAssignment = getPossibleAssignments(cityID, areaID);

		Set<UUID> orderIDs = possibleAssignment.keySet().stream().map(Order::getOrderID).collect(Collectors.toSet());
		Set<UUID> deliveryBoyIDs = possibleAssignment.values().stream().flatMap(List::stream).map(DeliveryBoy::getID)
			.collect(Collectors.toSet());

		Map<Integer, UUID> orderIDToIndexRevMap = getRevMap(orderIDs);
		Map<Integer, UUID> deliveryBoyIDToIndexRevMap = getRevMap(deliveryBoyIDs);

		DoubleMatrix costMatrix = featureCalculatorService.getFeatureCostMatrix(possibleAssignment);
		Map<Integer, Integer> assignment = assignmentStrategy.assign(costMatrix);
		return transformToUUID(assignment, orderIDToIndexRevMap, deliveryBoyIDToIndexRevMap);
	}

	private Map<Integer, UUID> getRevMap(Set<UUID> elementIDs){
		Map<Integer, UUID> revMap = new HashMap<>();
		Integer elementIndexValue = 0;
		for(UUID elementID:elementIDs) {
			revMap.put(elementIndexValue, elementID);
			elementIndexValue +=1 ;
		}
		return revMap;
	}

	private Map<UUID,UUID> transformToUUID(Map<Integer, Integer> assignment,
	                                       Map<Integer, UUID> orderIDToIndexRevMap,
	                                       Map<Integer, UUID> deliveryBoyIDToIndexRevMap) {
		return assignment.entrySet().stream().collect(Collectors.toMap(e-> orderIDToIndexRevMap.get(e.getKey()),
			e -> deliveryBoyIDToIndexRevMap.get(e.getValue())));
	}

	private Map<Order, List<DeliveryBoy>> getPossibleAssignments(Long cityID, Long areaID){
		return stashOrderService.getOrders(cityID, areaID)
			.parallelStream()
			.map(order -> {
				Map<Order, List<DeliveryBoy>> possibleAssignment = new HashMap<>();
				possibleAssignment.put(order, deliveryBoyService.getDeliveryBoys(order.getOrderLocation()));
				return possibleAssignment;
			}).collect(HashMap::new, Map::putAll, Map::putAll);
	}
}
