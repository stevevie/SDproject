import java.util.*;

public class Auction {
	  private int auction_id,user_id,status;
	  private double price;
	  private String title,description,code;
	  private Date deadline;
	  
	  public Auction(int auction_id, int user_id, int status, double price, String title, String description, String code,
				Date deadline) {
			super();
			this.auction_id = auction_id;
			this.user_id = user_id;
			this.status = status;
			this.price = price;
			this.title = title;
			this.description = description;
			this.code = code;
			this.deadline = deadline;
		}
	  
	  
	public Auction() {
		// TODO Auto-generated constructor stub
	}


	public int getAuction_id() {
		return auction_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public int getStatus() {
		return status;
	}
	public double getPrice() {
		return price;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getCode() {
		return code;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setAuction_id(int auction_id) {
		this.auction_id = auction_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	

}
