package main.models;

public class Customer {
  private int id;
  private int age;
  private String name;

  public int getId() {
    return id;
  }

  public int getAge() {
    return age;
  }

  public String getName() {
    return name;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
	public String toString() {
		return "Customer [id=" + id + ", name = " + name + ", age = " + age + "]";
	}
}