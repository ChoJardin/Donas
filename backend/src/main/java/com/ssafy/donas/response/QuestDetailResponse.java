package com.ssafy.donas.response;

import java.util.Date;
import java.util.List;

import com.ssafy.donas.domain.ArticleInfo;
import com.ssafy.donas.domain.ArticleShortInfo;
import com.ssafy.donas.domain.UserInfo;

import io.swagger.annotations.ApiModelProperty;

public class QuestDetailResponse {
	@ApiModelProperty(value = "id", position = 1)
	private long id;
	@ApiModelProperty(value = "title", position = 2)
	private String title;
	@ApiModelProperty(value = "description", position = 3)
	private String description;
	@ApiModelProperty(value = "picture", position = 4)
	private String picture;
	@ApiModelProperty(value = "type", position = 5)
	private String type;
	@ApiModelProperty(value = "start_at", position = 6)
	private Date startAt;
	@ApiModelProperty(value = "finish_at", position = 7)
	private Date finishAt;
	@ApiModelProperty(value = "mileage", position = 8)
	private long mileage;
	@ApiModelProperty(value = "percent", position = 9)
	private double percent;
	@ApiModelProperty(value = "certification", position = 10)
	private String certification;
	@ApiModelProperty(value = "success", position = 11)
	private int success;
	
	// all
	@ApiModelProperty(value = "users", position = 12)
	private List<UserInfo> users;
	
	// all
	@ApiModelProperty(value = "articles", position = 13)
	private List<ArticleInfo> articles;
	
	// 릴레이
	@ApiModelProperty(value = "targetCnt", position=14)
	private int targetCnt;
	@ApiModelProperty(value = "nowCnt", position=15)
	private int nowCnt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getFinishAt() {
		return finishAt;
	}

	public void setFinishAt(Date finishAt) {
		this.finishAt = finishAt;
	}

	public long getMileage() {
		return mileage;
	}

	public void setMileage(long mileage) {
		this.mileage = mileage;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public List<UserInfo> getUsers() {
		return users;
	}

	public void setUsers(List<UserInfo> users) {
		this.users = users;
	}



	public List<ArticleInfo> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleInfo> articles) {
		this.articles = articles;
	}

	public int getTargetCnt() {
		return targetCnt;
	}

	public void setTargetCnt(int targetCnt) {
		this.targetCnt = targetCnt;
	}

	public int getNowCnt() {
		return nowCnt;
	}

	public void setNowCnt(int nowCnt) {
		this.nowCnt = nowCnt;
	}
	
}

