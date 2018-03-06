package com.ca.mapper;

import java.util.List;

import com.ca.domain.Mess;

public interface IMessDAO {
	public void save(Mess mess);
    public List<Mess> findbyUserId(String userId);
    public void updateStatus(String userId);

}
