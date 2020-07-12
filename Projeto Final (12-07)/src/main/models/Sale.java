package main.models;

public class Sale {
  private int id;
  private int customerId;
  private int productId;
  public int getId() {
    return id;
  }

  public int getProductId() {
    return productId;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }
}