package com.ca.server;

import java.util.List;

import com.ca.domain.Mess;

public interface MessService {
	   public void save(Mess mess);
	   public List<Mess> findByUserId(String userId);
	   public void UpdateStatus(String userId);

}
