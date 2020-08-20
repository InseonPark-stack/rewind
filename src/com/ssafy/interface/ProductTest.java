package com.ssafy;

import java.util.Scanner;

public class ProductTest {
	private static Scanner sc;
	private static ProductMgrlmpl pm;

	public static void main(String[] args) {
		
		pm = new ProductMgrlmpl();
		sc = new Scanner(System.in);
		
		pm.tvAdd(new Tv(1111,"1번상품",10,10,55));
		pm.tvAdd(new Tv(2222,"2번상품",9,9,40));
		pm.refrAdd(new Refrigerator(3333,"3번상품",8,8,400));
		pm.refrAdd(new Refrigerator(4444,"4번상품",7,7,500));
		pm.refrAdd(new Refrigerator(5555,"5번상품",6,6,200));
		
		while(true) {			
			print();
			int menu = Integer.parseInt(sc.nextLine());
			if (menu == 0) { // 종료
				System.out.println("종료되었습니다.");
				break;
			}
			switch (menu) {
			case 1: // tv 저장
				tvInput();
				break;
			case 2: // 냉장고 저장
				refrInput();
				break;
			case 3: // 저장된 상품 볼수 있는 기능
				searchAll();
				break;
			case 4: // 번호로 검색하는 기능
				searchNum();
				break;
			case 5: // 이름으로 검색
				searchName();
				break;
			case 6: // TV 상품
				tvSearch();
				break;
			case 7: // 냉장고 상품
				refrSearch();
				break;
			case 8: // 번호로 삭제하는 기능
				deleteNum();
				break;
			case 9: // 재고 상품 금액
				priceList();
				break;
			case 10: // 400리터 이상 냉장고
				search_400();
				break;
			case 11: // 50인치 이상 TV
				search_50();
				break;
			case 12: // 가격 변경
				update();
				break;
			default:
				break;
			}
			
			
		} // end of while
	} // end of main
	private static void update() {
		System.out.print("상품번호 : ");
		int num = Integer.parseInt(sc.nextLine());
		System.out.print("변경할 가격 : ");
		int price = Integer.parseInt(sc.nextLine());
		pm.update(num, price);
	}
	private static void search_50() {
		pm.search_50();
	}
	private static void search_400() {
		pm.search_400();
	}
	private static void tvInput() {
		System.out.print("상품번호 : ");
		int productNum = Integer.parseInt(sc.nextLine());
		System.out.print("상품이름 : ");
		String productName = sc.nextLine();
		System.out.print("가격 : ");
		int price = Integer.parseInt(sc.nextLine());
		System.out.print("수량 : ");
		int stock = Integer.parseInt(sc.nextLine());	
		System.out.print("인치 : ");
		int inch = Integer.parseInt(sc.nextLine());
		pm.tvAdd(new Tv(productNum, productName, price, stock, inch));	
	}
	/** 냉장고저장 */
	private static void refrInput() {
		System.out.print("상품번호 : ");
		int productNum = Integer.parseInt(sc.nextLine());
		System.out.print("상품이름 : ");
		String productName = sc.nextLine();
		System.out.print("가격 : ");
		int price = Integer.parseInt(sc.nextLine());
		System.out.print("수량 : ");
		int stock = Integer.parseInt(sc.nextLine());		
		System.out.print("용량 : ");
		int vol = Integer.parseInt(sc.nextLine());
		pm.tvAdd(new Tv(productNum, productName, price, stock, vol));		
	}
	/** 상품이름검색 */
	private static void searchName() {
		System.out.print("검색할 상품이름 : ");
		String name = sc.nextLine();
		pm.list(name);
	}
	/** TV상품 출력 */
	private static void tvSearch() {
		pm.tvList();
	}
	/** 냉장고 상품 출력 */
	private static void refrSearch() {
		pm.refrList();
	}
	/** 특정가격 이하의 상품만 검색하는 기능 */
	private static void priceList() {		
		pm.stock();
	}
	/** 번호로삭제 */
	private static void deleteNum() {
		System.out.print("삭제할 상품번호 : ");
		int productNum = Integer.parseInt(sc.nextLine());
		pm.delete(productNum);
	}
	/** 상품 번호 검색 */
	private static void searchNum() {
		System.out.print("검색할 상품번호 : ");
		int productNum = Integer.parseInt(sc.nextLine());
		pm.list(productNum);
	}
	/** 상품 정보 검색 */
	private static void searchAll() {
		pm.list();
	}
	/** 메뉴 출력 */
	public static void print() {
		System.out.println("<<< 상품 관리 프로그램 >>>");		
		System.out.println("1. TV저장");
		System.out.println("2. 냉장고저장");
		System.out.println("3. 상품목록");
		System.out.println("4. 상품번호로 검색");
		System.out.println("5. 상품이름로 검색");		
		System.out.println("6. TV목록");		
		System.out.println("7. 냉장고목록");		
		System.out.println("8. 상품번호로 삭제");
		System.out.println("9. 전체 재고 상품 금액");
		System.out.println("10. 400L이상 냉장고 검색");
		System.out.println("11. 50inch이상 TV 검색");
		System.out.println("12. 가격 수정");
		System.out.println("0. 종료");
		System.out.println("원하는 번호를 선택하세요.");
		
	}
}
