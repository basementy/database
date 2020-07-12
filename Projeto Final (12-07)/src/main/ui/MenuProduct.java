package main.ui;

import main.dao.ProductDao;
import main.models.Product;

import java.util.List;

public class MenuProduct extends Menu {
  private ProductDao dao;

  public MenuProduct() {
    super();
    dao = new ProductDao();
  }

  private Product getProductData(Product product) {
    Product productData;

    if (product == null) {
      productData = new Product();
    } else {
      productData = product;
    }

    System.out.print("Product name: ");
    String name = entry.nextLine();

    System.out.print("Product category ID: ");
    int id = entry.nextInt();

    System.out.print("Product price: ");
    int price = entry.nextInt();

    entry.nextLine();

    productData.setCategoryId(id);
    productData.setPrice(price);
    productData.setName(name);

    return productData;
  }

  private int getProductId() {
    System.out.print("Choose Product ID: ");

    int id = entry.nextInt();
    entry.nextLine();

    return id;
  }

  @Override
  public void insert() {
    System.out.println("Insert Product");
    System.out.println();

    Product newProduct = getProductData(null);

    dao.insert(newProduct);
  }

  @Override
  public void update() {
    System.out.println("Update Product");
    System.out.println();
    printProducts();

    int id = getProductId();
    Product updatedProduct = dao.getById(id);
    Product newProduct = getProductData(updatedProduct);

    newProduct.setId(updatedProduct.getId());
    dao.update(newProduct);
  }

  @Override
  public void delete() {
    System.out.println("Delete Product");
    System.out.println();
    printProducts();

    int id = getProductId();

    dao.delete(id);
  }

  @Override
  public void list() {
    System.out.println("Products List");
    System.out.println();

    printProducts();
  }

  private void printProducts() {
    List<Product> products = dao.getAll();

    System.out.println("id\tname\tcategory_id\tprice");

    for (Product product : products) {
      System.out.println(product.getId() + "\t" + product.getName() + "\t" + product.getCategoryId() + "\t" + product.getPrice());
    }
    
    System.out.println();
  }
}