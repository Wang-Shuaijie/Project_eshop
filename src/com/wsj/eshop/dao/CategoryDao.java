package com.wsj.eshop.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wsj.eshop.bean.Category;

public class CategoryDao extends HibernateDaoSupport{
	/**
	 * 查找所有一级分类
	 * @return
	 */
	public List<Category> findAll(){
		String hql="from Category";
		List<Category> list=this.getHibernateTemplate().find(hql);
		return list;
	}
	/**
	 * 根据一级分类id查找
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid){
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	/**
	 * 保存一级分类
	 * @param category
	 */
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	/**
	 * 删除一级分类
	 * @param category
	 */
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}
	
	/**
	 * 修改一级分类
	 * @param category
	 */
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
}
