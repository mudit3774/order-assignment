package service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.DeliveryBoyDAO;
import model.DeliveryBoy;
import model.Point;

import java.util.List;

import static constant.ServiceConstants.DeliveryBoys.MAX_DISTANCE;

// This is a separate concern and should ideally be a wrapper over deliveryBoyService (external)
@Singleton
public class DeliveryBoyService {

	private final DeliveryBoyDAO deliveryBoyDAO;

	@Inject
	public DeliveryBoyService(DeliveryBoyDAO deliveryBoyDAO) {
		this.deliveryBoyDAO = deliveryBoyDAO;
	}

	// This is a wrapper, again there can be some business logic after interaction with the DB. Hence, abstracted.
	public List<DeliveryBoy> getDeliveryBoys(Point location) {
		return deliveryBoyDAO.getDeliveryBoysWithinRadius(location, MAX_DISTANCE);
	}
}
