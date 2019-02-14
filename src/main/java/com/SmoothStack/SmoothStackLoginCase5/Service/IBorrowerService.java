package com.SmoothStack.SmoothStackLoginCase5.Service;

import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Entity.Borrower;

public interface IBorrowerService {
	
	public List<Borrower> getBorrower();
	public boolean addBorrower(Borrower borrower);
	public List<Borrower> getBorrowerById(int cardNo);
	public Borrower getBorrowerByIdReturn(int cardNo);
	public boolean updateBorrower(Borrower borrower);
	public boolean deleteBorrower(Borrower borrower);
	public List<Borrower> getSingleBorrower(int count);

}
