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
<title>order</title>

<link href="css/common.css" rel="stylesheet" type="text/css"/>
<link href="css/cart.css" rel="stylesheet" type="text/css"/>
<script src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
$(function () {
	$("#link").click(function () {
		var addr=$("input[name='addr']").val();
	    var name=$("input[name='name']").val();
	    var phone=$("input[name='phone']").val();
		if(addr!=null&&addr!=""&&name!=null&&name!=""&&phone!=null&&phone!=""){
		   $("#orderForm").submit();
		}else{
		  alert("收货信息不能为空");
		}
	});
});

</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="container cart">

		<div class="span24">

			<div class="step step1">
				<ul>

					<li class="current"></li>
					<li>生成订单成功</li>
				</ul>
			</div>


			<table>
				<tbody>
					<tr>
						<th colspan="5">订单编号:<s:property value="model.oid" />&nbsp;&nbsp;&nbsp;&nbsp;
						</th>
					</tr>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
					<s:iterator value="model.orderItems" var="orderItem">
						<tr>
							<td width="60"><img
								src="<s:property value="#orderItem.product.image"/>" /></td>
							<td><s:property value="#orderItem.product.pname" /></td>
							<td><s:property value="#orderItem.product.shop_price" /></td>
							<td class="quantity" width="60"><s:property
									value="#orderItem.count" /></td>
							<td width="140"><span class="subtotal">￥<s:property
										value="#orderItem.subtotal" />
							</span></td>

						</tr>
					</s:iterator>
				</tbody>
			</table>
			<dl id="giftItems" class="hidden" style="display: none;">
			</dl>
			<div class="total">
				<em id="promotion"></em> 商品金额: <strong id="effectivePrice">￥<s:property
						value="model.total" />元
				</strong>
			</div>
			<form id="orderForm" action="order_payOrder.action" method="post">
				<input type="hidden" name="oid"
					value="<s:property value="model.oid"/>" />
				<div class="span24">
					<p>
						收货地址：<input name="addr" type="text"
							value="<s:property value="model.user.addr"/>"
							style="width: 350px" /> <br /> 收货人&nbsp;&nbsp;&nbsp;：<input
							name="name" type="text"
							value="<s:property value="model.user.name"/>"
							style="width: 150px" /> <br /> 联系方式：<input name="phone"
							type="text" value="<s:property value="model.user.phone"/>"
							style="width: 150px" />

					</p>
					<hr />
					<p>
						选择银行：<br /> <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"
							checked="checked" /> 工商银行 <img src="bank_img/icbc.bmp"
							align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="BOC-NET-B2C" />中国银行
						<img src="bank_img/bc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="pd_FrpId"
							value="ABC-NET-B2C" />农业银行 <img src="bank_img/abc.bmp"
							align="middle" /> <br /> <input type="radio" name="pd_FrpId"
							value="BOCO-NET-B2C" />交通银行 <img src="bank_img/bcc.bmp"
							align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="PINGANBANK-NET" />平安银行 <img src="bank_img/pingan.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="pd_FrpId"
							value="CCB-NET-B2C" />建设银行 <img src="bank_img/ccb.bmp"
							align="middle" /> <br /> <input type="radio" name="pd_FrpId"
							value="CEB-NET-B2C" />光大银行 <img
							src="${ pageContext.request.contextPath }/bank_img/guangda.bmp"
							align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C" />招商银行 <img src="bank_img/cmb.bmp" align="middle" />
					</p>
					<hr />
					<p style="text-align: right">
						<a id="link"> 
						<img src="images/finalbutton.gif" width="204" height="51" border="0" />
						</a>
					</p>
				</div>
			</form>
		</div>

	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>