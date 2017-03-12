package dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static config.Config.DB.DB;
import static config.Config.DB.HOST;
import static config.Config.DB.PORT;

@Singleton
public class PostgresDAO {
	private Connection connection;

	@Inject
	public PostgresDAO() {
		connect();
	}

	public Connection getConnection() {
		return connection;
	}

	private void connect() {
			try {
				Class.forName("org.postgresql.Driver");
			}
			catch (ClassNotFoundException e) {

				System.out.println("Cannot find Postgres Driver in library path!");
				e.printStackTrace();
			}

			System.out.println("PostgreSQL JDBC Driver Registered!");

			try {

				connection = DriverManager.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/" + DB);

			} catch (SQLException e) {

				System.out.println("Connection Failed!");
				e.printStackTrace();
			}

			if (connection != null) {
				System.out.println("Connection Successful!");
			} else {
				System.out.println("Failed to make connection!");
			}

	}
}
