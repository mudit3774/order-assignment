package dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.exception.GettingOrdersFailed;
import dao.exception.OrderInsertFailedException;
import model.Order;
import model.Point;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Note : Avoiding ORMs, not a big fan.

@Singleton
public class OrderStashDAO {

	private static final String SQL_INSERT_ORDER = "insert into orders " +
		"(order_id, area_id, city_id, assign_at, location) values " +
		"(?, ?, ?, ?, ST_SetSRID(ST_MakePoint(?, ?), 4326));";

	private static final String SQL_GET_ORDERS_BY_CITY_AREA = "select order_id, city_id, area_id, assign_at, " +
 		"st_x(location::geometry), st_y(location::geometry) from orders where city_id = ? and area_id = ? " +
		"and assign_at < now();";

	private Connection connection;

	@Inject
	public OrderStashDAO(PostgresDAO postgresDAO) {
		this.connection = postgresDAO.getConnection();
	}

	public Integer create(Order order) throws OrderInsertFailedException {
		try {
			PreparedStatement st = connection.prepareStatement(SQL_INSERT_ORDER);
			st.setString(1, order.getOrderID().toString());
			st.setLong(2, order.getAreaID());
			st.setLong(3, order.getCityID());
			st.setTimestamp(4, new Timestamp(order.getAssignAfter()));
			st.setDouble(5, order.getOrderLocation().getLatitude());
			st.setDouble(6, order.getOrderLocation().getLongitude());
			Integer numberOfRecordsUpdated = st.executeUpdate();
			st.close();
			return numberOfRecordsUpdated;
		} catch (Exception e) {
			throw new OrderInsertFailedException(e.getMessage(), e.getCause());
		}
	}

	public List<Order> getOrdersFromStash(Long cityID, Long areaID) {
		try {
			PreparedStatement st = connection.prepareStatement(SQL_GET_ORDERS_BY_CITY_AREA);
			st.setLong(1, cityID);
			st.setLong(2, areaID);
			ResultSet rs = st.executeQuery();
			return transformResultSetToGetOrders(rs);
		} catch (Exception e) {
			throw new GettingOrdersFailed(e.getMessage(), e.getCause());
		}
	}

	private List<Order> transformResultSetToGetOrders(ResultSet rs) throws SQLException {
		List<Order> orders = new ArrayList<Order>();
		System.out.println(rs.toString());
		while (rs.next())
		{
			System.out.println(rs);
			orders.add(new Order(
				UUID.fromString(rs.getString(1)),
				rs.getLong(2),
				rs.getLong(3),
				rs.getTimestamp(4).getTime(),
				new Point(
					rs.getDouble(5),
					rs.getDouble(6)
				)
			));
		}

		return orders;
	}



}
