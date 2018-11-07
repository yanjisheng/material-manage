package com.zznode.materialmanage.exception;

/**
 * 自定义异常类，用来处理物料管理系统中的业务异常
 * @author yanjisheng
 *
 */
public class MaterialManageException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MaterialManageException(){
		super();
	}
	
	public MaterialManageException(String message){
		super(message);
	}

	public MaterialManageException(String message, Throwable cause) {
		super(message, cause);
	}

}
