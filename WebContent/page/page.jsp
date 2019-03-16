<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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