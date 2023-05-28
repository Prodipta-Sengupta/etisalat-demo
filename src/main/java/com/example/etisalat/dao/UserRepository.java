package com.example.etisalat.dao;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByEmail(String email);

	public User findByName(String name);

}
