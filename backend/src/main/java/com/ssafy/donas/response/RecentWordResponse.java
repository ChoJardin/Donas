package com.ssafy.donas.response;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class RecentWordResponse {
    @ApiModelProperty(value = "nickname", position = 1)
    public String nickname;
    @ApiModelProperty(value = "search_time", position = 2)
    public Date searchTime;
}
