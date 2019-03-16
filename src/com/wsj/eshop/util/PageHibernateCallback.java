package com.wsj.eshop.util;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class PageHibernateCallback<T> implements HibernateCallback<List<T>> {
    private String hql;//hql语句
    private Object[] params;//参数
    private int startIndex;//开始坐标
    private int pageSize;//每页记录数
    
    
   public PageHibernateCallback(String hql, Object[] params, int startIndex, int pageSize) {
		super();
		this.hql = hql;
		this.params = params;
		this.startIndex = startIndex;
		this.pageSize = pageSize;
	}


    //分页原理，由limit限制语句 查询到定量的记录数
	@Override
	public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		// 执行hql语句
				Query query=session.createQuery(hql);
				//参数代替问号
				if(params!=null){
					for(int i=0;i<params.length;i++){
						query.setParameter(i, params[i]);
					}
				}
				//分页
				query.setFirstResult(startIndex);
				query.setMaxResults(pageSize);
				
				return query.list();
	}

}
