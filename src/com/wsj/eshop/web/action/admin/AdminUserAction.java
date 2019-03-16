package com.wsj.eshop.web.action.admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wsj.eshop.bean.User;
import com.wsj.eshop.service.UserService;
import com.wsj.eshop.util.PageBean;

public class AdminUserAction extends ActionSupport implements ModelDriven<User> {
	// 模型驱动使用的类
	private User user = new User();

	public User getModel() {
		return user;
	}

	// 注入用户的Service
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
	 * 查询所有用户
	 * 
	 * @return
	 */
	public String findAll() {
		PageBean<User> pageBean = userService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	public String delete() {
		User existUser = userService.findByUid(user.getUid());
		userService.delete(existUser);
		return "deleteSuccess";
	}

	/**
	 * 用户编辑
	 * 
	 * @return
	 */
	public String edit() {
		user = userService.findByUid(user.getUid());
		return "editSuccess";
	}

	/**
	 * 用户修改
	 * 
	 * @return
	 */
	public String update() {
		userService.update(user);
		return "updateSuccess";
	}
}
