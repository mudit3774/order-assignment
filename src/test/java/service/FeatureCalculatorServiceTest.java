package service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import model.DeliveryBoy;
import model.Order;
import model.Point;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;

public class FeatureCalculatorServiceTest {

	private FeatureCalculatorService featureCalculatorService;

	Injector injector = Guice.createInjector(new OrderingModule());

	@Before
	public void setUp(){
		featureCalculatorService = injector.getInstance(FeatureCalculatorService.class);
	}

	@Test
	public void getFeatureCostMatrix() throws Exception {
		Map<Order, List<DeliveryBoy>> TEST_INPUT = new HashMap<>();

			Order TEST_ORDER_1 = new Order(
			new UUID(12,1231421),
			1L,
			2L,
			300L,
			new Point(12.12312, 12.2123)
		);

		Order TEST_ORDER_2 = new Order(
			new UUID(12,1312421),
			1L,
			2L,
			300L,
			new Point(12.12312, 12.2123)
		);

		Order TEST_ORDER_3 = new Order(
			new UUID(12,5342323),
			1L,
			2L,
			300L,
			new Point(12.12312, 12.2123)
		);

		DeliveryBoy TEST_DELIVERY_BOY_1 = new DeliveryBoy(
			new UUID(12,132131),
			new Point(12.12312, 12.2123)
		);

		DeliveryBoy TEST_DELIVERY_BOY_2 = new DeliveryBoy(
			new UUID(12,323213312),
			new Point(12.12312, 12.2123)
		);

		List<DeliveryBoy> DeliveryBoysForOrder1 = new ArrayList<>();
		DeliveryBoysForOrder1.add(TEST_DELIVERY_BOY_1);
		DeliveryBoysForOrder1.add(TEST_DELIVERY_BOY_2);

		List<DeliveryBoy> DeliveryBoysForOrder2 = new ArrayList<>();
		DeliveryBoysForOrder2.add(TEST_DELIVERY_BOY_1);

		List<DeliveryBoy> DeliveryBoysForOrder3 = new ArrayList<>();
		DeliveryBoysForOrder3.add(TEST_DELIVERY_BOY_2);

		TEST_INPUT.put(TEST_ORDER_1, DeliveryBoysForOrder1);
		TEST_INPUT.put(TEST_ORDER_2, DeliveryBoysForOrder2);
		TEST_INPUT.put(TEST_ORDER_3, DeliveryBoysForOrder3);

		assertEquals(featureCalculatorService.getFeatureCostMatrix(TEST_INPUT).rows, 3);
	}

}