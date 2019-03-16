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

<title>msg</title>
</head>
<body>
<div id="divcontent">
		<table width="850px" border="0" cellspacing="0">
			<tr>
				<td style="padding:30px; text-align:center"><table width="60%"
						border="0" cellspacing="0" style="margin-top:70px">
						<tr>
							<td style="width:98">
							<img src="images/IconTexto_WebDev_009.jpg"
								width="128" height="128" />
							</td>
							<td style="padding-top:30px">
							    <font style="font-weight:bold; color:#FF0000"> 
							          <s:actionmessage />
									  <s:actionerror /> 
							    </font> 
							    <br /> 
							    <br/>
							    <a href="index.action" style="text-decoration: none">首页</a>
                                <a href="page/regist.jsp" style="text-decoration: none">注册</a>
                                <a href="page/login.jsp" style="text-decoration: none">登录</a>
							</td>
						</tr>
					</table>
					<h1>&nbsp;</h1>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>