package com.wsj.eshop.web.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wsj.eshop.bean.Category;
import com.wsj.eshop.bean.Product;
import com.wsj.eshop.service.CategoryService;
import com.wsj.eshop.service.ProductService;

public class IndexAction extends ActionSupport{
	// ע��һ�������Service:
	private CategoryService categoryService;
	// ע����Ʒ��Service
	private ProductService productService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		// ��ѯ����һ�����༯��
				List<Category> cList = categoryService.findAll();
				// ��һ��������뵽Session�ķ�Χ:
				ActionContext.getContext().getSession().put("cList", cList);
				//��ѯ������Ʒ
				List<Product> hList=productService.findHot();
				//���浽ֵջ��
				ActionContext.getContext().getValueStack().set("hList", hList);
				// ��ѯ������Ʒ:
				List<Product> nList = productService.findNew();
				// ���浽ֵջ��:
				ActionContext.getContext().getValueStack().set("nList", nList);
				return SUCCESS;
	}
}
