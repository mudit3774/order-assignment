package service.features;

import model.DeliveryBoy;
import service.features.annotation.DeliveryBoyFeature;

@DeliveryBoyFeature
public class DeliveryBoyIdleTime implements SingleEntityFeature<DeliveryBoy> {
	public Double getValue(DeliveryBoy deliveryBoy) {
		return null;
	}
}
