package com.zznode.materialmanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageSerializable;
import com.zznode.materialmanage.controller.helper.PageQueryModel;
import com.zznode.materialmanage.exception.MaterialManageException;
import com.zznode.materialmanage.model.Brand;
import com.zznode.materialmanage.model.BrandQuery;
import com.zznode.materialmanage.service.BrandService;
import com.zznode.materialmanage.service.UserService;

/**
 * 品牌管理
 * @author yanjisheng
 *
 */
@RestController
public class BrandController {

	@Autowired
	BrandService brandService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("brand/addOne")
	public void addOne(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString, 
			Brand brand) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_MANAGER){
			throw new MaterialManageException("错误：仅限管理员操作");
		}
		brandService.addOne(brand);
	}
	
	@GetMapping("brand/getById")
	public Brand getById(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString, 
			int brandId) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限正式员工操作");
		}
		return brandService.selectById(brandId);
	}
	
	@GetMapping("brand/queryByCondition")
	public PageSerializable<Brand> queryByCondition(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString, 
			BrandQuery condition, PageQueryModel pageQuery) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限正式员工操作");
		}
		return brandService.selectByCondition(condition, pageQuery);
	}
}
