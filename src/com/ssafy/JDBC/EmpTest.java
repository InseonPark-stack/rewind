package Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmpTest {	
	private static EmpMgrImpl emi;
	private static Scanner sc = new Scanner(System.in); 
	
	public static void main(String[] args) {
		emi = new EmpMgrImpl(); // 생성자를 통해 connection
		
		while(true) {			
			print();
			int menu = Integer.parseInt(sc.nextLine());
			if (menu == 0) { // 종료
				try {
					emi.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} // 종료할 때 연결 끊기
				System.out.println("종료되었습니다.");				
				break;
			}			
			switch (menu) {
			case 1: // 정보 저장
				input();
				break;
			case 2: // 전체 직원 검색
				searchAll();
				break;
			case 3: // 직원 번호 검색
				searchNo();
				break;	
			case 4: // 직원 이름 검색 
				searchName();
				break;
			case 5: // 수정
				update();
				break;
			case 6: // 삭제
				delete();
				break;
			default:
				System.out.println("잘못입력하셨습니다.");
				break;
			}
		}// end of while		
	}// end main
	/** 정보 저장 */
	public static void input() {
		System.out.print("직원번호 : ");
		int empNo = Integer.parseInt(sc.nextLine());
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("직위 : ");
		String position = sc.nextLine();
		System.out.print("부서 : ");
		String dept = sc.nextLine();
		emi.add(new Employee(empNo, name, position, dept));
	}
	/** 전체 직원 검색 */
	public static void searchAll() {
		List<Employee> empList = emi.search();
		for (Employee e : empList) {
			System.out.println(e);
		}
	}
	/** 직원 번호 검색 */
	public static void searchNo() {
		System.out.print("직원번호 : ");
		int empNo = Integer.parseInt(sc.nextLine());
		Employee e = emi.search(empNo);
		System.out.println(e);
	}
	/** 직원 이름 검색 */
	public static void searchName() {
		System.out.print("이름 : ");
		String name = sc.nextLine();
		List<Employee> empList = emi.search(name);
		for (Employee e : empList) {
			System.out.println(e);
		}
	}
	/** 수정 */
	public static void update() {
		System.out.print("직원번호 : ");
		int empNo = Integer.parseInt(sc.nextLine());
		System.out.print("수정할 부서 : ");
		String dept = sc.nextLine();
		if(emi.update(empNo,dept)) {
			System.out.println("수정 완료되었습니다.");
		} else {
			System.out.println("수정 미완료 "); // 예외처리에서 오류가 출력되겠지만
		}		
	}

	/** 삭제 */
	public static void delete() {
		System.out.print("직원번호 : ");
		int empNo = Integer.parseInt(sc.nextLine());
		if(emi.delete(empNo)) {
			System.out.println("삭제 완료되었습니다.");
		} else {
			System.out.println("삭제 미완료 "); // 예외처리에서 오류가 출력되겠지만
		}
	}

	/** 기본 출력 문*/
	public static void print() {
		System.out.println("<<< 직원 관리 프로그램 >>>");		
		System.out.print("1. 사원 정보 저장 / ");
		System.out.print("2. 사원 전체 정보 검색 / ");
		System.out.print("3. 사번으로 검색 / ");
		System.out.print("4. 이름으로 검색 / ");
		System.out.print("5. 사원정보 수정 / ");		
		System.out.println("6. 사원정보 삭제 / ");		
		System.out.println("0. 종료");
		System.out.println("원하는 번호를 선택하세요.");	
	}
}
	
	
