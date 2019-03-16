package com.wsj.eshop.web.action.admin;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wsj.eshop.bean.Order;
import com.wsj.eshop.bean.OrderItem;
import com.wsj.eshop.service.OrderService;
import com.wsj.eshop.util.PageBean;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {
	// ģ������ʹ�õ���
	private Order order = new Order();

	public Order getModel() {
		return order;
	}

	// ����page����
	private int page;
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	// ע��OrderService
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * ��ѯ���ж���
	 * @return
	 */
	public String findAll() {
		// �����ķ�ҳ��ѯ
		PageBean<Order> pageBean = orderService.findAll(page);
		// �����ݴ��뵽ֵջ�б��浽ҳ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// ҳ����ת:
		return "findAll";
	}

	/**
	 * �޸Ķ���״̬
	 * @return
	 */
	public String updateState() {
		// ����id��ѯ����
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		// ҳ����ת
		return "updateStateSuccess";
	}

	/**
	 * ���ݶ���id��ѯ������
	 * @return
	 */
	public String findOrderItem() {
		// ���ݶ���id��ѯ������:
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		// ��ʾ��ҳ��:
		ActionContext.getContext().getValueStack().set("list", list);
		// ҳ����ת
		return "findOrderItem";
	}
}
