package com.ca.dao;

import com.ca.domain.User;

public interface UserDAO {
	public User findByName(String username);

}
