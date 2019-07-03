package com.example.demo.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Member {
	private String name;
	private String address;
	@JsonIgnore
	private Integer ssn;
	//@JsonIgnore
	private Integer salary;
	
	public Member(String name, String address, Integer ssn, Integer salary) {
		super();
		this.name = name;
		this.address = address;
		this.ssn = ssn;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getSsn() {
		return ssn;
	}

	public void setSsn(Integer ssn) {
		this.ssn = ssn;
	}

	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

}
