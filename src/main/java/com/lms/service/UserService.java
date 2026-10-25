package com.lms.service;

import java.util.List;

import com.lms.pojo.User;

public interface UserService {
	public User checkLogin(String username, String password);
	
	public boolean addUser(User user);
	
	public boolean updateUser(User user);
	
	public List<User> getAllUsers();
	
	public User getUserById(long id);
	
	public boolean deleteUser(long id);
}
