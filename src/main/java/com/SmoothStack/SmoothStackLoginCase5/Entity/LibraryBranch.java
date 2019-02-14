package com.SmoothStack.SmoothStackLoginCase5.Entity;
//db to support jdbctest.java
public class LibraryBranch {
	private int branchId;
	private String branchName;
	private String branchAddress;
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	@Override
	public String toString() {
		return "LibraryBranch [branchId= " + branchId 
				+ ", branchName= " + branchName 
				+ ", branchAddress= " + branchAddress
				+ "]";
	}
	
	
}