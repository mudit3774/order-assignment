import com.google.inject.AbstractModule;
import service.features.DeliveryBoyIdleTime;
import service.features.LastMileCost;
import service.features.MultipleEntityFeature;
import service.features.SingleEntityFeature;

public class OrderingModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(MultipleEntityFeature.class).to(LastMileCost.class);
		bind(SingleEntityFeature.class).to(DeliveryBoyIdleTime.class);
//		bind(SingleEntityFeature.class).to(OrderDelayFeature.class);
	}
}
