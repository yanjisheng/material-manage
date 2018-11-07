package com.zznode.materialmanage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zznode.materialmanage.controller.MaterialManageExceptionHandler;
import com.zznode.materialmanage.controller.helper.PageQueryModel;

/**
 * 定时任务
 * @author yanjisheng
 *
 */

@Component
@EnableScheduling
public class BasicSchedule {
	
	private static Logger log = LoggerFactory.getLogger(BasicSchedule.class);
	
	private static final long TEN_MINUTES = 600000;
	
	@Scheduled(fixedRate = TEN_MINUTES)
	public void resetAttackCount(){
		MaterialManageExceptionHandler.resetErrorCount();
		PageQueryModel.resetAttackCount();
		log.info("执行定时任务：重置攻击次数");	
	}
}
