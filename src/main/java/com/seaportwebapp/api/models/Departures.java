package com.seaportwebapp.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblDepartures")
public class Departures {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long shipId;
	private Long berthId;
	private String departureDate;
	private String departureTime;
	public Departures() {
	}
	public Departures(Long shipId, Long berthId, String departureDate, String departureTime) {
		super();
		this.shipId = shipId;
		this.berthId = berthId;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
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
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
}
