package com.wsj.eshop.web.action.admin;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wsj.eshop.bean.Category;
import com.wsj.eshop.service.CategoryService;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
	// ģ������ʹ�õĶ���.
	private Category category = new Category();

	public Category getModel() {
		return category;
	}

	// ע��һ�������Service
	public CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// ����
	/**
	 * ��ѯ����һ������
	 * 
	 * @return
	 */
	public String findAll() {
		// ����Service��ѯ����һ������
		List<Category> cList = categoryService.findAll();
		// ͨ��ֵջ����һ�����༯��:
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}

	/**
	 * ���һ������
	 * 
	 * @return
	 */
	public String save() {
		// ����Service��ɱ���һ������
		categoryService.save(category);
		// ����ҳ����ת:
		return "saveSuccess";
	}

	/**
	 * ɾ��һ������
	 * 
	 * @return
	 */
	public String delete() {
		// ����Service��� һ�������ɾ��
		// ����ɾ��һ���Ȳ�ѯ��ɾ��:
		category = categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		// ����ҳ��ת��:
		return "deleteSuccess";
	}

	/**
	 * ��ת���޸�ҳ��
	 * 
	 * @return
	 */
	public String edit() {
		// ����cid:
		// ����cid���в�ѯ:
		category = categoryService.findByCid(category.getCid());
		// ���ҳ��ת��:��һ������������ʾ��ҳ����.
		return "editSuccess";
	}

	/**
	 * �޸�һ������
	 * 
	 * @return
	 */
	public String update() {
		// ʹ��ģ����������ǰ̨�ύ����:
		categoryService.update(category);
		// ҳ����ת:
		return "updateSuccess";
	}
}
