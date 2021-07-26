package com.ssafy.donas.response;

import io.swagger.annotations.ApiModelProperty;


public class LoginResponse {
    @ApiModelProperty(value = "nickname", position = 1)
    public String nickname;
    @ApiModelProperty(value = "questCnt", position = 2)
    public int questCnt;
}
