package com.wsj.eshop.service;

import java.util.List;

import com.wsj.eshop.bean.User;
import com.wsj.eshop.dao.UserDao;
import com.wsj.eshop.util.MailUtils;
import com.wsj.eshop.util.PageBean;
import com.wsj.eshop.util.UUIDUtils;

/**
 * User  Service层
 * @author WangShuaiJie
 *
 */
public class UserService {
	//注入UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	// 按用户名查询用户的方法:
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	// 根据uid查询
	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}

	// 业务层根据激活码查询用户
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	// 用户登录的方法
	public User login(User user) {
		return userDao.login(user);
	}
	
	//查询所有用户
	public PageBean<User> findByPage(Integer page){
		PageBean<User> pageBean=new PageBean<>();
		//设置当前页
		pageBean.setPage(page);
		//设置每页显示数
		int limit=5;//显示5条记录
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
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
	 * 注册用户
	 * @param user
	 */
	public void save(User user) {
		// 将数据存入到数据库
		user.setState(0); // 0:代表用户未激活.  1:代表用户已经激活.
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		// 发送激活邮件;
		MailUtils.sendMail(user.getEmail(), code);
	}
	
	public void delete(User existUser) {
		userDao.delete(existUser);
	}

	public void update(User existUser) {
		userDao.update(existUser);
	}
}
