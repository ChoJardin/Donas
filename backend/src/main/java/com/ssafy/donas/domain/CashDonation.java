package com.ssafy.donas.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.QuestParticipants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
//@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Table(name="Cash_donation")
public class CashDonation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cash_id")
	private Cash cash;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "charity_id")
	private Charity charity;

	public CashDonation() {}
	
	@Builder
	public CashDonation(User user, Cash cash, Charity charity) {
		this.user = user;
		this.cash = cash;
		this.charity = charity;
	}
	
	
}
