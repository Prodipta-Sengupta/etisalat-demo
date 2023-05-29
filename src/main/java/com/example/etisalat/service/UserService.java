package com.example.etisalat.service;

import java.util.List;

import com.example.etisalat.dao.User;

public interface UserService {
	public List<User> getAllUsers();

	public User getUserByName(String name);

	public User getUserByEmail(String email);

	public User createOrUpdateUser(User user);

	public User updateUserByName(User user);

	public User updateUserByEmail(User user);

	public void deleteUserByName(String name);

	public void deleteUserByemail(String email);

}
