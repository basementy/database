package main.ui;

import main.dao.CustomerDao;
import main.models.Customer;

import java.util.List;

public class MenuCustomer extends Menu {
  private CustomerDao dao;

  public MenuCustomer() {
    super();
    dao = new CustomerDao();
  }

  private Customer getCustomerData(Customer customer) {
    Customer customerData;

    if (customer == null) {
      customerData = new Customer();
    } else {
      customerData = customer;
    }

    System.out.print("Customer name: ");
    String name = entry.nextLine();

    System.out.print("Customer age: ");
    int age = entry.nextInt();

    entry.nextLine();

    customerData.setName(name);
    customerData.setAge(age);

    return customerData;
  }

  private int getCustomerId() {
    System.out.print("Choose Customer ID: ");

    int id = entry.nextInt();
    entry.nextLine();

    return id;
  }

  @Override
  public void insert() {
    System.out.println("Insert Customer");
    System.out.println();

    Customer newCustomer = getCustomerData(null);

    dao.insert(newCustomer);
  }

  @Override
  public void update() {
    System.out.println("Update Customer");
    System.out.println();
    printCustomers();

    int id = getCustomerId();
    Customer updatedCustomer = dao.getById(id);
    Customer newCustomer = getCustomerData(updatedCustomer);

    newCustomer.setId(updatedCustomer.getId());
    dao.update(newCustomer);
  }

  @Override
  public void delete() {
    System.out.println("Delete Customer");
    System.out.println();
    printCustomers();

    int id = getCustomerId();

    dao.delete(id);
  }

  @Override
  public void list() {
    System.out.println("Customers List");
    System.out.println();

    printCustomers();
  }

  private void printCustomers() {
    List<Customer> customers = dao.getAll();

    System.out.println("id\tname\tage");

    for (Customer customer : customers) {
      System.out.println(customer.getId() + "\t" + customer.getName() + "\t" + customer.getAge());
    }

    System.out.println();
  }
}