package com.wsj.eshop.web.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wsj.eshop.bean.Category;
import com.wsj.eshop.bean.Product;
import com.wsj.eshop.service.CategoryService;
import com.wsj.eshop.service.ProductService;

public class IndexAction extends ActionSupport{
	// 注入一级分类的Service:
	private CategoryService categoryService;
	// 注入商品的Service
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
		// 查询所有一级分类集合
				List<Category> cList = categoryService.findAll();
				// 将一级分类存入到Session的范围:
				ActionContext.getContext().getSession().put("cList", cList);
				//查询热门商品
				List<Product> hList=productService.findHot();
				//保存到值栈中
				ActionContext.getContext().getValueStack().set("hList", hList);
				// 查询最新商品:
				List<Product> nList = productService.findNew();
				// 保存到值栈中:
				ActionContext.getContext().getValueStack().set("nList", nList);
				return SUCCESS;
	}
}
