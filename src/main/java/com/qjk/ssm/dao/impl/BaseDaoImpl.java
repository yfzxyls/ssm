package com.qjk.ssm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.qjk.ssm.util.SerializeUtil;
@Repository
public class BaseDaoImpl<T> {
	
	@Resource
	SqlSessionTemplate sqlSessionTemplate;
	
	@Resource
	protected RedisTemplate<String, T> redisTemplate;
	
	protected void insert(String statement,T t){
		sqlSessionTemplate.insert(statement, t);
	}
	protected void delete(String statement,long id){
		sqlSessionTemplate.delete(statement, id);
	}
	protected T selectOne(String statement,long id){
		return sqlSessionTemplate.selectOne(statement, id);
	}
	protected List<T> selectList(String statement){
		return sqlSessionTemplate.selectList(statement);
	}
	
	protected void add2Redis(final String id, final T t) {
		
		  redisTemplate.execute(new RedisCallback<Boolean>() {  
	            public Boolean doInRedis(RedisConnection connection)  
	                    throws DataAccessException {  
	                byte[] key  = id.getBytes();  
	                byte[] value = SerializeUtil.serialize(t);  
	                return connection.setNX(key, value);
	            }  
	        });  
		
	}
	
    protected T getFromRedis(final String keyId) {  
        T result = redisTemplate.execute(new RedisCallback<T>() {  
            public T doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                byte[] key = keyId.getBytes();
                byte[] value = connection.get(key);  
                if (value == null) {  
                    return null;  
                }  
                T t = (T)SerializeUtil.unserialize(value);  
                return t;
            }  
        });  
        return result;  
    } 
    
    protected void deleteFromRedis(String key) {  
        List<String> list = new ArrayList<String>();  
        list.add(key);  
        deleteFromRedis(list);  
    }  
  
    /** 
     * 删除多个 
     * <br>------------------------------<br> 
     * @param keys 
     */  
    protected void deleteFromRedis(List<String> keys) {  
        redisTemplate.delete(keys);;  
    }  
    
    public boolean updateFromRedis(final String id,final T t) {  
       
        if (getFromRedis(id) == null) {  
        	add2Redis(id, t);
           return true;
        }  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                byte[] key  = id.getBytes();  
                byte[] value = SerializeUtil.serialize(t);  
                connection.set(key, value);  
                return true;
            }  
        });  
        return result;  
    }  
	
}
