package com.zznode.materialmanage.util;

import java.util.Random;

/**
 * 生成随机的int类型整数
 * @author yanjisheng
 *
 */
public class RandomInteger {

	private static Random random = new Random();
	
	public static int newRandom(){	
		return random.nextInt();
	}
}
