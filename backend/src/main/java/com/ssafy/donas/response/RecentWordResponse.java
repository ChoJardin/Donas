package com.ssafy.donas.response;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class RecentWordResponse {
	@ApiModelProperty(value = "id", position = 1)
    public long id;
    @ApiModelProperty(value = "nickname", position = 2)
    public String nickname;
    @ApiModelProperty(value = "search_time", position = 3)
    public Date searchTime;
}
