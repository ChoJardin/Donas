package com.ssafy.donas.response;

import io.swagger.annotations.ApiModelProperty;

public class FollowResponse {
    @ApiModelProperty(value = "id", position = 1)
    public long id;
    @ApiModelProperty(value = "picture", position = 2)
    public String picture;
    @ApiModelProperty(value = "description", position = 3)
    public String description;
}
