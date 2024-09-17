package com.seaportwebapp.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblShips")
public class Ships {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String shipName;
	private String shipNameAuth;
	private String shipNationality;
	private String shipPlate;
	private double shipSize;
	private double shipWeight;
	private double shipWattage;
	private String shipCheckIn;
	private int shipAuthInfo;
	private String shipImage;

	public Ships() {
	}

	public Ships(String shipName, String shipNameAuth, String shipNationality, String shipPlates, double shipSize,
			double shipWeight, double shipWattage, String shipCheckIn, int shipAuthInfo, String shipImage) {
		super();
		this.shipName = shipName;
		this.shipNameAuth = shipNameAuth;
		this.shipNationality = shipNationality;
		this.shipPlate = shipPlates;
		this.shipSize = shipSize;
		this.shipWeight = shipWeight;
		this.shipWattage = shipWattage;
		this.shipCheckIn = shipCheckIn;
		this.shipAuthInfo = shipAuthInfo;
		this.shipImage = shipImage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getShipNameAuth() {
		return shipNameAuth;
	}

	public void setShipNameAuth(String shipNameAuth) {
		this.shipNameAuth = shipNameAuth;
	}

	public String getShipNationality() {
		return shipNationality;
	}

	public void setShipNationality(String shipNationality) {
		this.shipNationality = shipNationality;
	}

	public String getShipPlate() {
		return shipPlate;
	}

	public void setShipPlate(String shipPlates) {
		this.shipPlate = shipPlates;
	}

	public double getShipSize() {
		return shipSize;
	}

	public void setShipSize(double shipSize) {
		this.shipSize = shipSize;
	}

	public double getShipWeight() {
		return shipWeight;
	}

	public void setShipWeight(double shipWeight) {
		this.shipWeight = shipWeight;
	}

	public double getShipWattage() {
		return shipWattage;
	}

	public void setShipWattage(double shipWattage) {
		this.shipWattage = shipWattage;
	}

	public String getShipCheckIn() {
		return shipCheckIn;
	}

	public void setShipCheckIn(String shipCheckIn) {
		this.shipCheckIn = shipCheckIn;
	}

	public int getShipAuthInfo() {
		return shipAuthInfo;
	}

	public void setShipAuthInfo(int shipAuthInfo) {
		this.shipAuthInfo = shipAuthInfo;
	}

	public String getShipImage() {
		return shipImage;
	}

	public void setShipImage(String shipImage) {
		this.shipImage = shipImage;
	}
}
