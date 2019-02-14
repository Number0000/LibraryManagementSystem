package com.SmoothStack.SmoothStackLoginCase5.Service;

import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Entity.BookCopies;

public interface IBookCopiesService {
	
	public List<BookCopies> getBookCopies();
	public boolean addBookCopies(BookCopies publisher);
	public List<BookCopies> getBookCopiesById(int bookId, int branchId);
	public BookCopies getBookCopiesByIdReturn(int bookId, int branchId);
	public boolean updateBookCopies(BookCopies publisher);
	public boolean deleteBookCopies(BookCopies publisher);
	public List<BookCopies> getSingleBookCopies(int count);

}
