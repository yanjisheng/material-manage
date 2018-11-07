package com.zznode.materialmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zznode.materialmanage.model.TerminalStatus;

/**
 * 终端状态
 * @author yanjisheng
 *
 */
@Mapper
public interface TerminalStatusDao {

	public int addTerminalStatus(@Param("statusCode") short statusCode, @Param("statusName") String statusName);
	
	public List<TerminalStatus> getAllStatus();
}
