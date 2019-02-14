package com.SmoothStack.SmoothStackLoginCase5.Dao;

import java.sql.Connection;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Entity.BookLoan;

public interface IBookLoanDao {
	public List<BookLoan> findAll(Connection conn);
	public List<BookLoan> findAllByCardNo(Connection conn, int cardNo);
	public boolean addBookLoan(Connection conn, BookLoan bookLoan);
	public List<BookLoan> getBookLoanById(Connection conn, int bookId, int branchId, int cardNo);
	public BookLoan getBookLoanByIdReturn(Connection conn, int bookId, int branchId, int cardNo);
	public boolean updateBookLoan(Connection conn, BookLoan bookLoan);
	public boolean deleteBookLoanById(Connection conn, BookLoan bookLoan);
	public List<BookLoan> getSingleBookLoan(Connection conn, int count);
	
}
