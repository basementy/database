package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.models.Sale;
import main.interfaces.IDao;

public class SaleDao implements IDao<Sale> {
  public List<Sale> getAll() {
		Connection connection = DatabaseAccess.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		List<Sale> sales = new ArrayList<Sale>();

		try {
			String SQL = "SELECT * FROM Sale";
			statement = connection.createStatement();
      resultSet = statement.executeQuery(SQL);

      while (resultSet.next()) {
        Sale sale = getSaleFromRs(resultSet);
        sales.add(sale);
      }
		} catch (SQLException e) {
			throw new RuntimeException("[getAllSales] Erro ao selecionar todas as Sales", e);
		} finally {
			close(connection, statement, resultSet);
		}

		return sales;
	}

	public Sale getById(int id) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Sale sale = null;

		try {
			String SQL = "SELECT * FROM Sale WHERE id = ?";
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, id);
      resultSet = statement.executeQuery();

      while (resultSet.next()) {
        sale = getSaleFromRs(resultSet);
      }
		} catch (SQLException e) {
			throw new RuntimeException("[getSaleById] Erro ao selecionar Sale por ID", e);
		} finally {
			close(connection, statement, resultSet);
		}

		return sale;
	}

	public void insert(Sale sale) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String SQL = "INSERT INTO Sale (customer_id, product_id) VALUES (?, ?)";
			statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

			statement.setInt(1, sale.getCustomerId());
			statement.setInt(2, sale.getProductId());
			statement.executeUpdate();

      resultSet = statement.getGeneratedKeys();

      if (resultSet.next()) {
        sale.setId(resultSet.getInt(1));
      }
		} catch (SQLException e) {
			throw new RuntimeException("[insertSale] Erro ao inserir Sale", e);
		} finally {
			close(connection, statement, resultSet);
		}
	}

	public void delete(int id) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;

		try {
			String SQL = "DELETE Sale WHERE id = ?";
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, id);
      statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("[deleteSale] Erro ao remover Sale por ID", e);
		} finally {
			close(connection, statement, null);
		}
	}

	public void update(Sale sale) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String SQL = "UPDATE Sale SET customer_id = ?, product_id = ? WHERE id = ?";
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, sale.getCustomerId());
			statement.setInt(2, sale.getProductId());
			statement.setInt(3, sale.getId());
      statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("[updateSale] Erro ao atualizar Sale", e);
		} finally {
			close(connection, statement, resultSet);
		}
	}

	private Sale getSaleFromRs(ResultSet resultSet) throws SQLException {
    Sale sale = new Sale();

		sale.setId(resultSet.getInt("id"));
		sale.setCustomerId(resultSet.getInt("customer_id"));
		sale.setProductId(resultSet.getInt("product_id"));

		return sale;
	}

	private void close(Connection connection, Statement statement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar recursos", e);
		}
	}
}