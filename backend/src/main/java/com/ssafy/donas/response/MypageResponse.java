package com.ssafy.donas.response;

import io.swagger.annotations.ApiModelProperty;

public class MypageResponse {
    @ApiModelProperty(value = "nickname", position = 1)
    public String nickname;
    @ApiModelProperty(value = "email", position = 2)
    public String email;
    @ApiModelProperty(value = "picture", position = 3)
    public String picture;
    @ApiModelProperty(value = "description", position = 4)
    public String description;
    @ApiModelProperty(value = "mileage", position = 5)
    public long mileage;
    @ApiModelProperty(value = "questCnt", position = 6)
    public int questCnt;
    @ApiModelProperty(value = "questPercent", position = 7)
    public int questPercent;
}
