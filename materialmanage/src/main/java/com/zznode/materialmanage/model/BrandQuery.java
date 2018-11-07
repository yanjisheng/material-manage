package com.zznode.materialmanage.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 品牌查询条件
 * @author yanjisheng
 *
 */
public class BrandQuery extends Brand {

	private static final long serialVersionUID = 1L;
	
	private List<String> terminalTypeList;
	private List<String> manufacturerList;
	private List<String> networkModeList;
	
	public List<String> getTerminalTypeList() {
		return terminalTypeList;
	}
	public void setTerminalTypeList(List<String> terminalTypeList) {
		this.terminalTypeList = terminalTypeList;
	}
	public List<String> getManufacturerList() {
		return manufacturerList;
	}
	public void setManufacturerList(List<String> manufacturerList) {
		this.manufacturerList = manufacturerList;
	}
	public List<String> getNetworkModeList() {
		return networkModeList;
	}
	public void setNetworkModeList(List<String> networkModeList) {
		this.networkModeList = networkModeList;
	}
	
	@Override
	public void setTerminalType(String terminalType) {
		super.setTerminalType(terminalType);
		if(terminalTypeList == null){
			terminalTypeList = new ArrayList<>();
		}
		this.terminalTypeList.add(terminalType);
	}
	@Override
	public void setManufacturer(String manufacturer) {
		super.setManufacturer(manufacturer);
		if(manufacturerList == null){
			manufacturerList = new ArrayList<>();
		}
		this.manufacturerList.add(manufacturer);
	}
	@Override
	public void setNetworkMode(String networkMode) {
		super.setNetworkMode(networkMode);
		if(networkModeList == null){
			networkModeList = new ArrayList<>();
		}
		this.networkModeList.add(networkMode);
	}
		
}
