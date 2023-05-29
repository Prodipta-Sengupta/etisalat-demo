package com.example.etisalat.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.etisalat.dao.User;
import com.example.etisalat.dao.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;

	@Override
	public User getUserByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		Iterable<User> usersIterable = userRepository.findAll();
		Iterator<User> userIterator = usersIterable.iterator();
		while (userIterator.hasNext()) {
			users.add(userIterator.next());
		}

		return users;
	}

	@Override
	public User createOrUpdateUser(User user) {
		return userRepository.save(user);

	}

	@Override
	public User updateUserByName(User user) {
		User fetchedUser = userRepository.findByName(user.getName());
		fetchedUser.setEmail(user.getEmail());
		return userRepository.save(fetchedUser);
	}

	@Override
	public User updateUserByEmail(User user) {
		User fetchedUser = userRepository.findByEmail(user.getEmail());
		fetchedUser.setName(user.getName());
		return userRepository.save(fetchedUser);
	}

	@Override
	public void deleteUserByName(String name) {
		User user = userRepository.findByName(name);
		if (user != null) {
			Integer id = user.getId();
			userRepository.deleteById(id);
		}
	}

	@Override
	public void deleteUserByemail(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			Integer id = user.getId();
			userRepository.deleteById(id);
		}
	}

}
