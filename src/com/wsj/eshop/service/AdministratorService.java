package com.wsj.eshop.service;

import com.wsj.eshop.bean.Administrator;
import com.wsj.eshop.dao.AdministratorDao;

public class AdministratorService {
	//×¢ÈëDao
	private AdministratorDao administratorDao;
	public void setAdministratorDao(AdministratorDao administratorDao) {
		this.administratorDao = administratorDao;
	}
	
	public Administrator login(Administrator admin) {
		return administratorDao.login(admin);
	}
}
