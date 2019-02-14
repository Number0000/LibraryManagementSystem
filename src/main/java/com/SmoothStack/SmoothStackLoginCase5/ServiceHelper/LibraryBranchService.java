package com.SmoothStack.SmoothStackLoginCase5.ServiceHelper;

import java.sql.Connection;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Connection.Connections;
import com.SmoothStack.SmoothStackLoginCase5.Dao.ILibraryBranchDao;
import com.SmoothStack.SmoothStackLoginCase5.Entity.LibraryBranch;
import com.SmoothStack.SmoothStackLoginCase5.Mysql.LibraryBranchDao;
import com.SmoothStack.SmoothStackLoginCase5.Service.ILibraryBranchService;

public class LibraryBranchService implements ILibraryBranchService{
	ILibraryBranchDao libraryBranchDao;
	
	public LibraryBranchService() {
		libraryBranchDao = new LibraryBranchDao();
	}
	
	public List<LibraryBranch> getLibraryBranch(){
		Connection conn = Connections.connection();
		return libraryBranchDao.findAll(conn);
	}
	
	public boolean addLibraryBranch(LibraryBranch libraryBranch) {
		Connection conn = Connections.connection();
		return libraryBranchDao.addLibraryBranch(conn, libraryBranch);
	}
	
	public List<LibraryBranch> getLibraryBranchById(int branchId){
		Connection conn = Connections.connection();
		return libraryBranchDao.getLibraryBranchById(conn, branchId);
	}
	
	public LibraryBranch getLibraryBranchByIdReturn(int branchId){
		Connection conn = Connections.connection();
		return libraryBranchDao.getLibraryBranchByIdReturn(conn, branchId);
	}
	
	public boolean updateLibraryBranch(LibraryBranch libraryBranch){
		Connection conn = Connections.connection();
		return libraryBranchDao.updateLibraryBranch(conn, libraryBranch);
	}
	
	public boolean deleteLibraryBranch(LibraryBranch libraryBranch){
		Connection conn = Connections.connection();
		return libraryBranchDao.deleteLibraryBranchById(conn, libraryBranch);
	}
	
	public List<LibraryBranch> getSingleLibraryBranch(int count){
		Connection conn = Connections.connection();
		return libraryBranchDao.getSingleLibraryBranch(conn, count);
	}
	
}
