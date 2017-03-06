package controller;

import dao.exception.OrderInsertFailedException;
import model.Order;
import model.context.StashRequest;
import service.StashOrderService;

import javax.inject.Inject;

public class StashOrder {

	private final StashOrderService stashOrderService;

	@Inject
	public StashOrder(StashOrderService stashOrderService) {
		this.stashOrderService = stashOrderService;
	}

	public Integer stash(StashRequest request) throws OrderInsertFailedException {
		// Note : Using a builder would help, but I am assuming the request is validated by the client.
		// also, saving time :P

		Order order = new Order(
			request.getOrderID(),
			request.getCityID(),
			request.getAreaID(),
			request.getAssignAfter(),
			request.getOrderLocation()
		);

		return stashOrderService.stash(order);
	}
}
