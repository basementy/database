import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
  private String connectionUrl;

  public Database(String database, String user, String password) {
    // Init the database URL
    this.connectionUrl =
      "jdbc:sqlserver://localhost;"
        + "databaseName=" + database
        + ";user=" + user
        + ";password=" + password;
  }

  public void insertClient() {
    String insertString = "INSERT INTO Client (name, company) VALUES (?, ?)";

    try (
      Connection connection = DriverManager.getConnection(this.connectionUrl);
      PreparedStatement statement = connection.prepareStatement(insertString);
    ) {
      Client client = new Client("Ana", "Simple Company");

      // Set the statement index with the current client
      statement.setString(1, client.getName());
      statement.setString(2, client.getCompany());

      // Execute the query
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void indexClients() {
    try (
      Connection connection = DriverManager.getConnection(this.connectionUrl);
      Statement statement = connection.createStatement();
    ) {
      String selectString = "SELECT * FROM Client";
      ResultSet result = statement.executeQuery(selectString);

      if (result.next()) {
        while (result.next()) {
          // Set the client based on the result
          int clientId = result.getInt("id");
          String clientName = result.getString("name");
          String clientCompany = result.getString("company");

          Client client = new Client(clientName, clientCompany);
          client.setId(clientId);

          // Print client.toString()
          System.out.println("\n");
          System.out.println(client);
        }
      } else {
        System.out.println("\nNão foram encontrados resultados");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void insertSale() {
    String insertString = "INSERT INTO Sale (product, client_id) VALUES (?, ?)";

    try (Connection connection = DriverManager.getConnection(this.connectionUrl);
        PreparedStatement statement = connection.prepareStatement(insertString);) {
      Sale sale = new Sale(100, "Pencil 10x");

      // Set the statement index with the current sale
      statement.setString(1, sale.getProduct());
      statement.setInt(2, sale.getClientId());

      // Execute the query
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void indexSales() {
    try (
      Connection connection = DriverManager.getConnection(this.connectionUrl);
      Statement statement = connection.createStatement();
    ) {
      String selectString = "SELECT * FROM Sale";
      ResultSet result = statement.executeQuery(selectString);

      if (result.next()) {
        while (result.next()) {
          // Set the client based on the result
          int saleId = result.getInt("id");
          int saleClientId = result.getInt("client_id");
          String saleProduct = result.getString("product");

          Sale resultSale = new Sale(saleClientId, saleProduct);
          resultSale.setId(saleId);

          // Print client.toString()
          System.out.println("\n");
          System.out.println(resultSale);
        }
      } else {
        System.out.println("\nNão foram encontrados resultados");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}