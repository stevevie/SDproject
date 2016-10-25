
public class Bid {
	private int cliente_id,auct_id,price;

	public Bid(int cliente_id, int auct_id, int price) {
		super();
		this.cliente_id = cliente_id;
		this.auct_id = auct_id;
		this.price = price;
	}

	public int getCliente_id() {
		return cliente_id;
	}

	public int getAuct_id() {
		return auct_id;
	}

	public int getPrice() {
		return price;
	}

	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}

	public void setAuct_id(int auct_id) {
		this.auct_id = auct_id;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
