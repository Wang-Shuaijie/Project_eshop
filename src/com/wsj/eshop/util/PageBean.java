package com.wsj.eshop.util;

import java.util.List;

/**
 * 分页类
 * @author WangShuaiJie
 *
 */
public class PageBean<T> {
	private int page;//当前页
	private int totalCount;//总记录数
	private int totalPage;//总页数
	private int limit;//每页记录数
	private List<T> list;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totlaPage) {
		this.totalPage = totlaPage;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
