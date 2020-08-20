package ws;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Test {
	static BookDAO bdao = new BookDAO();
	public static void main(String[] args) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = bdao.getConnection();
			
		}catch( ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("1.아래의 데이터를 입력하세요.");
		bdao.insertBook(new Book("a1101","JAVA 기본","자앤 기술연구소","자앤 출판사",23000,"기본"));
		bdao.insertBook(new Book("a1102","JAVA 중급","자앤 기술연구소","자앤 출판사",25000,"중급"));
		bdao.insertBook(new Book("a1103","JAVA 실전","자앤 기술연구소","자앤 출판사",30000,"실전"));
		
		System.out.println("2.현재 도서 목록 출력");
		printAllBooks();
		
		System.out.println("3. a1101 도서를 검색해보세요.");
		Book cb = bdao.findBook("a1101");
		System.out.println(cb);
		
		System.out.println("4. a1104 도서를 추가하세요.");
		Book b4 = new Book("a1104","JAVA 심화","자앤 기술연구소","자앤 출판사",28000,"심화");
		bdao.insertBook(b4);
		printAllBooks();
		
		System.out.println("5. a1102 도서를 수정 후 목록을 출력하세요.");
		Book b5 = new Book("a1101","JAVA 기본","자앤 기술연구소","자앤 출판사",20000,"기본");
		bdao.updateBook(b5);
		cb = bdao.findBook("a1101");
		System.out.println(cb);
		
		System.out.println("6. a1103 도서를 삭제 후 목록을 출력하세요.");
		bdao.deleteBook("a1103");
		printAllBooks();
		
		try {
			bdao.close(con);
			bdao.close(rs);
			bdao.close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void printAllBooks() {
		List<Book> alist = bdao.listBooks();
		for (Book b : alist) {
			System.out.println(b);
		}
	}
}
