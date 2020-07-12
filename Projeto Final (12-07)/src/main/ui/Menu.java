package main.ui;

import java.util.Scanner;

abstract public class Menu {
  protected Scanner entry;

  public Menu() {
    entry = new Scanner(System.in);
  }

  abstract public void insert();
  abstract public void update();
  abstract public void delete();
  abstract public void list();
}