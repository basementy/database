import java.sql.*;

public class App {
	public static void main(String[] args) throws Exception {
		String connectionUrl = "jdbc:sqlserver://localhost;databaseName=SampleDatabase;user=testeLogon;password=sample123";
		String insertString = "INSERT INTO Person (nome, idade) VALUES (?, ?)";

		try (
			Connection connection = DriverManager.getConnection(connectionUrl);
			PreparedStatement statement = connection.prepareStatement(insertString);
		) {
			Pessoa p1 = new Pessoa("Ana", 50);

			statement.setString(1, p1.getNome());
			statement.setInt(2, p1.getIdade());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
