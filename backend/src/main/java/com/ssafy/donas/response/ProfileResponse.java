package com.ssafy.donas.response;

import io.swagger.annotations.ApiModelProperty;

public class ProfileResponse {
    @ApiModelProperty(value = "nickname", position = 1)
    public String nickname;
    @ApiModelProperty(value = "picture", position = 2)
    public String picture;
    @ApiModelProperty(value = "description", position = 3)
    public String description;
    @ApiModelProperty(value = "questCnt", position = 4)
    public int questCnt;
    @ApiModelProperty(value = "follower", position = 5)
    public int follower;
    @ApiModelProperty(value = "following", position = 6)
    public int following;
    @ApiModelProperty(value = "isFollowing", position = 7)
    public boolean isFollowing;
}
