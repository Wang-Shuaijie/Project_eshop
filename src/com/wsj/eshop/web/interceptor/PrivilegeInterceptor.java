package com.wsj.eshop.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wsj.eshop.bean.Administrator;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// TODO Auto-generated method stub
		// 判断是否登录,如果登录,放行,没有登录,跳转到登录页面.
		Administrator admin = (Administrator) ServletActionContext.getRequest().getSession()
				.getAttribute("administrator");
		if (admin != null) {
			// 已经登录过
			return actionInvocation.invoke();
		} else {
			// 跳转到登录页面:
			ActionSupport support = (ActionSupport) actionInvocation.getAction();
			support.addActionError("您还没有登录!没有权限访问!");
			return ActionSupport.LOGIN;
		}
	}

}
