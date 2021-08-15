package com.ssafy.donas.response;

import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ArticleShortResponse {
	@ApiModelProperty(value = "articleId", required = true , position = 1)
	public long articleId;
	@ApiModelProperty(value = "content", position = 2)
	public String content;
	@ApiModelProperty(value = "image", position = 3)
	public String image;
	@ApiModelProperty(value = "questId",position = 4)
	public long questId;
	@ApiModelProperty(value = "type", position = 5)
	public String type;
	@ApiModelProperty(value = "createAt", position = 6)
	public LocalDateTime createAt;
	@ApiModelProperty(value = "updateAt", position = 7)
	public LocalDateTime updateAte;
	@ApiModelProperty(value = "commentCnt", position = 8)
	public int commentCnt;
	@ApiModelProperty(value = "likeCnt", position = 9)
	public int likeCnt;
	
}
