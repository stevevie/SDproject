

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;


public class UtilityMethods {
	//This variable have to be static???? The connection and statemt of course. I've doubts on the others
	//the instance variables have to be static
	
    private  Connection connection;
	private Statement statement ;
	private  ResultSet result ;
	private  ResultSet result2;
	private  ResultSetMetaData meta1; 
	private  Pattern p = null;        //The pattern.compile is the regexp
	private  Matcher m = null; //This is the string that is going to be passes
	private DbConnectionPool pool;
	private Scanner scanner;
	private Cliente cliente;
	
	public UtilityMethods() throws SQLException{
		this.cliente=new Cliente();
		this.pool=new DbConnectionPool();
		this.connection=pool.getConnection();
		statement=null;
		this.scanner=new Scanner(System.in);
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
		
			meta1=result.getMetaData();
			int numberOfColumns	=	meta1.getColumnCount();	
			
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
	
	
	public void showMyAuctions(){
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
											  "ON Auctions.idAuction=Bids.Auctions_idAuction"+
											  "WHERE Bids.Users_idUser="+cliente.getClientId()+"and Auctions.Status=1;");
			   
				
				if(result.getInt("RowNumber")!=0){
					response="items_count:"+result.getInt("RowNumber")+", ";
					
					result=statement.executeQuery("SELECT *"+
												  "FROM Auctions"+
												  "INNER JOIN Bids"+
												  "ON Auctions.idAuction=Bids.Auctions_idAuction" +
												  "WHERE Bids.Users_idUser="+cliente.getClientId()+"and Auctions.Status=1;");
					
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
	       }finally {
			if (statement != null) { statement.close(); }
	       }
		
		
	}
	

