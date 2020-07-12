package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.models.Customer;
import main.interfaces.IDao;

public class CustomerDao implements IDao<Customer> {
  public List<Customer> getAll() {
		Connection connection = DatabaseAccess.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		List<Customer> customers = new ArrayList<Customer>();

		try {
			String SQL = "SELECT * FROM Customer";
			statement = connection.createStatement();
      resultSet = statement.executeQuery(SQL);

      while (resultSet.next()) {
        Customer customer = getCustomerFromRs(resultSet);
        customers.add(customer);
      }
		} catch (SQLException e) {
			throw new RuntimeException("[getAllCustomers] Erro ao selecionar todos os Customers", e);
		} finally {
			close(connection, statement, resultSet);
		}

		return customers;
	}

	public Customer getById(int id) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Customer customer = null;

		try {
			String SQL = "SELECT * FROM Customer WHERE id = ?";
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, id);
      resultSet = statement.executeQuery();

      while (resultSet.next()) {
        customer = getCustomerFromRs(resultSet);
      }
		} catch (SQLException e) {
			throw new RuntimeException("[getCustomerById] Erro ao selecionar Customer por ID", e);
		} finally {
			close(connection, statement, resultSet);
		}

		return customer;
	}

	public void insert(Customer customer) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String SQL = "INSERT INTO Customer (name, age) VALUES (?, ?)";
			statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, customer.getName());
      statement.setInt(2, customer.getAge());
      statement.executeUpdate();
      resultSet = statement.getGeneratedKeys();

      if (resultSet.next()) {
        customer.setId(resultSet.getInt(1));
      }
		} catch (SQLException e) {
			throw new RuntimeException("[insertCustomer] Erro ao inserir Customer", e);
		} finally {
			close(connection, statement, resultSet);
		}
	}

	public void delete(int id) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;

		try {
			String SQL = "DELETE Customer WHERE id = ?";
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, id);
      statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("[deleteCustomer] Erro ao remover a Customer por ID", e);
		} finally {
			close(connection, statement, null);
		}
	}

	public void update(Customer customer) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String SQL = "UPDATE Customer SET name = ?, age = ? WHERE id = ?";
			statement = connection.prepareStatement(SQL);
      statement.setString(1, customer.getName());
      statement.setInt(2, customer.getAge());
      statement.setInt(3, customer.getId());
      statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("[updateCustomer] Erro ao atualizar Customer", e);
		} finally {
			close(connection, statement, resultSet);
		}
	}

	private Customer getCustomerFromRs(ResultSet resultSet) throws SQLException {
    Customer customer = new Customer();

		customer.setId(resultSet.getInt("id"));
		customer.setName(resultSet.getString("name"));
		customer.setAge(resultSet.getInt("age"));

		return customer;
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