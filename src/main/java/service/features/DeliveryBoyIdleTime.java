package service.features;

import com.google.inject.Inject;
import model.DeliveryBoy;
import service.features.annotation.DeliveryBoyFeature;

@DeliveryBoyFeature
public class DeliveryBoyIdleTime implements SingleEntityFeature<DeliveryBoy> {

	@Inject
	public DeliveryBoyIdleTime() {
	}

	public Double getValue(DeliveryBoy deliveryBoy) {
		return 0.8;
	}
}
