package com.wsj.eshop.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wsj.eshop.bean.Product;
import com.wsj.eshop.service.ProductService;
import com.wsj.eshop.util.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	//注入模型驱动
    private Product product=new Product();
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	
	//注入service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private Integer cid;//接受一级类型id
	private Integer csid;//接受二级类型id
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public ProductService getProductService() {
		return productService;
	}
	
	private int page;//接受当前页数
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	//功能
	/**
	 * 根据商品id查询
	 * @return
	 */
	public String findByPid() {
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
	/**
	 * 根据一级类型id查询
	 * @return
	 */
	public String findByCid() {
		PageBean<Product> pageBean=productService.findByPageCid(cid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	/**
	 * 根据二级类型id查询
	 * @return
	 */
	public String findByCsid() {
		PageBean<Product> pageBean=productService.findByPageCsid(csid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

}
