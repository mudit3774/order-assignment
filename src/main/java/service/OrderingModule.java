package service;

import com.google.inject.AbstractModule;
import service.features.*;

public class OrderingModule extends AbstractModule {
	@Override
	public void configure() {
		bind(MultipleEntityFeature.class).to(LastMileCost.class);
		bind(SingleEntityFeature.class).to(DeliveryBoyIdleTime.class);
//		bind(SingleEntityFeature.class).to(OrderDelayFeature.class);
	}
}
