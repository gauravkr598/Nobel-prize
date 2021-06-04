package db.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
		public DBConnection(){}
		private static Connection con=null;
		public  static Connection getConnection() {
			try { 
				 if(con==null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/github","root","admin");
				System.out.println("Connection stablished");
				 }
			}  catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return con;	
		}
		 
}

