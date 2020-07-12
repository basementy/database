package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccess {
	private static Connection conn = null;
  private static final String connectionUrl = "jdbc:sqlserver://localhost;databaseName=ProjetoFinal;user=databaseAdmin;password=admin123";

  public static Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(connectionUrl);
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Erro conectando ao banco de dados", ex);
		}

		return conn;
	}
}