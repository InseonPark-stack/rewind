package Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpMgrImpl implements EmpMgr{
	private static final String DB_NAME = "ssafydb";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/"+DB_NAME+"?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private static final String DB_USER = "insun";
	private static final String DB_PASSWORD = "as12as12";
	
	Connection connection = null;
	
	public EmpMgrImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private Connection getConnection() {
		// 2. Connection 
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;		
	}
	
	public void close() throws SQLException {
		if(connection != null) {
			connection.close();
		}
	}
	
	/** 파라메터로 전달된 직원정보를 DB에 저장한다. */
	@Override
	public void add(Employee b) {
		String sql = "insert into employee VALUES (?,?,?,?)";
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, b.getEmpNo());
			ps.setString(2, b.getName());
			ps.setString(3, b.getPosition());
			ps.setString(4, b.getDept());
			if(ps.executeUpdate() == 1) {
				System.out.println("정상적으로 입력되었습니다.");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** 저장된 모든 직원 정보를 리턴한다 */
	@Override
	public List<Employee> search() {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		String sql = "Select * from employee";
		try(PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();){
			while(rs.next()) {
				employeeList.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return employeeList;	
	}

	/** 파라메터로 전달된 사번과 같은 직원 정보를 찾아서 리턴한다.   
	 *  empNo이 존재하지 않을 시 null을 리턴한다.*/
	@Override
	public Employee search(int empNo) {
		Employee b = new Employee();
		String sql = "Select * from employee where empNo = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql);){
				ps.setInt(1, empNo);
				try(ResultSet rs = ps.executeQuery();){				
					if(rs.next()) {
						b.setEmpNo(rs.getInt(1));
						b.setName(rs.getString(2));
						b.setPosition(rs.getString(3));
						b.setDept(rs.getString(4));
					} else {
						return null;
					}
				}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	/** 파라메터로 전달된 글자를 이름에 포함한 
	  * 모든 직원 정보를 찾아서 리턴한다.  */
	@Override
	public List<Employee> search(String name) {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		String sql = "Select * from employee where name like \"%" + name + "%\"";
		try(PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();){				
				while(rs.next()) {
					employeeList.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
				}				
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	/** 파라메터로 전달된 사번으로 직원을 
    	찾아 부서를 수정한다.
    	정상적으로 직원을 찾아 수정했을 경우 true,  
    	직원을 찾지 못했을 경우 false를 리턴한다. */
	@Override
	public boolean update(int empNo, String dept) {
		boolean check = false;
		String sql = "update employee set dept = ? where empNo = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, dept);
			ps.setInt(2, empNo);
			if(ps.executeUpdate() == 1) {
				check = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	/** 파라메터의 사번과 같은 직원을 찾아 삭제한다.
     	직원 삭제시  배열의 값이 연속되도록(null값을 갖지 않도록) 처리한다.
     	정상적으로 직원을 찾아 삭제했을 경우 true,  
     	직원을 찾지 못했을 경우 false를 리턴한다. */
	@Override
	public boolean delete(int empNo) {
		boolean check = false;
		String sql = "delete from employee where empNo = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, empNo);
			if(ps.executeUpdate() == 1) {
				check = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

}
