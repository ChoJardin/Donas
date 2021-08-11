package com.ssafy.donas.domain;

public class UserInfo {
	
	private long id;
	private String nickname;
	private String picture;
	private String description;
	
	public UserInfo(long id, String nickname, String picture, String description) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.picture = picture;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
