package com.vrs.vo;

public class Vehicle extends BaseVO {

	
	private String source;
	private String category;
	private String plateNumber;
	private String manufacture, type, color;
	private String registrationDate;
	private String pendingFines;
	
	private User user;
	
	public Vehicle() {}
	
	public Vehicle(int id) {
		super(id);
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getPendingFines() {
		return pendingFines;
	}

	public void setPendingFines(String pendingFines) {
		this.pendingFines = pendingFines;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
