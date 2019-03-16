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
<title>product</title>

<link href="css/common.css" type="text/css" rel="stylesheet">
<link href="css/product.css" type="text/css" rel="stylesheet">

<script src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function saveCart() {
		document.getElementById("cartForm").submit();
	}
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container productContent">
		<!-- 侧边栏类型展示 -->
		<div class="span6">
			<div class="hotProductCategory">
				<s:iterator value="#session.cList" var="c">
					<dl>
						<dt>
							<a
								href="product_findByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property
									value="#c.cname" /></a>
						</dt>
						<s:iterator var="cs" value="#c.categorySeconds">
							<dd>
								<a
									href="product_findByCsid.action?csid=<s:property value="#cs.csid"/>&page=1"><s:property
										value="#cs.csname" /></a>
							</dd>
						</s:iterator>
					</dl>
				</s:iterator>
			</div>

		</div>

		<div class="span18 last">

			<!-- Action里面有一个属性model，model就是product  -->

			<div class="productImage">
				<a title="" style="outline-style: none; text-decoration: none;"
					id="zoom" href="<s:property value="model.image"/>" rel="gallery">
					<div class="zoomPad">
						<img style="opacity: 1;" title="" class="medium"
							src="<s:property value="model.image"/>">
					</div>
				</a>
			</div>
			<div class="name">
				<s:property value="model.pname" />
			</div>
			<div class="sn">
				<div>
					编号：
					<s:property value="model.pid" />
				</div>
			</div>
			<div class="info">
				<dl>
					<dt>商城价:</dt>
					<dd>
						<strong>￥：<s:property value="model.shop_price" />元
						</strong> 参 考 价：
						<del>
							￥
							<s:property value="model.market_price" />
							元
						</del>
					</dd>
				</dl>
				<dl>
					<dt>促销:</dt>
					<dd>
						<a target="_blank" title="限时抢购 (2018-09-30 ~ 2018-12-01)">限时抢购</a>
					</dd>
				</dl>
				<dl>
					<dt></dt>
					<dd>
						<span> </span>
					</dd>
				</dl>
			</div>
			<form id="cartForm" action="cart_addCart.action" method="post">
				<input type="hidden" name="pid"
					value="<s:property value="model.pid"/>" />
				<div class="action">
					<dl class="quantity">
						<dt>购买数量:</dt>
						<dd>
							<input id="count" name="count" value="1" maxlength="4"
								onpaste="return false;" type="text" />
						</dd>
						<dd>件</dd>
					</dl>

					<div class="buy">
						<input id="addCart" class="addCart" value="加入购物车" type="button"
							onclick="saveCart()" />
					</div>
				</div>
			</form>
			<div id="bar" class="bar">
				<ul>
					<li id="introductionTab"><a href="#introduction">商品介绍</a></li>

				</ul>
			</div>

			<div id="introduction" name="introduction" class="introduction">
				<div class="title">
					<strong><s:property value="model.pdesc" /></strong>
				</div>
				<div>
					<img src="<s:property value="model.image"/>">
				</div>
			</div>

		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>