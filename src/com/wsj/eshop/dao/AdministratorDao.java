package com.wsj.eshop.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wsj.eshop.bean.Administrator;

public class AdministratorDao extends HibernateDaoSupport{
	public Administrator login(Administrator admin) {
		String hql="from Administrator where username= ? and password= ?";
		List<Administrator> list=this.getHibernateTemplate().find(hql,admin.getUsername(),admin.getPassword());
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
