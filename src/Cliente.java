
import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Cliente {
	private int clientId;
	private String clientPass,clientUsername;
	
	public Cliente(){}
	
	
    public Cliente(int clientId, String clientPass, String clientUsername) {
		super();
		this.clientId = clientId;
		this.clientPass = clientPass;
		this.clientUsername = clientUsername;
	}

    

	public int getClientId() {
		return clientId;
	}



	public String getClientPass() {
		return clientPass;
	}



	public String getClientUsername() {
		return clientUsername;
	}



	public void setClientId(int clientId) {
		this.clientId = clientId;
	}



	public void setClientPass(String clientPass) {
		this.clientPass = clientPass;
	}



	public void setClientUsername(String clientUsername) {
		this.clientUsername = clientUsername;
	}



	void ask_acessed_types(){
    	
    	System.out.println("POSSIBLE TYPES: create_auction, search_auction, detail_auction, my_auctions, bid, message, online_users");

    	
    }
    
/*
    public static void main(String[] args) {
    	

    	// CONNECTION WITH FILE SYSTEM (DATA BASE)
    	try {

			String content = "This is the content to write into file";

			// to save users and pass (ex: bar1 / bar2)
				// bar1 = username
				// bar2 = password
			File file1 = new File("users.txt");
			//------------------------------------
			// to save all actions
				// bar1 = best bid value or initial value
				// bar2 = best bid (username)
				// bar3 = code of the action
				// bar4 = username of the action autor
				// bar5 = name of the action
				// bar6 = details/description
				// bar7 = date of the action
				// barN = mural messages
			File file2 = new File("actions.txt");
			//--------------------------------------
			// to save all messagens
				// bar1 = username (who gets)
				// bar2 = username (who sent)
				// bar3 = message
				// bar4 = 1(read)/0(not read)
			File file3 = new File("messagens.txt");
			//---------------------------------------

			// if file doesnt exists, then create it
			if (!file1.exists()) {
				file1.createNewFile();
			}
			if (!file2.exists()) {
				file2.createNewFile();
			}
			if (!file3.exists()) {
				file3.createNewFile();
			}

			FileWriter fw = new FileWriter(file1.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			
			bw.append(content);
			//bufferedWriter.flush(); 
			bw.close();

			//System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	get_acess();
    }*/
}