package com.wsj.eshop.util;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class PageHibernateCallback<T> implements HibernateCallback<List<T>> {
    private String hql;//hql���
    private Object[] params;//����
    private int startIndex;//��ʼ����
    private int pageSize;//ÿҳ��¼��
    
    
   public PageHibernateCallback(String hql, Object[] params, int startIndex, int pageSize) {
		super();
		this.hql = hql;
		this.params = params;
		this.startIndex = startIndex;
		this.pageSize = pageSize;
	}


    //��ҳԭ����limit������� ��ѯ�������ļ�¼��
	@Override
	public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		// ִ��hql���
				Query query=session.createQuery(hql);
				//���������ʺ�
				if(params!=null){
					for(int i=0;i<params.length;i++){
						query.setParameter(i, params[i]);
					}
				}
				//��ҳ
				query.setFirstResult(startIndex);
				query.setMaxResults(pageSize);
				
				return query.list();
	}

}
