import java.io.*;
import java.sql.*;
import java.util.*;


//This classes has the utility of maintain multiple connections, because the connection to
//database is very expensive. After i will add the database
	public	class DbConnectionPool	{	
		
		private static	List<Connection>	freeDbConnections;
		
		static	{	
				freeDbConnections	=	new LinkedList<Connection>();	
					
				try	{	
					Class.forName("com.mysql.jdbc.Driver");	
				}catch	(ClassNotFoundException	e)	{	
					System.out.printf("DB	driver	not found!",e);	
				}	
		}	

		private	static Connection createDBConnection() throws SQLException
		{	
			Connection	newConnection	=	null;	
			String ip	=	"localhost";	
			String port	=	"3306";	
			String db	=	"mydb";	
			String	username	=	"root";	//it depends from your configurations
			String	password	=	"root";
			
			newConnection	=	DriverManager.getConnection("jdbc:mysql://"+	ip+":"+	port+"/"+db,	username,	password);	
			newConnection.setAutoCommit(false);	
			return newConnection;	
		}
		
		
		public	static synchronized Connection	getConnection()	throws SQLException
		{	
			Connection	connection;	
			if	(!freeDbConnections.isEmpty())	
			{	
					connection	=	(Connection)freeDbConnections.get(0);	
					DbConnectionPool.freeDbConnections.remove(0);	
					try	{	
						if (connection.isClosed())
							connection = DbConnectionPool.getConnection();	
					}
					catch(SQLException	e)	{	
						connection	=	DbConnectionPool.getConnection();	
					}	
			}else{
				connection	=	DbConnectionPool.createDBConnection();
			}
			
				return	connection;	
}	
		
		public	static synchronized void releaseConnection(Connection	connection)	
		{	
			DbConnectionPool.freeDbConnections.add(connection);	
		}	

}