package com.seaportwebapp.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblBerths")
public class Berhts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String berthName;
	private int berthCapacity;
	private String berthStatus;

	public Berhts() {
	}

	public Berhts(String berthName, int berthCapacity, String berthStatus) {
		super();
		this.berthName = berthName;
		this.berthCapacity = berthCapacity;
		this.berthStatus = berthStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBerthName() {
		return berthName;
	}

	public void setBerthName(String berthName) {
		this.berthName = berthName;
	}

	public int getBerthCapacity() {
		return berthCapacity;
	}

	public void setBerthCapacity(int berthCapacity) {
		this.berthCapacity = berthCapacity;
	}

	public String getBerthStatus() {
		return berthStatus;
	}

	public void setBerthStatus(String berthStatus) {
		this.berthStatus = berthStatus;
	}
}
