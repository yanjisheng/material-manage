package com.zznode.materialmanage.util;

import com.zznode.materialmanage.exception.ConverterException;

/**
 * byte数组转16进制字符串工具类
 * @author yanjisheng
 *
 */
public class ByteArrayHexStringConverter {

	public static String toHexString(byte[] byteArray){
		StringBuffer hexString = new StringBuffer();
		for(int i=0;i<byteArray.length;i++){
			int firstDigit = (byteArray[i]+128)/16;
			int secondDigit = (byteArray[i]+128)%16;
			if(firstDigit<=9){
				hexString.append(firstDigit);
			}else if(firstDigit==10){
				hexString.append('A');
			}else if(firstDigit==11){
				hexString.append('B');
			}else if(firstDigit==12){
				hexString.append('C');
			}else if(firstDigit==13){
				hexString.append('D');
			}else if(firstDigit==14){
				hexString.append('E');
			}else if(firstDigit==15){
				hexString.append('F');
			}				
			if(secondDigit<=9){
				hexString.append(secondDigit);
			}else if(secondDigit==10){
				hexString.append('A');
			}else if(secondDigit==11){
				hexString.append('B');
			}else if(secondDigit==12){
				hexString.append('C');
			}else if(secondDigit==13){
				hexString.append('D');
			}else if(secondDigit==14){
				hexString.append('E');
			}else if(secondDigit==15){
				hexString.append('F');
			}
		}
		return hexString.toString();
	}
	
	public static byte[] decodeHexString(String hexString) throws ConverterException{
		byte[] byteArray = new byte[hexString.length()/2];
		if(hexString.length()%2!=0){
			throw new ConverterException("错误：字符串长度必须为2的倍数");
		}
		for(int i=0; i<hexString.length()/2; i++){
			String twoDigits = hexString.substring(2*i, 2*i+2);
			byte byteDigit = (byte) (Integer.parseInt(twoDigits, 16) - 128);
			byteArray[i] = byteDigit;
		}
		return byteArray;
	}
}
