package com.zznode.materialmanage.controller.helper;

import java.util.Locale;

/**
 * 分页查询用model
 * @author yanjisheng
 *
 */
public class PageQueryModel {
	
	private static String[] illegalOrderByWords = {"select", "insert", "update", "delele", "drop", "truncate", 
			"grant", ";"};//非法的排序字段词语（小写），用来防SQL注入
	private static int attackCount = 0;
	
	private Integer pageNum = 1;
	private Integer pageSize = 10;
	private String orderBy;
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		if(pageNum != null){
			this.pageNum = pageNum;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize != null){
			this.pageSize = pageSize;
		}
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		for(String word : illegalOrderByWords){
			if(orderBy.toLowerCase(Locale.ENGLISH).indexOf(word) >= 0){
				attackCount++;
				if(attackCount >= 10){
					throw new RuntimeException("我们已经报警，请立即停止攻击并到公安局自首");
				}
				return;
			}
		}
		this.orderBy = orderBy;
	}
	
	public static void resetAttackCount(){
		attackCount = 0;
	}
	
}
