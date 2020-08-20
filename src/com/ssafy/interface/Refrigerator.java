package com.ssafy;

public class Refrigerator extends Product{
	private int vol;
	
	public Refrigerator() {}
	public Refrigerator(int productNum, String productName, int price, int stock, int vol) {
		super(productNum,productName,price,stock);
		this.vol = vol;
	}	
	public void setVol(int vol) {
		this.vol = vol;
	}
	public int getVol() {
		return this.vol;
	}
	@Override
	public String toString() {
		return "[상품번호 : " + getProductNum() + ", 상품이름 : " + getProductName()
				+ ", 가격 : " + getPrice() + ", 재고 : " + getStock() + ", 용량 : " + getVol() + "]";
	}

	
}
