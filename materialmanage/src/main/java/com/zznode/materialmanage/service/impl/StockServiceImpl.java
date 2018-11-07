package com.zznode.materialmanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.zznode.materialmanage.controller.helper.PageQueryModel;
import com.zznode.materialmanage.dao.StockDao;
import com.zznode.materialmanage.dao.StockDiscardDao;
import com.zznode.materialmanage.dao.StockInDao;
import com.zznode.materialmanage.dao.StockMaintenanceDao;
import com.zznode.materialmanage.dao.StockMoveDao;
import com.zznode.materialmanage.dao.StockRecycleDao;
import com.zznode.materialmanage.dao.StockReuseDao;
import com.zznode.materialmanage.dao.StockUseDao;
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
import com.zznode.materialmanage.service.StockService;
import com.zznode.materialmanage.util.RandomInteger;

/**
 * 库存操作
 * @author yanjisheng
 *
 */
@Service
public class StockServiceImpl implements StockService {

	private static Logger log = LoggerFactory.getLogger(StockServiceImpl.class);
	
	@Autowired
	StockDao stockDao;
	
	@Autowired
	StockInDao stockInDao;
	
	@Autowired
	StockMoveDao stockMoveDao;
	
	@Autowired
	StockUseDao stockUseDao;
	
	@Autowired
	StockRecycleDao stockRecycleDao;
	
	@Autowired
	StockMaintenanceDao stockMaintenanceDao;
	
	@Autowired
	StockDiscardDao stockDiscardDao;
	
	@Autowired
	StockReuseDao stockReuseDao;
	
	@Override
	public Stock getStockBySn(String terminalSn) {
		log.info("按终端sn["+terminalSn+"]查询库存");
		return stockDao.selectBySn(terminalSn);
	}

	@Override
	public PageSerializable<Stock> queryStock(StockQuery condition, PageQueryModel pageQuery) {
		log.info("按条件分页查询库存");
		if(StringUtils.isEmpty(pageQuery.getOrderBy())){
			pageQuery.setOrderBy("terminalId DESC");
		}
		PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
		List<Stock> list = stockDao.selectByCondition(condition);
		return new PageSerializable<Stock>(list);
	}

	@Transactional(rollbackFor = MaterialManageException.class)
	@Override
	public void addStockIn(StockIn stockIn) throws MaterialManageException {
		log.info("入库-操作人id["+stockIn.getOperatorId()+"]-仓库id["+stockIn.getWarehouseId()+"]");
		stockIn.setStockInId(RandomInteger.newRandom());
		List<Stock> stockList = new ArrayList<Stock>();
		for(String terminalSn : stockIn.getSnList()){
			Stock stock = new Stock();
			stock.setBrandId(stockIn.getBrandId());
			stock.setWarehouseId(stockIn.getWarehouseId());
			stock.setTerminalSn(terminalSn);
			stock.setStatus(STATUS_AVAILABLE);
			stockList.add(stock);
		}
		try {
			int addResult = stockDao.addStockList(stockList);
			if(addResult != stockList.size()){
				throw new MaterialManageException("入库单错误，请检查入库终端SN号是否与已入库的终端SN号重复");
			}
		} catch (Exception e) {
			throw new MaterialManageException("入库单错误，请检查入库终端SN号是否与已入库的终端SN号重复", e);
		}
		stockInDao.addStockIn(stockIn);	
		stockInDao.addStockInSnList(stockIn);
	}	

	@Override
	public StockIn getStockInById(int id) {
		log.info("按id["+id+"]查询入库单");
		return stockInDao.selectById(id);
	}

	@Override
	public PageSerializable<StockIn> queryStockIn(StockInQuery condition, PageQueryModel pageQuery) {
		log.info("按条件分页查询入库单");
		if(StringUtils.isEmpty(pageQuery.getOrderBy())){
			pageQuery.setOrderBy("operateTime DESC");
		}
		PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
		List<StockIn> list = stockInDao.selectByCondition(condition);
		return new PageSerializable<StockIn>(list);
	}

	@Transactional(rollbackFor = MaterialManageException.class)
	@Override
	public void addStockMove(StockMove stockMove) throws MaterialManageException {
		log.info("调拨-操作人id["+stockMove.getOperatorId()+"]-调入仓库id["+stockMove.getWarehouseInId()
				+"]-调出仓库id["+stockMove.getWarehouseOutId()+"]");
		stockMove.setStockMoveId(RandomInteger.newRandom());
		for(String terminalSn : stockMove.getSnList()){
			Stock stock = new Stock();
			stock.setTerminalSn(terminalSn);
			stock.setWarehouseId(stockMove.getWarehouseInId());
			try {
				int updateResult = stockDao.updateStock(stock);
				if(updateResult != 1){
					throw new MaterialManageException("调拨单错误，请检查调拨终端SN号是否已入库");
				}
			} catch (Exception e) {
				throw new MaterialManageException("调拨单错误，请检查调拨终端SN号是否已入库", e);
			}
		}
		stockMoveDao.addStockMove(stockMove);
		stockMoveDao.addStockMoveSnList(stockMove);
	}

