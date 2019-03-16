package com.wsj.eshop.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wsj.eshop.bean.CategorySecond;
import com.wsj.eshop.util.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport{
	/**
	 * 统计二级分类的个数
	 * 
	 * @return
	 */
	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 查询所有二级分类
	 * 
	 * @return
	 */
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 根据id查询二级分类
	 * 
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	/**
	 * 分页查询
	 * 
	 * @param begin开始位置
	 * @param limit每回查询的记录数
	 * @return
	 */
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<CategorySecond>(hql, null, begin,
						limit));
		return list;

	}

	// DAO中的保存二级分类的方法
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	// DAO中的删除二级分类的方法
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	// DAO中的修改二级分类的方法
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}
}
