package mc.sn.day14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ReviewDatabase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReviewDatabase rd = new ReviewDatabase();
		try {
			rd.testQuery();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection testConnectDB() throws ClassNotFoundException, SQLException {
		//접속코드 작성하세요
		String driver = "oracle.jdbc.OracleDriver";
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String pwd = "1234";
		//드라이버 로딩
		Class.forName(driver);
		//컨넥션 만들어야 한다.(연결)
		Connection con = DriverManager.getConnection(jdbcURL,id,pwd);
		
		//연결종료
		//con.close();
		return con;
	}
	public void testQuery2() {
		String sql = "insert into gisaTBL values (?,?,?,?,?,?,?,?,?,?,?)";
		//DML 코드 작성하세요(Insert,Update,Delete)
		//컨넥션을 testConnectDB로 받아오고 싶다.
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = this.testConnectDB();
			//쿼리를 보내는 통로를 만들어야 한다.
			stmt = con.prepareStatement(sql);//new 를 사용하지 않는 이유?
			stmt.setInt(1, 0);
			int affectedCount = stmt.executeUpdate();
			if(affectedCount>0) {
				
			} else {
				
			}
		} catch(ClassNotFoundException ce) {
			
		} catch(SQLException se) {
			
		} finally {
			try {
				stmt.close();
				con.close();
			} catch(SQLException se) {
				
			}
		}
	}
	
	public void testQuery() throws ClassNotFoundException, SQLException {
		//DML 코드 작성하세요(Insert,Update,Delete)
		//컨넥션을 testConnectDB로 받아오고 싶다.
		Connection con = this.testConnectDB();
		//쿼리를 보내는 통로를 만들어야 한다.
		Statement stmt = con.createStatement();//new 를 사용하지 않는 이유?
		String sql = "insert into gisaTBL values (/*11개의 값을 설정*/)";
		int affectedCount = stmt.executeUpdate(sql);
		if(affectedCount>0) {
			
		} else {
			
		}
		stmt.close();
		con.close();
	}

}
