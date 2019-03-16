package com.wsj.eshop.web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wsj.eshop.bean.Cart;
import com.wsj.eshop.bean.CartItem;
import com.wsj.eshop.bean.Product;
import com.wsj.eshop.service.ProductService;

public class CartAction extends ActionSupport{
	private Integer pid;//接受商品pid
	private int count;//接受商品数量

	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	//注入productService
	private ProductService productService;
    public void setProductService(ProductService productService) {
		this.productService = productService;
	}
    
    //功能
    /**
     * 添加到购物车
     * @return
     */
    public String addCart() {
    	CartItem cartItem=new CartItem();//封装成一个cartItem对象
    	cartItem.setCount(count);
    	Product product=productService.findByPid(pid);
    	cartItem.setProduct(product);
    	//从session中取得购物车
    	Cart cart=getCart();
    	cart.addCart(cartItem);
		return "addCart";
    }
    /**
     * 清空购物车
     * @return
     */
	public String clearCart() {
		// 获得购物车对象.
		Cart cart = getCart();
		// 调用购物车中清空方法.
		cart.clearCart();
		return "clearCart";
	}
    /**
     * 从购物车中移除购物项的方法:
     * @return
     */
	public String removeCart() {
		// 获得购物车对象
		Cart cart = getCart();
		// 调用购物车中移除的方法:
		cart.removeCart(pid);
		// 返回页面:
		return "removeCart";
	}
	/**
	 * 我的购物车
	 * @return
	 */
	public String myCart() {
		return "myCart";
	}
	
	/**
	 * 从session中取出购物车
	 * @return
	 */
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
