package com.wsj.eshop.bean;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ���ﳵ
 * @author WangShuaiJie
 *
 */
public class Cart {
	// �������:Map��key������Ʒpid,value:������
	private Map<Integer, CartItem> map=new LinkedHashMap<>();
	
	// Cart��������һ����cartItems����.
	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	// �����ܼ�:
	private double total;

	public double getTotal() {
		return total;
	}
	
	//���ﳵ����
	/**
	 * �����ﳵ����빺�ﳵ
	 * @param cartItem
	 */
	public void addCart(CartItem cartItem) {
		// �жϹ��ﳵ���Ƿ��Ѿ����ڸù�����:
		/*
		 * �������: 
		 * ��������  �ܼ� = �ܼ� + ������С�� 
		 * ���������: 
		 * ��map����ӹ����� * �ܼ� = �ܼ� + ������С��
		 */
		// ��ȡ��Ʒid
		Integer pid = cartItem.getProduct().getPid();
        //�жϹ��ﳵ�Ƿ����
		if(map.containsKey(pid)) {
			CartItem _cartItem=map.get(pid);
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else {
			map.put(pid, cartItem);
		}
		total=total+cartItem.getSubtotal();
	}

	/**
	 * �ӹ��ﳵ�Ƴ�������
	 * 
	 * @param pid
	 */
	public void removeCart(Integer pid) {
		// ���������Ƴ����ﳵ:
		CartItem cartItem = map.remove(pid);
		// �ܼ� = �ܼ� -�Ƴ��Ĺ�����С��:
		total = total-cartItem.getSubtotal();
	}

	/**
	 * ��չ��ﳵ
	 */
	public void clearCart() {
		// �����й��������
		map.clear();
		// ���ܼ�����Ϊ0
		total = 0;
	}
}
