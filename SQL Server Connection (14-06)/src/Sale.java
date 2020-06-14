public class Sale {
  private int id;
  private int clientId;
  private String product;

  public Sale(int clientId, String product) {
    this.clientId = clientId;
    this.product = product;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getClientId() {
    return this.clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public String getProduct() {
    return this.product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  @Override
	public String toString() {
		return "Sale [id=" + this.id + ", client_id=" + this.clientId + ", product=" + this.product + "]";
	}
}