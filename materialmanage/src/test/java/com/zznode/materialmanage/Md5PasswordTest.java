package com.zznode.materialmanage;

import org.junit.Test;

import com.zznode.materialmanage.util.Md5Password;

/**
 *
 * @author yanjisheng
 *
 */
public class Md5PasswordTest {
	
	@Test
	public void test(){
		System.out.println(Md5Password.toMd5("abc123", "staff"));
	}
}
