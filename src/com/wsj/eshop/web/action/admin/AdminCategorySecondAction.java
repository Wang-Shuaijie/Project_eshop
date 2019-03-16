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

	// ����
	/**
	 * ��ѯ���ж�������
	 * 
	 * @return
	 */
	public String findAll() {
		// ����Service���в�ѯ.
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		// ��pageBean�����ݴ��뵽ֵջ��.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	/**
	 * ��ת�����ҳ��
	 * 
	 * @return
	 */
	public String addPage() {
		// ��ѯ����һ������.
		List<Category> cList = categoryService.findAll();
		// �����ϴ��뵽ֵջ��.
		ActionContext.getContext().getValueStack().set("cList", cList);
		// ҳ����ת:
		return "addPage";
	}

	/**
	 * ��Ӷ�������
	 * 
	 * @return
	 */
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}

	/**
	 * ɾ����������
	 * 
	 * @return
	 */
	public String delete() {
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}

	/**
	 * ��ת���޸�ҳ��
	 * 
	 * @return
	 */
	public String edit() {
		// ����id��ѯ��������:
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		// ��ѯ����һ������:
		List<Category> cList = categoryService.findAll();
		// �����ϴ��뵽ֵջ��.
		ActionContext.getContext().getValueStack().set("cList", cList);
		// ҳ����ת:
		return "editSuccess";
	}

	/**
	 * �޸Ķ�������
	 * 
	 * @return
	 */
	public String update() {
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}

}
