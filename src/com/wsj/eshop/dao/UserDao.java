package com.wsj.eshop.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wsj.eshop.bean.User;
import com.wsj.eshop.util.PageHibernateCallback;

/**
 * 3��ܹ�dao��  user
 * @author WangShuaiJie
 *
 */
public class UserDao extends HibernateDaoSupport{
	/**
	 * ���û�����ѯ�û�
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
	 * ����uid�����û�   
	 * @param uid
	 * @return
	 */
	public User findByUid(Integer uid) {
		return this.getHibernateTemplate().get(User.class, uid);//�˷���ֻ����������id
	}
	/**
	 * ���������ѯ�û�
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
	 * �û���¼
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
	 * �û�����
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
     * ��ѯ�����û���Ϣ����ҳ
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

