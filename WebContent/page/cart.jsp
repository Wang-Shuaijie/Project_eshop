<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>cart</title>
<link href="css/common.css" rel="stylesheet" type="text/css"/>
<link href="css/cart.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
    <div class="container cart">
		<s:if test="#session.cart.cartItems.size() != 0">
		<div class="span24">
			<div class="step step1">
				购物车信息
			</div>
				<table>
					<tbody>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>
						<s:iterator var="cartItem" value="#session.cart.cartItems">
						<tr>
							<td width="60">
								<img src="<s:property value="#cartItem.product.image"/>">
							</td>
							<td>
								<a target="_blank"><s:property value="#cartItem.product.pname"/></a>
							</td>
							<td>
								￥<s:property value="#cartItem.product.shop_price"/>
							</td>
							<td class="quantity" width="60">
								<s:property value="#cartItem.count"/>
							</td>
							<td width="140">
								<span class="subtotal">￥<s:property value="#cartItem.subtotal"/></span>
							</td>
							<td>
								<a href="cart_removeCart.action?pid=<s:property value="#cartItem.product.pid"/>" class="delete">删除</a>
							</td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em>
							<em>
								登录后确认是否享有优惠
							</em>
					赠送积分: <em id="effectivePoint"><s:property value="#session.cart.total"/></em>
					商品金额: <strong id="effectivePrice">￥<s:property value="#session.cart.total"/>元</strong>
				</div>
				<div class="bottom">
					<a href="cart_clearCart.action" id="clear" class="clear">清空购物车</a>
					<a href="order_saveOrder.action" id="submit" class="submit">提交订单</a>
				</div>
				
		</div>
		</s:if>
		<s:else>
			<div class="span24">
				<div class="step step1">
					<span><h2>亲!您还没有购物!请先去购物!</h2></span>
				</div>
			</div>
		</s:else>
	</div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>