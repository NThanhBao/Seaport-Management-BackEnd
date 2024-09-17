package com.seaportwebapp.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblCargo")
public class Cargo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String cargoDescription;
	private int cargoQuantity;

	public Cargo() {
	}

	public Cargo(String cargoDescription, int cargoQuantity) {
		this.cargoDescription = cargoDescription;
		this.cargoQuantity = cargoQuantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCargoDescription() {
		return cargoDescription;
	}

	public void setCargoDescription(String cargoDescription) {
		this.cargoDescription = cargoDescription;
	}

	public int getCargoQuantity() {
		return cargoQuantity;
	}

	public void setCargoQuantity(int cargoQuantity) {
		this.cargoQuantity = cargoQuantity;
	}
}
