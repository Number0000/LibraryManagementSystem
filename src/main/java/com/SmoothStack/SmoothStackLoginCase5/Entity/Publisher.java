package com.SmoothStack.SmoothStackLoginCase5.Entity;
//db to support jdbctest.java
public class Publisher {
	private int publisherId;
	private String publisherName;
	private String publisherAddress;
	private String publisherPhone;
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getPublisherAddress() {
		return publisherAddress;
	}
	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
	public String getPublisherPhone() {
		return publisherPhone;
	}
	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}
	@Override
	public String toString() {
		return "Publisher [publisherId= " + publisherId 
				+ ", publisherName= " + publisherName 
				+ ", publisherAddress= " + publisherAddress 
				+ ", publisherPhone= " + publisherPhone 
				+ "]";
	}
	
}
