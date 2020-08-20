package com.ssafy;

public interface IProductMgr {
	public void tvAdd(Tv p); // tv 검색
	
	public void refrAdd(Refrigerator p); // 냉장고 검색
	
	public void list(); // 상품정보 전체를 검색하는 기능
		
	public void tvList(); // tv정보만 검색	

	public void refrList(); // 냉장고정보만 검색
			
	public void list(int num); // 상품번호로 검색
		
	public void list(String name); // 상품명으로 검색
			
	public void delete(int num); // 상품번호로 삭제
			
	public void stock(); // 전체 재고 가격 확인
}
