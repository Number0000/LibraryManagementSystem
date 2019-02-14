package com.SmoothStack.SmoothStackLoginCase5.Service;

import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Entity.BookLoan;

public interface IBookLoanService {
	
	public List<BookLoan> getBookLoan();
	public List<BookLoan> getBookLoanByCardNo(int cardNo);
	public boolean addBookLoan(BookLoan bookLoan);
	public List<BookLoan> getBookLoanById(int bookId, int branchId, int cardNo);
	public BookLoan getBookLoanByIdReturn(int bookId, int branchId, int cardNo);
	public boolean updateBookLoan(BookLoan bookLoan);
	public boolean deleteBookLoan(BookLoan bookLoan);
	public List<BookLoan> getSingleBookLoan(int count);
}
