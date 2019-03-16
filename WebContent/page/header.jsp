<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
    %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="index.action"> <font
				size="26px" style="color: red; font-family: 华文楷体">乐购</font>
			</a>
		</div>
	</div>
	<div class="span9">
		<div class="headerAd">
			<img src="images/header.jpg"
				width="320" height="50" alt="正品保障" title="正品保障" />
		</div>
	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<s:if test="#session.existUser == null">
					<li id="headerLogin" class="headerLogin"
						style="display: list-item;"><a
						href="page/login.jsp">登录</a>|</li>
					<li id="headerRegister" class="headerRegister"
						style="display: list-item;"><a
						href="page/regist.jsp">注册</a>|</li>
				</s:if>
				<s:else>
					<li id="headerLogin" class="headerLogin"
						style="display: list-item;"><s:property
							value="#session.existUser.name" /> |</li>
					<li id="headerLogin" class="headerLogin"
						style="display: list-item;"><a
						href="order_findByUid.action?page=1">我的订单</a>
						|</li>
					<li id="headerRegister" class="headerRegister"
						style="display: list-item;"><a
						href="user_quit.action">退出</a>|
					</li>
				</s:else>
				<li><a>会员中心</a> |</li>
				<li><a>购物指南</a> |</li>
				<li><a>关于我们</a></li>
			</ul>
		</div>
		<div class="cart">
			<a href="cart_myCart.action">购物车</a>
		</div>
		<div class="phone">
			客服热线: <strong>00000/000000</strong>
		</div>
	</div>
	<div class="span24">
		<ul class="mainNav">
			<li><a href="index.action">首页</a>
				|</li>
			<s:iterator var="c" value="#session.cList">
				<li><a
					href="product_findByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property
							value="#c.cname" /> </a> |</li>
			</s:iterator>

		</ul>
	</div>
</div>