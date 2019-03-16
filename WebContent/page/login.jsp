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
<base href=<%=basePath%>>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login</title>

<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/login.css" rel="stylesheet" type="text/css">
<script src="js/jquery-1.8.3.js"></script>

<script type="text/javascript">
$(function () {
	$("#checkImg").click(function () {
		$("#checkImg").attr("src","checkImg.action?time="+new Date().getTime());
	});
});

</script>

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="container login">
		<div class="span12">
		</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>会员登录</strong>USER LOGIN
					</div>
					<div>
						<s:actionerror />
					</div>
					<!-- novalidate="novalidate"浏览器不对输入验证，由自己的代码检验 -->
					<form id="loginForm"
						action="user_login.action"
						method="post" novalidate="novalidate">
						<table>
							<tbody>
								<tr>
									<th>用户名:</th>
									<td><input type="text" id="username" name="username"
										class="text" maxlength="20"></td>
								</tr>
								<tr>
									<th>密&nbsp;&nbsp;码:</th>
									<td><input type="password" id="password" name="password"
										class="text" maxlength="20" autocomplete="off"></td>
								</tr>

								<tr>
									<th><span class="requiredField">*</span>验证码:</th>
									<td><span class="fieldSet"> <input type="text"
											id="checkcode" name="checkcode" class="text captcha"
											maxlength="4" autocomplete="off"><img id="checkImg"
											class="captchaImage"
											src="checkImg.action"
											onclick="" title="点击更换验证码">
									</span></td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><input type="submit" class="submit" value="登 录">
									</td>
								</tr>

								<tr class="register">
									<th>&nbsp;</th>
									<td>
										<dl>
											<dt>还没有注册账号？</dt>
											<dd>
												立即注册即可体验在线购物！
												<a href="/page/regist.jsp">立即注册</a>
											</dd>
										</dl>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>