package com.wsj.eshop.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wsj.eshop.bean.User;
import com.wsj.eshop.service.UserService;

/**
 * web层 User
 * 
 * @author WangShuaiJie
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	// 模型驱动对象
	private User user = new User();

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	// 注入userservice
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 接受验证码
	private String checkcode;

	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	/**
	 * AJAX检查用户是否存在
	 * @return
	 * @throws IOException
	 */
	public String findByName() throws IOException {
		// 调用Service进行查询:
		User existUser = userService.findByUsername(user.getUsername());
		// 获得response对象,对页面输出:
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// 判断
		if (existUser != null) {
			// 查询到该用户:用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		} else {
			// 没查询到该用户:用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}
	
	/**
	 * 用户注册的方法:
	 */
	public String regist() {		
		// 判断验证码程序:从session中获得验证码的随机值:
		String checkcode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcode1)){ //忽略大小写
			this.addActionError("验证码输入错误!");
			return "registPage";
		}
		userService.save(user);
		this.addActionMessage("注册成功!请去邮箱激活!");
		return "msg";
	}
	
	/**
	 * 用户激活的方法
	 */
	public String active() {
		// 根据激活码查询用户:
		User existUser = userService.findByCode(user.getCode());
		// 判断
		if (existUser == null) {
			// 激活码错误的
			this.addActionMessage("激活失败:激活码错误!");
		} else {
			// 激活成功
			// 修改用户的状态
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功:请去登录!");
		}
		return "msg";
	}
	
	/**
	 * 登录的方法
	 */
	public String login() {
		String checkcode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcode1)){
			this.addActionError("验证码输入错误!");
			return "loginPage";
		}
		User existUser = userService.login(user);
		// 判断
		if (existUser == null) {
			// 登录失败
			this.addActionError("登录失败:用户名或密码错误或用户未激活!");
			return LOGIN;
		} else {
			// 登录成功
			// 将用户的信息存入到session中
			ServletActionContext.getRequest().getSession()
					.setAttribute("existUser", existUser);
			// 页面跳转
			return SUCCESS;
		}	
	}
	
	/**
	 * 用户退出的方法
	 */
	public String exit(){
		// 销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "exit";
	}

}
