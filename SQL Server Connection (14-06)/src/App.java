import java.sql.*;

public class App {
	public static void main(String[] args) throws Exception {
		String connectionUrl = "jdbc:sqlserver://localhost\\sqlexpress:1433;databaseName=SampleDatabase;user=testeLogon;password=sample123";
		String insertString = "INSERT INTO Pessoa (id, nome, idade) VALUES (?, ?, ?)";

		try (
			Connection con = DriverManager.getConnection(connectionUrl);
			PreparedStatement stmt = con.prepareStatement(insertString);
		) {
			Pessoa p1 = new Pessoa(1, "Maria", 50);

			stmt.setInt(1, p1.getId());
			stmt.setString(2, p1.getNome());
			stmt.setInt(3, p1.getIdade());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
