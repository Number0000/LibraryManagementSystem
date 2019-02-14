package com.SmoothStack.SmoothStackLoginCase5.Dao;

import java.sql.Connection;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Entity.Borrower;

public interface IBorrowerDao {
	public List<Borrower> findAll(Connection conn);
	public boolean addBorrower(Connection conn, Borrower borrower);
	public List<Borrower> getBorrowerById(Connection conn, int cardNo);
	public Borrower getBorrowerByIdReturn(Connection conn, int cardNo);
	public boolean updateBorrower(Connection conn, Borrower borrower);
	public boolean deleteBorrowerById(Connection conn, Borrower borrower);
	public List<Borrower> getSingleBorrower(Connection conn, int count);
		
}
