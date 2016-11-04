/*
* LAST EDIT 26-10-2016
* ALFREDO SOLDADINHO & GUIDO RIZZO
* help links:
* https://www.youtube.com/watch?v=2i4t-SL1VsU (get itens in database)
* https://www.youtube.com/watch?v=Q4T7jg0Lv4E (put itens in database)
*/


import javax.swing.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.sql.*;
import java.util.*;
import java.io.*;

//////////////////////////////////////////////////////////////////////////////////////////////
// IMPORTANT NOTES:
// - when we end the methods we still need apply the protocol key-value to the input and output strings (see report document)
// - the protocol must be apply only in methods tested and aproved in terms of functionaly and protection
// - the method create_auction still dont work perfectly when we go insert in database
// - in function create_auction the deadline still need be protected
//////////////////////////////////////////////////////////////////////////////////////////////

public class Metodos {
	
	public static Connection conexao = null;
	public static Statement statement = null;
	public static ResultSet pedido = null;
	
	//////////////////////////////////////////////////////////////////////////
	// TASK 1: create_auction
	public static void create_auction () throws SQLException {
		// need insert one action on table "auction"
		//System.out.println("entrou");
		
		// IMPORTANTE NOTE: deadline still need be protected
		
		// 1) ask information to the user
		Scanner in = new Scanner(System.in);
        String code = "";
        System.out.print("code: ");
        code = in.next();
   
        // PROTECTION: protect this scan element to it dont be null
        if(code == ""){
        	System.out.println("type: create_auction, ok: false");
        	create_auction(); // give new chance to user create a action
        }
        
        // PROTECTION: the code must have 10 or 13 digits only
        int code_size = code.length();
        //System.out.println(code_size);
        
        if(code_size == 10){
        	System.out.println("type: create_auction, ok: true");
        }
        else if(code_size == 13){
        	System.out.println("type: create_auction, ok: true");
        }
        else {
        	System.out.println("type: create_auction, ok: false");
        	create_auction(); // give new chance to user create a action
        }
        
        // PROTECTION: the code must have digits/numbers only
        char[] c = code.toCharArray();
        int d = 0;
        for ( int i = 0; i < c.length; i++ ){
            // if the car is not a digit:
            if ( !Character.isDigit(c[i])){
                d = 1;
                break;
            }
        }
        if (d==1){
        	System.out.println("type: create_auction, ok: true");
        	create_auction(); // give new chance to user create a action
        }
        String title = "";
        System.out.print("title: ");
        title = in.next();
        if(title == ""){
        	System.out.println("type: create_auction, ok: false");
        	create_auction(); // give new chance to user create a action
        }
        String description = "";
        System.out.print("description: ");
        description = in.next();
        if(description == ""){
        	System.out.println("type: create_auction, ok: false");
        	create_auction(); // give new chance to user create a action
        }
        String deadline = "";
        System.out.print("deadline: ");
        deadline = in.next();
        if(deadline == ""){
        	System.out.println("type: create_auction, ok: false");
        	create_auction(); // give new chance to user create a action
        }
       
        float amount = 0; // max price that the buyer accept
        System.out.print("amount: ");
        amount = in.nextFloat();
        // PROTECTION: the amount must be more than zero
        if(amount <= 10){
        	System.out.println("type: create_auction, ok: false");
        	create_auction(); // give new chance to user create a action
        	
        }
       
		// 5) insert information on table "auctions" only if them is VALID
        // only if it can escape of the protections conditions
        String sql = "INSERT INTO actions"
					+ "(Title, Description_auc, Status_auc, Code_auc, Deadline_auc, Price_auc)"
					+ "VALUES ('" +title+ "','" + description + "','" + 1 + "','" + code + "','"
					+ "2017-01-01 00:01" + "','" + amount + "')";
			Statement statement = conexao.createStatement();
			statement.executeUpdate(sql);
        
		
	}
	
