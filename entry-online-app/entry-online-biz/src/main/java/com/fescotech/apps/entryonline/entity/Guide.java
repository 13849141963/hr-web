package com.fescotech.apps.entryonline.entity;

public class Guide {
	private String name;
	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Guide() {
		super();
	}

	public Guide(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

}
