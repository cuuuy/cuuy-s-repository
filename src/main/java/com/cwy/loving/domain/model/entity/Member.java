package com.cwy.loving.domain.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Member {
	@Id
	@GeneratedValue
	@NotNull
	int id;
	@NotNull
	String name;
	@NotNull
	String password;
	String join_date;
	String edit_date;
	String profile;
	

}