package com.wsj.eshop.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wsj.eshop.bean.User;
import com.wsj.eshop.util.PageHibernateCallback;

/**
 * 3层架构dao层  user
 * @author WangShuaiJie
 *
 */
public class UserDao extends HibernateDaoSupport{
	/**
	 * 按用户名查询用户
	 * @param username
	 * @return
	 */
	public User findByUsername(String username){
		String hql="from User where username=?";
		List<User> list=this.getHibernateTemplate().find(hql, username);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 根据uid查找用户   
	 * @param uid
	 * @return
	 */
	public User findByUid(Integer uid) {
		return this.getHibernateTemplate().get(User.class, uid);//此方法只适用于主键id
	}
	/**
	 * 按激活码查询用户
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		String hql = "from User where code = ?";
		List<User> list = this.getHibernateTemplate().find(hql, code);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user) {
		String hql = "from User where username = ? and password = ? and state = ?";
		List<User> list = this.getHibernateTemplate().find(hql,
				user.getUsername(), user.getPassword(), 1);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 用户总数
	 * @return
	 */
	public int findCount() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}
    /**
     * 查询所有用户信息并分页
     * @param begin
     * @param limit
     * @return
     */
	public List<User> findByPage(int begin, int limit) {
		String hql = "from User";
		List<User> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<User>(hql, null, begin, limit));
		return list;
	}

	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}
	
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}
	
	public void delete(User existUser) {
		this.getHibernateTemplate().delete(existUser);
	}
}

