package com.SmoothStack.SmoothStackLoginCase5.Dao;

import java.sql.Connection;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Entity.User;

public interface IUserDao{
	
	public List<User> findAll(Connection conn);
	public List<User> findById(Connection conn, int userId);
}
