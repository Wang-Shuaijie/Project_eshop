package com.wsj.eshop.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 一级类型实体类
 * @author WangShuaiJie
 *
 */
public class Category implements Serializable{
	
	/**
	 * 由于类型在项目运行时保存在session中显示，所以需要序列化
	 */
	private static final long serialVersionUID = 1L;
	private Integer cid;
	private String cname;
	private Set<CategorySecond> categorySeconds=new HashSet<>();
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	
}
