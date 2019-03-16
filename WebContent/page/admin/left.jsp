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
<title>admin/left</title>
<link href="css/left.css" rel="stylesheet" type="text/css"/>
<link href="css/dtree.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<table width="100" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="12"></td>
		</tr>
	</table>
	<table width="100%" border="0">
		<tr>
			<td>
				<div class="dtree">

					<a href="javascript: d.openAll();">展开所有</a> | <a
						href="javascript: d.closeAll();">关闭所有</a>

					<script type="text/javascript"
						src="js/dtree.js"></script>
					<script type="text/javascript">
						d = new dTree('d');
						d.add('01', -1, '系统菜单树');
						d.add('0101', '01', '用户管理', '', '', 'mainFrame');

						d.add('010101', '0101', '用户管理',
								'adminUser_findAll.action?page=1', '',
								'mainFrame');
						d.add('0102', '01', '一级分类管理', '', '', 'mainFrame');
						d.add('010201', '0102', '一级分类管理',
								'adminCategory_findAll.action', '',
								'mainFrame');
						d.add('0103', '01', '二级分类管理');
						d.add('010301', '0103', '二级分类管理',
								'adminCategorySecond_findAll.action?page=1',
								'', 'mainFrame');
						d.add('0104', '01', '商品管理');
						d.add('010401', '0104', '商品管理',
								'adminProduct_findAll.action?page=1', '',
								'mainFrame');
						d.add('0105', '01', '订单管理');
						d.add('010501', '0105', '订单管理',
								'adminOrder_findAll.action?page=1', '',
								'mainFrame');
						document.write(d);					
					</script>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>