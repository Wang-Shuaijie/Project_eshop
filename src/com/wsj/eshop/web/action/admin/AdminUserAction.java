package com.wsj.eshop.web.action.admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wsj.eshop.bean.User;
import com.wsj.eshop.service.UserService;
import com.wsj.eshop.util.PageBean;

public class AdminUserAction extends ActionSupport implements ModelDriven<User> {
	// ģ������ʹ�õ���
	private User user = new User();

	public User getModel() {
		return user;
	}

	// ע���û���Service
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * ��ѯ�����û�
	 * 
	 * @return
	 */
	public String findAll() {
		PageBean<User> pageBean = userService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	/**
	 * ɾ���û�
	 * 
	 * @return
	 */
	public String delete() {
		User existUser = userService.findByUid(user.getUid());
		userService.delete(existUser);
		return "deleteSuccess";
	}

	/**
	 * �û��༭
	 * 
	 * @return
	 */
	public String edit() {
		user = userService.findByUid(user.getUid());
		return "editSuccess";
	}

	/**
	 * �û��޸�
	 * 
	 * @return
	 */
	public String update() {
		userService.update(user);
		return "updateSuccess";
	}
}
