package service.features;

import model.Order;
import service.features.annotation.OrderFeature;

@OrderFeature
public class OrderDelayFeature implements SingleEntityFeature<Order> {
	public Double getValue(Order order) {
		return null;
	}
}
