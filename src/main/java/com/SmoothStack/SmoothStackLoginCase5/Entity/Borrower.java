package com.SmoothStack.SmoothStackLoginCase5.Entity;
//db to support jdbctest.java
public class Borrower {
	private int cardNo;
	private String borrowerName;
	private String borrowerAddress;
	private String borrowerPhone;
	private String borrowerPassword;
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getBorrowerAddress() {
		return borrowerAddress;
	}
	public void setBorrowerAddress(String borrowerAddress) {
		this.borrowerAddress = borrowerAddress;
	}
	public String getBorrowerPhone() {
		return borrowerPhone;
	}
	public void setBorrowerPhone(String borrowerPhone) {
		this.borrowerPhone = borrowerPhone;
	}
	public String getBorrowerPassword() {
		return borrowerPassword;
	}
	public void setBorrowerPassword(String borrowerPassword) {
		this.borrowerPassword = borrowerPassword;
	}
	@Override
	public String toString() {
		return "Borrower [cardNo= " + cardNo 
				+ ", borrowerName= " + borrowerName 
				+ ", borrowerAddress= " + borrowerAddress
				+ ", borrowerPhone= " + borrowerPhone 
				+ ", borrowerPassword= " + borrowerPassword + "]";
	}
	
	
	
}
