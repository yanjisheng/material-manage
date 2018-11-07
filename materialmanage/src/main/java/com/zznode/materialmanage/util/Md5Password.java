package com.zznode.materialmanage.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密工具类
 * @author yanjisheng
 *
 */
public class Md5Password {
	
	public static String toMd5(String password){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] temp = (password+"yjs").getBytes("UTF-8");
			byte[] buff = md.digest(temp);
			return ByteArrayHexStringConverter.toHexString(buff);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String toMd5(String password, String loginName){
		return toMd5(password+loginName);
	}
	
}
