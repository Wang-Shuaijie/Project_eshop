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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/product.css" rel="stylesheet" type="text/css" />
<title>productlist</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="container productList">
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

			<form id="productForm" action="" method="post">
                <!-- 商品展示 -->
				<div id="result" class="result table clearfix">
					<ul>
						<s:iterator var="p" value="pageBean.list">
							<li>
							   <a href="product_findByPid.action?pid=<s:property value="#p.pid"/>">
									<img src="<s:property value="#p.image"/>"
									width="170" height="170" style="display: inline-block;">

									<span style='color:green'> <s:property value="#p.pname" /></span> 
									<span class="price"> 商城价： ￥<s:property value="#p.shop_price" /> </span>
								 </a>
							</li>
						</s:iterator>
					</ul>
				</div>
				
				<!-- 分页信息展示 -->
				<div class="pagination">
					<span>第 <s:property value="pageBean.page" />/<s:property
							value="pageBean.totalPage" /> 页
					</span>
					<!-- 商品一级类型 -->
					<s:if test="cid != null">
						<!-- 上一页和第一页的符号 -->
						<s:if test="pageBean.page != 1">
							<a
								href="product_findByCid.action?cid=<s:property value="cid"/>&page=1"
								class="firstPage">&nbsp;</a>
							<a
								href="product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page-1"/>"
								class="previousPage">&nbsp;</a>
						</s:if>

						<s:iterator var="i" begin="1" end="pageBean.totalPage">
							<s:if test="pageBean.page != #i">
								<a
									href="product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="#i"/>">
									<s:property value="#i" />
								</a>
							</s:if>
							<s:else>
								<span class="currentPage"><s:property value="#i" /> </span>
							</s:else>
						</s:iterator>
						<!-- 下一页和最终页的符号 -->
						<s:if test="pageBean.page != pageBean.totalPage">
							<a class="nextPage"
								href="product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
							<a class="lastPage"
								href="product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
						</s:if>
					</s:if>
					<!-- 商品二级类型 -->
					<s:if test="csid != null">
						<s:if test="pageBean.page != 1">
							<a
								href="product_findByCsid.action?csid=<s:property value="csid"/>&page=1"
								class="firstPage">&nbsp;</a>
							<a
								href="product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.page-1"/>"
								class="previousPage">&nbsp;</a>
						</s:if>

						<s:iterator var="i" begin="1" end="pageBean.totalPage">
							<s:if test="pageBean.page != #i">
								<a
									href="product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="#i"/>"><s:property
										value="#i" /> </a>
							</s:if>
							<s:else>
								<span class="currentPage"><s:property value="#i" /> </span>
							</s:else>
						</s:iterator>

						<s:if test="pageBean.page != pageBean.totalPage">
							<a class="nextPage"
								href="product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
							<a class="lastPage"
								href="product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
						</s:if>
					</s:if>
				</div>

			</form>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>