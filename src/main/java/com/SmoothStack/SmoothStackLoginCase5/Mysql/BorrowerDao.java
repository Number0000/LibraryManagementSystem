package com.SmoothStack.SmoothStackLoginCase5.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Dao.IBorrowerDao;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Borrower;

public class BorrowerDao implements IBorrowerDao {
	
	public List<Borrower> findAll(Connection conn){
		List<Borrower> borrowerList = new ArrayList<>();
		
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Borrower borrower;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_borrower`;";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				borrower = new Borrower();
				borrower.setCardNo(resultSet.getInt("cardNo"));
				borrower.setBorrowerName(resultSet.getString("name"));
				borrower.setBorrowerAddress(resultSet.getString("address"));
				borrower.setBorrowerPhone(resultSet.getString("phone"));
				borrower.setBorrowerPassword(resultSet.getString("password"));
				borrowerList.add(borrower);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrowerList;
	}

	@Override
	public boolean addBorrower(Connection conn, Borrower borrower) {
		// TODO Auto-generated method stub
		PreparedStatement prepareStatement = null;
		boolean reply = false; int replyInt = 0;
		String sqlQuery = "INSERT INTO `lms`.`tbl_borrower` (`cardNo`, `name`, `address`, `phone`, `password`) VALUES (?, ?, ?, ?, ?);";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, borrower.getCardNo());
			prepareStatement.setString(2, borrower.getBorrowerName());
			prepareStatement.setString(3, borrower.getBorrowerAddress());
			prepareStatement.setString(4, borrower.getBorrowerPhone());
			prepareStatement.setString(5, borrower.getBorrowerPassword());

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
	public List<Borrower> getBorrowerById(Connection conn, int cardNo){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_borrower` WHERE `lms`.`tbl_borrower`.`cardNo` = ?;";
		Borrower borrower;
		List<Borrower> borrowerList = null;
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, cardNo);
			System.out.println(prepareStatement.executeUpdate());
			while(resultSet.next()){
				borrower = new Borrower();
				borrower.setCardNo(resultSet.getInt("cardNo"));
				borrower.setBorrowerName(resultSet.getString("name"));
				borrower.setBorrowerAddress(resultSet.getString("address"));
				borrower.setBorrowerPhone(resultSet.getString("phone"));
				borrower.setBorrowerPassword(resultSet.getString("password"));
				borrowerList.add(borrower);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrowerList;
	}
	
	@SuppressWarnings("null")
	public Borrower getBorrowerByIdReturn(Connection conn, int cardNo){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_borrower` WHERE `lms`.`tbl_borrower`.`cardNo` = ?;";
		Borrower borrower = null;
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, cardNo);
			System.out.println(prepareStatement.executeUpdate());
			while(resultSet.next()){
				borrower = new Borrower();
				borrower.setCardNo(resultSet.getInt("cardNo"));
				borrower.setBorrowerName(resultSet.getString("name"));
				borrower.setBorrowerAddress(resultSet.getString("address"));
				borrower.setBorrowerPhone(resultSet.getString("phone"));
				borrower.setBorrowerPassword(resultSet.getString("password"));
				return borrower;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrower;
	}
		
	public boolean updateBorrower(Connection conn, Borrower borrower){
		System.out.println("Update borrower");
		System.out.println("press 'N/A' to remain the same value or just enter them after relative element");
		PreparedStatement prepareStatement = null;
		boolean reply = false; int replyInt = 0;
		String sqlQuery = "UPDATE `lms`.`tbl_borrower` SET `name` = ?, `address` = ?, `phone` = ? WHERE (`cardNo` = ?);";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			
			prepareStatement.setString(1, borrower.getBorrowerName());
			prepareStatement.setString(2, borrower.getBorrowerAddress());
			prepareStatement.setString(3, borrower.getBorrowerPhone());
			prepareStatement.setInt(4, borrower.getCardNo());

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

	public boolean deleteBorrowerById(Connection conn, Borrower borrower){
		PreparedStatement prepareStatement = null;
		boolean reply = false; int replyInt = 0;
		String sqlQuery = "DELETE FROM `lms`.`tbl_borrower` WHERE `lms`.`tbl_borrower`.`cardNo` = ?;";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, borrower.getCardNo());
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
	public List<Borrower> getSingleBorrower(Connection conn, int count){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Borrower borrower = null;
		List<Borrower> borrowerList = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_borrower` LIMIT ?, 1;";
		try{
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, count-1);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				borrower = new Borrower();
				borrower.setCardNo(resultSet.getInt("cardNo"));
				borrower.setBorrowerName(resultSet.getString("name"));
				borrower.setBorrowerAddress(resultSet.getString("address"));
				borrower.setBorrowerPhone(resultSet.getString("phone"));
				borrowerList.add(borrower);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrowerList;
	}

}
