import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.javafx.collections.ImmutableObservableList;
import dao.exception.OrderInsertFailedException;
import model.Order;
import model.Point;
import service.StashOrderService;

import java.util.List;
import java.util.UUID;

public class MainClass {

	public static void main(String[] args) throws OrderInsertFailedException {
		Injector injector = Guice.createInjector(new OrderingModule());
//		StashOrder stashOrder =  injector.getInstance(StashOrder.class);
//		stashOrder.stash(new StashRequest(
//			new UUID(1, 1123131310),
//			7L,
//			128L,
//			System.currentTimeMillis() + 5000,
//			new Point(72.1223323, 18.11238022)
//		));
//
//		DeliveryBoyService deliveryBoyService = injector.getInstance(DeliveryBoyService.class);
//		System.out.print(deliveryBoyService.getDeliveryBoys(new Point(12.2312, 19.209)));

		StashOrderService stashOrderService = injector.getInstance(StashOrderService.class);
		System.out.println(stashOrderService.getOrders(7L, 128L));

	}

	// This a dummy functions that gives hardcoded orders for now.
	public static List<Order> getOrders(){
		Order order1 = new Order(
			new UUID(1, 1123131310),
			7L,
			128L,
			5L,
			new Point(72.1223323, 18.11238022)
		);

		Order order2 = new Order(
			new UUID(2, 1231212310),
			5L,
			188L,
			6L,
			new Point(72.1223323, 19.11238022)
		);

		Order order3 = new Order(
			new UUID(3, 11212870),
			1L,
			198L,
			5L,
			new Point(72.1223323, 18.11238022)
		);

		Order order4 = new Order(
			new UUID(4, 10123812),
			1L,
			198L,
			5L,
			new Point(72.1223323, 18.11238022)
		);

		return new ImmutableObservableList<Order>(order1, order2, order3, order4);
	}

}
