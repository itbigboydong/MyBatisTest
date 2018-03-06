package com.ca.server;

import com.ca.domain.User;

public interface UserService {
	  public User findByName(String username);
	  public void save(User user);
	  public User findVerify(String username);
}
