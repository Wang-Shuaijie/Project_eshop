<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
    %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>orderlist</title>

<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/cart.css" rel="stylesheet" type="text/css" />
<link href="css/product.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="container cart">

		<div class="span24">

			<div class="step step1">
				<ul>

					<li class="current"></li>
					<li>我的订单</li>
				</ul>
			</div>


			<table>
				<tbody>
					<s:iterator var="order" value="pageBean.list">
						<tr>
							<th colspan="5">订单编号:<s:property value="#order.oid" />&nbsp;&nbsp;&nbsp;&nbsp;订单金额:<font
								color="red"><s:property value="#order.total" /> </font>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="red"> 
								<s:if test="#order.state == 1">
										<a href="order_findByOid.action?oid=<s:property value="#order.oid" />">付款</a>
								</s:if> 
								<s:if test="#order.state == 2">
									已付款
								</s:if> 
								<s:if test="#order.state == 3">
										<a href="order_updateState.action?oid=<s:property value="#order.oid" />">确认收货</a>
								</s:if> 
								<s:if test="#order.state == 4">
									交易结束
								</s:if> 
								</font>
							</th>
						</tr>
						<tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<s:iterator value="#order.orderItems" var="orderItem">
							<tr>
								<td width="60">
								    <img src="<s:property value="#orderItem.product.image"/>" />
								</td>
								<td><s:property value="#orderItem.product.pname" />
								</td>
								<td><s:property value="#orderItem.product.shop_price" />
								</td>
								<td class="quantity" width="60"><s:property
										value="#orderItem.count" />
								</td>
								<td width="140"><span class="subtotal">￥<s:property
											value="#orderItem.subtotal" /> </span>
								</td>
							</tr>
						</s:iterator>
					</s:iterator>
					<tr>
						<th colspan="5">
							<div class="pagination">
								<span>第<s:property value="pageBean.page" />/<s:property
										value="pageBean.totalPage" />页
								</span>
								<s:if test="pageBean.page != 1">
									<a href="order_findByUid.action?page=1" class="firstPage">&nbsp;</a>
									<a
										href="order_findByUid.action?page=<s:property value="pageBean.page-1"/>"
										class="previousPage">&nbsp;</a>
								</s:if>
								<s:iterator var="i" begin="1" end="pageBean.totalPage">
									<s:if test="pageBean.page != #i">
										<a href="order_findByUid.action?page=<s:property value="#i"/>"><s:property
												value="#i" /> </a>
									</s:if>
									<s:else>
										<span class="currentPage"><s:property value="#i" /> </span>
									</s:else>
								</s:iterator>
								<s:if test="pageBean.page != pageBean.totalPage">
									<a class="nextPage"
										href="order_findByUid.action?page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
									<a class="lastPage"
										href="order_findByUid.action?page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
								</s:if>
							</div>
						</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>