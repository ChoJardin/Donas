package com.ssafy.donas.request;

import javax.validation.Valid;

import com.sun.istack.NotNull;
import java.util.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class RelayNextListRequest {
	@ApiModelProperty(required = true)
    @NotNull
    long questId;
	
	@ApiModelProperty(required = true)
    @NotNull
    List<Long> nextList;

	public long getQuestId() {
		return questId;
	}

	public List<Long> getNextList() {
		return nextList;
	}
	
}
