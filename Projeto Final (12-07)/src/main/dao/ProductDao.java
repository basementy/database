package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.models.Product;
import main.interfaces.IDao;

public class ProductDao implements IDao<Product> {
  public List<Product> getAll() {
		Connection connection = DatabaseAccess.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		List<Product> products = new ArrayList<Product>();

		try {
			String SQL = "SELECT * FROM Product";
			statement = connection.createStatement();
      resultSet = statement.executeQuery(SQL);

      while (resultSet.next()) {
        Product product = getProductFromRs(resultSet);
        products.add(product);
      }
		} catch (SQLException e) {
			throw new RuntimeException("[getAllProducts] Erro ao selecionar todos os Products", e);
		} finally {
			close(connection, statement, resultSet);
		}

		return products;
	}

	public Product getById(int id) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Product product = null;

		try {
			String SQL = "SELECT * FROM Product WHERE id = ?";
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, id);
      resultSet = statement.executeQuery();

      while (resultSet.next()) {
        product = getProductFromRs(resultSet);
      }
		} catch (SQLException e) {
			throw new RuntimeException("[getProductById] Erro ao selecionar Product por ID", e);
		} finally {
			close(connection, statement, resultSet);
		}

		return product;
	}

	public void insert(Product product) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String SQL = "INSERT INTO Product (category_id, name, price) VALUES (?, ?, ?)";
			statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

			statement.setInt(1, product.getCategoryId());
			statement.setString(2, product.getName());
			statement.setInt(3, product.getPrice());
			statement.executeUpdate();

      resultSet = statement.getGeneratedKeys();

      if (resultSet.next()) {
        product.setId(resultSet.getInt(1));
      }
		} catch (SQLException e) {
			throw new RuntimeException("[insertProduct] Erro ao inserir Product", e);
		} finally {
			close(connection, statement, resultSet);
		}
	}

	public void delete(int id) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;

		try {
			String SQL = "DELETE Product WHERE id = ?";
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, id);
      statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("[deleteProduct] Erro ao remover a Product por ID", e);
		} finally {
			close(connection, statement, null);
		}
	}

	public void update(Product product) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String SQL = "UPDATE Product SET category_id = ?, name = ?, price = ? WHERE id = ?";
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, product.getCategoryId());
			statement.setString(2, product.getName());
			statement.setInt(3, product.getPrice());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("[updateProduct] Erro ao atualizar Product", e);
		} finally {
			close(connection, statement, resultSet);
		}
	}

	private Product getProductFromRs(ResultSet resultSet) throws SQLException {
    Product product = new Product();

		product.setId(resultSet.getInt("id"));
		product.setCategoryId(resultSet.getInt("category_id"));
		product.setName(resultSet.getString("name"));
		product.setPrice(resultSet.getInt("price"));

		return product;
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