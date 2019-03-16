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
<title>admin/bottom</title>
</head>
<body MS_POSITIONING="GridLayout">
		<table width="100%" border="0" cellspacing="0" cellpadding="10" height="64">
			<tr>
				<td align="center" width="100%" style= valign="top" background="images/bt_02.jpg">商城管理平台&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
				<font class="font12">
				<a class="a03" target="_blank" href="">
				<font color="#000000"><br>Make by Wang Shuaijie</font></a></font></td>
			</tr>
		</table>
</body>
</html>