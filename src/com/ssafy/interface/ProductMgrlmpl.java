package com.ssafy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductMgrlmpl implements IProductMgr{	
	private List<Tv> TvList = new ArrayList<Tv>();
	private List<Refrigerator> RefrigeratorList = new ArrayList<Refrigerator>();;
	
	public void tvAdd(Tv p) { // 티비정보 저장
		TvList.add(p);	
	}
	
	public void refrAdd(Refrigerator p) { // 냉장고정보 저장
		RefrigeratorList.add(p);
	}
	
	public void list() { // 상품정보 전체를 검색하는 기능
		Iterator<Tv> it = TvList.iterator();		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		Iterator<Refrigerator> rfit = RefrigeratorList.iterator();		
		while(rfit.hasNext()) {
			System.out.println(rfit.next());
		}	
	}
	public void tvList() { // tv정보만 검색
		Iterator<Tv> it = TvList.iterator();		
		while(it.hasNext()) {
			System.out.println(it.next());
		}	
	}
	public void refrList() { // 냉장고정보만 검색
		Iterator<Refrigerator> it = RefrigeratorList.iterator();		
		while(it.hasNext()) {
			System.out.println(it.next());
		}		
	}
	
	public void list(int num) { // 상품번호로 검색
		Iterator<Tv> it = TvList.iterator();		
		while(it.hasNext()) {
			Tv temp = it.next();
			if(temp.getProductNum() == num)
				System.out.println(temp);
		}
		Iterator<Refrigerator> rfit = RefrigeratorList.iterator();		
		while(rfit.hasNext()) {
			Refrigerator temp = rfit.next();
			if(temp.getProductNum() == num)
				System.out.println(temp);
		}	
	}
	public void list(String name) { // 상품명으로 검색
		Iterator<Tv> it = TvList.iterator();		
		while(it.hasNext()) {
			Tv temp = it.next();
			if(temp.getProductName().contains(name))
				System.out.println(temp);
		}
		Iterator<Refrigerator> rfit = RefrigeratorList.iterator();		
		while(rfit.hasNext()) {
			Refrigerator temp = rfit.next();
			if(temp.getProductName().contains(name))
				System.out.println(temp);
		}	
	}
	
	public void delete(int num) { // 상품번호로 삭제
		Iterator<Tv> tvit = TvList.iterator();		
		while(tvit.hasNext()) {
			if(tvit.next().getProductNum() == num)
				tvit.remove();
		}	
		Iterator<Refrigerator> refrit = RefrigeratorList.iterator();		
		while(refrit.hasNext()) {
			if(refrit.next().getProductNum() == num)
				refrit.remove();
		}	
	}
	
	public void stock() { // 전체 재고 상품 금액을 구하는 기능
		int sum = 0;
		Iterator<Tv> it = TvList.iterator();		
		while(it.hasNext()) {
			Tv temp = it.next();
			sum += temp.getPrice() * temp.getStock();
		}
		Iterator<Refrigerator> rfit = RefrigeratorList.iterator();		
		while(rfit.hasNext()) {
			Refrigerator temp = rfit.next();
			sum += temp.getPrice() * temp.getStock();
		}	
		System.out.println(sum + "원");
	}
	
	public void search_400() { // 400L 이상의 냉장고 검색
		Iterator<Refrigerator> it = RefrigeratorList.iterator();		
		while(it.hasNext()) {
			Refrigerator temp = it.next();
			if(temp.getVol() >= 400) {
				System.out.println(temp);
			}
		}
	}
	public void search_50() { // 50인치 이상 TV검색
		Iterator<Tv> it = TvList.iterator();		
		while(it.hasNext()) {
			Tv temp = it.next();
			if(temp.getInch() >= 50) {
				System.out.println(temp);
			}
		}
	}
	
	public void update(int num, int price) { // 번호와 가격을 입력받아 가격을 변경
		for (Tv t : TvList) {
			if(t.getProductNum() == num) {
				t.setPrice(price);
			}			
		}
		for (Refrigerator rf : RefrigeratorList) {
			if(rf.getProductNum() == num) {
				rf.setPrice(price);
			}
		}
	}
}
