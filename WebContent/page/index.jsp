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

<link href="css/slider.css" rel="stylesheet" type="text/css">
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/index.css" rel="stylesheet" type="text/css">
<title>index</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="container index">
		<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix">
				<div class="title">
					<strong>热门商品</strong>
					<!-- <a  target="_blank"></a> -->
				</div>
				<ul class="tabContent" style="display: block;">
					<s:iterator value="hList" var="p">
						<li>
						    <a href="product_findByPid.action?pid=<s:property value="#p.pid"/>"target="_blank">
						    <img src="<s:property value="#p.image"/>"style="display: block;"></a>
						</li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div class="span24">
			<div id="newProduct" class="newProduct clearfix">
				<div class="title">
					<strong>最新商品</strong> <a target="_blank"></a>
				</div>
				<ul class="tabContent" style="display: block;">
					<s:iterator var="p" value="nList">
						<li>
						    <a href="product_findByPid.action?pid=<s:property value="#p.pid"/>"target="_blank">
							<img src="<s:property value="#p.image"/>"style="display: block;"></a>
						</li>
					</s:iterator>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>