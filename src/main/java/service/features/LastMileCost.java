package service.features;

import model.DeliveryBoy;
import model.Point;

// We should pass Restaurant instead of point, Just avoiding a new object
public class LastMileCost implements MultipleEntityFeature<Point, DeliveryBoy> {

	public Double getValue(Point point, DeliveryBoy deliveryBoy) {
		return null;
	}

}
