package com.fescotech.apps.admin.biz.admin;

public class DeductType {
	private Long ParentId;
	private String Name;
	private int ID;
	private int state;
	private String Note;
	
	public Long getParentId() {
		return ParentId;
	}
	public void setParentId(Long parentId) {
		ParentId = parentId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
}
