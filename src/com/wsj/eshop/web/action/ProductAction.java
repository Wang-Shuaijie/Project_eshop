package com.wsj.eshop.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wsj.eshop.bean.Product;
import com.wsj.eshop.service.ProductService;
import com.wsj.eshop.util.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	//ע��ģ������
    private Product product=new Product();
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	
	//ע��service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private Integer cid;//����һ������id
	private Integer csid;//���ܶ�������id
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
	
	private int page;//���ܵ�ǰҳ��
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	//����
	/**
	 * ������Ʒid��ѯ
	 * @return
	 */
	public String findByPid() {
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
	/**
	 * ����һ������id��ѯ
	 * @return
	 */
	public String findByCid() {
		PageBean<Product> pageBean=productService.findByPageCid(cid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	/**
	 * ���ݶ�������id��ѯ
	 * @return
	 */
	public String findByCsid() {
		PageBean<Product> pageBean=productService.findByPageCsid(csid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

}
