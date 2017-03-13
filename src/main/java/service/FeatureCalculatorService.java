package service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import model.DeliveryBoy;
import model.Order;
import org.jblas.DoubleMatrix;
import service.features.DeliveryBoyIdleTime;
import service.features.LastMileCost;
import service.features.OrderDelayFeature;
import service.features.SingleEntityFeature;

import java.util.*;
import java.util.stream.Collectors;

import static org.jblas.DoubleMatrix.ones;
import static sun.swing.MenuItemLayoutHelper.max;

@Singleton
public class FeatureCalculatorService {
	// We are creating individual objects for each feature.
	// The idea is to fetch all classed annotated with @OrderFeature or @DeliveryBoy
	// feature and use them accordingly. Due to lack of time, it is done like this.
	// Will clean it if I get time.

	private ArrayList<SingleEntityFeature> deliveryBoyFeatures;
	private ArrayList<SingleEntityFeature> orderFeatures;

	private DeliveryBoyIdleTime deliveryBoyIdleTime;
	private LastMileCost lastMileCost;
	private OrderDelayFeature orderDelayFeature;

	private static Double M = 500.0;

	@Inject
	public FeatureCalculatorService(DeliveryBoyIdleTime deliveryBoyIdleTime,
	                                LastMileCost lastMileCost,
	                                OrderDelayFeature orderDelayFeature) {
		this.deliveryBoyIdleTime = new DeliveryBoyIdleTime();
		this.lastMileCost = lastMileCost;
		this.orderDelayFeature = orderDelayFeature;

		this.deliveryBoyFeatures = new ArrayList<>();
		this.deliveryBoyFeatures.add(deliveryBoyIdleTime);

		this.orderFeatures = new ArrayList<>();
		this.orderFeatures.add(orderDelayFeature);
	}

	public DoubleMatrix getFeatureCostMatrix(Map<Order, List<DeliveryBoy>> possibleAssignment) {
		List<UUID> orderIDs = possibleAssignment.keySet().stream().map(Order::getOrderID).collect(Collectors.toList());
		List<UUID> deliveryBoyIDs = possibleAssignment.values().stream().flatMap(List::stream).map(DeliveryBoy::getID)
			.collect(Collectors.toList());

		Map<UUID, Integer> orderIDToIndexMap = new HashMap<>();
		Map<UUID, Integer> deliveryBoyIDToIndexMap = new HashMap<>();

		Integer orderIndexValue = 0;
		for(UUID orderID:orderIDs) {
			orderIDToIndexMap.put(orderID, orderIndexValue);
			orderIndexValue +=1 ;
		}

		Integer deliveryBoyIndexValue = 0;
		for(UUID deliveryBoyID:deliveryBoyIDs) {
			deliveryBoyIDToIndexMap.put(deliveryBoyID, deliveryBoyIndexValue);
			deliveryBoyIndexValue +=1 ;
		}

		Integer costMatrixSize = max(orderIDs.size(), deliveryBoyIDs.size());
		DoubleMatrix costMatrix = ones(costMatrixSize, costMatrixSize).mul(M);

		for(Order order:possibleAssignment.keySet())
		{
			for(DeliveryBoy deliveryBoy:possibleAssignment.get(order)) {
				costMatrix.put(orderIDToIndexMap.get(order.getOrderID()),
					deliveryBoyIDToIndexMap.get(deliveryBoy.getID()),
					calculateCost(order, deliveryBoy));
			}
		}

		return costMatrix;
	}

	private Double calculateCost(Order order, DeliveryBoy deliveryBoy) {
		return 0.1;
	}
}
