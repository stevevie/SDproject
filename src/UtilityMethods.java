// EDITED ON 22-10-2016
// ALFREDO SOLDADINHO

import java.sql.*;
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
	private static ResultSetMetaData meta1; 
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
	// TASK 1 (by: Guido): create_auction
	public static void create_auction(Cliente cliente){
		// need insert one action on table "auction"
		//System.out.println("entrou");
		
		
		// 1) ask information to the user
		
		// 2) insert information on table "auctions"
		
	}
	
	//////////////////////////////////////////////////////////////////////////
	// TASK 2 (by: Guido): search_auction
	public static void search_auction(String searchingString){
		// need search auction by code with 10 or 13 digits only
		
		// 1) ask for the code
        //Scanner in = new Scanner(System.in);
        //String code = "";
        //System.out.print("code: ");//never do the scanner and System.out in other methods... you have to do that only in the main
        //code = in.next();
        //int cont = 0; // will increment when 1 result found
		int countRows=0; 
		try{
		if(searchingString.length()>=10 && searchingString.length()<=13){	
	
			try {
				statement=connection.createStatement();
				result=statement.executeQuery("");//statements which does the select on db thanks to searchingstring
		        String countString=result.getString(0);
		        if(Integer.parseInt(countString)!=0){	
		        	meta1=result.getMetaData();
		        	int numberOfColumns	=	meta1.getColumnCount();	
		        		for	(int	i	=	1;	i	<=	numberOfColumns;	i++)	{		
		        			String columnName	=	meta1.getColumnLabel(i);			
		        		}	
				
		        		while(result.next()){	//print all che table results, we have to display only what we need	
		        			for	(int	i=1;i<=numberOfColumns;	i++)	{		
		        				String columnValue	=result.getString(i);		
		        				System.out.print(columnValue);		
		        			}		
		        		}
		        }else{
		        	throw new ExeptionLenght()
		        }
		        
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			   
	
		//}catch{
			
	//	}finally{
		  
		//}
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
		
	}
	//////////////////////////////////////////////////////////////////////////
	// TASK 3 (by: Guido): detail_auction
	public static void  detail_auction(){
		
		
	}
	//////////////////////////////////////////////////////////////////////////
	// TASK 4 (By: Guido): my_auctions
	public static void my_auctions(Cliente cliente){
		 int idCliente=cliente.getId();
			//try{
				try {
					statement=connection.createStatement();
					result=statement.executeQuery("");//search query by the id of the user
		        	meta1=result.getMetaData();
		        	int numberOfColumns	=	meta1.getColumnCount();	
		        		for	(int	i	=	1;	i	<=	numberOfColumns;	i++)	{		
		        			String columnName	=	meta1.getColumnLabel(i);			
		        		}	
				
		        		//it always works even if we have one column
		        		while(result.next()){	//print all che table results, we have to display only what we need	
		        			for	(int	i=1;i<=numberOfColumns;	i++)	{		
		        				String columnValue	=result.getString(i);		
		        				System.out.print(columnValue);
		        			}
		        		}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			        				
			
			        //   }else{
					//exeption
			        	//   System.out.print(true);
			           //}
			//}catch{
				
			//}finally{
			  
			//}
	}	
	//////////////////////////////////////////////////////////////////////////
	// TASK 5 (By: Guido): bid 
	public static void bid(Bid bid){
		try{
			statement=connection.createStatement();
			statement.executeQuery("INSERT INTO Bid VALUES ("+bid.getCliente_id()+","+bid.getAuct_id()+","+bid.getPrice());//search query by the id of the user
			result=statement.executeQuery("SELECT MAX(ActualPrice_auc) FROM nometabella WHERE idAuction="+bid.getAuct_id());
			if(Integer.parseInt(result.getString(0))<bid.getPrice()){
				statement.execute("UPDATE Auctions SET ActualPrice_auc"+bid.getPrice()+"WHERE idAuction="+bid.getAuct_id());
			}else{
				//rho
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//////////////////////////////////////////////////////////////////////////
	// TASK 6 (By: Guido): message
	public static void message(Message msg, String text){
		statement=connection.createStatement();
		msg.setText(text);
		//try{
		   statement.executeQuery("");
		//}catch{
			
		//}
	}
	//////////////////////////////////////////////////////////////////////////
	// TASK 7 (By: Guido): detail_auction
	public static void online_users(){
		  
			statement=connection.createStatement();  
			result=statement.executeQuery("");//statements which selects all users with status==1
			String countString=result.getString(0);
			
			//try{	
			        	meta1=result.getMetaData();
			        	int numberOfColumns	=	meta1.getColumnCount();	
					for	(int	i	=	1;	i	<=	numberOfColumns;	i++)	{		
							String columnName	=	meta1.getColumnLabel(i);			
						}	
					
					while(result.next()){	//print all che table results, we have to display only what we need	
						for	(int	i=1;i<=numberOfColumns;	i++)	{		
						String columnValue	=result.getString(i);		
						System.out.print("item"columnValue);		
						}		
					}		
		//	}catch{
				
		//	}finally{
				
		//	}
	}
	//////////////////////////////////////////////////////////////////////////

	
	//////////////////////////////////////////////////////////////////////////
	// TASK 0a (By Alfredo)
	 public static void registry(){
	      
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
	          pedido = statement.executeQuery("select * from users");
	          
	          // Process the result set
	          while (pedido.next()) {
	            
	            String user = pedido.getString("username");
	            String pass = pedido.getString("password");
	            int s1 = 0;
	            int s2 = 0;
	            //System.out.println(user + ", " + pass);
	            
	            
	    
	            if((user.equals("user1")) && (pass.equals("user1")){
	              // ask another username: username already exists
	              ok = 1;
	              //System.out.println("já existe!");
	              System.out.println("type: login, ok: false");
	              //System.out.println(user + ", " + pass);
	              registry();
	            }
	            else{
	              // insert user and pass in database
	              // execute SQL query
	              
	              //pedido = statement.executeQuery("INSERT INTO users (username, password) VALUES ('sd','sd')");
	              System.out.println("type: login, ok: true");
	              System.out.println(user + ", " + pass);
	            }
	          }
	          
	          }
	          catch (Exception exc) {
	            exc.printStackTrace();
	          }
	       
	    }
    
    //////////////////////////////////////////////////////////////////////////
    // TASK 0b (By Guido)
    public static void login(){

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
        	login_types();
        //}else{
        	//System.out.println("error");
        	//get_access();
        //}
        
    }
    
    public static void login_types(){
    	Scanner in = new Scanner(System.in);

    	System.out.println("POSSIBLE TYPES: create_auction, search_auction, detail_auction, my_auctions, bid, message, online_users");
    	String type = "";
    	System.out.print("type: ");
        type = in.next();
        Cliente cliente;
        
        if(type.equals("create_auction")){
        	//System.out.println("entrou");
        	create_auction(cliente);
    	}
    	else if(type.equals("search_auction")){
    		search_auction(type);
    	}
    	else if(type.equals("detail_auction")){
    		
    	}
    	else{
    		login_types();
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
	public static void main(String[] args) throws SQLException {
	    
	    
	    try {
	      
	      db_connection();
	      registry();
	      
	    
	    }
	    catch (Exception exc) {
	      exc.printStackTrace();
	    }
	    
	    /*
	    finally {
	      if (pedido != null) {
	        pedido.close();
	      }
	      
	      if (statement != null) {
	        statement.close();
	      }
	      
	      if (conexao != null) {
	        conexao.close();
	      }
	    }*/
	  }
}
    
