package com.ssafy;

public class Tv extends Product{
	private int inch;
	
	public Tv() {}
	public Tv(int productNum, String productName, int price, int stock, int inch) {
		super(productNum,productName,price,stock);
		this.inch = inch;
	}	
	public void setInch(int inch) {
		this.inch = inch;
	}
	public int getInch() {
		return this.inch;
	}
	@Override
	public String toString() {
		return "[상품번호 : " + getProductNum() + ", 상품이름 : " + getProductName() + ", 가격 : "
				+ getPrice() + ", 재고 : " + getStock() + ", 인치 : " + getInch() +"]";
	}

}
