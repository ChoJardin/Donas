package com.ssafy.donas.response;

<<<<<<< HEAD
import java.util.List;

import com.ssafy.donas.domain.Comment;

import io.swagger.annotations.ApiModelProperty;


=======
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
public class ArticleResponse {
	@ApiModelProperty(value = "id", required = true , position = 1)
	public long id;
	@ApiModelProperty(value = "quest_id", required = true , position = 2)
	public long questId;
	@ApiModelProperty(value = "image", position = 3)
	public String image;
	@ApiModelProperty(value = "content", position = 4)
	public String content;
<<<<<<< HEAD
	@ApiModelProperty(value = "type", position = 5)
	public String type;
	@ApiModelProperty(value = "comment", position = 6)
	public int comment;
	@ApiModelProperty(value = "like", position = 7)
	public int like;
	
}
=======
	@ApiModelProperty(value = "createdAt", position = 5)
	public LocalDateTime createdAt;
	@ApiModelProperty(value = "updated_at", position = 6)
	public LocalDateTime updatedAt;
	@ApiModelProperty(value = "type", position = 7)
	public String type;
	@ApiModelProperty(value = "comment", position = 8)
	public int comment;
	@ApiModelProperty(value = "like", position = 9)
	public int like;
}
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
