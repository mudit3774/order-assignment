package service.features;

import com.google.inject.Inject;
import model.DeliveryBoy;
import model.Point;

// We should pass Restaurant instead of point, Just avoiding a new object
public class LastMileCost implements MultipleEntityFeature<Point, DeliveryBoy> {

	@Inject
	public LastMileCost() {
	}

	public Double getValue(Point point, DeliveryBoy deliveryBoy) {
		return 0.7;
	}

}
