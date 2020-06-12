import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App {
	public static void main(String[] args) throws Exception {
		String connectionUrl = "jdbc:sqlserver://localhost;databaseName=SampleDatabase;user=testeLogon;password=sample123";
		String insertString = "INSERT INTO Pessoa (id, nome, idade) VALUES (?, ?, ?)";

		try (
			Connection connection = DriverManager.getConnection(connectionUrl);
			PreparedStatement statement = connection.prepareStatement(insertString);
		) {
			Pessoa p1 = new Pessoa(1, "Maria", 50);

			statement.setInt(1, p1.getId());
			statement.setString(2, p1.getNome());
			statement.setInt(3, p1.getIdade());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
