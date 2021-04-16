package mc.sn.day14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ReviewDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReviewDB rd= ReviewDB();
		try {
			rd.connectDB();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	private static ReviewDB ReviewDB() {
		// TODO Auto-generated method stub
		return null;
	}

	public void connectDB() throws SQLException,ClassNotFoundException{
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.OracleDriver";
		String id = "hr";
		String pwd = "1234";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbcURL,id,pwd);
		if(con!=null) {
			System.out.println("connected");

			con.close();
		}else {
			System.out.println("fail");
		}
		
	}

}
