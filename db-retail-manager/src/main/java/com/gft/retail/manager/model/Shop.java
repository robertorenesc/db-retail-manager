package com.gft.retail.manager.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Object that represents a SHOP object of datanase
 * 
 * @author Roberto Salazar - GFT
 */
@Entity
@Table(name="SHOP")
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private BigDecimal id;
	
	@Column(name="NAME")
	private String name;
	
	@OneToOne(mappedBy="shop", fetch=FetchType.LAZY)
	@JsonManagedReference
	private Address address;
	
	@Transient
	long distance;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}
	
}
