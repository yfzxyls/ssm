package com.qjk.ssm.exception;

public final class UserException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserException() {
		super();
	}
	
	public UserException(String msg) {
		super(msg);
	}
	
	public UserException(String msg,Throwable e){
		super(msg,e);
	}
}
