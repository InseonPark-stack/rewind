package Employee;

import java.util.List;

public interface EmpMgr {
	//- add(Emplyee  b) : 파라메터로 전달된 직원정보를 DB에 저장한다.   
	public void add(Employee b);
	
	//- search() : 저장된 모든 직원 정보를 리턴한다
	public List<Employee> search();
	
	//   - search(int empNo) : 파라메터로 전달된 사번과 같은 직원 정보를 찾아서 
    // 리턴한다.    empNo이 존재하지 않을 시 null을 리턴한다.
	public Employee search(int empNo);
	
	//   - search(String name) : 파라메터로 전달된 글자를 이름에 포함한 
	//     모든 직원 정보를 찾아서 리턴한다.  
	public List<Employee> search(String name);
	
	/*  - update(int empNo,  String dept) :파라메터로 전달된 사번으로 직원을 
    	찾아 부서를 수정한다.
    	정상적으로 직원을 찾아 수정했을 경우 true,  
    	직원을 찾지 못했을 경우 false를 리턴한다.*/
	public boolean update(int empNo, String dept);
	
	/*   - delete(int empNo) : 파라메터의 사번과 같은 직원을 찾아 삭제한다.
     	직원 삭제시  배열의 값이 연속되도록(null값을 갖지 않도록) 처리한다.
     	정상적으로 직원을 찾아 삭제했을 경우 true,  
     	직원을 찾지 못했을 경우 false를 리턴한다. */
	public boolean delete(int empNo);
}
