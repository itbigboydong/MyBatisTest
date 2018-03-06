package com.ca.mapper;

import com.ca.domain.User;

public interface IUserDAO {
	public User findByName(String username);
	public void save(User user);
	public User findVerify(String username);
}
