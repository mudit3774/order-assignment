package service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.OrderStashDAO;
import dao.exception.OrderInsertFailedException;
import model.Order;

import java.util.List;

@Singleton
public class StashOrderService {

	private final OrderStashDAO orderStashDAO;

	@Inject
	public StashOrderService(OrderStashDAO orderStashDAO) {
		this.orderStashDAO = orderStashDAO;
	}

	// Note : This is a wrapper over the DAO, in case we want to have custom business logic.
	public Integer stash(Order order) throws OrderInsertFailedException {
		return orderStashDAO.create(order);
	}

	public List<Order> getOrders(Long cityID, Long areaID){
		return orderStashDAO.getOrdersFromStash(cityID, areaID);
	}
}
