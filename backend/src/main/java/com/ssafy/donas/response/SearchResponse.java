package com.ssafy.donas.response;

import io.swagger.annotations.ApiModelProperty;

public class SearchResponse {
	@ApiModelProperty(value = "id", position = 1)
	public long id;
    @ApiModelProperty(value = "nickname", position = 2)
    public String nickname;
    @ApiModelProperty(value = "picture", position = 3)
    public String picture;
    @ApiModelProperty(value = "description", position = 4)
    public String description;
}
