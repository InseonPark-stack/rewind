package ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	PreparedStatement ps;
	ResultSet rs;
	Connection connection = null;
	
	public Connection getConnection() {
		// 2. Connection 
		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ssafydb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8","insun","as12as12");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;		
	}
	public void close(Connection connection) throws SQLException {
		if(connection != null) {
			connection.close();
		}
	}
	public void close(Statement statement) throws SQLException {
		if(statement != null) {
			statement.close();
		}
	}
	public void close(ResultSet resultSet) throws SQLException {
		if(resultSet != null) {
			resultSet.close();
		}
	}
	public void insertBook(Book book) {
		try(PreparedStatement ps = connection.prepareStatement("insert into book values (?,?,?,?,?,?)");){
			ps.setString(1, book.getIsbn());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getIsbn());
			ps.setInt(5, book.getPrice());
			ps.setString(6, book.getDescription());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateBook(Book book) {
		try(PreparedStatement ps = connection.prepareStatement("update book set title=? , Author=? , publisher=? , price=? , description=? where isbn =?");){
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getIsbn());
			ps.setInt(4, book.getPrice());
			ps.setString(5, book.getDescription());
			ps.setString(6, book.getIsbn());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteBook(String isbn) {
		try(PreparedStatement ps = connection.prepareStatement("delete from book where isbn =?");){
			ps.setString(1, isbn);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Book findBook(String isbn) {
		Book b = new Book();
		try(PreparedStatement ps = connection.prepareStatement("select * from book where isbn=?");){
			ps.setString(1, isbn); // 입력인자가 있을 때에는 resultset을 같이 try구문에 넣으면 에러발생
				try(ResultSet rs = ps.executeQuery();){
					while(rs.next()) {
						b.setAuthor(rs.getString("Author"));
						b.setDescription(rs.getString("Description"));
						b.setIsbn(rs.getString("isbn"));
						b.setPrice(rs.getInt("price"));
						b.setPublisher(rs.getString("publisher"));
						b.setTitle(rs.getString("title"));
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public List<Book> listBooks(){
		List<Book> list = new ArrayList<Book>();		
		try(PreparedStatement ps = connection.prepareStatement("select * from book");
				ResultSet rs = ps.executeQuery();){ // 입력인자가 없어서 에러가 안남
			while(rs.next()) {
				Book b = new Book();
				b.setAuthor(rs.getString("Author"));
				b.setDescription(rs.getString("Description"));
				b.setIsbn(rs.getString("isbn"));
				b.setPrice(rs.getInt("price"));
				b.setPublisher(rs.getString("publisher"));
				b.setTitle(rs.getString("title"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;		
	}
	public int count() {
		int count = 0;
		try(PreparedStatement ps= connection.prepareStatement("select count(*) from book");
				ResultSet rs = ps.executeQuery();){	
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
