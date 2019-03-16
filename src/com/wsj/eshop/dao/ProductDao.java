package com.wsj.eshop.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wsj.eshop.bean.Product;
import com.wsj.eshop.util.PageHibernateCallback;

/**
 * ��Ʒ���Ʋ�
 * 
 * @author WangShuaiJie
 *
 */
public class ProductDao extends HibernateDaoSupport {
	/**
	 * ��ҳ������Ʒ��ѯ
	 * 
	 * @return
	 */
	public List<Product> findHot() {
		// ʹ������������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// ��ѯ���ŵ���Ʒ,��������is_host = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		// �����ڵ����������:
		criteria.addOrder(Order.desc("pdate"));

		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	/**
	 * ��ҳ������Ʒ��ѯ
	 * 
	 * @return
	 */
	public List<Product> findNew() {
		// ʹ������������ѯ:
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// �����ڽ��е�������:
		criteria.addOrder(Order.desc("pdate"));
		// ִ�в�ѯ:
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	// ������ƷID��ѯ��Ʒ
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	// ����һ������id��ѯ��Ʒ�ĸ���
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * ����һ������id��ѯ��Ʒ���ϣ�����ҳ
	 * 
	 * @param cid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// select p.* from Category c,CategorySecond cs,Product p where c.cid =
		// cs.category.cid and cs.csid = p.categorySecond.csid and c.cid = ?
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		// ��ҳ��һ��д��:
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Product>(hql, new Object[] { cid }, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;

	}

	/**
	 * ���ݶ�������id��ѯ��Ʒ����
	 * 
	 * @param csid
	 * @return
	 */
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * ���ݶ�������id��ѯ��Ʒ���ϣ�����ҳ
	 * 
	 * @param csid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Product>(hql, new Object[] { csid }, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * ��̨ͳ����Ʒ�����ķ���
	 * 
	 * @return
	 */
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * ��̨��ѯ������Ʒ����ҳ
	 * 
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// DAO�еı�����Ʒ�ķ���
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	// DAO�е�ɾ����Ʒ�ķ���
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}
}
