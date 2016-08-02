package com.qjk.ssm.dao.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.stereotype.Repository;

import com.qjk.ssm.dao.IUserDao;
import com.qjk.ssm.data.User;
import com.qjk.ssm.util.RedisUtil;

@Repository(value="userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{


	public int addUser(User user) {
		
		int result = sqlSessionTemplate.insert("insertUser",user);
		
		this.add2Redis(RedisUtil.getKey(user.getClass(), user.getUid()), user);

		return result;

	}

	public int deleteUser(long id) {
		int result = sqlSessionTemplate.delete("deleteUser",id);
		this.deleteFromRedis(RedisUtil.getKey(User.class, id));
		return result;

	}

	@Test
	public User findUserById(long id) {
		
		User user = this.getFromRedis(RedisUtil.getKey(User.class, id));
		if(user == null){
			user = sqlSessionTemplate.selectOne("selectUser", id);
			this.add2Redis(RedisUtil.getKey(user.getClass(), user.getUid()), user);
			return user;
		}
		
		return user;
		
	}

	public List<User> selectUsers() {

		return sqlSessionTemplate.selectList("selectUserList");
	}

	public int updateUser(User user) {
		int result = sqlSessionTemplate.update("updateUser", user);
		this.updateFromRedis(RedisUtil.getKey(User.class, user.getUid()), user);
		return result;
	}
	
	public User findUserByAccount(String account) {
		
		return sqlSessionTemplate.selectOne("selectUserByAccount",account);
	}

}
