package com.wsj.eshop.service;

import java.util.List;

import com.wsj.eshop.bean.User;
import com.wsj.eshop.dao.UserDao;
import com.wsj.eshop.util.MailUtils;
import com.wsj.eshop.util.PageBean;
import com.wsj.eshop.util.UUIDUtils;

/**
 * User  Service��
 * @author WangShuaiJie
 *
 */
public class UserService {
	//ע��UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	// ���û�����ѯ�û��ķ���:
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	// ����uid��ѯ
	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}

	// ҵ�����ݼ������ѯ�û�
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	// �û���¼�ķ���
	public User login(User user) {
		return userDao.login(user);
	}
	
	//��ѯ�����û�
	public PageBean<User> findByPage(Integer page){
		PageBean<User> pageBean=new PageBean<>();
		//���õ�ǰҳ
		pageBean.setPage(page);
		//����ÿҳ��ʾ��
		int limit=5;//��ʾ5����¼
		pageBean.setLimit(limit);
		// �����ܼ�¼��:
		int totalCount = 0;
		totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		int begin=(page-1)*limit;
		List<User> list = userDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;		
		
	}
	
	/**
	 * ע���û�
	 * @param user
	 */
	public void save(User user) {
		// �����ݴ��뵽���ݿ�
		user.setState(0); // 0:�����û�δ����.  1:�����û��Ѿ�����.
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		// ���ͼ����ʼ�;
		MailUtils.sendMail(user.getEmail(), code);
	}
	
	public void delete(User existUser) {
		userDao.delete(existUser);
	}

	public void update(User existUser) {
		userDao.update(existUser);
	}
}
