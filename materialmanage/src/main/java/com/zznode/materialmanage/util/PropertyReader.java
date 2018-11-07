package com.zznode.materialmanage.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 读取自定义配置
 * @author yanjisheng
 *
 */
@Configuration
@ConfigurationProperties(prefix="materialmanage")
public class PropertyReader {

	private String tokenKey;
	private Integer tokenValidHours;
	private boolean enableTokenAuth;
	
	public String getTokenKey() {
		return tokenKey;
	}
	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}
	public Integer getTokenValidHours() {
		return tokenValidHours;
	}
	public void setTokenValidHours(Integer tokenValidHours) {
		this.tokenValidHours = tokenValidHours;
	}
	public boolean isEnableTokenAuth() {
		return enableTokenAuth;
	}
	public void setEnableTokenAuth(boolean enableTokenAuth) {
		this.enableTokenAuth = enableTokenAuth;
	}
	
}
