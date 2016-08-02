package com.qjk.ssm.exception;

public final class SmsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SmsException() {
		super();
	}
	
	public SmsException(String msg) {
		super(msg);
	}
	
	public SmsException(String msg,Throwable e){
		super(msg,e);
	}
}
