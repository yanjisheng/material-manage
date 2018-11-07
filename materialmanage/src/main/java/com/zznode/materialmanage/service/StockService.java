package com.zznode.materialmanage.service;

import com.github.pagehelper.PageSerializable;
import com.zznode.materialmanage.controller.helper.PageQueryModel;
import com.zznode.materialmanage.exception.MaterialManageException;
import com.zznode.materialmanage.model.Stock;
import com.zznode.materialmanage.model.StockDiscard;
import com.zznode.materialmanage.model.StockDiscardQuery;
import com.zznode.materialmanage.model.StockIn;
import com.zznode.materialmanage.model.StockInQuery;
import com.zznode.materialmanage.model.StockMaintenance;
import com.zznode.materialmanage.model.StockMaintenanceQuery;
import com.zznode.materialmanage.model.StockMove;
import com.zznode.materialmanage.model.StockMoveQuery;
import com.zznode.materialmanage.model.StockQuery;
import com.zznode.materialmanage.model.StockRecycle;
import com.zznode.materialmanage.model.StockRecycleQuery;
import com.zznode.materialmanage.model.StockReuse;
import com.zznode.materialmanage.model.StockReuseQuery;
import com.zznode.materialmanage.model.StockUse;
import com.zznode.materialmanage.model.StockUseQuery;

/**
 * 库存操作
 * @author yanjisheng
 *
 */
public interface StockService {

	/**
	 * 可用
	 */
	public static final short STATUS_AVAILABLE = 100;
	/**
	 * 预领
	 */
	public static final short STATUS_TO_USE = 201;
	/**
	 * 在用
	 */
	public static final short STATUS_IN_USE = 200;
	/**
	 * 待回收
	 */
	public static final short STATUS_TO_RECYCLE = 301;
	/**
	 * 已回收
	 */
	public static final short STATUS_RECYCLED = 300;
	/**
	 * 返修
	 */
	public static final short STATUS_MAINTAIN = 402;
	/**
	 * 报废
	 */
	public static final short STATUS_DISCARD = 400;
	/**
	 * 备件
	 */
	public static final short STATUS_REUSE = 101;
	
	/**
	 * 按sn查询库存
	 */
	public Stock getStockBySn(String terminalSn);
	
	/**
	 * 按条件查询库存
	 */
	public PageSerializable<Stock> queryStock(StockQuery condition, PageQueryModel pageQuery);
	
	/**
	 * 入库
	 */
	public void addStockIn(StockIn stockIn) throws MaterialManageException;
	
	/**
	 * 按id查询入库单
	 */
	public StockIn getStockInById(int id);
	
	/**
	 * 按条件查询入库单
	 */
	public PageSerializable<StockIn> queryStockIn(StockInQuery condition, PageQueryModel pageQuery);
	
	/**
	 * 调拨
	 */
	public void addStockMove(StockMove stockMove) throws MaterialManageException;
	
	/**
	 * 按id查询调拨单
	 */
	public StockMove getStockMoveById(int id);
	
	/**
	 * 按条件查询调拨单
	 */
	public PageSerializable<StockMove> queryStockMove(StockMoveQuery condition, PageQueryModel pageQuery);
	
	/**
	 * 领用
	 */
	public void addStockUse(StockUse stockUse) throws MaterialManageException;
	
	/**
	 * 安装（激活）
	 */
	public void updateStockUse(StockUse stockUse) throws MaterialManageException;
	
	/**
	 * 按id查询领用单
	 */
	public StockUse getStockUseById(int stockUseId);
	
	/**
	 * 按条件查询领用单
	 */
	public PageSerializable<StockUse> queryStockUse(StockUseQuery condition, PageQueryModel pageQuery);
	
	/**
	 * 准备回收
	 */
	public void addStockRecycle(StockRecycle stockRecycle) throws MaterialManageException;
	
	/**
	 * 回收入库
	 */
	public void updateStockRecycle(StockRecycle stockRecycle) throws MaterialManageException;
	
	/**
	 * 按id查询回收单
	 */
	public StockRecycle getStockRecycleById(int stockRecycleId);
	
	/**
	 * 按条件查询回收单
	 */
	public PageSerializable<StockRecycle> queryStockRecycle(StockRecycleQuery condition, PageQueryModel pageQuery);

	/**
	 * 返修
	 */
	public void addStockMaintenance(StockMaintenance stockMaintenance) throws MaterialManageException;
	
	/**
	 * 按id查询返修单
	 */
	public StockMaintenance getStockMaintenanceById(int stockMaintenanceId);
	
	/**
	 * 按条件查询返修单
	 */
	public PageSerializable<StockMaintenance> queryStockMaintenance(StockMaintenanceQuery condition, PageQueryModel pageQuery);

	/**
	 * 报废
	 */
	public void addStockDiscard(StockDiscard stockDiscard) throws MaterialManageException;
	
	/**
	 * 按id查询报废单
	 */
	public StockDiscard getStockDiscardById(int stockDiscardId);
	
	/**
	 * 按条件查询报废单
	 */
	public PageSerializable<StockDiscard> queryStockDiscard(StockDiscardQuery condition, PageQueryModel pageQuery);

	/**
	 * 新备件单
	 */
	public void addStockReuse(StockReuse stockReuse) throws MaterialManageException;
	
	/**
	 * 按id查询备件单
	 */
	public StockReuse getStockReuseById(int stockReuseId);
	
	/**
	 * 按条件查询备件单
	 */
	public PageSerializable<StockReuse> queryStockReuse(StockReuseQuery condition, PageQueryModel pageQuery);
}
