package com.wsj.eshop.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CategorySecond implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer csid;
	private String csname;
	
	// 所属一级分类.存的是一级分类的对象
	private Category category;
	// 配置商品集合
	private Set<Product> products=new HashSet<>();

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
}
