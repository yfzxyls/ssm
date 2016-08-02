package com.qjk.ssm.util;

import com.qjk.ssm.data.User;

public final class RedisUtil {
	public static String USER_PREFIX = "user:id:";
	
	private RedisUtil(){
		
	}
	
	public static String getKey(Class<?> c,long id){
		
		String key = "";
		
		if(c == User.class){
			key = USER_PREFIX+id;
		}
		
		return key;
	}
	
	
}
