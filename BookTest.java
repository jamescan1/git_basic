package mc.sn.test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		list.add(new BookDTO(91424,"Java Basic","김하나","Jaen.kr",15000,"Java 기본 문법"));
		list.add(new BookDTO(93455,"JDBC Pro   ","김철수","Jaen.kr",23000," "));
		list.add(new BookDTO(95355,"Servlet/JSP","박자바","Jaen.kr",41000,"Model2기반"));
		list.add(new BookDTO(95332,"Android App","홍길동","Jaen.kr",25000,"Lightweight Framework"));
		list.add(new BookDTO(95356,"OOAD분석,설계","소나무","Jaen.kr",30000," "));
		
		BookTest bt = new BookTest();
		for(BookDTO book : list) {
			try {
				bt.insertStudent(book);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			bt.printAllBooks();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertStudent(BookDTO book) throws ClassNotFoundException, SQLException {
		//테이블에 5권의 책정보 삽입
		//테이블이 존재, 컨넥션 정보 있어야 한다.
		Connection con = this.getConnection();
		String sql = "insert into bookTBL values (?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, book.getIsbn());
		pstmt.setString(2, book.getTitle());
		pstmt.setString(3, book.getAuthor());
		pstmt.setString(4, book.getPublisher());
		pstmt.setInt(5, book.getPrice());
		pstmt.setString(6, book.getDescription());
		int affectedCount = pstmt.executeUpdate();
		if(affectedCount>0) {
			System.out.println("success");
		} else {
			System.out.println("fails");
		}
		pstmt.close();
		con.close();
	}
	
	public void printAllBooks() throws ClassNotFoundException, SQLException {
		//테이블의 책정보가져와서  실습1과 마찬가지로 출력하시오
		Connection con = this.getConnection();
		String sql = "select * from bookTBL";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		BookDTO book = null;
		while(rs.next()) {
			book = new BookDTO(rs.getInt("isbn"),rs.getString("title"),rs.getString("author"),rs.getString("publisher"),rs.getInt("price"),rs.getString("description"));
			list.add(book);
		}
		rs.close();
		pstmt.close();
		con.close();
		System.out.println("-----------------------도서정보-------------------");
		for(BookDTO temp: list) {
			System.out.println(temp);
		}
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
