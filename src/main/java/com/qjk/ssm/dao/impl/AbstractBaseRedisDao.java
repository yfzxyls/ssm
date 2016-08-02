package com.qjk.ssm.dao.impl;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

public abstract class AbstractBaseRedisDao<K, V> {
	
	@Resource
	protected RedisTemplate<K, V> redisTemplate;

	/**
	 * redisTemplate
	 * 
	 * @param redisTemplate
	 *            the redisTemplate to set
	 */
	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}
