package com.SmoothStack.SmoothStackLoginCase5.Service;

import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Entity.Publisher;

public interface IPublisherService {
	
	public List<Publisher> getPublisher();
	public boolean addPublisher(Publisher publisher);
	public List<Publisher> getPublisherById(int publisherId);
	public Publisher getPublisherByIdReturn(int publisherId);
	public boolean updatePublisher(Publisher publisher);
	public boolean deletePublisher(Publisher publisher);
	public List<Publisher> getSinglePublisher(int count);

}
