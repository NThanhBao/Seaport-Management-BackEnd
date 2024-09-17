package com.seaportwebapp.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblCargoDetails")
public class CargoDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long cargoId;
	private String cargoType;
	private int cargoQuantity;
	private String cargoStart;
	private String cargoEnd;
	private String cargoStartDate;
	private String cargoStartTime;
	private String cargoEndDate;
	private String cargoEndTime;

	public CargoDetails() {
	}

	public CargoDetails(Long cargoId, String cargoType, int cargoQuantity, String cargoStart, String cargoEnd,
			String cargoStartDate, String cargoStartTime, String cargoEndDate, String cargoEndTime) {
		super();
		this.cargoId = cargoId;
		this.cargoType = cargoType;
		this.cargoQuantity = cargoQuantity;
		this.cargoStart = cargoStart;
		this.cargoEnd = cargoEnd;
		this.cargoStartDate = cargoStartDate;
		this.cargoStartTime = cargoStartTime;
		this.cargoEndDate = cargoEndDate;
		this.cargoEndTime = cargoEndTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCargoId() {
		return cargoId;
	}

	public void setCargoId(Long cargoId) {
		this.cargoId = cargoId;
	}

	public String getCargoType() {
		return cargoType;
	}

	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
	}

	public int getCargoQuantity() {
		return cargoQuantity;
	}

	public void setCargoQuantity(int cargoQuantity) {
		this.cargoQuantity = cargoQuantity;
	}

	public String getCargoStart() {
		return cargoStart;
	}

	public void setCargoStart(String cargoStart) {
		this.cargoStart = cargoStart;
	}

	public String getCargoEnd() {
		return cargoEnd;
	}

	public void setCargoEnd(String cargoEnd) {
		this.cargoEnd = cargoEnd;
	}

	public String getCargoStartDate() {
		return cargoStartDate;
	}

	public void setCargoStartDate(String cargoStartDate) {
		this.cargoStartDate = cargoStartDate;
	}

	public String getCargoStartTime() {
		return cargoStartTime;
	}

	public void setCargoStartTime(String cargoStartTime) {
		this.cargoStartTime = cargoStartTime;
	}

	public String getCargoEndDate() {
		return cargoEndDate;
	}

	public void setCargoEndDate(String cargoEndDate) {
		this.cargoEndDate = cargoEndDate;
	}

	public String getCargoEndTime() {
		return cargoEndTime;
	}

	public void setCargoEndTime(String cargoEndTime) {
		this.cargoEndTime = cargoEndTime;
	}
}