	//////////////////////////////////////////////////////////////////////////
	// TASK 2 search_auction
	public static void search_auction(){
		// need search auction by code with 10 or 13 digits only
		
		// 1) ask for the code
        Scanner in = new Scanner(System.in);
        String code = "";
        System.out.print("code: ");
        code = in.next();
        int cont = 0; // will increment when 1 result found
		
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
	// TASK 3 detail_auction
	public static void  detail_auction(){
		
		
	}
	//////////////////////////////////////////////////////////////////////////
	// TASK 4 my_auctions
	public static void my_auctions(){
		// list the all action of a user 
		
		
	}
	//////////////////////////////////////////////////////////////////////////
	// TASK 5  bid 
	public static void bid(){
		
		// 1) receive the bid from console by the user to a ID auction
		
		// 2) check if that bid is a value greater than value in column "price" in table: auctions
			// if the bid value is smaller than value in column "price" dont do the steps 3 and 4
			// in this case return a message to the user on the console
			// and ask for a higher value
		
		// 3) if bid value ok: insert the bid on table: bids
		
		// 4) if bid value ok: update the the column "price" on table: auctions
	
	}
	//////////////////////////////////////////////////////////////////////////
	// TASK 6 message
	public static void message(){
		
		
	}
	//////////////////////////////////////////////////////////////////////////
	// TASK 7 detail_auction
	public static void online_users(){
		
		//in table "users" the column status 0 means that user is offline
		// and status 1 means that user is online
		
		
	}
	//////////////////////////////////////////////////////////////////////////
	// TASK 8: warn the user that their BID was exceeded
	public static void warn_user(){
		
		// when user online it must receive a instant message on the console
		// that message can also be saved on table: messages
		
		// if the user is online in that moment he will receive the warn immediately
		// if the user is offline he will receive this warn when he do the login
		
		// this warn also must be sent to all users that did past bids in that auction
		// we can check all that users in table: bids
		
	}

	//////////////////////////////////////////////////////////////////////////
	// TASK 0a - topic 6.1 (3 pts in checklist)
	public static void registry(){ //(COMPLETE / TESTED OK)
    	
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
     				
     				String user = pedido.getString("Username"); //get the user from table users
     				String pass = pedido.getString("Password"); //get the pass from table users
     				
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
 					Statement statement = conexao.createStatement();
 					statement.executeUpdate(sql);
 					get_access();

 				}
     			
     			}
     			catch (Exception exc) {
     				exc.printStackTrace();
     			}
        

       
    }
    
    //////////////////////////////////////////////////////////////////////////
    // TASK 0b - topic 6.1 (3 pts in checklist)
	public static void login(){ // (COMPLETE / TESTED OK)
    	
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
     				
     				String user = pedido.getString("Username"); //get the user from table users
     				String pass = pedido.getString("Password"); //get the pass from table users
     				
     				int s1 = 0;
     				int s2 = 0;
     				//System.out.println(user + ", " + pass);
     				
     				// in case of registry the program must check if the user
     				// already exists 
     				// dont need compare the password
     				if(user.equals(username)){
     					//s1 = 1;
     					s1 = 1;
     				}
     				
     				if(pass.equals(password)){
     					s2 = 1;
     				}
     				
     				if(s1 == 1 && s2 == 1){
     					ok = 1;
     					
     				}
     			
     			}
     			if (ok==1){
     				// if ok=1 is because the user and pass word are confirmed
     				// in same line on the table users
     				// give acess to the user:
     				System.out.println("type: login, ok: true");
     				login_types(); // this types is only available when user get success in login
 				}
 				else{
 					System.out.println("type: login, ok: false");
 					// the user will need put correct login or create one account
 					// so the program will call the function get_acess() again
 					get_access();
 					
 				}
     			
     			}
     			catch (Exception exc) {
     				exc.printStackTrace();
     			}
       
    }

    //////////////////////////////////////////////////////////////////////////
    // TASK 0c) 
    static void get_access(){ 
    	// this method is for user get the basic options: login and register

    	
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
    
    //////////////////////////////////////////////////////////////////////////
	// TASK 0d) 
    public static void login_types() throws SQLException{ // (MUST BE COMPLETE)
    	// this method is to the user get menu options when he get success in login
    Scanner in = new Scanner(System.in);

        System.out.println("POSSIBLE TYPES: create_auction, search_auction, detail_auction, my_auctions, bid, message, online_users, exit");
        String type = "";
        System.out.print("type: ");
        type = in.next();
        
        if(type.equals("create_auction")){
            //System.out.println("entrou");
            create_auction();
        }
        else if(type.equals("search_auction")){
            search_auction();
        }
        else if(type.equals("detail_auction")){
            detail_auction();
        }
        //(...)
        else{
            login_types();
        }  

    }
    
    //////////////////////////////////////////////////////////////////////////
    // TASK 0e) 
	public static void db_connection() throws SQLException{ //(COMPLETE)
		// this function will be used in RMI server in future
		// this method connect the program with the database
    	
		
		conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root" , "abc");
        //conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/sd", "root" , "abc");
		statement = conexao.createStatement();
			
	}
    
    public static void main(String[] args) throws SQLException{
    	
    	db_connection();
    	get_access();
    }
}
