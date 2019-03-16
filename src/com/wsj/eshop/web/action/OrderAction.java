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
	//注入OrderService
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	// 接收page
	private int page;
	public int getPage() {
		return page;
	}
    public void setPage(int page) {
		this.page = page;
	}
    
    //接受支付通道编号
  	private String pd_FrpId;
	public String getPd_FrpId() {
		return pd_FrpId;
	}
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	} 
	
    
    //功能
	/**
	 * 生成订单
	 * @return
	 */
	public String saveOrder() {
		Cart cart=(Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null) {
			this.addActionMessage("你还没有购买商品!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		order.setState(1);//未付款
		order.setOrdertime(new Date());
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		order.setUser(user);
		if (user == null) {
			this.addActionMessage("你还没有登录!");
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
		// 清空购物车:
		cart.clearCart();

		// 页面需要回显订单信息:
		// 使用模型驱动了 所有可以不使用值栈保存了
		// ActionContext.getContext().getValueStack().set("order", order);
		return "saveOrder";	
	}
	
	/**
	 * 查询我的订单
	 * @return
	 */
	public String findByUid() {
		// 获得用户的id.
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		// 获得用户的id
		Integer uid = existUser.getUid();
		// 根据用户的id查询订单:
		PageBean<Order> pageBean = orderService.findByUid(uid, page);
		// 将PageBean数据带到页面上.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}

	/**
	 * 根据id查询订单
	 * @return
	 */
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOid";
	}
	
	
	public String payOrder() throws IOException {
		// 1.修改数据:
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		// 修改订单
		orderService.update(currOrder);
		// 2.完成付款:
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "xxxxxxxxx";// 商户编号:  易宝注册的商户
		String p2_Order = order.getOid().toString();// 订单编号:
		String p3_Amt = "0.00"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://192.168.216.1:8080/Project_eshop/order_callBack.action"; // 商户接收支付成功数据的地址:192.168.216.1本机ipv4
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "xxxxxxxxxxx"; // 易宝给的秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
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

		// 重定向:向易宝出发:
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}

	/**
	 * 付款成功后跳回路径
	 * @return
	 */
	public String callBack() {
		//获取返回的12个参数 ,也可以在action用get，set方法
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
		String keyValue = "xxxxxxxxxxxxx"; // 易宝给的秘钥
		//调用PaymentUtil的校验方法来校验调用者的身份
		boolean b=PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
		/*
		 * >如果校验失败：保存错误信息，转发到msg.jsp
		 * >如果校验通过：
		 * 判断访问的方法是重定向还是点对点，如果要是重定向
		 * 修改订单状态，保存成功信息，转发到msg.jsp
		 * 如果是点对点：修改订单状态，返回success
		 */
		if(!b) {
			this.addActionError("无效签名，支付失败");
			return "msg";
		}
		// 修改订单的状态:
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// 修改订单状态为2:已经付款:
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("支付成功!订单编号为: " + r6_Order + " 付款金额为: " + r3_Amt);
		return "msg";
	}

	
	/**
	 * 结束订单
	 * @return
	 */
	public String updateState() {
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