	@Override
	public StockMove getStockMoveById(int id) {
		log.info("按id["+id+"]查询调拨单");
		return stockMoveDao.selectById(id);
	}

	@Override
	public PageSerializable<StockMove> queryStockMove(StockMoveQuery condition, PageQueryModel pageQuery) {
		log.info("按条件分页查询调拨单");
		if(StringUtils.isEmpty(pageQuery.getOrderBy())){
			pageQuery.setOrderBy("operateTime DESC");
		}
		PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
		List<StockMove> list = stockMoveDao.selectByCondition(condition);
		return new PageSerializable<>(list);
	}

	@Transactional(rollbackFor = MaterialManageException.class)
	@Override
	public void addStockUse(StockUse stockUse) throws MaterialManageException {
		log.info("操作人["+stockUse.getOperatorId()+"]领用终端["+stockUse.getTerminalSn()+"]");
		Stock stock = stockDao.selectBySn(stockUse.getTerminalSn());
		if(stock == null){
			MaterialManageException e = new MaterialManageException("领用单错误，请检查领用终端SN号是否已入库");
			throw e;
		}
		stock.setStatus(STATUS_TO_USE);
		stockDao.updateStock(stock);
		stockUseDao.addStockUse(stockUse);
	}

	@Transactional(rollbackFor = MaterialManageException.class)
	@Override
	public void updateStockUse(StockUse stockUse) throws MaterialManageException {
		log.info("操作人["+stockUse.getInstallerId()+"]安装（激活）终端(领用单号["+stockUse.getStockUseId()+"])");
		StockUse stockUseInDatabase = stockUseDao.selectById(stockUse.getStockUseId());
		Stock stock = stockDao.selectBySn(stockUseInDatabase.getTerminalSn());
		if(stock == null){
			MaterialManageException e = new MaterialManageException("领用单错误，请检查领用终端SN号是否已入库");
			throw e;
		}
		stock.setStatus(STATUS_IN_USE);
		stockDao.updateStock(stock);
		stockUseDao.updateStockUse(stockUse);
	}

	@Override
	public StockUse getStockUseById(int stockUseId) {
		log.info("按id["+stockUseId+"]查询领用单");
		return stockUseDao.selectById(stockUseId);
	}

	@Override
	public PageSerializable<StockUse> queryStockUse(StockUseQuery condition, PageQueryModel pageQuery) {
		log.info("按条件分页查询领用单");
		if(StringUtils.isEmpty(pageQuery.getOrderBy())){
			pageQuery.setOrderBy("operateTime DESC");
		}
		PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
		List<StockUse> list = stockUseDao.selectByCondition(condition);
		return new PageSerializable<>(list);
	}

	@Transactional(rollbackFor = MaterialManageException.class)
	@Override
	public void addStockRecycle(StockRecycle stockRecycle) throws MaterialManageException {
		log.info("操作人["+stockRecycle.getInstallerId()+"]准备回收终端["+stockRecycle.getTerminalSn()+"]");
		Stock stock = stockDao.selectBySn(stockRecycle.getTerminalSn());
		if(stock == null){
			MaterialManageException e = new MaterialManageException("回收单错误，请检查回收终端SN号是否已入库");
			throw e;
		}
		stock.setStatus(STATUS_TO_RECYCLE);
		stockDao.updateStock(stock);
		stockRecycleDao.addStockRecycle(stockRecycle);
	}

	@Transactional(rollbackFor = MaterialManageException.class)
	@Override
	public void updateStockRecycle(StockRecycle stockRecycle) throws MaterialManageException {
		log.info("操作人["+stockRecycle.getOperatorId()+"]回收入库终端(回收单号["+stockRecycle.getStockRecycleId()+"])");
		StockRecycle stockRecyleInDatabase = stockRecycleDao.selectById(stockRecycle.getStockRecycleId());
		Stock stock = stockDao.selectBySn(stockRecyleInDatabase.getTerminalSn());
		if(stock == null){
			MaterialManageException e = new MaterialManageException("回收单错误，请检查回收终端SN号是否已入库");
			throw e;
		}
		stock.setStatus(STATUS_RECYCLED);
		stock.setWarehouseId(stockRecycle.getWarehouseId());
		stockDao.updateStock(stock);
		stockRecycleDao.updateStockRecycle(stockRecycle);
	}

