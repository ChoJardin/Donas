package com.ssafy.donas.response;

import io.swagger.annotations.ApiModelProperty;


public class LoginResponse {
	@ApiModelProperty(value = "id", position = 1)
    public long id;
    @ApiModelProperty(value = "nickname", position = 2)
    public String nickname;
    @ApiModelProperty(value = "questCnt", position = 3)
    public int questCnt;
}
