package com.seaportwebapp.api.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblArrivals")
public class Arrivals {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long shipId;
	private Long berthId;
	private String arrivalDate;
	private String arrivalTime;

	public Arrivals() {
	}

	public Arrivals(Long shipId, Long berthId, String arrivalDate, String arrivalTime) {
		this.shipId = shipId;
		this.berthId = berthId;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShipId() {
		return shipId;
	}

	public void setShipId(Long shipId) {
		this.shipId = shipId;
	}

	public Long getBerthId() {
		return berthId;
	}

	public void setBerthId(Long berthId) {
		this.berthId = berthId;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
}
