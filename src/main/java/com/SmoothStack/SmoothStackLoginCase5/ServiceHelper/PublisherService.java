package com.SmoothStack.SmoothStackLoginCase5.ServiceHelper;

import java.sql.Connection;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Connection.Connections;
import com.SmoothStack.SmoothStackLoginCase5.Dao.IPublisherDao;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Publisher;
import com.SmoothStack.SmoothStackLoginCase5.Mysql.PublisherDao;
import com.SmoothStack.SmoothStackLoginCase5.Service.IPublisherService;


public class PublisherService implements IPublisherService{
	IPublisherDao publisherDao;
	
	public PublisherService() {
		publisherDao = new PublisherDao();
	}
	
	public List<Publisher> getPublisher(){
		Connection conn = Connections.connection();
		return publisherDao.findAll(conn);
	}
	
	public boolean addPublisher(Publisher publisher) {
		Connection conn = Connections.connection();
		return publisherDao.addPublisher(conn, publisher);
	}
	
	public List<Publisher> getPublisherById(int publisherId){
		Connection conn = Connections.connection();
		return publisherDao.getPublisherById(conn, publisherId);
	}
	
	public Publisher getPublisherByIdReturn(int publisherId){
		Connection conn = Connections.connection();
		return publisherDao.getPublisherByIdReturn(conn, publisherId);
	}
	
	public boolean updatePublisher(Publisher publisher){
		Connection conn = Connections.connection();
		return publisherDao.updatePublisher(conn, publisher);
	}
	
	public boolean deletePublisher(Publisher publisher){
		Connection conn = Connections.connection();
		return publisherDao.deletePublisherById(conn, publisher);
	}
	
	public List<Publisher> getSinglePublisher(int count){
		Connection conn = Connections.connection();
		return publisherDao.getSinglePublisher(conn, count);
	}
	
}
