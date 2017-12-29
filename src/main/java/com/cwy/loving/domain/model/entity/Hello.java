package com.cwy.loving.domain.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hello {
	@Id
	@GeneratedValue
	int id;
	String name;
	String password;
	String join_date;
	String now_date;
	String profile;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJoin_date() {
		return join_date;
	}

	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}

	public String getNow_date() {
		return now_date;
	}

	public void setNow_date(String now_date) {
		this.now_date = now_date;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	
}