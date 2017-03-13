package service;

import com.google.inject.Inject;
import model.DeliveryBoy;
import model.Order;
import org.apache.log4j.Logger;
import org.jblas.DoubleMatrix;
import util.assignmentStrategies.AssignmentStrategy;
import util.assignmentStrategies.HungarianAssignment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
		Map<Order, List<DeliveryBoy>> possibleAssignments = getPossibleAssignments(cityID, areaID);
		DoubleMatrix costMatrix = featureCalculatorService.getFeatureCostMatrix(possibleAssignments);
		Map<Integer, Integer> assignment = assignmentStrategy.assign(costMatrix);
		return null;
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
