package service;

import com.google.inject.Inject;
import model.DeliveryBoy;
import model.Order;
import util.HungarianAssignment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class AssignmentService {
	private FeatureCalculatorService featureCalculatorService;
	private DeliveryBoyService deliveryBoyService;
	private StashOrderService stashOrderService;

	private HungarianAssignment hungarianAssignment;

	@Inject
	public AssignmentService(FeatureCalculatorService featureCalculatorService,
	                         DeliveryBoyService deliveryBoyService,
	                         StashOrderService stashOrderService) {
		this.featureCalculatorService = featureCalculatorService;
		this.deliveryBoyService = deliveryBoyService;
		this.stashOrderService = stashOrderService;
	}

	public Map<UUID, UUID> assign(Long city_id, Long area_id){
		List<Map<Order, List<DeliveryBoy>>> possibleAssignments = stashOrderService.getOrders(city_id, area_id)
			.parallelStream()
			.map(order -> {
				Map<Order, List<DeliveryBoy>> possibleAssignment = new HashMap<Order, List<DeliveryBoy>>();
				possibleAssignment.put(order, deliveryBoyService.getDeliveryBoys(order.getOrderLocation()));
				return possibleAssignment;
			}).collect(Collectors.toList());


		Map<UUID, Map<UUID, Double>> featureMatrix = featureCalculatorService.getFeatureCostMatrix(
			flattenList(possibleAssignments));
		return null;
		//return hungarianAssignment.assign(featureMatrix);
	}

	private Map<Order, List<DeliveryBoy>> flattenList(List<Map<Order, List<DeliveryBoy>>> list) {
		return null;
	}
}
