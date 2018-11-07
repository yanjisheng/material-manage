package com.zznode.materialmanage.exception;

/**
 * byte数组转16进制字符串中出现的错误
 * @author yanjisheng
 *
 */
public class ConverterException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConverterException() {
		super();
	}

	public ConverterException(String message) {
		super(message);
	}	
}
