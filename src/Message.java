
public class Message {
	private int message_id,status,sender_id,reciver_id;
	private String text;
	
	public Message(int message_id, int status, int sender_id, int reciver_id, String text) {
		this.message_id = message_id;
		this.status = status;
		this.sender_id = sender_id;
		this.reciver_id = reciver_id;
		this.text = text;
	}

	public int getMessage_id() {
		return message_id;
	}

	public int getStatus() {
		return status;
	}

	public int getSender_id() {
		return sender_id;
	}

	public int getReciver_id() {
		return reciver_id;
	}

	public String getText() {
		return text;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setSender_id(int sender_id) {
		this.sender_id = sender_id;
	}

	public void setReciver_id(int reciver_id) {
		this.reciver_id = reciver_id;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}
