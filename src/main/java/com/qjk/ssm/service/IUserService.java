package com.qjk.ssm.service;

import java.util.List;

import com.qjk.ssm.data.User;
import com.qjk.ssm.exception.UserException;

public interface IUserService {
	
	User joinPhone(String phone,String password) throws UserException;
	
	User joinEmail(String email,String password) throws UserException;
	
	void addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(User user);
	
	User login(String account,String password) throws UserException;
	
	User findUserById(long id);
	
	List<User> queryUser();
	
}
