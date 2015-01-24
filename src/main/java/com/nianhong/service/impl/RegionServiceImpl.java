package com.nianhong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nianhong.dao.RegionDao;
import com.nianhong.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService{

	@Autowired
	private RegionDao regionDao;
	
	@Override
	public List<String> loadProvince() {
		return regionDao.loadProvince();
	}

	@Override
	public List<String> loadCity(String province) {
		return regionDao.loadCity(province);
	}

}
