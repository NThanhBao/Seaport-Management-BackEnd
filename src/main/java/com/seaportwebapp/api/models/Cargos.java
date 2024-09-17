package com.seaportwebapp.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblCagos")
public class Cargos {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long shipId;
	private String cargoDesc;

	public Cargos() {
	}

	public Cargos(Long shipId, String cargoDesc) {
		super();
		this.shipId = shipId;
		this.cargoDesc = cargoDesc;
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

	public String getCargoDesc() {
		return cargoDesc;
	}

	public void setCargoDesc(String cargoDesc) {
		this.cargoDesc = cargoDesc;
	}
}
