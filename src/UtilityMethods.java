// EDITED ON 22-10-2016
// ALFREDO SOLDADINHO

import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;


import java.io.*;


public class UtilityMethods {
	//This variable have to be static???? The connection and statemt of course. I've doubts on the others
	//the instance variables have to be static
	
    private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet result = null;
	private static ResultSet result2 = null;
	private static ResultSetMetaData meta1; 
	private Scanner scanner;
	Cliente cliente;
	
	public UtilityMethods(Cliente cliente,DbConnectionPool pool){
		this.cliente=cliente;
		try {
			connection=pool.getConnection();
		} catch (SQLException e) {
			//send exeption 
			//the method createdbconnection is private for a solid programming method
		}
	}
	//////////////////////////////////////////////////////////////////////////
	// TASK 1 (by: Guido): editar value of an auction looking at previous version
	public void editAuction(){
		// need insert one action on table "auction"
		//System.out.println("entrou");
		int idAuc=scanner.nextInt();
		String response="type: edit_auction ,";
		try {
			
			statement=connection.createStatement();
			result=statement.executeQuery("SELECT *"+
										  "FROM Auctions"+
										  "WHERE Auction.idAuction="+idAuc+";");
			while(result.next()){	//print all che table results, we have to display only what we need	
				for	(int	i=1;i<=numberOfColumns;	i++){		
					response+="id:"+result.getString("idAuction")+" ,";
					response+="title:"+result.getString("Title")+".";
					response+="description:"+result.getString("Description")+" ,";
					response+="deadline:"+result.getString("Deadline")+".";
					response+="\n";
				}		
			}
			//Check each data alone, a user can't miss to insert datas more than 4 times
			int checkChances=0;
		if(checkChances>4){
			System.out.println("Edit id:");
			int newId=scanner.nextInt();
			statement.executeQuery("UPDATE Auctions SET Auctions.idAuction="+newId+"'"+
					   			   "WHERE Auctions.idAuction="+idAuc+";");
			
			
			System.out.println("Edit title:");
			String newTitle=scanner.next();
			statement.executeQuery("UPDATE Auctions SET Auctions.Title='"+newTitle+"',"+
					   				"WHERE Auctions.idAuction="+newId+";");
			
			System.out.println("Edit description:");
			String newDescription=scanner.next();
			statement.executeQuery("UPDATE Auctions SET "+
									"Auctions.Description='"+newDescription+"'"+
					     			"WHERE Auctions.idAuction="+newId+";");
			
			
			System.out.println("Edit deadline(YYYY-MM-DD HH:MM:SS) :");
			String newDeadline=scanner.next();
			statement.executeQuery("UPDATE Auctions SET Auctions.Deadline='"+newDeadline+"'"+
					   			   "WHERE Auctions.idAuction="+newId+";");
			
			System.out.println("type: edit_auction, ok: true");
		}else{
			
			System.out.println("type: edit_auction, ok: false");
				
		}	
       }catch (SQLException e) {
   			System.out.println("type: edit_auction, ok: false");
		// TODO Auto-generated catch block
		e.printStackTrace();
       }
	}
	
	
	public static void showMyAuctions(Cliente client){
		//Before this method we have to search the client id
		//Our database don't let us search the id only with the username and pass
		//than we have to provide a solution(How should we search the id of a client?)
		String response="type: my_auctions ,";
		try {
				
				statement=connection.createStatement();
				//statements which does the select on db thanks to searchingstring
				result=statement.executeQuery("SELECT COUNT(1) as RowNumber"+
											  "FROM Auctions"+
											  "INNER JOIN Bids"+
											  "ON Auctions.idAuction=Bids.Auctions_idAuction"
											  "WHERE Bids.Users_idUser="+client.getId+"and Auctions.Status=1;");
			   
				
				if(result.getInt("RowNumber")!=0){
					response="items_count:"+result.getInt("RowNumber")+", ";
					
					result=statement.executeQuery("SELECT *"+
												  "FROM Auctions"+
												  "INNER JOIN Bids"+
												  "ON Auctions.idAuction=Bids.Auctions_idAuction"
												  "WHERE Bids.Users_idUser="+client.getId+"and Auctions.Status=1;");
					
					meta1=result.getMetaData();
					int numberOfColumns	=	meta1.getColumnCount();	
	        			while(result.next()){	//print all che table results, we have to display only what we need	
	        				for	(int	i=1;i<=numberOfColumns;	i++){		
	        					response+="items_"+(i-1)+"id:"+result.getString("idAuction")+" ,";
	        					response+="items_"+(i-1)+"code:"+result.getString("Code")+" ,";
	        					response+="items_"+(i-1)+"title:"+result.getString("Title");
	        					response+="\n";
	        				}		
	        			}
	        			System.out.println(response);
					}else{
						response+="item_count:0.";
						System.out.println(response);
					}
				
	       }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	       }
		
		
	}
	

	//////////////////////////////////////////////////////////////////////////
	// TASK 2 (by: Guido): search_auction
	public static void searchAuction(String searchingString) {
		// need search auction by code with 10 or 13 digits only
		
		// 1) ask for the code
        Scanner in = new Scanner(System.in);
        String code = "";
        System.out.print("code: ");//never do the scanner and System.out in other methods... you have to do that only in the main
        code = in.next();
        
		
        String response="type: my_auctions, ";
        
			try {
				if(searchingString.length()==10 || searchingString.length()==13){//Questo è un metodo che dovrebbe essere interno a parte che chiama l'eccezione con throws ExeptionLenght
					statement=connection.createStatement();
				
					//statements which does the select on db thanks to searchingstring
					result=statement.executeQuery("SELECT COUNT(1) as RowNumber"+
												  "FROM Auctions "+
												  "WHERE Auctions.Code="+Integer.parseInt(searchingString)+";");
				   
					if(result.getShort("RowNumber")!=0){
						response="items_count:"+result.getShort("RowNumber")+", ";
						result=statement.executeQuery("SELECT *"+
								  					  "FROM Auctions"+
								  					  "WHERE Auctions.idAuction="+Integer.parseInt(searchingString)+";");
						meta1=result.getMetaData();
						int numberOfColumns	=	meta1.getColumnCount();	
						
		        			while(result.next()){	//print all che table results, we have to display only what we need	
		        				for	(int	i=1;i<=numberOfColumns;	i++){		
		        					response+="items_"+(i-1)+"id:"+result.getString("idAuction")+" ,";
		        					response+="items_"+(i-1)+"code:"+result.getString("Code")+" ,";
		        					response+="items_"+(i-1)+"title:"+result.getString("Title");
		        					response+="\n";
		        				}		
		        			}
		        			System.out.println(response);
						}else{
							response+="item_count:0.";
							System.out.println(response);
						}
					}
		       }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		       }
	
	} 		
		
        // 2) check if code is valid (need have 10 or 13 digits)
		
		// 3) check if the code is on table "auctions"
        
        // 4) compute the number of examples found
        	// how many examples found?
		
		// 5) print results to the user
        	// example:
        		// type: search_action
        		// items_count: 2
        		// items_0_id: 101, items_0_code: 9780451524935, items_0_title: 1984
        		// items_1_id: 103, items_1_code: 9780451524935, items_1_title: 1984 usado
	
	
	//////////////////////////////////////////////////////////////////////////
	// TASK 3 (by: Guido): detail_auction
	public void  detail_auction(){
		System.out.println("Insert the id you want to search:");
		int searchId=scanner.nextInt();
		String response="type: detail_auction, id:"+searchId+"\n";
		System.out.println(response);
		
		try{
			statement=connection.createStatement();
			result=statement.executeQuery("SELECT *"+
										  "FROM Auctions"+
										  "WHERE Auctions.idAuction="+searchId+";");	
		 
			
			//RESULT2 DOES THE INNER JOIN TO KNOW THE USERNAME OF THE SENDER
			result2=statement.executeQuery("SELECT *"+
										   "FROM Messages"+
										   "INNER JOIN Users"+
										   "ON Messages.User_sender=Users.idUser"+
					 					   "WHERE Messages.Auctions_idAuctions="+searchId+";");	
			meta1=result.getMetaData();	
    	   	int numberOfColumns=meta1.getColumnCount();
    	   	response="detail_auction , ";
    	   	
			while(result.next()){	//print all che table results, we have to display only what we need	
    	   		for	(int	i=1;i<=numberOfColumns;	i++){	
    	   			response+="title :"+result.getString("Title")+", ";
    	   			response+="description :"+result.getString("Description")+", ";
    	   			response+="deadline :"+result.getString("Deadline")+", ";
    	   		}
    	   	}
			
			
			meta1=result2.getMetaData();	
    	    numberOfColumns=meta1.getColumnCount();
    	    result=statement.executeQuery("SELECT COUNT(1) AS RowCounts"+
    	    							  "FROM Messages"+
    	    							  "WHERE Messages.Auctions_idAuction="+searchId+";");
    	if(result.getInt("RowCounts")!=0){
			while(result2.next()){	//print all che table results, we have to display only what we need	
    	   		for	(int	i=1;i<=numberOfColumns;	i++){	
    	   			response+="messages_"+(i-1)+"user :"+result2.getString("Username")+", ";
    	   			response+="messages_"+(i-1)+"text :"+result2.getString("Text")+", ";
    	   		}
    	   	}
    	}
    	
    	result=statement.executeQuery("SELECT COUNT(1) AS RowCounts"+
    								  "FROM Bids"+
    								  "WHERE Auctions_idAuction="+searchId+";");	
    	response+="bid_count:"+result.getInt("RowCounts");
    	System.out.println(response);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//////////////////////////////////////////////////////////////////////////
	// TASK 4 (By: Guido): createAuction
	public void createAuction(Cliente cliente){//insert auction into database
		 int idCliente=cliente.getId();
			//try{
		   System.out.println("insert price:");
		   String price = scanner.nextLine();
		   
		   System.out.println("insert title:");
		   String title = scanner.nextLine();
		   
		   System.out.println("insert description:");
		   String description =scanner.nextLine();
		   
		   System.out.println("insert code:");
		   String code = scanner.nextLine();

		   System.out.println("insert deadline:");
		   String deadline = scanner.nextLine();
		   //check control on deadline
				try {
					result=statement.executeQuery("INSERT INTO Auctions (Title, Description, Status, Code, Deadline, ActualPrice)"+
												  "VALUES ('"+title+"','"+description+"',1,'"+code+"','"+deadline+"',10);");
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			        				
	}	
	
	
	
	//////////////////////////////////////////////////////////////////////////
	// TASK 5 (By: Guido): bid 
	public void makeBid(Bid bid){
		//The control on the variables has to be done when an user begin insert the data
		try{
			statement=connection.createStatement();
			result=statement.executeQuery("SELECT ActualPrice_auc FROM Auctions WHERE idAuction="+bid.getAuct_id());
			if(Integer.parseInt(result.getString(0))<bid.getPrice()){
				System.out.println("type: bid, id:"+bid.getAuct_id()+"amount:"+bid.getPrice()+"\n");
				statement.executeQuery("UPDATE Auctions SET ActualPrice_auc'"+bid.getPrice()+"' WHERE idAuction='"+bid.getAuct_id()+"'");
				statement.executeQuery("INSERT INTO Bid VALUES ("+bid.getCliente_id()+","+bid.getAuct_id()+","+bid.getPrice());//search query by the id of the user
				System.out.println("type: bid, ok:true");
				Cliente client;
				notifyBestOffer(bid);
			}else{
				System.out.println("type: bid, ok:false.");
			}
		}catch (SQLException e) {
			System.out.println("type: bid, ok:false.");
			e.printStackTrace();
		}
	}
	//////////////////////////////////////////////////////////////////////////
	// TASK 6 (By: Guido): message
	public void insertMessage(Message msg) throws SQLException{
		
		msg.setText(scanner.nextLine());
		int senderID=scanner.nextInt();
		int reciverID=scanner.nextInt();
		statement=connection.createStatement();
		result=statement.executeQuery("SELECT Username FROM Users WHERE Status=1 and Username='"+reciverID+"'");
		if(result.getString("Username").equals(""))
		{
			statement.executeQuery("INSERT INTO Messages (Text_msg,Status,User_sender,User_reciver)"+
								   "VALUES ('"+msg.getText()+"',0,"+senderID+","+reciverID+");");
		}
		System.out.println("Type: message, id");
	}
	
	//i have to finish this
	public static void checkAuctionTermination() throws ParseException
	{
		try {
			statement=connection.createStatement();
			result=statement.executeQuery("SELECT * "+
										  "FROM Auctions"+
										  "WHERE Status=0");
			
				if(result.getString("Deadline").equals(""))
				{
					meta1=result.getMetaData();	
		    	   	int numberOfColumns=meta1.getColumnCount();
					Date itemDate=new Date(0);
					Calendar calendar1=Calendar.getInstance();
					Calendar calendar2=Calendar.getInstance();
					int idAuc=0;
					
					while(result.next()){	//print all che table results, we have to display only what we need	
		    	   		for	(int	i=1;i<=numberOfColumns;	i++){
		    	   			idAuc=result.getInt("idAuction");
		    	   			itemDate = result.getDate("Deadline");
		    	   			calendar1.setTime(itemDate);
		    	   				if(calendar1.after(calendar2))
		    	   				{
		    	   					statement.execute("UPDATE Auctions"+
	    	   									  	  "SET  Auctions.Status=1"+
	    	   									  	  "WHERE Auctions.idAuction="+idAuc+";");
		    	   				}
		    	   		}
		    	   	}
				  
								
				/*I need to provide a comparation between dates */
							}
							
				}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//notifyBestOffert
	public static void notifyBestOffer(Bid bid)
	{
			//We have to do a select for let the other users know that the best offert were made from another client
			
			try {
				statement=connection.createStatement();
				result=statement.executeQuery("SELECT Bids.Users_idUser"+
											  "FROM Auctions"+
											  "INNER JOIN Bids"+
											  "ON Auctions.idAuction="+bid.getAuct_id()+" "+
											  "WHERE Bids.Users_idUser<>="+bid.getCliente_id()+";");
				
				ArrayList<Pair> arrayList=new ArrayList<>();
				meta1=result.getMetaData();	
	    	   	int numberOfColumns=meta1.getColumnCount();
	    	   	
	    	   	Pair e;
	    	   	//it always works even if we have one column
	    	   	while(result.next()){	//print all che table results, we have to display only what we need	
	    	   		for	(int	i=1;i<=numberOfColumns;	i++){
	    	   			e=new Pair("type:notification_bid , id:"+bid.getAuct_id()+" ,amount:"+bid.getPrice()+"\n",result.getInt("Users_idUser"));
	    	   			arrayList.add(e);
	    	   		}
	    	   	}
	    	   	//in the arrayList we have stored all the Strings with notifications duplicated with all the ids of users
	    	   
	    	   	
	    	   	
	    	   	//At this point we have only to display
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	//showMessages has to be periodically called by the client
	public void showMessages() throws SQLException{
		String message="";
		int reciverID=0;
		   statement=connection.createStatement();
	       result=statement.executeQuery("SELECT Messages.Text,Messages.idMessage,Messages.User_sender"+
	    		   						 "FROM Messages"+
	    		   						 "WHERE Status=0 and Username='"+cliente.getId+"';");//statements which selects all users with status==1
	       
	       
	       if(result.getString("User_sender").equals("")){
	    	   System.out.println("You don't have notifications");
	       }else{
	    	   	int[] idArray;
	    	   	String response="type: message, ";
	    	   	//statements which selects all users with status==1
	    	   	meta1=result.getMetaData();	
	    	   	int numberOfColumns=meta1.getColumnCount();
	    	   	//it always works even if we have one column
	    	   	while(result.next()){	//print all che table results, we have to display only what we need	
	    	   		for	(int	i=1;i<=numberOfColumns;	i++)	{	
	    	   			response+="id:"+result.getString("idMessage")+", ";
	    	   			idArray[i-1]=Integer.parseInt(result.getString("idMessage"));
	    	   			response+="text: "+result.getString("Test_msg");
	    	   			response+="\n";
	    	   		}
	    	   	}
	    	   	for(int i=0;i<idArray.length;i++){
	    	   			   statement.executeQuery("UPDATE Messages"+
	    	   									  "SET Messages.Status=1"+
	    	   									  "WHERE Messages.idMessage="+idArray[i]+";");
	    	   	}
	    	   	System.out.println(response);	
	       }	
	}
	//////////////////////////////////////////////////////////////////////////
	// TASK 7 (By: Guido): detail_auction
	public static void online_users() throws SQLException
	{	  
			statement=connection.createStatement();  
			result=statement.executeQuery("SELECT COUNT(1) AS RowNumber	FROM Users WHERE Status=1");
			try {
				if((Integer.parseInt(result.getString("RowNumber")))>0){
					String response="type online_users, user_count="+ result.getString("RowNumber")+", ";
					result=statement.executeQuery("SELECT Username FROM Users WHERE Status=1");//statements which selects all users with status==1
		        	meta1=result.getMetaData();	
				    int numberOfColumns=meta1.getColumnCount();
		        		//it always works even if we have one column
		        		while(result.next()){	//print all che table results, we have to display only what we need	
		        			for	(int	i=1;i<=numberOfColumns;	i++)	{	
		        				response+=""+result.getString(i)+" ,";
		        				
		        			}
		        		}
		        		System.out.println(response);
				} 
			}catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		//	}catch{
				
		//	}finally{
				
		//	}
			}
	
    //////////////////////////////////////////////////////////////////////////
    // TASK 0b (By Guido)
    public static void login(){
    	Cliente client=new Cliente();
    	// 1: ASK FOR USERNAME AND PASSWORD (COMPLETE!)
    	int registered = 0; // this value will be 1 when login success
        Scanner in = new Scanner(System.in);
        String username = "";
        String password = "";
        System.out.print("username: ");
        username = in.next();
        System.out.print("password: ");
        password = in.next();
        
        // 2: CHECK IF USER EXISTS ON TABLE "users"
        // need check on database if pair user and pass exists
        // and put the value of registered to 1
        
        // 3: GIVE ACESS TO THIS USER
        // if user get success on login will call this function: login_types()
        //if (registered == 1){ 
        	login_types(client);
        //}else{
        	//System.out.println("error");
        	//get_access();
        //}
        
    }
    
    public static void login_types(Cliente cliente){//create an object to store variables
    	Scanner in = new Scanner(System.in);

    	System.out.println("POSSIBLE TYPES: create_auction, search_auction, detail_auction, my_auctions, bid, message, online_users");
    	String type = "";
    	System.out.print("type: ");
        type = in.next();
        
        /*notification for messages*/
        statement=connection.createStatement();
        result=statement.executeQuery("SELECT Messages.Text,Messages.idMessage,Messages.User_sender"+
				  					  "FROM Messages"+
				  					  "WHERE Status=0 and Username='"+cliente.getId+"';");//statements which selects all users with status==1
        
        
        if(result.getString("User_sender").equals("")){
        	System.out.println("You don't have notifications");
        }else{
        	int[] idArray;
        	String response="type: notification_message, ";
			//statements which selects all users with status==1
        	meta1=result.getMetaData();	
		    int numberOfColumns=meta1.getColumnCount();
        		//it always works even if we have one column
        		while(result.next()){	//print all che table results, we have to display only what we need	
        			for	(int	i=1;i<=numberOfColumns;	i++)	{	
        				response+="id:"+result.getString("idMessage")+", ";
        				idArray[i-1]=Integer.parseInt(result.getString("idMessage"));
        				response+="user: "+result.getString("idUser")+", ";
        				response+="text: "+result.getString("Test_msg");
        				response+="\n";
        			}
        		}
        		for(int i=0;i<idArray.length;i++){
        			result=statement.executeQuery("UPDATE Messages"+
        									 	  "SET Messages.Status=1"+
        									 	  "WHERE Messages.idMessage="+idArray[i]+";");
        		}
        		System.out.println(response);	
        }
        
        
        
        
        if(type.equals("create_auction")){
        	//System.out.println("entrou");
        	createAuction(cliente);
    	}
    	else if(type.equals("search_auction")){
    		searchAuction(type);
    	}
    	else if(type.equals("detail_auction")){
    		
    	}
    	else{
    		login_types(cliente);
    	}

    }
    
    static void get_access(){
    	
    	Scanner in = new Scanner(System.in);

    	System.out.println("POSSIBLE TYPES: login, registry");
        int login = 0; // 0 not logged // 1 logged
    	String type = "";
    	System.out.print("type: ");
        type = in.next();
        
        // possible first types: login or registry
        
    	if(type.equals("login")){
    		login();
    	}
    	
    	else if(type.equals("registry")){
        	registry();
    	}
    	else{
    		// if the user dont write login or registry 
    		// the fuction will be call again
    		get_access();
    	}
    }
    
	public static void db_connection() throws SQLException{
		// this function will be used in RMI server in future
    	
		Scanner in = new Scanner(System.in);
		//String bd_name = "";
		String bd_user = "";
		String bd_pass = "";
		//System.out.print("BD Name: ");
        //bd_name = in.next();
        System.out.print("BD Username: ");
        bd_user = in.next();
        System.out.print("BD Password: ");
        bd_pass = in.next();
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sd", bd_user , bd_pass);
        //conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/sd", "root" , "abc");
		statement = connection.createStatement();	
	}
	
}
    
