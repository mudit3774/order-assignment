import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.javafx.collections.ImmutableObservableList;
import controller.StashOrder;
import dao.exception.OrderInsertFailedException;
import model.Order;
import model.Point;
import model.context.StashRequest;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;
import service.AssignmentService;
import service.OrderingModule;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MainClass {

	final static Logger logger = Logger.getLogger(Main.class);

	// Please ignore this code. Mostly for testing
	public static void main(String[] args) throws OrderInsertFailedException {
		Injector injector = Guice.createInjector(new OrderingModule());
		StashOrder stashOrder =  injector.getInstance(StashOrder.class);
		AssignmentService assignmentService = injector.getInstance(AssignmentService.class);

		// Test Stash Order
		logger.info("Stashing Orders Test :");

		getOrders().forEach(order -> {
			logger.info("Stashing order with orderID : " + order.getOrderID() );
			stashOrder.stash(new StashRequest(
				order.getOrderID(),
				order.getCityID(),
				order.getAreaID(),
				order.getAssignAfter(),
				order.getOrderLocation()
			));
		});

		// Test Assignment
		logger.info("Running Assignment on seeded data");
		Map<UUID, UUID> finalAssignments = assignmentService.assign(1L, 7L);
		logger.info("Final Assignments : ");
		logger.info(finalAssignments);
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
			new UUID(2, 1212342310),
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
