package com.wsj.eshop.bean;

public class CartItem {
	private Product product;	// ����������Ʒ��Ϣ
	private int count;			// ����ĳ����Ʒ����
	private double subtotal;	// ����ĳ����ƷС��
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * �Զ�����۸�
	 * @return
	 */
	public double getSubtotal() {
		return count*product.getShop_price();
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
		
}
