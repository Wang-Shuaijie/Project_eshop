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
<title>admin/home</title>
</head>
<frameset rows="103,*,43" frameborder=0 border="0" framespacing="0">
       <frame src="page/admin/top.jsp" name="topFrame" scrolling="NO" noresize>
       <frameset cols="159,*" frameborder="0" border="0" framespacing="0">
		  <frame src="page/admin/left.jsp" name="leftFrame" noresize scrolling="YES">
		  <frame src="page/admin/welcome.jsp" name="mainFrame">
	   </frameset>
</frameset>
</html>