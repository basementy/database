import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JOptionPane;

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
    Scanner scan = new Scanner(System.in);
    String insertString = "INSERT INTO Client (name, company) VALUES (?, ?)";
    String clientName = JOptionPane.showInputDialog("Digite o nome do cliente: ");
    String clientCompany = JOptionPane.showInputDialog("Digite a empresa do cliente: ");

    System.out.print("\033[H\033[2J");
    System.out.flush();

    try (
      Connection connection = DriverManager.getConnection(this.connectionUrl);
      PreparedStatement statement = connection.prepareStatement(insertString);
    ) {
      Client client = new Client(clientName, clientCompany);

      statement.setString(1, client.getName());
      statement.setString(2, client.getCompany());
      statement.executeUpdate();

      JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso");
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Erro ao tentar inserir (Visualize o console para mais detalhes)");
      scan.nextLine();
    }
  }

  public void indexClients() {
    try (
      Connection connection = DriverManager.getConnection(this.connectionUrl);
      Statement statement = connection.createStatement();
    ) {
      Scanner scan = new Scanner(System.in);
      String selectString = "SELECT * FROM Client";
      ResultSet result = statement.executeQuery(selectString);

      System.out.print("\033[H\033[2J");
      System.out.flush();

      while (result.next()) {
        int clientId = result.getInt("id");
        String clientName = result.getString("name");
        String clientCompany = result.getString("company");
        Client client = new Client(clientName, clientCompany);
        client.setId(clientId);

        System.out.println(client);
      }

      System.out.println("\nPressione qualquer tecla para continuar...");
      scan.nextLine();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void insertSale() {
    Scanner scan = new Scanner(System.in);
    String insertString = "INSERT INTO Sale (product, client_id) VALUES (?, ?)";
    String saleProduct = JOptionPane.showInputDialog("Digite o nome do produto: ");
    int saleClientId = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente: "));

    System.out.print("\033[H\033[2J");
    System.out.flush();

    try (
      Connection connection = DriverManager.getConnection(this.connectionUrl);
      PreparedStatement statement = connection.prepareStatement(insertString);
    ) {
      Sale sale = new Sale(saleClientId, saleProduct);

      statement.setString(1, sale.getProduct());
      statement.setInt(2, sale.getClientId());
      statement.executeUpdate();

      JOptionPane.showMessageDialog(null, "Venda inserida com sucesso");
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Erro ao tentar inserir (Visualize o console para mais detalhes)");
      scan.nextLine();
    }
  }

  public void indexSales() {
    try (
      Connection connection = DriverManager.getConnection(this.connectionUrl);
      Statement statement = connection.createStatement();
    ) {
      Scanner scan = new Scanner(System.in);
      String selectString = "SELECT * FROM Sale";
      ResultSet result = statement.executeQuery(selectString);

      System.out.print("\033[H\033[2J");
      System.out.flush();

      while (result.next()) {
        int saleId = result.getInt("id");
        int saleClientId = result.getInt("client_id");
        String saleProduct = result.getString("product");
        Sale resultSale = new Sale(saleClientId, saleProduct);
        resultSale.setId(saleId);

        System.out.println(resultSale);
      }

      System.out.println("\nPressione qualquer tecla para continuar...");
      scan.nextLine();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void indexSaleByClient() {
    Scanner scan = new Scanner(System.in);
    String saleClientId = JOptionPane.showInputDialog("Digite o ID do cliente: ");
    String selectString =
      "SELECT name as client_name, product " +
      "FROM Sale as s " +
      "JOIN Client as c " +
      "ON c.id = s.client_id " +
      "WHERE c.id = " + saleClientId + ";";

    System.out.print("\033[H\033[2J");
    System.out.flush();

    try (
      Connection connection = DriverManager.getConnection(this.connectionUrl);
      Statement statement = connection.createStatement();
    ) {
      ResultSet result = statement.executeQuery(selectString);

      while (result.next()) {
        String saleClientName = result.getString("client_name");
        String saleProduct = result.getString("product");

        System.out.println("Sale [client_name = " + saleClientName + ", product = " + saleProduct + "]");
      }

      System.out.println("\nPressione qualquer tecla para continuar...");
      scan.nextLine();
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Erro ao tentar inserir (Visualize o console para mais detalhes)");
      scan.nextLine();
    }
  }
}