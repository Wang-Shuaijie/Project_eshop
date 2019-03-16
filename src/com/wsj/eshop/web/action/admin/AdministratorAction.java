package com.wsj.eshop.web.action.admin;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wsj.eshop.bean.Administrator;
import com.wsj.eshop.service.AdministratorService;

public class AdministratorAction extends ActionSupport implements ModelDriven<Administrator>{
    private Administrator admin=new Administrator();
	@Override
	public Administrator getModel() {
		// TODO Auto-generated method stub
		return admin;
	}
    //ע��service
	private AdministratorService administratorService;
	public void setAdministratorService(AdministratorService administratorService) {
		this.administratorService = administratorService;
	}
	
	//��̨��¼�ķ���
	public String login() {
		Administrator administrator=administratorService.login(admin);
		if(administrator==null) {
			this.addActionError("�û������������!");
			return "loginFail";
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("administrator",administrator );
			return "loginSuccess";
		}
	}
}
