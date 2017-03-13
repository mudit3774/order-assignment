package util;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.UUID;

public class ObjectMother {

	UUID TEST_DELIVERY_BOY_1 = new UUID(1212035, 1012321023);
	UUID TEST_DELIVERY_BOY_2 = new UUID(1233125, 121231223);
	UUID TEST_ORDER_1 = new UUID(5123901, 112313121);
	UUID TEST_ORDER_2 = new UUID(5123901, 1123166121);


	public Map<UUID, Map<UUID, Double>> getTestOptimizationMatrix(){

		Map<UUID, Double> orderDeliveryBoysCost1 = ImmutableMap.<UUID, Double>builder()
			.put(TEST_DELIVERY_BOY_1, 0.8)
			.put(TEST_DELIVERY_BOY_2, 0.3)
			.build();

		Map<UUID, Double> orderDeliveryBoysCost2 = ImmutableMap.<UUID, Double>builder()
			.put(TEST_DELIVERY_BOY_1, 0.7)
			.put(TEST_DELIVERY_BOY_2, 0.1)
			.build();

		Map<UUID, Map<UUID, Double>> TEST_MATRIX = ImmutableMap.<UUID, Map<UUID, Double>>builder()
			.put(TEST_ORDER_1, orderDeliveryBoysCost1)
			.put(TEST_ORDER_2, orderDeliveryBoysCost2)
			.build();

		return TEST_MATRIX;

	}

	public Map<UUID, Map<UUID, Double>> getRowReducedTestOptimizationMatrix(){
		Map<UUID, Double> orderDeliveryBoysCostReduced1 = ImmutableMap.<UUID, Double>builder()
			.put(TEST_DELIVERY_BOY_1, 0.5)
			.put(TEST_DELIVERY_BOY_2, 0.0)
			.build();

		Map<UUID, Double> orderDeliveryBoysCostReduced2 = ImmutableMap.<UUID, Double>builder()
			.put(TEST_DELIVERY_BOY_1, 0.6)
			.put(TEST_DELIVERY_BOY_2, 0.0)
			.build();

		Map<UUID, Map<UUID, Double>> ROW_REDUCED_MATRIX = ImmutableMap.<UUID, Map<UUID, Double>>builder()
			.put(TEST_ORDER_1, orderDeliveryBoysCostReduced1)
			.put(TEST_ORDER_2, orderDeliveryBoysCostReduced2)
			.build();

		return ROW_REDUCED_MATRIX;

	}
}
