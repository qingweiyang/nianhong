package com.nianhong.service;

import java.util.List;

public interface RegionService {
	
	/**
	 * 获取地域所有 省份 信息
	 * 
	 * @return
	 */
	public List<String> loadProvince();
	
	/**
	 * 获取省份下的所有 市 信息
	 * 
	 * @param province
	 * @return
	 */
	public List<String> loadCity(String province);
}
