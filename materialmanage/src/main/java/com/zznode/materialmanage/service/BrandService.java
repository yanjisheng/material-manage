package com.zznode.materialmanage.service;

import java.util.List;

import com.github.pagehelper.PageSerializable;
import com.zznode.materialmanage.controller.helper.PageQueryModel;
import com.zznode.materialmanage.model.Brand;
import com.zznode.materialmanage.model.BrandQuery;

/**
 * 品牌管理
 * @author yanjisheng
 *
 */
public interface BrandService {
	
	/**
	 * 按id查询品牌信息
	 */
	public Brand selectById(int brandId);
	
	/**
	 * 按条件分页查询品牌信息
	 */
	public PageSerializable<Brand> selectByCondition(BrandQuery condition, PageQueryModel pageQuery);
	
	/**
	 * 添加品牌信息
	 */
	public void addOne(Brand brand);
	
	/**
	 * 批量添加品牌信息
	 */
	public void addList(List<Brand> brandList);
}
