package service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.OrderStashDAO;
import dao.exception.OrderInsertFailedException;
import model.Order;
import org.apache.log4j.Logger;

import java.util.List;

@Singleton
public class StashOrderService {

	final static Logger logger = Logger.getLogger(StashOrderService.class);
	private final OrderStashDAO orderStashDAO;

	@Inject
	public StashOrderService(OrderStashDAO orderStashDAO) {
		this.orderStashDAO = orderStashDAO;
	}

	// Note : This is a wrapper over the DAO, in case we want to have custom business logic.
	public Integer stash(Order order) throws OrderInsertFailedException {
		logger.debug("Stashing Order : " + order.getOrderID());
		return orderStashDAO.create(order);
	}

	public List<Order> getOrders(Long cityID, Long areaID){
		logger.debug("Getting Orders for cityID: " + cityID);
		return orderStashDAO.getOrdersFromStash(cityID, areaID);
	}
}
