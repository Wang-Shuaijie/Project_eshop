package com.wsj.eshop.web.action.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wsj.eshop.bean.CategorySecond;
import com.wsj.eshop.bean.Product;
import com.wsj.eshop.service.CategorySecondService;
import com.wsj.eshop.service.ProductService;
import com.wsj.eshop.util.PageBean;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product=new Product();
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	
	//����page����
	private int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	//ע��productService
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//ע��categoryService
	private CategorySecondService categorySecondService;
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	//�ļ��ϴ���3������
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	//����
	/**
	 * ��ѯ������Ʒ
	 * @return
	 */
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		// ��PageBean���ݴ��뵽ֵջ��.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// ҳ����ת
		return "findAll";
	}
    /**
     * ��ת�����ҳ��
     * @return
     */
	public String addPage() {
		// ��ѯ���еĶ�������:
		List<CategorySecond> csList = categorySecondService.findAll();
		// �����������������ʾ��ҳ����
		ActionContext.getContext().getValueStack().set("csList", csList);
		// ҳ����ת
		return "addPageSuccess";
	}

	/**
	 * �����Ʒ
	 * @return
	 * @throws IOException
	 */
	public String save() throws IOException {
		// ���ύ��������ӵ����ݿ���.
		product.setPdate(new Date());
		// product.setImage(image);
		if (upload != null) {
			// ����ƷͼƬ�ϴ�����������.
			// ����ϴ�ͼƬ�ķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + uploadFileName);
			// �ļ��ϴ�:
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
    /**
     * ɾ����Ʒ
     * @return
     */
	public String delete() {
		// ����id��ѯ��Ʒ��Ϣ
		product = productService.findByPid(product.getPid());
		// ɾ����Ʒ��ͼƬ:
		String path = ServletActionContext.getServletContext().getRealPath(
				"/" + product.getImage());
		File file = new File(path);
		file.delete();
		// ɾ�����ݿ�����Ʒ��¼:
		productService.delete(product);
		// ҳ����ת
		return "deleteSuccess";
	}
	/**
	 * �����޸�ҳ��
	 * @return
	 */
	public String edit() {
		// ������Ʒid��ѯ��Ʒ��Ϣ
		product = productService.findByPid(product.getPid());
		// ��ѯ���ж�������
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		// ҳ����ת���༭ҳ��:
		return "editSuccess";
	}
	/**
	 * �޸���Ʒ
	 * @return
	 * @throws IOException
	 */
	public String update() throws IOException {
		// ����Ϣ�޸ĵ����ݿ�
		product.setPdate(new Date());

		// �ϴ�:
		if (upload != null) {
			String delPath = ServletActionContext.getServletContext()
					.getRealPath("/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			// ����ϴ�ͼƬ�ķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + uploadFileName);
			// �ļ��ϴ�:
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		// ҳ����ת
		return "updateSuccess";
	}
}
