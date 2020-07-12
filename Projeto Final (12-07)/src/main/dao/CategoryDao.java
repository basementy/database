package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.models.Category;
import main.interfaces.IDao;

public class CategoryDao implements IDao<Category> {
  public List<Category> getAll() {
		Connection connection = DatabaseAccess.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		List<Category> categories = new ArrayList<Category>();

		try {
			String SQL = "SELECT * FROM Category";
			statement = connection.createStatement();
      resultSet = statement.executeQuery(SQL);

      while (resultSet.next()) {
        Category category = getCategoryFromRs(resultSet);
        categories.add(category);
      }
		} catch (SQLException e) {
			throw new RuntimeException("[getAllCategories] Erro ao selecionar todos os Categories", e);
		} finally {
			close(connection, statement, resultSet);
		}

		return categories;
	}

	public Category getById(int id) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Category category = null;

		try {
			String SQL = "SELECT * FROM Category WHERE id = ?";
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, id);
      resultSet = statement.executeQuery();

      while (resultSet.next()) {
        category = getCategoryFromRs(resultSet);
      }
		} catch (SQLException e) {
			throw new RuntimeException("[getCategoryById] Erro ao selecionar Category por ID", e);
		} finally {
			close(connection, statement, resultSet);
		}

		return category;
	}

	public void insert(Category category) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String SQL = "INSERT INTO Category (name) VALUES (?)";
			statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, category.getName());
      statement.executeUpdate();
      resultSet = statement.getGeneratedKeys();

      if (resultSet.next()) {
        category.setId(resultSet.getInt(1));
      }
		} catch (SQLException e) {
			throw new RuntimeException("[insertCategory] Erro ao inserir Category", e);
		} finally {
			close(connection, statement, resultSet);
		}
	}

	public void delete(int id) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;

		try {
			String SQL = "DELETE Category WHERE id = ?";
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, id);
      statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("[deleteCategory] Erro ao remover a Category por ID", e);
		} finally {
			close(connection, statement, null);
		}
	}

	public void update(Category category) {
		Connection connection = DatabaseAccess.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String SQL = "UPDATE Category SET name = ? WHERE id = ?";
			statement = connection.prepareStatement(SQL);
			statement.setString(1, category.getName());
      statement.setInt(2, category.getId());
      statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("[updateCategory] Erro ao atualizar Category", e);
		} finally {
			close(connection, statement, resultSet);
		}
	}

	private Category getCategoryFromRs(ResultSet resultSet) throws SQLException {
    Category category = new Category();

		category.setId(resultSet.getInt("id"));
		category.setName(resultSet.getString("name"));

		return category;
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