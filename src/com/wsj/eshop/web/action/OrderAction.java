package com.wsj.eshop.web.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wsj.eshop.bean.Cart;
import com.wsj.eshop.bean.CartItem;
import com.wsj.eshop.bean.Order;
import com.wsj.eshop.bean.OrderItem;
import com.wsj.eshop.bean.User;
import com.wsj.eshop.service.OrderService;
import com.wsj.eshop.util.PageBean;
import com.wsj.eshop.util.PaymentUtil;

public class OrderAction extends ActionSupport implements ModelDriven<Order>{
    private Order order=new Order();
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	//ע��OrderService
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	// ����page
	private int page;
	public int getPage() {
		return page;
	}
    public void setPage(int page) {
		this.page = page;
	}
    
    //����֧��ͨ�����
  	private String pd_FrpId;
	public String getPd_FrpId() {
		return pd_FrpId;
	}
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	} 
	
    
    //����
	/**
	 * ���ɶ���
	 * @return
	 */
	public String saveOrder() {
		Cart cart=(Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null) {
			this.addActionMessage("�㻹û�й�����Ʒ!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		order.setState(1);//δ����
		order.setOrdertime(new Date());
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		order.setUser(user);
		if (user == null) {
			this.addActionMessage("�㻹û�е�¼!");
			return "msg";
		}
		for(CartItem cartItem:cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		orderService.save(order);
		// ��չ��ﳵ:
		cart.clearCart();

		// ҳ����Ҫ���Զ�����Ϣ:
		// ʹ��ģ�������� ���п��Բ�ʹ��ֵջ������
		// ActionContext.getContext().getValueStack().set("order", order);
		return "saveOrder";	
	}
	
	/**
	 * ��ѯ�ҵĶ���
	 * @return
	 */
	public String findByUid() {
		// ����û���id.
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		// ����û���id
		Integer uid = existUser.getUid();
		// �����û���id��ѯ����:
		PageBean<Order> pageBean = orderService.findByUid(uid, page);
		// ��PageBean���ݴ���ҳ����.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}

	/**
	 * ����id��ѯ����
	 * @return
	 */
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOid";
	}
	
	
	public String payOrder() throws IOException {
		// 1.�޸�����:
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		// �޸Ķ���
		orderService.update(currOrder);
		// 2.��ɸ���:
		// ������Ҫ�Ĳ���:
		String p0_Cmd = "Buy"; // ҵ������:
		String p1_MerId = "xxxxxxxxx";// �̻����:  �ױ�ע����̻�
		String p2_Order = order.getOid().toString();// �������:
		String p3_Amt = "0.00"; // ������:
		String p4_Cur = "CNY"; // ���ױ���:
		String p5_Pid = ""; // ��Ʒ����:
		String p6_Pcat = ""; // ��Ʒ����:
		String p7_Pdesc = ""; // ��Ʒ����:
		String p8_Url = "http://192.168.216.1:8080/Project_eshop/order_callBack.action"; // �̻�����֧���ɹ����ݵĵ�ַ:192.168.216.1����ipv4
		String p9_SAF = ""; // �ͻ���ַ:
		String pa_MP = ""; // �̻���չ��Ϣ:
		String pd_FrpId = this.pd_FrpId;// ֧��ͨ������:
		String pr_NeedResponse = "1"; // Ӧ�����:
		String keyValue = "xxxxxxxxxxx"; // �ױ�������Կ
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// ���ױ���������:
		StringBuffer sb = new StringBuffer(
				"https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

		// �ض���:���ױ�����:
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}

	/**
	 * ����ɹ�������·��
	 * @return
	 */
	public String callBack() {
		//��ȡ���ص�12������ ,Ҳ������action��get��set����
		HttpServletRequest req=ServletActionContext.getRequest();
		String p1_MerId = req.getParameter("p1_MerId");
		String r0_Cmd = req.getParameter("r0_Cmd");
		String r1_Code = req.getParameter("r1_Code");
		String r2_TrxId = req.getParameter("r2_TrxId");
		String r3_Amt = req.getParameter("r3_Amt");
		String r4_Cur = req.getParameter("r4_Cur");
		String r5_Pid = req.getParameter("r5_Pid");
		String r6_Order = req.getParameter("r6_Order");
		String r7_Uid = req.getParameter("r7_Uid");
		String r8_MP = req.getParameter("r8_MP");
		String r9_BType = req.getParameter("r9_BType");
		String hmac = req.getParameter("hmac");
		String keyValue = "xxxxxxxxxxxxx"; // �ױ�������Կ
		//����PaymentUtil��У�鷽����У������ߵ����
		boolean b=PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
		/*
		 * >���У��ʧ�ܣ����������Ϣ��ת����msg.jsp
		 * >���У��ͨ����
		 * �жϷ��ʵķ������ض����ǵ�Ե㣬���Ҫ���ض���
		 * �޸Ķ���״̬������ɹ���Ϣ��ת����msg.jsp
		 * ����ǵ�Ե㣺�޸Ķ���״̬������success
		 */
		if(!b) {
			this.addActionError("��Чǩ����֧��ʧ��");
			return "msg";
		}
		// �޸Ķ�����״̬:
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// �޸Ķ���״̬Ϊ2:�Ѿ�����:
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("֧���ɹ�!�������Ϊ: " + r6_Order + " ������Ϊ: " + r3_Amt);
		return "msg";
	}

	
	/**
	 * ��������
	 * @return
	 */
	public String updateState() {
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
