package com.ssafy.donas.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
@Table(name="Images")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String path;
	
	private long size;
	
}
