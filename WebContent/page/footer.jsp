<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
    %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
			<img src="images/footer.jpg"
				width="950" height="52" alt="我们的优势" title="我们的优势">
		</div>
	</div>
	<div class="span24">
		<ul class="bottomNav">
			<li><a>关于我们</a> |</li>
			<li><a>联系我们</a> |</li>
			<li><a>招贤纳士</a> |</li>
			<li><a>法律声明</a> |</li>
			<li><a>友情链接</a> |</li>
			<li><a target="_blank">支付方式</a> |</li>
			<li><a target="_blank">配送方式</a> |</li>
			<li><a>服务声明</a> |</li>
			<li><a>广告声明</a>
			</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2018-2019  Make by Wang Shuaijie</div>
	</div>
</div>