public class Client {
	private int id;
	private String name;
	private String company;

	public Client(String name, String company) {
		this.name = name;
		this.company = company;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Client [id = " + this.id + ", name = " + this.name + ", company = " + this.company + "]";
	}
}