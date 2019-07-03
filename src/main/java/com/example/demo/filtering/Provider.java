package com.example.demo.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("noPHI")
public class Provider {

	private String name;
	private String address;
	private Integer taxId;
	private Integer revenue;
	
	public Provider(String name, String address, Integer taxId, Integer revenue) {
		super();
		this.name = name;
		this.address = address;
		this.taxId = taxId;
		this.revenue = revenue;
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

	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	public Integer getRevenue() {
		return revenue;
	}

	public void setRevenue(Integer revenue) {
		this.revenue = revenue;
	}
}
