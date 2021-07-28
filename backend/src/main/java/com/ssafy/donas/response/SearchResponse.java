package com.ssafy.donas.response;

import io.swagger.annotations.ApiModelProperty;

public class SearchResponse {
    @ApiModelProperty(value = "nickname", position = 1)
    public String nickname;
    @ApiModelProperty(value = "picture", position = 2)
    public String picture;
    @ApiModelProperty(value = "description", position = 3)
    public String description;
}
