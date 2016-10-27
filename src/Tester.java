import java.sql.SQLException;

public class Tester {
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
