package com.seaportwebapp.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblSortShips")
public class SortShips {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long shipId;
	private Long berthId;
	private int locationBerth;

	public SortShips() {
	}

	public SortShips(Long shipId, Long berthId, int locationBerth) {
		super();
		this.shipId = shipId;
		this.berthId = berthId;
		this.locationBerth = locationBerth;
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

	public int getLocationBerth() {
		return locationBerth;
	}

	public void setLocationBerth(int locationBerth) {
		this.locationBerth = locationBerth;
	}
}
