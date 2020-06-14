public class App {
	public static void main(String[] args) throws Exception {
		Database companyDatabase = new Database("Company", "databaseAdmin", "admin123");

		// Sale
		companyDatabase.indexSales();
	}
}
