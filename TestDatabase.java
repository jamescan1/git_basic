package mc.sn.day13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabase {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub

		TestDatabase td = new TestDatabase();
		try {
			td.connectDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void connectDB() throws SQLException,ClassNotFoundException{
	
			String jdbcURL= "jdbc:oracle:thin:@localhost:1521:xe";
			String driver="oracle.jdbc.OracleDriver";
			String id="hr";
			String pwd="1234";
			Class.forName(driver);
			Connection con  = DriverManager.getConnection(jdbcURL, id, pwd);
			if(con!=null) {
				System.out.println("connected");
				con.close();
			}else {
				System.out.println("fail");
				
			}
	}
	
	
	
}
