<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <!-- 1.request.getSchema();可以返回当前页面所使用的协议，就是"http"
　　         2.request.getServerName();返回当前页面所在服务器的名字，"localhost"
　　         3.request.getServerPort();返回当前页面所在服务器的端口号，"8080"
　　         4.request.getContextPath();返回当前页面所在的项目的名字，例如"eshop" -->
    <%
    String path=request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">  <!--可以为当前的链接使用绝对路径 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index</title>
</head>
<body>
     <jsp:forward page="index.action"></jsp:forward>
</body>
</html>