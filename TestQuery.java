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
	//기본 생성자 만들어서 list 인스턴스 생성해서 할당하는 코드 작성하시오.
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
		//기사 테이블의 내용을 조회
		//지역코드가 B이고 국어+영어 점수가 최대인 것 조회
		String sql = "select max(kor+eng) from gisaTBL where localCode = ?";
		Connection con = this.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "B");
		ResultSet rs = pstmt.executeQuery();
		//테이블 처리 방법
		while(rs.next()) {
			System.out.println("max value is "+rs.getInt(1));
		}
		rs.close();
		pstmt.close();
		con.close();
	}
	public void query4() throws ClassNotFoundException, SQLException {
		//기사 테이블의 내용을 조회
		//지역코드가 B이고 국어+영어 점수가 최대인 것 조회
		String sql = "select stdNo,email,kor,eng from gisaTBL "
													+ "where localCode = ?";
		Connection con = this.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "B");
		ResultSet rs = pstmt.executeQuery();
		//테이블 처리 방법
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
		//gisaTBL에 한 줄의 값을 삽입
		//StudentDTO dto = null;
		Connection con = this.getConnection();
		// 데이터 삽입 쿼리 작성해 보세요
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
				System.out.println("입력완료");
			} else {
				System.out.println("입력실패");
			}
			
		}
		pstmt.close();
		con.close();
		
	}
	public void query2() throws NumberFormatException, IOException, SQLException, ClassNotFoundException {
		this.makeGisaData();
		System.out.println(list.size());
		//gisaTBL에 한 줄의 값을 삽입
		StudentDTO dto = list.get(0);
		Connection con = this.getConnection();
		// 데이터 삽입 쿼리 작성해 보세요
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
			System.out.println("입력완료");
		} else {
			System.out.println("입력실패");
		}
		stmt.close();
		con.close();
		
	}
	public void makeGisaData() throws NumberFormatException, IOException {
		//파일에 접속해서 해당 라인 읽어와서 아래와 같이 분리해서 리스트 저장
		File file = new File("data\\Abc1115.csv");
		if(file.exists()) {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine())!=null) {
				//990001,addx, 17, 29, 16, 49, 43,154,C,A,C
				String temp = line;//"990001,addx, 17, 29, 16, 49, 43,154,C,A,C";
				//위의 스트링을 ,로 분리하여 StudentDTO 인스턴스를 하나 생성하고
				//생성된 인스턴스를 출력하여 셋팅한 자료가 제대로 들어갔는지 확인하는 코드 생성
				//2시20분까지 하세요.
				String[] data = temp.split(",");
				StudentDTO dto = new StudentDTO();
				int i = Integer.parseInt(data[0]);
				dto.setStdNo(i);
				//990001을 int형으로 바꾸어야 한다 --> 어떻게?
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
		// id가 2번인 userName을 lee로 변경하는 쿼리를 작성하시오.
		sql = "UPDATE testTBL2 SET userName='lee' WHERE id = 2";
		// userName 이 kim인 자료를 모두 삭제하는 쿼리를 작성하시오.
		sql = "DELETE FROM where userName = 'kim'";
//		Statement stmt = con.createStatement();
//		int affectedCount = stmt.executeUpdate(sql);
//		if(affectedCount>0) {
//			System.out.println("입력완료");
//		} else {
//			System.out.println("입력실패");
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