	//////////////////////////////////////////////////////////////////////////
	// TASK 2 (by: Guido): search_auction
	public void searchAuction(String searchingString) {
		// need search auction by code with 10 or 13 digits only
		
		// 1) ask for the code
        Scanner in = new Scanner(System.in);
        String code = "";
        System.out.print("code: ");//never do the scanner and System.out in other methods... you have to do that only in the main
        code = in.next();
        
		
        String response="type: my_auctions, ";
        
			try {
				 p=Pattern.compile("[a-zA-Z]");
				 m=p.matcher(searchingString);
				 
				if((searchingString.length()==10 || searchingString.length()==13) && m.matches()){//Questo è un metodo che dovrebbe essere interno a parte che chiama l'eccezione con throws ExeptionLenght
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
		       }finally {
		           if (statement != null) { statement.close(); }
		       }
	
	} 		
		
        // 2) check if code is valid (need have 10 or 13 digits)
		
		// 3) check if the code is on table "auctions"
        
        // 4) compute the number of examples founde
        	// how many examples found?
		
		// 5) print results to the user
        	// example:
        		// type: search_action
        		// items_count: 2
        		// items_0_id: 101, items_0_code: 9780451524935, items_0_title: 1984
        		// items_1_id: 103, items_1_code: 9780451524935, items_1_title: 1984 usado
	
	
	//////////////////////////////////////////////////////////////////////////
	// TASK 3 (by: Guido): detail_auction
	public void  detailAuction(){
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
		}finally {
	        if (statement != null) { statement.close(); }
	    }
	}
	
	
	//////////////////////////////////////////////////////////////////////////
	// TASK 4 (By: Guido): createAuction
	public void createAuction(){//insert auction into database
		 int idCliente=cliente.getClientId();
		 Auction nova=new Auction();
		
		   System.out.println("insert price:");
		   nova.setPrice(scanner.nextDouble());
		
		   System.out.println("insert title:");
		   nova.setPrice(scanner.nextLine()); 
		   
		   System.out.println("insert description:");
		   nova.setDescription(scanner.nextLine());
		   
		   System.out.println("insert code:");
		   nova.setCode(scanner.nextLine());
		   
		   System.out.println("insert deadline:");
		   nova.setDeadline();
		   //check control on deadline
		
				try {
					result=statement.executeQuery("INSERT INTO Auctions (Title, Description, Status, Code, Deadline, ActualPrice)"+
												  " VALUES ('"+title+"','"+description+"',1,'"+code+"','"+deadline+"',10);");
					if (statement != null) { statement.close();}
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
				notifyBestOffer(bid);
			}else{
				System.out.println("type: bid, ok:false.");
			}
		}catch (SQLException e) {
			System.out.println("type: bid, ok:false.");
			e.printStackTrace();
		}finally {
	        if (statement != null) { statement.close(); }
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
								   " VALUES ('"+msg.getText()+"',0,"+senderID+","+reciverID+");");
		}
		System.out.println("Type: message, id");
	}
	
	//i have to finish this
	public void checkAuctionTermination() throws ParseException
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
		    	   				}else{
		    	   					System.out.println("Your auction are not expiried");
		    	   				}
		    	   		}
		    	   	}
				  
								
				/*I need to provide a comparation between dates */
							}
							
				}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	        if (statement != null) { statement.close(); }
	    }
	}
	
	
	//notifyBestOffert
	public void notifyBestOffer(Bid bid)
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
			}finally {
		        if (statement != null) { statement.close(); }
		    }
	}
	//showMessages has to be periodically called by the client
	public void showMessages() throws SQLException{
		String message="";
		int reciverID=0;
		   statement=connection.createStatement();
	       result=statement.executeQuery("SELECT Messages.Text,Messages.idMessage,Messages.User_sender"+
	    		   						 "FROM Messages"+
	    		   						 "WHERE Status=0 and Username='"+cliente.getClientId()+"';");//statements which selects all users with status==1
	       
	       
	       if(result.getString("User_sender")!=null){
	    	   System.out.println("You don't have notifications");
	       }else{
	    	    ArrayList idArray = new ArrayList<Integer>();//inizzializzare un array
	    	   	String response="type: message, ";
	    	   	//statements which selects all users with status==1
	    	   	meta1=result.getMetaData();	
	    	   	int numberOfColumns=meta1.getColumnCount();
	    	   	//it always works even if we have one column
	    	   	while(result.next()){	//print all che table results, we have to display only what we need	
	    	   		for	(int	i=1;i<=numberOfColumns;	i++)	{	
	    	   			response+="id:"+result.getString("idMessage")+", ";
	    	   			idArray.add(new Integer(Integer.parseInt(result.getString("idMessage"))));
	    	   			response+="text: "+result.getString("Test_msg");
	    	   			response+="\n";
	    	   		}
	    	   	}
	    	   	for(int i=0;i<idArray.size();i++){
	    	   			   statement.executeQuery("UPDATE Messages"+
	    	   									  "SET Messages.Status=1"+
	    	   									  "WHERE Messages.idMessage="+idArray.get(i-1)+";");
	    	   	}
	    	   	System.out.println(response);	
	       }	
	}
	
	//////////////////////////////////////////////////////////////////////////
	// TASK 7 (By: Guido): detail_auction
	public void onlineUsers() throws SQLException
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
			}finally {
		        if (statement != null) { statement.close(); }
		    }
			
		}

	
	
	public void registry(){ //(COMPLETE / TESTED OK)
    	
    	int ok = 0; // false (ok = 1 when true)
        Scanner in = new Scanner(System.in);
        String username = "";
        String password = "";
        System.out.print("username: ");
        username = in.next();
        System.out.print("password: ");
        password = in.next();
        
        // if account created need print "ok: true"
        
        try {
        		// Execute SQL query 			
     			result = statement.executeQuery("select * from users");
     			
     			// Process the result set
     			while (result.next()) {
     				
     				String user = result.getString("Username"); //get the user from table users
     				String pass = result.getString("Password"); //get the pass from table users
     				
     				int s1 = 0;
     				int s2 = 0;
     				
     				// in case of registry the program must check if the user
     				// already exists 
     				// dont need compare the password
     				if(user.equals(username)){
     					//s1 = 1;
     					ok = 1;
     				}
     			}
     			if (ok==1){
     				// the username already is in use
 					System.out.println("type: login, ok: false");
 					get_access();
 				}
 				else{
 					// the username is free
 					System.out.println("type: login, ok: true");
 					
 					// insert user and pass in database
 					String sql = "INSERT INTO users"
 							   + "(Username, Password)"
 							    + "VALUES ('" +username+ "','" +password +"')";
 					Statement statement = connection.createStatement();
 					statement.executeUpdate(sql);
 					get_access();

 				}
     		
     			}
     			catch (Exception exc) {
     				exc.printStackTrace();
     			}
 
    }

	
    //////////////////////////////////////////////////////////////////////////
    // TASK 0b (By Guido)
    public void login(){ //login has to be done yet
    	 	System.out.println("type: login, ");
    	 	System.out.println(" username: ");
    	 	String usr=scanner.next();
    	 	System.out.println(", password: ");
    	 	String pws=scanner.next();

    	 
    	 	try {

    	 		 String query="SELECT *  FROM users  WHERE Password='"+pws+"' and Username='"+usr+"';";
    	 		 
    	 		 statement=connection.createStatement();
    	 		 result=statement.executeQuery(query);

    	 		 
    	 		 if(result.next()){
    	 			 	this.cliente.setClientId(result.getInt("idUser"));
    	 			 	this.cliente.setClientUsername(result.getString("Username"));
    	 			 	this.cliente.setClientPass(result.getString("Password"));

    	 			 	System.out.println("type:login,ok:true.");
    	 		 }else{
    	 			 System.out.print("type:login,ok:false.");
    	 		 }
			} catch (SQLException e) {
				System.out.print("type:login,ok:false.");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
    	 	
    	 	login_types();
        // possible first types: login or registry
    }
    
    public void login_types()  {//create an object to store variables
    	
    	//GEt the messagess not read 
    	/* try {
 			statement=connection.createStatement();
 			result=statement.executeQuery("SELECT * "+
   					  					  "FROM messages,users "+
   					  					  "WHERE messages.Status=0 and users.idUser="+cliente.getClientId()+";");
 		
       //statements which selects all users with status==1
         
         
         if(result.getString("Users_sender")==null){
         	System.out.println("You don't have notifications");
         }else{
         	ArrayList<Integer> idArray=new ArrayList<>();
         	String response="type: notification_message, ";
 			//statements which selects all users with status==1
         	meta1=result.getMetaData();	
 		    int numberOfColumns=meta1.getColumnCount();
         		//it always works even if we have one column
         		while(result.next()){	//print all che table results, we have to display only what we need	
         			for	(int	i=1;i<=numberOfColumns;	i++)	{	
         				response+="id:"+result.getInt("idMessage")+", ";
         				idArray.add(new Integer(result.getInt("idMessage")));
         				response+="user: "+result.getString("idUser")+", ";
         				response+="text: "+result.getString("Test_msg");
         				response+="\n";
         			}
         		}
         		for(int i=0;i<idArray.size();i++){
         			result=statement.executeQuery("UPDATE Messages"+
         									 	  "SET Messages.Status=1"+
         									 	  "WHERE Messages.idMessage="+idArray.get(i)+";");
         		}
         		System.out.println(response);
         }
        }catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}*/
    	 
    	 
    	 
    	Scanner in = new Scanner(System.in);
    	System.out.println("POSSIBLE TYPES: create_auction, search_auction, detail_auction, my_auctions, bid, message, online_users");
    	String type = "";
    	System.out.print("type: ");
        type = in.next();
        
        /*notification for messages*/
       
       
        
        
        
        
        if(type.equals("create_auction")){
        	createAuction();
    	}else if(type.equals("bid")){
    		makeBid();
    	}else if(type.equals("message")){
    		insertMessage();
    	}else if(type.equals("online_users")){
    		onlineUsers();
    	}else if(type.equals("search_auction")){
    		System.out.print("Insert what auction do you want to search: ");
            type = in.next();
    		searchAuction(type);
    	}else if(type.equals("detail_auction")){
    		detailAuction();
    	}
    	else{
    		login_types();
    	}

    }
    
   public void get_access(){
    	
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
	
}
    
