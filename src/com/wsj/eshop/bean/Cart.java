package com.wsj.eshop.bean;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车
 * @author WangShuaiJie
 *
 */
public class Cart {
	// 购物项集合:Map的key就是商品pid,value:购物项
	private Map<Integer, CartItem> map=new LinkedHashMap<>();
	
	// Cart对象中有一个叫cartItems属性.
	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	// 购物总计:
	private double total;

	public double getTotal() {
		return total;
	}
	
	//购物车功能
	/**
	 * 将购物车项加入购物车
	 * @param cartItem
	 */
	public void addCart(CartItem cartItem) {
		// 判断购物车中是否已经存在该购物项:
		/*
		 * 如果存在: 
		 * 数量增加  总计 = 总计 + 购物项小计 
		 * 如果不存在: 
		 * 向map中添加购物项 * 总计 = 总计 + 购物项小计
		 */
		// 获取商品id
		Integer pid = cartItem.getProduct().getPid();
        //判断购物车是否存在
		if(map.containsKey(pid)) {
			CartItem _cartItem=map.get(pid);
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else {
			map.put(pid, cartItem);
		}
		total=total+cartItem.getSubtotal();
	}

	/**
	 * 从购物车移除购物项
	 * 
	 * @param pid
	 */
	public void removeCart(Integer pid) {
		// 将购物项移除购物车:
		CartItem cartItem = map.remove(pid);
		// 总计 = 总计 -移除的购物项小计:
		total = total-cartItem.getSubtotal();
	}

	/**
	 * 清空购物车
	 */
	public void clearCart() {
		// 将所有购物项清空
		map.clear();
		// 将总计设置为0
		total = 0;
	}
}
