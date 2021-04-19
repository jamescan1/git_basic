package mc.sn.day14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TestQuery {
	private ArrayList<StudentDTO> list;
	//�⺻ ������ ���� list �ν��Ͻ� �����ؼ� �Ҵ��ϴ� �ڵ� �ۼ��Ͻÿ�.
	public TestQuery() {
		list = new ArrayList<StudentDTO>();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestQuery tq = new TestQuery();
		try {
			tq.query1();
			//tq.query3();
			tq.quiz2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void quiz2() throws ClassNotFoundException, SQLException {
		//��� ���̺��� ������ ��ȸ
		//�����ڵ尡 B�̰� ����+���� ������ �ִ��� �� ��ȸ
		String sql = "select max(kor+eng) from gisaTBL where localCode = ?";
		Connection con = this.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "B");
		ResultSet rs = pstmt.executeQuery();
		//���̺� ó�� ���
		while(rs.next()) {
			System.out.println("max value is "+rs.getInt(1));
		}
		rs.close();
		pstmt.close();
		con.close();
	}
	public void query4() throws ClassNotFoundException, SQLException {
		//��� ���̺��� ������ ��ȸ
		//�����ڵ尡 B�̰� ����+���� ������ �ִ��� �� ��ȸ
		String sql = "select stdNo,email,kor,eng from gisaTBL "
													+ "where localCode = ?";
		Connection con = this.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "B");
		ResultSet rs = pstmt.executeQuery();
		//���̺� ó�� ���
		while(rs.next()) {
			int stdNo = rs.getInt("stdNo");
			String email = rs.getString("email");
			int kor = rs.getInt("kor");
			int eng = rs.getInt("eng");
			System.out.println(stdNo+","+email+","+kor+","+eng);
			//break;
		}
		rs.close();
		pstmt.close();
		con.close();
	}
	public void query3() throws NumberFormatException, IOException, SQLException, ClassNotFoundException {
		//this.makeGisaData();
		System.out.println(list.size());
		//gisaTBL�� �� ���� ���� ����
		//StudentDTO dto = null;
		Connection con = this.getConnection();
		// ������ ���� ���� �ۼ��� ������
		String sql = "INSERT INTO gisaTBL VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		for (StudentDTO dto : list) {
			pstmt.setInt(1, dto.getStdNo());
			pstmt.setString(2, dto.getEmail());
			pstmt.setInt(3, dto.getKor());
			pstmt.setInt(4, dto.getEng());
			pstmt.setInt(5, dto.getMath());
			pstmt.setInt(6, dto.getSci());
			pstmt.setInt(7, dto.getHist());
			pstmt.setInt(8, dto.getTotal());
			pstmt.setString(9, dto.getMgrCode());
			pstmt.setString(10, dto.getAccPoint());
			pstmt.setString(11, dto.getLocalCode());
			int affectedCount = pstmt.executeUpdate();
			
			if(affectedCount>0) {
				System.out.println("�Է¿Ϸ�");
			} else {
				System.out.println("�Է½���");
			}
			
		}
		pstmt.close();
		con.close();
		
	}
	public void query2() throws NumberFormatException, IOException, SQLException, ClassNotFoundException {
		this.makeGisaData();
		System.out.println(list.size());
		//gisaTBL�� �� ���� ���� ����
		StudentDTO dto = list.get(0);
		Connection con = this.getConnection();
		// ������ ���� ���� �ۼ��� ������
		String sql = "INSERT INTO gisaTBL VALUES (";
		StringBuffer sb = new StringBuffer(sql);
		sb.append(dto.getStdNo()+",'");
		sb.append(dto.getEmail()+"',");
		sb.append(dto.getKor()+",");
		sb.append(dto.getEng()+",");
		sb.append(dto.getMath()+",");
		sb.append(dto.getSci()+",");
		sb.append(dto.getHist()+",");
		sb.append(dto.getTotal()+",'");
		sb.append(dto.getMgrCode()+"','");
		sb.append(dto.getAccPoint()+"','");
		sb.append(dto.getLocalCode()+"')");
		sql = sb.toString();
		System.out.println(sql);
		Statement stmt = con.createStatement();
		int affectedCount = stmt.executeUpdate(sql);
		if(affectedCount>0) {
			System.out.println("�Է¿Ϸ�");
		} else {
			System.out.println("�Է½���");
		}
		stmt.close();
		con.close();
		
	}
	public void makeGisaData() throws NumberFormatException, IOException {
		//���Ͽ� �����ؼ� �ش� ���� �о�ͼ� �Ʒ��� ���� �и��ؼ� ����Ʈ ����
		File file = new File("data\\Abc1115.csv");
		if(file.exists()) {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine())!=null) {
				//990001,addx, 17, 29, 16, 49, 43,154,C,A,C
				String temp = line;//"990001,addx, 17, 29, 16, 49, 43,154,C,A,C";
				//���� ��Ʈ���� ,�� �и��Ͽ� StudentDTO �ν��Ͻ��� �ϳ� �����ϰ�
				//������ �ν��Ͻ��� ����Ͽ� ������ �ڷᰡ ����� ������ Ȯ���ϴ� �ڵ� ����
				//2��20�б��� �ϼ���.
				String[] data = temp.split(",");
				StudentDTO dto = new StudentDTO();
				int i = Integer.parseInt(data[0]);
				dto.setStdNo(i);
				//990001�� int������ �ٲپ�� �Ѵ� --> ���?
				dto.setEmail(data[1]);
				i = Integer.parseInt(data[2].trim());
				dto.setKor(i);
				dto.setEng(Integer.parseInt(data[3].trim()));
				dto.setMath(Integer.parseInt(data[4].trim()));
				dto.setSci(Integer.parseInt(data[5].trim()));
				dto.setHist(Integer.parseInt(data[6].trim()));
				dto.setTotal(Integer.parseInt(data[7].trim()));
				dto.setMgrCode(data[8]);
				dto.setAccPoint(data[9]);
				dto.setLocalCode(data[10]);
				//System.out.println(dto);
				list.add(dto);
			}
			br.close();
			fr.close();
		}
	}
	
	public void query1() throws ClassNotFoundException, SQLException {
		Connection con = this.getConnection();
		String sql = "INSERT INTO testTBL2(id,username,age) "
				+ "VALUES (7,'lee','addr')";
		// id�� 2���� userName�� lee�� �����ϴ� ������ �ۼ��Ͻÿ�.
		sql = "UPDATE testTBL2 SET userName='lee' WHERE id = 2";
		// userName �� kim�� �ڷḦ ��� �����ϴ� ������ �ۼ��Ͻÿ�.
		sql = "DELETE FROM where userName = 'kim'";
//		Statement stmt = con.createStatement();
//		int affectedCount = stmt.executeUpdate(sql);
//		if(affectedCount>0) {
//			System.out.println("�Է¿Ϸ�");
//		} else {
//			System.out.println("�Է½���");
//		}
//		stmt.close();
//		con.close();
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.OracleDriver";
		String id = "hr";
		String pwd = "1234";
		
		Class.forName(driver);
		con = DriverManager.getConnection(jdbcURL,id,pwd);
		
		return con;
	}

}
