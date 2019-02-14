package com.SmoothStack.SmoothStackLoginCase5.Dao;

import java.sql.Connection;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Entity.LibraryBranch;

public interface ILibraryBranchDao {
	public List<LibraryBranch> findAll(Connection conn);
	public boolean addLibraryBranch(Connection conn, LibraryBranch libraryBranch);
	public List<LibraryBranch> getLibraryBranchById(Connection conn, int branchId);
	public LibraryBranch getLibraryBranchByIdReturn(Connection conn, int branchId);
	public boolean updateLibraryBranch(Connection conn, LibraryBranch libraryBranch);
	public boolean deleteLibraryBranchById(Connection conn, LibraryBranch libraryBranch);
	public List<LibraryBranch> getSingleLibraryBranch(Connection conn, int count);
		
}
