package com.zznode.materialmanage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.zznode.materialmanage.controller.helper.PageQueryModel;
import com.zznode.materialmanage.dao.BrandDao;
import com.zznode.materialmanage.model.Brand;
import com.zznode.materialmanage.model.BrandQuery;
import com.zznode.materialmanage.service.BrandService;

/**
 * 品牌管理
 * @author yanjisheng
 *
 */
@Service
public class BrandServiceImpl implements BrandService {
	
	private static Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);
	
	@Autowired
	BrandDao dao;

	@Override
	public Brand selectById(int brandId) {
		log.info("按id["+brandId+"]查询品牌信息");
		return dao.selectById(brandId);
	}

	@Override
	public PageSerializable<Brand> selectByCondition(BrandQuery condition, PageQueryModel pageQuery) {
		log.info("按条件分页查询品牌信息");
		if(StringUtils.isEmpty(pageQuery.getOrderBy())){
			pageQuery.setOrderBy("brandId DESC");
		}
		PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
		List<Brand> list = dao.selectByCondition(condition);
		return new PageSerializable<Brand>(list);
	}

	@Override
	public void addOne(Brand brand) {
		log.info("添加品牌信息");
		dao.addOne(brand);	
	}

	@Override
	public void addList(List<Brand> brandList) {
		log.info("批量添加品牌信息");
		dao.addList(brandList);
	}
	
	
}
