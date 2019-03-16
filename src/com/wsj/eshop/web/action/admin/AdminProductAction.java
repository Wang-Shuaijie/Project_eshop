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
	
	//接受page参数
	private int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	//注入productService
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//注入categoryService
	private CategorySecondService categorySecondService;
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	//文件上传的3个属性
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
	
	//功能
	/**
	 * 查询所有商品
	 * @return
	 */
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		// 将PageBean数据存入到值栈中.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// 页面跳转
		return "findAll";
	}
    /**
     * 跳转到添加页面
     * @return
     */
	public String addPage() {
		// 查询所有的二级分类:
		List<CategorySecond> csList = categorySecondService.findAll();
		// 将二级分类的数据显示到页面上
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转
		return "addPageSuccess";
	}

	/**
	 * 添加商品
	 * @return
	 * @throws IOException
	 */
	public String save() throws IOException {
		// 将提交的数据添加到数据库中.
		product.setPdate(new Date());
		// product.setImage(image);
		if (upload != null) {
			// 将商品图片上传到服务器上.
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
    /**
     * 删除商品
     * @return
     */
	public String delete() {
		// 根据id查询商品信息
		product = productService.findByPid(product.getPid());
		// 删除商品的图片:
		String path = ServletActionContext.getServletContext().getRealPath(
				"/" + product.getImage());
		File file = new File(path);
		file.delete();
		// 删除数据库中商品记录:
		productService.delete(product);
		// 页面跳转
		return "deleteSuccess";
	}
	/**
	 * 跳到修改页面
	 * @return
	 */
	public String edit() {
		// 根据商品id查询商品信息
		product = productService.findByPid(product.getPid());
		// 查询所有二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转到编辑页面:
		return "editSuccess";
	}
	/**
	 * 修改商品
	 * @return
	 * @throws IOException
	 */
	public String update() throws IOException {
		// 将信息修改到数据库
		product.setPdate(new Date());

		// 上传:
		if (upload != null) {
			String delPath = ServletActionContext.getServletContext()
					.getRealPath("/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		// 页面跳转
		return "updateSuccess";
	}
}
