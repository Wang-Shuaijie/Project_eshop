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
		// �ж��Ƿ��¼,�����¼,����,û�е�¼,��ת����¼ҳ��.
		Administrator admin = (Administrator) ServletActionContext.getRequest().getSession()
				.getAttribute("administrator");
		if (admin != null) {
			// �Ѿ���¼��
			return actionInvocation.invoke();
		} else {
			// ��ת����¼ҳ��:
			ActionSupport support = (ActionSupport) actionInvocation.getAction();
			support.addActionError("����û�е�¼!û��Ȩ�޷���!");
			return ActionSupport.LOGIN;
		}
	}

}
