package service.features;

import com.google.inject.Inject;
import model.DeliveryBoy;
import model.Point;
import org.jblas.DoubleMatrix;

import java.util.List;

// We should pass Restaurant instead of point, Just avoiding a new object
public class LastMileCost implements MultipleEntityFeature<Point, DeliveryBoy> {

	@Inject
	public LastMileCost() {
	}

	@Override
	public DoubleMatrix getValue(Point orderLocations, List<DeliveryBoy> deliveryBoys) {
		return null;
	}

	public Double getValue(Point point, DeliveryBoy deliveryBoy) {
		return 0.7;
	}

}