	@Override
	public StockRecycle getStockRecycleById(int stockRecycleId) {
		log.info("按id["+stockRecycleId+"]查询回收单");
		return stockRecycleDao.selectById(stockRecycleId);
	}

	@Override
	public PageSerializable<StockRecycle> queryStockRecycle(StockRecycleQuery condition, PageQueryModel pageQuery) {
		log.info("按条件分页查询回收单");
		if(StringUtils.isEmpty(pageQuery.getOrderBy())){
			pageQuery.setOrderBy("operateTime DESC");
		}
		PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
		List<StockRecycle> list = stockRecycleDao.selectByCondition(condition);
		return new PageSerializable<>(list);
	}

	@Transactional(rollbackFor = MaterialManageException.class)
	@Override
	public void addStockMaintenance(StockMaintenance stockMaintenance) throws MaterialManageException {
		log.info("操作人["+stockMaintenance.getOperatorId()+"]返修回收终端["+stockMaintenance.getTerminalSn()+"]");
		Stock stock = stockDao.selectBySn(stockMaintenance.getTerminalSn());
		if(stock == null){
			throw new MaterialManageException("返修单错误，请检查返修终端SN号是否已入库");
		}
		stock.setStatus(STATUS_MAINTAIN);
		stock.setWarehouseId(stockMaintenance.getWarehouseId());
		stockDao.updateStock(stock);
		stockMaintenanceDao.addStockMaintenance(stockMaintenance);
	}

	@Override
	public StockMaintenance getStockMaintenanceById(int stockMaintenanceId) {
		log.info("按id["+stockMaintenanceId+"]查询返修单");
		return stockMaintenanceDao.selectById(stockMaintenanceId);
	}

	@Override
	public PageSerializable<StockMaintenance> queryStockMaintenance(StockMaintenanceQuery condition, PageQueryModel pageQuery) {
		log.info("按条件分页查询返修单");
		if(StringUtils.isEmpty(pageQuery.getOrderBy())){
			pageQuery.setOrderBy("operateTime DESC");
		}
		PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
		List<StockMaintenance> list = stockMaintenanceDao.selectByCondition(condition);
		return new PageSerializable<>(list);
	}
	
	@Transactional(rollbackFor = MaterialManageException.class)
	@Override
	public void addStockDiscard(StockDiscard stockDiscard) throws MaterialManageException {
		log.info("操作人["+stockDiscard.getOperatorId()+"]报废终端["+stockDiscard.getTerminalSn()+"]");
		Stock stock = stockDao.selectBySn(stockDiscard.getTerminalSn());
		if(stock == null){
			throw new MaterialManageException("报废单错误，请检查返修终端SN号是否已入库");
		}
		stock.setStatus(STATUS_DISCARD);
		stockDao.updateStock(stock);
		stockDiscardDao.addStockDiscard(stockDiscard);
	}

	@Override
	public StockDiscard getStockDiscardById(int stockDiscardId) {
		log.info("按id["+stockDiscardId+"]查询报废单");
		return stockDiscardDao.selectById(stockDiscardId);
	}

	@Override
	public PageSerializable<StockDiscard> queryStockDiscard(StockDiscardQuery condition, PageQueryModel pageQuery) {
		log.info("按条件分页查询报废单");
		if(StringUtils.isEmpty(pageQuery.getOrderBy())){
			pageQuery.setOrderBy("operateTime DESC");
		}
		PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
		List<StockDiscard> list = stockDiscardDao.selectByCondition(condition);
		return new PageSerializable<>(list);
	}
	
	@Transactional(rollbackFor = MaterialManageException.class)
	@Override
	public void addStockReuse(StockReuse stockReuse) throws MaterialManageException {
		log.info("操作人["+stockReuse.getOperatorId()+"]备件终端["+stockReuse.getTerminalSn()+"]");
		Stock stock = stockDao.selectBySn(stockReuse.getTerminalSn());
		if(stock == null){
			throw new MaterialManageException("备件单错误，请检查备件终端SN号是否已入库");
		}
		stock.setStatus(STATUS_REUSE);
		stockDao.updateStock(stock);
		stockReuseDao.addStockReuse(stockReuse);
	}

	@Override
	public StockReuse getStockReuseById(int stockReuseId) {
		log.info("按id["+stockReuseId+"]查询备件单");
		return stockReuseDao.selectById(stockReuseId);
	}

	@Override
	public PageSerializable<StockReuse> queryStockReuse(StockReuseQuery condition, PageQueryModel pageQuery) {
		log.info("按条件分页查询备件单");
		if(StringUtils.isEmpty(pageQuery.getOrderBy())){
			pageQuery.setOrderBy("operateTime DESC");
		}
		PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
		List<StockReuse> list = stockReuseDao.selectByCondition(condition);
		return new PageSerializable<>(list);
	}

}
