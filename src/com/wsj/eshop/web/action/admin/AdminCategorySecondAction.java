package com.wsj.eshop.web.action.admin;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wsj.eshop.bean.Category;
import com.wsj.eshop.bean.CategorySecond;
import com.wsj.eshop.service.CategorySecondService;
import com.wsj.eshop.service.CategoryService;
import com.wsj.eshop.util.PageBean;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {
	private CategorySecond categorySecond = new CategorySecond();

	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}

	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	private CategorySecondService categorySecondService;

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	// 功能
	/**
	 * 查询所有二级分类
	 * 
	 * @return
	 */
	public String findAll() {
		// 调用Service进行查询.
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		// 将pageBean的数据存入到值栈中.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String addPage() {
		// 查询所有一级分类.
		List<Category> cList = categoryService.findAll();
		// 将集合存入到值栈中.
		ActionContext.getContext().getValueStack().set("cList", cList);
		// 页面跳转:
		return "addPage";
	}

	/**
	 * 添加二级分类
	 * 
	 * @return
	 */
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}

	/**
	 * 删除二级分类
	 * 
	 * @return
	 */
	public String delete() {
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @return
	 */
	public String edit() {
		// 根据id查询二级分类:
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		// 查询所有一级分类:
		List<Category> cList = categoryService.findAll();
		// 将集合存入到值栈中.
		ActionContext.getContext().getValueStack().set("cList", cList);
		// 页面跳转:
		return "editSuccess";
	}

	/**
	 * 修改二级分类
	 * 
	 * @return
	 */
	public String update() {
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}

}
