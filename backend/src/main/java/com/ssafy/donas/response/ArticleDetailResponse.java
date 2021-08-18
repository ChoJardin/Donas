package com.ssafy.donas.response;

import java.time.LocalDateTime;
import java.util.List;

import com.ssafy.donas.domain.CommentInfo;
import com.ssafy.donas.domain.LikeInfo;

import io.swagger.annotations.ApiModelProperty;

public class ArticleDetailResponse {
	@ApiModelProperty(value = "articleId", required = true , position = 1)
	public long articleId;
	@ApiModelProperty(value = "questId", required = true , position = 2)
	public long questId;
	@ApiModelProperty(value = "image", position = 3)
	public String image;
	@ApiModelProperty(value = "content", position = 4)
	public String content;
	@ApiModelProperty(value = "createdAt", position = 5)
	public LocalDateTime createdAt;
	@ApiModelProperty(value = "updateAt", position = 6)
	public LocalDateTime updateAt;
	@ApiModelProperty(value = "type", position = 7)
	public String type;
	@ApiModelProperty(value = "commentCnt", position = 8)
	public int commentCnt;
	@ApiModelProperty(value = "commentList", position = 9)
	public List<CommentInfo> commentList;
	@ApiModelProperty(value = "likeCnt", position = 10)
	public int likeCnt;
	@ApiModelProperty(value = "like", position = 11)
	public boolean like;
	@ApiModelProperty(value = "likeList", position = 12)
	public List<LikeInfo> likeList;
	@ApiModelProperty(value = "questTitle", position = 13)
	public String questTitle;
	@ApiModelProperty(value = "makerName", position = 14)
	public String makerName;
	@ApiModelProperty(value = "makerImage",position = 15)
	public String makerImage;
	
}
