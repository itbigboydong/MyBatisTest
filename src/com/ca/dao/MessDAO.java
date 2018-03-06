package com.ca.dao;

import java.util.List;

import com.ca.domain.Mess;

public interface MessDAO {
	public void save(Mess mess);
    public List<Mess> findbyUserId(String userId);
    public void updateStatus(String userId);

}
