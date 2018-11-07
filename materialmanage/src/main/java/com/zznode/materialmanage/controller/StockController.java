package com.zznode.materialmanage.controller;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageSerializable;
import com.zznode.materialmanage.controller.helper.PageQueryModel;
import com.zznode.materialmanage.exception.MaterialManageException;
import com.zznode.materialmanage.model.Stock;
import com.zznode.materialmanage.model.StockIn;
import com.zznode.materialmanage.model.StockInQuery;
import com.zznode.materialmanage.model.StockMove;
import com.zznode.materialmanage.model.StockMoveQuery;
import com.zznode.materialmanage.model.StockQuery;
import com.zznode.materialmanage.model.StockRecycle;
import com.zznode.materialmanage.model.StockRecycleQuery;
import com.zznode.materialmanage.model.StockUse;
import com.zznode.materialmanage.model.StockUseQuery;
import com.zznode.materialmanage.model.User;
import com.zznode.materialmanage.service.StockService;
import com.zznode.materialmanage.service.UserService;

/**
 * 库存
 * @author yanjisheng
 *
 */
@RestController
public class StockController {
	
	@Autowired
	StockService stockService;
	
	@Autowired
	UserService userService;
	
	/**
	 * 查询库存
	 */
	@GetMapping("stock/queryStock")
	public PageSerializable<Stock> queryStock(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			StockQuery condition, PageQueryModel pageQuery) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		return stockService.queryStock(condition, pageQuery);
	}
	
	/**
	 * 按SN号查询终端信息
	 */
	@GetMapping("stock/getStockBySn")
	public Stock getStockById(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString, 
			@RequestParam String terminalSn) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		return stockService.getStockBySn(terminalSn);
	}
	
	/**
	 * 入库
	 */
	@PostMapping("stock/addStockIn")
	public void addStockIn(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			Integer operatorId, Integer warehouseId, Integer brandId, String remark, String[] sn) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		StockIn stockIn = new StockIn();
		if(operatorId != null){
			stockIn.setOperatorId(operatorId);
		}else{
			User user = userService.getUserByLoginName(loginName);
			if(user != null){
				stockIn.setOperatorId(user.getUserId());
			}
		}
		stockIn.setOperateTime(new Date());
		stockIn.setWarehouseId(warehouseId);
		stockIn.setBrandId(brandId);
		stockIn.setRemark(remark);
		stockIn.setSnList(Arrays.asList(sn));
		stockService.addStockIn(stockIn);
	}
	
	/**
	 * 查询入库单信息
	 */
	@GetMapping("stock/queryStockIn")
	public PageSerializable<StockIn> queryStockIn(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			StockInQuery condition, PageQueryModel pageQuery) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		return stockService.queryStockIn(condition, pageQuery);
	}
	
	/**
	 * 按id查询入库单详细信息
	 */
	@GetMapping("stock/getStockInDetail")
	public StockIn getStockInDetail(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			Integer id) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		return stockService.getStockInById(id);
	}
	
	/**
	 * 调拨 
	 */
	@PostMapping("stock/addStockMove")
	public void addStockMove(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString, 
			Integer operatorId, Integer warehouseInId, Integer warehouseOutId, String remark, String[] sn) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		StockMove stockMove = new StockMove();
		if(operatorId != null){
			stockMove.setOperatorId(operatorId);
		}else{
			User user = userService.getUserByLoginName(loginName);
			if(user != null){
				stockMove.setOperatorId(user.getUserId());
			}
		}
		stockMove.setOperateTime(new Date());
		stockMove.setWarehouseInId(warehouseInId);
		stockMove.setWarehouseOutId(warehouseOutId);
		stockMove.setRemark(remark);
		stockMove.setSnList(Arrays.asList(sn));
		stockService.addStockMove(stockMove);
	}
	
	/**
	 * 查询调拨单信息
	 */
	@GetMapping("stock/queryStockMove")
	public PageSerializable<StockMove> queryStockMove(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			StockMoveQuery condition, PageQueryModel pageQuery) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		return stockService.queryStockMove(condition, pageQuery);
	}
	
	/**
	 * 按id查询调拨单详细信息
	 */
	@GetMapping("stock/getStockMoveDetail")
	public StockMove getStockMoveDetail(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			Integer id) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		return stockService.getStockMoveById(id);
	}
	
	/**
	 * 领用
	 */
	@PostMapping("stock/addStockUse")
	public void addStockUse(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			StockUse stockUse) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		if(stockUse.getOperatorId() == null){
			User user = userService.getUserByLoginName(loginName);
			if(user != null){
				stockUse.setOperatorId(user.getUserId());
			}
		}
		stockUse.setOperateTime(new Date());
		stockService.addStockUse(stockUse);
	}
	
	/**
	 * 安装（激活）
	 */
	@PostMapping("stock/updateStockUse")
	public void updateStockUse(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			StockUse stockUse) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		if(stockUse.getInstallerId() == null){
			User user = userService.getUserByLoginName(loginName);
			if(user != null){
				stockUse.setInstallerId(user.getUserId());
			}
		}
		stockUse.setInstallTime(new Date());
		stockService.updateStockUse(stockUse);
	}
	
	/**
	 * 查询领用单信息
	 */
	@GetMapping("stock/queryStockUse")
	public PageSerializable<StockUse> queryStockUse(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			StockUseQuery condition, PageQueryModel pageQuery) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		return stockService.queryStockUse(condition, pageQuery);
	}
	
	/**
	 * 按id查询领用单详细信息
	 */
	@GetMapping("stock/getStockUseDetail")
	public StockUse getStockUseDetail(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			Integer stockUseId) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		return stockService.getStockUseById(stockUseId);
	}
	
	/**
	 * 准备回收
	 */
	@PostMapping("stock/addStockRecycle")
	public void addStockRecycle(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			StockRecycle stockRecycle) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		if(stockRecycle.getInstallerId() == null){
			User user = userService.getUserByLoginName(loginName);
			if(user != null){
				stockRecycle.setInstallerId(user.getUserId());
			}
		}
		stockRecycle.setOperateTime(new Date());
		stockService.addStockRecycle(stockRecycle);
	}
	
	/**
	 * 回收入库
	 */
	@PostMapping("stock/updateStockRecycle")
	public void updateStockRecycle(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			StockRecycle stockRecycle) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		if(stockRecycle.getOperatorId() == null){
			User user = userService.getUserByLoginName(loginName);
			if(user != null){
				stockRecycle.setOperatorId(user.getUserId());
			}
		}
		stockRecycle.setRecycleTime(new Date());
		stockService.updateStockRecycle(stockRecycle);
	}
	
	/**
	 * 查询回收单信息
	 */
	@GetMapping("stock/queryStockRecycle")
	public PageSerializable<StockRecycle> queryStockRecycle(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			StockRecycleQuery condition, PageQueryModel pageQuery) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		return stockService.queryStockRecycle(condition, pageQuery);
	}
	
	/**
	 * 按id查询回收单详细信息
	 */
	@GetMapping("stock/getStockRecycleDetail")
	public StockRecycle getStockRecycleDetail(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString,
			Integer stockRecycleId) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		return stockService.getStockRecycleById(stockRecycleId);
	}
}
