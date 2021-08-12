package com.ssafy.donas.response;

import io.swagger.annotations.ApiModelProperty;

public class CharityResponse {
	
	@ApiModelProperty(value="id", required = true, position = 1)
	public long id;
	@ApiModelProperty(value="name", position = 2)
	public String name;
	@ApiModelProperty(value="quarter",position = 3)
	public long quarter;
	@ApiModelProperty(value="total",position = 4)
	public long total;
	@ApiModelProperty(value="description",position = 5)
	public String description;
	@ApiModelProperty(value="quarter",position = 6)
	public String homepage;
	@ApiModelProperty(value="picture",position = 7)
	public String picture;
	@ApiModelProperty(value="tage",position = 8)
	public String tag;
}
