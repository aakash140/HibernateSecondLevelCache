package com.java.hibernate.second;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="EMPLOYEE_SECOND_LEVEL")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="employee")
public class Employee {
	@Id @GeneratedValue
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SALARY")
	private int salay;

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

	public int getSalay() {
		return salay;
	}

	public void setSalay(int salay) {
		this.salay = salay;
	}
}