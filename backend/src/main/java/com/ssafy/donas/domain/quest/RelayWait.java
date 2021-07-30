package com.ssafy.donas.domain.quest;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
//@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Table(name="Relay_wait")
public class RelayWait {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, name="wait_rank")
	private int waitRank;
	
	@Column(nullable = false, name="user_id")
	private long userId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date deadline;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relay_id")
	private Relay relay;
	
	public RelayWait() {}
	
	@Builder
	public RelayWait(Relay relay, int waitRank, long userId, Date deadline) {
		this.relay = relay;
		this.waitRank = waitRank;
		this.userId = userId;
		this.deadline = deadline;
	}

}
