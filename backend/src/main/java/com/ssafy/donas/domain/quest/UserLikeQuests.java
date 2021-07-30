package com.ssafy.donas.domain.quest;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.donas.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
//@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Table(name="User_like_quests")
public class UserLikeQuests {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quest_id")
	private Quest quest;
	
	@Builder
	public UserLikeQuests(User user, Quest quest) {
		this.user = user;
		this.quest = quest;
	}
}
