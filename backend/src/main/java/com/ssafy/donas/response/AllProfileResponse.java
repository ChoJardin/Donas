package com.ssafy.donas.response;

import java.util.List;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.ArticleInfo;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.QuestInfo;

import io.swagger.annotations.ApiModelProperty;

public class AllProfileResponse {
	@ApiModelProperty(value = "id", position = 1)
    public long id;
    @ApiModelProperty(value = "nickname", position = 2)
    public String nickname;
    @ApiModelProperty(value = "picture", position = 3)
    public String picture;
    @ApiModelProperty(value = "description", position = 4)
    public String description;
    @ApiModelProperty(value = "quests", position = 5)
    public List<QuestInfo> quests;
    @ApiModelProperty(value = "questCnt", position = 6)
    public int questCnt;
    @ApiModelProperty(value = "questPercent", position = 7)
    public int questPercent;
    @ApiModelProperty(value = "follower", position = 8)
    public int follower;
    @ApiModelProperty(value = "following", position = 9)
    public int following;
    @ApiModelProperty(value = "isFollowing", position = 10)
    public boolean isFollowing;
    @ApiModelProperty(value = "articles", position = 11)
    public List<ArticleInfo> articles;
}
