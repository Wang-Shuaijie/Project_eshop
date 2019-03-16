package com.wsj.eshop.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wsj.eshop.bean.Order;
import com.wsj.eshop.bean.OrderItem;
import com.wsj.eshop.util.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport{
	/**
	 * 保存订单
	 * @param order
	 */
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}
	/**
	 * 修改订单
	 * @param currOrder
	 */
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}
	/**
	 * 根据订单id查询订单项
	 * @param oid
	 * @return
	 */
	public List<OrderItem> findOrderItem(Integer oid){
		String hql="from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list=this.getHibernateTemplate().find(hql, oid);
		if(list!=null&&list.size()>0) {
			return list;
		}
		return null;
	}
	/**
	 * 统计用户订单的个数
	 * @param uid
	 * @return
	 */
	public int findCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	
	/**
	 * 查询用户订单并分页
	 * @param uid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Order> findPageByUid(Integer uid, int begin, int limit) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { uid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	/**
	 * 根据订单id查询订单
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}
	/**
	 * 统计所有订单的个数
	 * @return
	 */
	public int findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	/**
	 * 查询所有订单并分页
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, null, begin, limit));
		return list;
	}
}
