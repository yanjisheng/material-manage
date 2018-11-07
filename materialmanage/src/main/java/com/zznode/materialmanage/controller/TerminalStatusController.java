package com.zznode.materialmanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zznode.materialmanage.dao.TerminalStatusDao;
import com.zznode.materialmanage.exception.MaterialManageException;
import com.zznode.materialmanage.model.TerminalStatus;
import com.zznode.materialmanage.service.UserService;

/**
 * 终端状态
 * @author yanjisheng
 *
 */
@RestController
public class TerminalStatusController {

	@Autowired
	UserService userService;
	
	@Autowired
	TerminalStatusDao terminalStatusDao;
	
	@PostMapping("terminalStatus/add")
	public void add(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			@RequestParam Short statusCode, @RequestParam String statusName) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_MANAGER){
			throw new MaterialManageException("错误：仅限管理员操作");
		}
		try {
			terminalStatusDao.addTerminalStatus(statusCode, statusName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MaterialManageException("错误：statusCode或statusName与数据库中已存在的值重复");
		}
	}
	
	@GetMapping("terminalStatus/getAll")
	public List<TerminalStatus> getAll(){
		return terminalStatusDao.getAllStatus();
	}
}
