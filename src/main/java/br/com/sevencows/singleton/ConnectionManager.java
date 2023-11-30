package br.com.sevencows.singleton;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

	private static ConnectionManager connectionManager;

	private ConnectionManager() {

	};

	public static ConnectionManager getInstance() {
		if (connectionManager == null) {
			connectionManager = new ConnectionManager();
		}
		return connectionManager;
	}

	public Connection getConnection() {

		String url = "";
		String user = "";
		String password = "";

		Connection connection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection("jdbc:oracle:thin:@" + url, user, password);

			System.out.println("Conectado com sucesso!");

		} catch (ClassNotFoundException e) {

			System.err.println("O Driver JDBC não foi encontrado!");
			e.printStackTrace();

		} catch (Exception e) {

			e.getMessage();
			e.getStackTrace();

		}

		return connection;

	}

}
