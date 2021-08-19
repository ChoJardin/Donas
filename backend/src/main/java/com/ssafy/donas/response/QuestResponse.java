package com.ssafy.donas.response;

import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
public class QuestResponse {
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
	@ApiModelProperty(value = "success", position = 8)
	public int success;
}
