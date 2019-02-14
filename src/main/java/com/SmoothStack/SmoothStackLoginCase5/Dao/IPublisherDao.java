package com.SmoothStack.SmoothStackLoginCase5.Dao;

import java.sql.Connection;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Entity.Publisher;

public interface IPublisherDao {
	public List<Publisher> findAll(Connection conn);
	public boolean addPublisher(Connection conn, Publisher publisher);
	public List<Publisher> getPublisherById(Connection conn, int publisherId);
	public Publisher getPublisherByIdReturn(Connection conn, int publisherId);
	public boolean updatePublisher(Connection conn, Publisher publisher);
	public boolean deletePublisherById(Connection conn, Publisher publisher);
	public List<Publisher> getSinglePublisher(Connection conn, int count);
		
}
