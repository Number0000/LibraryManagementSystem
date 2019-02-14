package com.SmoothStack.SmoothStackLoginCase5.ServiceHelper;

import java.sql.Connection;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Connection.Connections;
import com.SmoothStack.SmoothStackLoginCase5.Dao.IBorrowerDao;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Borrower;
import com.SmoothStack.SmoothStackLoginCase5.Mysql.BorrowerDao;
import com.SmoothStack.SmoothStackLoginCase5.Service.IBorrowerService;

public class BorrowerService implements IBorrowerService{
	IBorrowerDao borrowerDao;
	
	public BorrowerService() {
		borrowerDao = new BorrowerDao();
	}
	
	public List<Borrower> getBorrower(){
		Connection conn = Connections.connection();
		return borrowerDao.findAll(conn);
	}
	
	public boolean addBorrower(Borrower borrower) {
		Connection conn = Connections.connection();
		return borrowerDao.addBorrower(conn, borrower);
	}
	
	public List<Borrower> getBorrowerById(int cardNo){
		Connection conn = Connections.connection();
		return borrowerDao.getBorrowerById(conn, cardNo);
	}
	
	public Borrower getBorrowerByIdReturn(int cardNo){
		Connection conn = Connections.connection();
		return borrowerDao.getBorrowerByIdReturn(conn, cardNo);
	}
	
	public boolean updateBorrower(Borrower borrower){
		Connection conn = Connections.connection();
		return borrowerDao.updateBorrower(conn, borrower);
	}
	
	public boolean deleteBorrower(Borrower borrower){
		Connection conn = Connections.connection();
		return borrowerDao.deleteBorrowerById(conn, borrower);
	}
	
	public List<Borrower> getSingleBorrower(int count){
		Connection conn = Connections.connection();
		return borrowerDao.getSingleBorrower(conn, count);
	}
	
	
}
