package main.models;

public class Product {
  private int id;
  private int price;
  private int categoryId;
  private String name;

  public int getId() {
    return id;
  }

  public int getPrice() {
    return price;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}