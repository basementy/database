package main.ui;

import main.dao.SaleDao;
import main.models.Sale;

import java.util.List;

public class MenuSale extends Menu {
  private SaleDao dao;

  public MenuSale() {
    super();
    dao = new SaleDao();
  }

  private Sale getSaleData(Sale sale) {
    Sale saleData;

    if (sale == null) {
      saleData = new Sale();
    } else {
      saleData = sale;
    }

    System.out.print("Sale customer ID: ");
    int customerId = entry.nextInt();

    System.out.print("Sale product ID: ");
    int productId = entry.nextInt();

    entry.nextLine();

    saleData.setCustomerId(customerId);
    saleData.setProductId(productId);

    return saleData;
  }

  private int getSaleId() {
    System.out.print("Choose Sale ID: ");

    int id = entry.nextInt();
    entry.nextLine();

    return id;
  }

  @Override
  public void insert() {
    System.out.println("Insert Sale");
    System.out.println();

    Sale newSale = getSaleData(null);

    dao.insert(newSale);
  }

  @Override
  public void update() {
    System.out.println("Update Sale");
    System.out.println();
    printSales();

    int id = getSaleId();
    Sale updatedSale = dao.getById(id);
    Sale newSale = getSaleData(updatedSale);

    newSale.setId(updatedSale.getId());
    dao.update(newSale);
  }

  @Override
  public void delete() {
    System.out.println("Delete Sale");
    System.out.println();
    printSales();

    int id = getSaleId();

    dao.delete(id);
  }

  @Override
  public void list() {
    System.out.println("Sales List");
    System.out.println();

    printSales();
  }

  private void printSales() {
    List<Sale> sales = dao.getAll();

    System.out.println("id\tcustomer_id\tproduct_id");

    for (Sale sale : sales) {
      System.out.println(sale.getId() + "\t" + sale.getCustomerId() + "\t" + sale.getProductId());
    }

    System.out.println();
  }
}