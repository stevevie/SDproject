
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Testo {
public static void main(String[] args) throws SQLException { 
	GregorianCalendar clendar=new GregorianCalendar();
	
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date itemDate=new Date(0);
		

	
	 System.out.println(itemDate);
	
  }
}