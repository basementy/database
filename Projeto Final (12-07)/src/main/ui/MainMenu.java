package main.ui;

import java.util.Scanner;

public class MainMenu {
	private static final int OP_EXIT = 0;
	private static final int OP_CUSTOMER = 1;
	private static final int OP_PRODUCT = 2;
	private static final int OP_CATEGORY = 3;
	private static final int OP_SALE = 4;

	private static final int OP_INSERT = 1;
	private static final int OP_LIST = 2;
	private static final int OP_UPDATE = 3;
	private static final int OP_DELETE = 4;

	private enum State {
    MAIN,
    CUSTOMER,
    PRODUCT,
    CATEGORY,
    SALE,
  };

  private Scanner entry;
  private State currentState;

	public MainMenu() {
		currentState = State.MAIN;
		entry = new Scanner(System.in);
	}

	private void printMainMenu() {
		System.out.print("\033[H\033[2J");
    System.out.flush();
		System.out.println("1 - Administration: Customers");
		System.out.println("2 - Administration: Products");
		System.out.println("3 - Administration: Categories");
		System.out.println("4 - Administration: Sales");
	}

	private void printSecondaryMenu(String menuType) {
		System.out.println("Administration: " + menuType);
		System.out.println();
		System.out.println("1 - Insert");
		System.out.println("2 - List");
		System.out.println("3 - Update");
		System.out.println("4 - Delete");
	}

	public void execute() {
		int option;
		Menu menu;

		do {
			switch(currentState) {
        case CUSTOMER:
          printSecondaryMenu("Customer");
          break;
        case PRODUCT:
          printSecondaryMenu("Product");
          break;
        case CATEGORY:
          printSecondaryMenu("Category");
          break;
        case SALE:
          printSecondaryMenu("Sale");
          break;
        default:
          printMainMenu();
			}

			System.out.println("0 - Sair");
      System.out.println();

			System.out.print("Choose a option: ");
			option = entry.nextInt();
			entry.nextLine();
      System.out.println();

			if (currentState == State.MAIN) {
				switch (option) {
          case OP_CUSTOMER:
            currentState = State.CUSTOMER;
            break;
          case OP_PRODUCT:
            currentState = State.PRODUCT;
            break;
          case OP_CATEGORY:
            currentState = State.CATEGORY;
            break;
          case OP_SALE:
            currentState = State.SALE;
            break;
				}
			} else {
        if (currentState == State.CUSTOMER) {
          menu = new MenuCustomer();
        } else if (currentState == State.PRODUCT) {
          menu = new MenuProduct();
        } else if (currentState == State.CATEGORY) {
          menu = new MenuCategory();
        } else {
          menu = new MenuSale();
        }

				switch (option) {
					case OP_INSERT:
						menu.insert();
						break;
					case OP_UPDATE:
						menu.update();
						break;
					case OP_DELETE:
						menu.delete();
						break;
					case OP_LIST:
						menu.list();
						break;
					case OP_EXIT:
						printMainMenu();
						break;
					default:
						System.out.println("Invalid option. Try again!");
				}
			}
		} while (option != 0);
	}
}