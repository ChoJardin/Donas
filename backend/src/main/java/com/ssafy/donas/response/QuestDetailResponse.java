package com.ssafy.donas.response;

import java.util.Date;
import java.util.List;

import com.ssafy.donas.domain.UserInfo;

import io.swagger.annotations.ApiModelProperty;

public class QuestDetailResponse {
	@ApiModelProperty(value = "id", position = 1)
	public long id;
	@ApiModelProperty(value = "title", position = 2)
	public String title;
	@ApiModelProperty(value = "description", position = 3)
	public String description;
	@ApiModelProperty(value = "picture", position = 4)
	public String picture;
	@ApiModelProperty(value = "type", position = 5)
	public String type;
	@ApiModelProperty(value = "start_at", position = 6)
	public Date startAt;
	@ApiModelProperty(value = "finish_at", position = 7)
	public Date finishAt;
	@ApiModelProperty(value = "mileage", position = 8)
	public long mileage;
	@ApiModelProperty(value = "percent", position = 9)
	public double percent;
	@ApiModelProperty(value = "certification", position = 10)
	private String certification;
	@ApiModelProperty(value = "success", position = 10)
	private int success;
	// 그룹이나 릴레이 퀘스트 경우
	@ApiModelProperty(value = "users", position = 11)
	private List<UserInfo> users;
	
	
	
	
}
