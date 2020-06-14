import java.util.Scanner;

public class App {
	public static void main(String[] args) throws Exception {
		int option = 0;
		Database companyDatabase = new Database("Company", "databaseAdmin", "admin123");
		Scanner scan = new Scanner(System.in);

		do {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			menu();

			option = scan.nextInt();

			switch (option) {
				case 1:
					companyDatabase.insertClient();
					break;
				case 2:
					companyDatabase.insertSale();
					break;
				case 3:
					companyDatabase.indexClients();
					break;
				case 4:
					companyDatabase.indexSales();
					break;
				case 5:
					companyDatabase.indexSaleByClient();
					break;
				default:
					System.out.println("Opção inválida.");
			}
		} while (option != 0);

		scan.close();
	}

	public static void menu() {
		System.out.println("\nSistema - Company");
		System.out.println("0. Sair");
		System.out.println("1. Inserir Cliente");
		System.out.println("2. Inserir Venda");
		System.out.println("3. Listar Clientes");
		System.out.println("4. Listar Vendas");
		System.out.println("5. Listar Vendas P/ Cliente");
		System.out.println("\nOpcao: ");
	}
}
