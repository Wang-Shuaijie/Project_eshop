package com.wsj.eshop.service;

import java.util.List;

import com.wsj.eshop.bean.Category;
import com.wsj.eshop.dao.CategoryDao;

public class CategoryService {
	// 注入dao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * 业务层查找一级分类
	 * 
	 * @return
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	/**
	 * 业务层根据一级分类id查找
	 * 
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}

	/**
	 * 业务层保存一级分类
	 * 
	 * @param category
	 */
	public void save(Category category) {
		categoryDao.save(category);
	}

	/**
	 * 业务层删除一级分类
	 * 
	 * @param category
	 */
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	/**
	 * 业务层修改一级分类
	 * 
	 * @param category
	 */
	public void update(Category category) {
		categoryDao.update(category);
	}
}
