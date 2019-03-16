package com.wsj.eshop.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wsj.eshop.bean.User;
import com.wsj.eshop.service.UserService;

/**
 * web�� User
 * 
 * @author WangShuaiJie
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	// ģ����������
	private User user = new User();

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	// ע��userservice
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// ������֤��
	private String checkcode;

	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	/**
	 * AJAX����û��Ƿ����
	 * @return
	 * @throws IOException
	 */
	public String findByName() throws IOException {
		// ����Service���в�ѯ:
		User existUser = userService.findByUsername(user.getUsername());
		// ���response����,��ҳ�����:
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// �ж�
		if (existUser != null) {
			// ��ѯ�����û�:�û����Ѿ�����
			response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
		} else {
			// û��ѯ�����û�:�û�������ʹ��
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
		}
		return NONE;
	}
	
	/**
	 * �û�ע��ķ���:
	 */
	public String regist() {		
		// �ж���֤�����:��session�л����֤������ֵ:
		String checkcode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcode1)){ //���Դ�Сд
			this.addActionError("��֤���������!");
			return "registPage";
		}
		userService.save(user);
		this.addActionMessage("ע��ɹ�!��ȥ���伤��!");
		return "msg";
	}
	
	/**
	 * �û�����ķ���
	 */
	public String active() {
		// ���ݼ������ѯ�û�:
		User existUser = userService.findByCode(user.getCode());
		// �ж�
		if (existUser == null) {
			// ����������
			this.addActionMessage("����ʧ��:���������!");
		} else {
			// ����ɹ�
			// �޸��û���״̬
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("����ɹ�:��ȥ��¼!");
		}
		return "msg";
	}
	
	/**
	 * ��¼�ķ���
	 */
	public String login() {
		String checkcode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcode1)){
			this.addActionError("��֤���������!");
			return "loginPage";
		}
		User existUser = userService.login(user);
		// �ж�
		if (existUser == null) {
			// ��¼ʧ��
			this.addActionError("��¼ʧ��:�û��������������û�δ����!");
			return LOGIN;
		} else {
			// ��¼�ɹ�
			// ���û�����Ϣ���뵽session��
			ServletActionContext.getRequest().getSession()
					.setAttribute("existUser", existUser);
			// ҳ����ת
			return SUCCESS;
		}	
	}
	
	/**
	 * �û��˳��ķ���
	 */
	public String exit(){
		// ����session
		ServletActionContext.getRequest().getSession().invalidate();
		return "exit";
	}

}
