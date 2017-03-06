package dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.exception.GettingDeliveryBoysFailed;
import model.DeliveryBoy;
import model.Point;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
public class DeliveryBoyDAO {

	private static final String SQL_GET_DELIVERY_BOYS = "select id, ST_X(location::geometry), ST_Y(location::geometry) " +
		"from delivery_boys where ST_DWithin(Geography(location), Geography(ST_MakePoint(?, ?)), ?);";

	private Connection connection;

	@Inject
	public DeliveryBoyDAO(PostgresDAO postgresDAO) {
		this.connection = postgresDAO.getConnection();
	}

	public List<DeliveryBoy> getDeliveryBoysWithinRadius(Point location, Integer radiusInMeter) {
		try {
			PreparedStatement st = connection.prepareStatement(SQL_GET_DELIVERY_BOYS);
			st.setDouble(1, location.getLatitude());
			st.setDouble(2, location.getLongitude());
			st.setInt(3, radiusInMeter);
			ResultSet rs = st.executeQuery();
			return transformResultSetToGetDeliveryBoys(rs);
		} catch (Exception e) {
			throw new GettingDeliveryBoysFailed(e.getMessage(), e.getCause());
		}
	}

	private List<DeliveryBoy> transformResultSetToGetDeliveryBoys(ResultSet rs) throws SQLException {
		List<DeliveryBoy> deliveryBoys = new ArrayList<DeliveryBoy>();
		System.out.println(rs.toString());
		while (rs.next())
		{
			System.out.println(rs);
			deliveryBoys.add(new DeliveryBoy(
				UUID.fromString(rs.getString(1)),
				new Point(
					rs.getDouble(2),
					rs.getDouble(3)
				)
			));
		}

		return deliveryBoys;
	}

}
