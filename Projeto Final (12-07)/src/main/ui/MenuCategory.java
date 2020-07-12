package main.ui;

import main.dao.CategoryDao;
import main.models.Category;

import java.util.List;

public class MenuCategory extends Menu {
  private CategoryDao dao;

  public MenuCategory() {
    super();
    dao = new CategoryDao();
  }

  private Category getCategoryData(Category category) {
    Category categoryData;

    if (category == null) {
      categoryData = new Category();
    } else {
      categoryData = category;
    }

    System.out.print("Category name: ");
    String name = entry.nextLine();

    entry.nextLine();

    categoryData.setName(name);

    return categoryData;
  }

  private int getCategoryId() {
    System.out.print("Choose Category ID: ");

    int id = entry.nextInt();
    entry.nextLine();

    return id;
  }

  @Override
  public void insert() {
    System.out.println("Insert Category");
    System.out.println();

    Category newCategory = getCategoryData(null);

    dao.insert(newCategory);
  }

  @Override
  public void update() {
    System.out.println("Update Category");
    System.out.println();
    printCategories();

    int id = getCategoryId();
    Category updatedCategory = dao.getById(id);
    Category newCategory = getCategoryData(updatedCategory);

    newCategory.setId(updatedCategory.getId());
    dao.update(newCategory);
  }

  @Override
  public void delete() {
    System.out.println("Delete Customer");
    System.out.println();
    printCategories();

    int id = getCategoryId();

    dao.delete(id);
  }

  @Override
  public void list() {
    System.out.println("Categories List");
    System.out.println();

    printCategories();
  }

  private void printCategories() {
    List<Category> categories = dao.getAll();

    System.out.println("id\tname");

    for (Category category : categories) {
      System.out.println(category.getId() + "\t" + category.getName());
    }

    System.out.println();
  }
}