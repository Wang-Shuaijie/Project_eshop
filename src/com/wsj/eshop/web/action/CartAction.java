package com.wsj.eshop.web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wsj.eshop.bean.Cart;
import com.wsj.eshop.bean.CartItem;
import com.wsj.eshop.bean.Product;
import com.wsj.eshop.service.ProductService;

public class CartAction extends ActionSupport{
	private Integer pid;//������Ʒpid
	private int count;//������Ʒ����

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

	//ע��productService
	private ProductService productService;
    public void setProductService(ProductService productService) {
		this.productService = productService;
	}
    
    //����
    /**
     * ��ӵ����ﳵ
     * @return
     */
    public String addCart() {
    	CartItem cartItem=new CartItem();//��װ��һ��cartItem����
    	cartItem.setCount(count);
    	Product product=productService.findByPid(pid);
    	cartItem.setProduct(product);
    	//��session��ȡ�ù��ﳵ
    	Cart cart=getCart();
    	cart.addCart(cartItem);
		return "addCart";
    }
    /**
     * ��չ��ﳵ
     * @return
     */
	public String clearCart() {
		// ��ù��ﳵ����.
		Cart cart = getCart();
		// ���ù��ﳵ����շ���.
		cart.clearCart();
		return "clearCart";
	}
    /**
     * �ӹ��ﳵ���Ƴ�������ķ���:
     * @return
     */
	public String removeCart() {
		// ��ù��ﳵ����
		Cart cart = getCart();
		// ���ù��ﳵ���Ƴ��ķ���:
		cart.removeCart(pid);
		// ����ҳ��:
		return "removeCart";
	}
	/**
	 * �ҵĹ��ﳵ
	 * @return
	 */
	public String myCart() {
		return "myCart";
	}
	
	/**
	 * ��session��ȡ�����ﳵ
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
