package service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import model.DeliveryBoy;
import model.Order;
import org.apache.log4j.Logger;
import org.jblas.DoubleMatrix;
import service.features.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.jblas.DoubleMatrix.ones;
import static sun.swing.MenuItemLayoutHelper.max;

@Singleton
public class FeatureCalculatorService {
	// We are creating individual objects for each feature.
	// The idea is to fetch all classed annotated with @OrderFeature or @DeliveryBoy
	// feature and use them to crate arrayList of feature. Due to lack of time, it is done like this.
	// Will clean it if I get time.

	final static Logger logger = Logger.getLogger(FeatureCalculatorService.class);
	private ArrayList<SingleEntityFeature> deliveryBoyFeatures;
	private ArrayList<SingleEntityFeature> orderFeatures;
	private ArrayList<MultipleEntityFeature> orderDeliveryBoyFeatures;

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

		this.orderDeliveryBoyFeatures = new ArrayList<>();
		this.orderDeliveryBoyFeatures.add(lastMileCost);
	}

	public DoubleMatrix getFeatureCostMatrix(Map<Order, List<DeliveryBoy>> possibleAssignment) {
		Set<UUID> orderIDs = possibleAssignment.keySet().stream().map(Order::getOrderID).collect(Collectors.toSet());
		Set<UUID> deliveryBoyIDs = possibleAssignment.values().stream().flatMap(List::stream).map(DeliveryBoy::getID)
			.collect(Collectors.toSet());

		Map<UUID, Integer> orderIDToIndexMap = getMap(orderIDs);
		Map<UUID, Integer> deliveryBoyIDToIndexMap = getMap(deliveryBoyIDs);

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

	private Map<UUID, Integer> getMap(Set<UUID> elementIDs){
		Map<UUID, Integer> returnMap = new HashMap<>();
		Integer elementIndexValue = 0;
		for(UUID elementID:elementIDs) {
			returnMap.put(elementID, elementIndexValue);
			elementIndexValue +=1 ;
		}
		return returnMap;
	}

	// Note : The features have a bounding value, hence not normalised.
	// We can also normalise if this is not giving good results.m
	private Double calculateCost(Order order, DeliveryBoy deliveryBoy) {
		// TODO : make it a weight sum of all features
		Double orderDeliveryBoyFeatureCost = orderDeliveryBoyFeatures.stream()
			.map(orderDeliveryBoyFeature -> orderDeliveryBoyFeature.getValue(order.getOrderLocation(), deliveryBoy))
			.mapToDouble(i->i).sum();

		Double orderFeatureCost = orderFeatures.stream()
			.map(orderFeature -> orderFeature.getValue(order))
			.mapToDouble(i->i).sum();

		Double deliveryBoyFeatureCost = deliveryBoyFeatures.stream()
			.map(deliveryBoyFeature -> deliveryBoyFeature.getValue(deliveryBoy))
			.mapToDouble(i->i).sum();

		// TODO : make it a weight sum of all features
		return orderDeliveryBoyFeatureCost + deliveryBoyFeatureCost + orderFeatureCost;
	}
}
