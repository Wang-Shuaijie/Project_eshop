package com.wsj.eshop.service;

import java.util.List;

import com.wsj.eshop.bean.Order;
import com.wsj.eshop.bean.OrderItem;
import com.wsj.eshop.dao.OrderDao;
import com.wsj.eshop.util.PageBean;

public class OrderService {
	// ע��OrderDao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// ҵ��㱣�涩���ķ���
	public void save(Order order) {
		orderDao.save(order);
	}

	// ҵ����ѯ������ķ���
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}

	// ҵ�������û�id��ѯ����,����ҳ��ѯ.
	public PageBean<Order> findByUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		// ��ʾ5��
		int limit = 5;
		pageBean.setLimit(limit);
		// �����ܼ�¼��:
		int totalCount = 0;
		totalCount = orderDao.findCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ����ÿҳ��ʾ���ݼ���:
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findPageByUid(uid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	// ���ݶ���id��ѯ����
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	// ҵ����޸Ķ����ķ���:
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	// ҵ����ѯ���ж�������
	public PageBean<Order> findAll(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// ���ò���
		pageBean.setPage(page);
		// ����ÿҳ��ʾ�ļ�¼��:
		int limit = 10;
		pageBean.setLimit(limit);
		// �����ܼ�¼��
		int totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ����ÿҳ��ʾ���ݼ���
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
}
