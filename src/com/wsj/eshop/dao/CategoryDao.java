package com.wsj.eshop.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wsj.eshop.bean.Category;

public class CategoryDao extends HibernateDaoSupport{
	/**
	 * ��������һ������
	 * @return
	 */
	public List<Category> findAll(){
		String hql="from Category";
		List<Category> list=this.getHibernateTemplate().find(hql);
		return list;
	}
	/**
	 * ����һ������id����
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid){
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	/**
	 * ����һ������
	 * @param category
	 */
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	/**
	 * ɾ��һ������
	 * @param category
	 */
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}
	
	/**
	 * �޸�һ������
	 * @param category
	 */
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
}
