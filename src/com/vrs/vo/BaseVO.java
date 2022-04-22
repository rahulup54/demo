package com.vrs.vo;

public abstract class BaseVO {

	private int id;
	
	public BaseVO()
	{
		
	}
	
	public BaseVO(int id) {
		this.setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
