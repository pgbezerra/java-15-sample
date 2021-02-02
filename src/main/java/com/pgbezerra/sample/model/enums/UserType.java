package com.pgbezerra.sample.model.enums;

public enum UserType {
	
	ADMIN(1),
	COMMOM(2),
	UNKNOW(3);
	
	private Integer code;
	
	private UserType(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return this.code;
	}

}
