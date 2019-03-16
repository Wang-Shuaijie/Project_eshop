package com.wsj.eshop.service;

import java.util.List;

import com.wsj.eshop.bean.Category;
import com.wsj.eshop.dao.CategoryDao;

public class CategoryService {
	// ע��dao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * ҵ������һ������
	 * 
	 * @return
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	/**
	 * ҵ������һ������id����
	 * 
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}

	/**
	 * ҵ��㱣��һ������
	 * 
	 * @param category
	 */
	public void save(Category category) {
		categoryDao.save(category);
	}

	/**
	 * ҵ���ɾ��һ������
	 * 
	 * @param category
	 */
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	/**
	 * ҵ����޸�һ������
	 * 
	 * @param category
	 */
	public void update(Category category) {
		categoryDao.update(category);
	}
}
