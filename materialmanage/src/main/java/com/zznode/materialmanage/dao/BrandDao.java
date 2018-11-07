package com.zznode.materialmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zznode.materialmanage.model.Brand;

/**
 * 品牌管理
 * @author yanjisheng
 *
 */
@Mapper
public interface BrandDao {

	public Brand selectById(@Param(value = "brandId") int brandId);
	
	public List<Brand> selectByCondition(Brand brand);
	
	public int addOne(Brand brand);
	
	public int addList(List<Brand> list);
	
	public int deleteById(@Param(value = "brandId") int brandId);
}
