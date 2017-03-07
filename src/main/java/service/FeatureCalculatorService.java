package service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import service.features.DeliveryBoyIdleTime;
import service.features.LastMileCost;
import service.features.OrderDelayFeature;
import service.features.SingleEntityFeature;

import java.util.ArrayList;

@Singleton
public class FeatureCalculatorService {
	// We are creating individual objects for each feature.
	// The idea is to fetch all classed annoted with @OrderFeature or @DeliveryBoy
	// feature and use them accordingly. Due to lack of time, it is done like this.
	// Will clean it if I get time.

	private ArrayList<SingleEntityFeature> deliveryBoyFeatures;
	private ArrayList<SingleEntityFeature> orderFeatures;

	private DeliveryBoyIdleTime deliveryBoyIdleTime;
	private LastMileCost lastMileCost;
	private OrderDelayFeature orderDelayFeature;

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

	public void test() {
		deliveryBoyFeatures.stream().forEach(x -> {
			System.out.println(x.getValue(null));
		});

		orderFeatures.stream().forEach(x -> {
			System.out.println(x.getValue(null));
		});
	}
}
