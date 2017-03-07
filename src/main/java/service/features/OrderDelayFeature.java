package service.features;

import com.google.inject.Inject;
import service.features.annotation.OrderFeature;

@OrderFeature
public class OrderDelayFeature implements SingleEntityFeature{

	@Inject
	public OrderDelayFeature() {
	}

	@Override
	public Double getValue(Object object) {
		return 0.5;
	}
}
