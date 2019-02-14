package com.SmoothStack.SmoothStackLoginCase5.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Dao.IPublisherDao;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Publisher;

public class PublisherDao implements IPublisherDao {
	
	public List<Publisher> findAll(Connection conn){
		List<Publisher> publisherList = new ArrayList<>();
		
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Publisher publisher;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_publisher`;";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				publisher = new Publisher();
				publisher.setPublisherId(resultSet.getInt("publisherId"));
				publisher.setPublisherName(resultSet.getString("publisherName"));
				publisher.setPublisherAddress(resultSet.getString("publisherAddress"));
				publisher.setPublisherPhone(resultSet.getString("publisherPhone"));
				publisherList.add(publisher);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publisherList;
	}

	@Override
	public boolean addPublisher(Connection conn, Publisher publisher) {
		// TODO Auto-generated method stub
		PreparedStatement prepareStatement = null;
		boolean reply = false; int replyInt = 0;
		String sqlQuery = "INSERT INTO `lms`.`tbl_publisher` (`publisherId`, `publisherName`, `publisherAddress`, `publisherPhone`) VALUES (?, ?, ?, ?);";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, publisher.getPublisherId());
			prepareStatement.setString(2, publisher.getPublisherName());
			prepareStatement.setString(3, publisher.getPublisherAddress());
			prepareStatement.setString(4, publisher.getPublisherPhone());
			replyInt = prepareStatement.executeUpdate();
			if(replyInt == 1) {
				reply = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reply;
	}
	
	@SuppressWarnings("null")
	public List<Publisher> getPublisherById(Connection conn, int publisherId){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_publisher` WHERE `lms`.`tbl_publisher`.`publisherId` = ?;";
		Publisher publisher;
		List<Publisher> publisherList = null;
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, publisherId);
			System.out.println(prepareStatement.executeUpdate());
			while(resultSet.next()){
				publisher = new Publisher();
				publisher.setPublisherId(resultSet.getInt("publisherId"));
				publisher.setPublisherName(resultSet.getString("publisherName"));
				publisher.setPublisherAddress(resultSet.getString("publisherAddress"));
				publisher.setPublisherPhone(resultSet.getString("publisherPhone"));
				publisherList.add(publisher);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publisherList;
	}
	
	@SuppressWarnings("null")
	public Publisher getPublisherByIdReturn(Connection conn, int publisherId){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_publisher` WHERE `lms`.`tbl_publisher`.`publisherId` = ?;";
		Publisher publisher = null;
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, publisherId);
			System.out.println(prepareStatement.executeUpdate());
			while(resultSet.next()){
				publisher = new Publisher();
				publisher.setPublisherId(resultSet.getInt("publisherId"));
				publisher.setPublisherName(resultSet.getString("publisherName"));
				publisher.setPublisherAddress(resultSet.getString("publisherAddress"));
				publisher.setPublisherPhone(resultSet.getString("publisherPhone"));
				return publisher;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publisher;
	}
		
	public boolean updatePublisher(Connection conn, Publisher publisher){
		PreparedStatement prepareStatement = null;
		boolean reply = false; int replyInt = 0;
		String sqlQuery = "UPDATE `lms`.`tbl_publisher` SET `publisherName` = ?, `publisherAddress` = ?, `publisherPhone` = ? WHERE (`publisherId` = ?);";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			
			prepareStatement.setString(1, publisher.getPublisherName());
			prepareStatement.setString(2, publisher.getPublisherAddress());
			prepareStatement.setString(3, publisher.getPublisherPhone());
			prepareStatement.setInt(4, publisher.getPublisherId());

			replyInt = prepareStatement.executeUpdate();
			if(replyInt == 1) {
				reply = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reply;
	}

	public boolean deletePublisherById(Connection conn, Publisher publisher){
		PreparedStatement prepareStatement = null;
		boolean reply = false; int replyInt = 0;
		String sqlQuery = "DELETE FROM `lms`.`tbl_publisher` WHERE `lms`.`tbl_publisher`.`publisherId` = ?;";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, publisher.getPublisherId());
			replyInt = prepareStatement.executeUpdate();
			if(replyInt == 1) {
				reply = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reply;
	}
	
	@SuppressWarnings("null")
	public List<Publisher> getSinglePublisher(Connection conn, int count){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Publisher publisher = null;
		List<Publisher> publisherList = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_library_branch` LIMIT ?, 1;";
		try{
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, count-1);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				publisher = new Publisher();
				publisher.setPublisherId(resultSet.getInt("publisherId"));
				publisher.setPublisherName(resultSet.getString("publisherName"));
				publisher.setPublisherAddress(resultSet.getString("publisherAddress"));
				publisher.setPublisherPhone(resultSet.getString("publisherPhone"));
				publisherList.add(publisher);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publisherList;
	}

}
