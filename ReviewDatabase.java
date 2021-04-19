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
		//�����ڵ� �ۼ��ϼ���
		String driver = "oracle.jdbc.OracleDriver";
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String pwd = "1234";
		//����̹� �ε�
		Class.forName(driver);
		//���ؼ� ������ �Ѵ�.(����)
		Connection con = DriverManager.getConnection(jdbcURL,id,pwd);
		
		//��������
		//con.close();
		return con;
	}
	public void testQuery2() {
		String sql = "insert into gisaTBL values (?,?,?,?,?,?,?,?,?,?,?)";
		//DML �ڵ� �ۼ��ϼ���(Insert,Update,Delete)
		//���ؼ��� testConnectDB�� �޾ƿ��� �ʹ�.
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = this.testConnectDB();
			//������ ������ ��θ� ������ �Ѵ�.
			stmt = con.prepareStatement(sql);//new �� ������� �ʴ� ����?
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
		//DML �ڵ� �ۼ��ϼ���(Insert,Update,Delete)
		//���ؼ��� testConnectDB�� �޾ƿ��� �ʹ�.
		Connection con = this.testConnectDB();
		//������ ������ ��θ� ������ �Ѵ�.
		Statement stmt = con.createStatement();//new �� ������� �ʴ� ����?
		String sql = "insert into gisaTBL values (/*11���� ���� ����*/)";
		int affectedCount = stmt.executeUpdate(sql);
		if(affectedCount>0) {
			
		} else {
			
		}
		stmt.close();
		con.close();
	}

}
