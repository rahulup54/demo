package com.vrs.vo;

public class Insurance extends BaseVO {

	private String provider;
	private String insuranceNumber;
	private String insuranceValidDate;
	
	private User user;
	
	public Insurance(){}

	public Insurance(String provider, 
					 String insuranceNumber, 
					 String insuranceValidDate) 
	{
		super();
		this.provider = provider;
		this.insuranceNumber = insuranceNumber;
		this.insuranceValidDate = insuranceValidDate;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public String getInsuranceValidDate() {
		return insuranceValidDate;
	}

	public void setInsuranceValidDate(String insuranceValidDate) {
		this.insuranceValidDate = insuranceValidDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
