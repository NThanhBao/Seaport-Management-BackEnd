package com.seaportwebapp.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblFinance")
public class Finance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String bill;
	private double pay;
	private String movinContract;
	private double portFee;

	public Finance() {
	}

	public Finance(String bill, double pay, String movinContract, double portFee) {
		super();
		this.bill = bill;
		this.pay = pay;
		this.movinContract = movinContract;
		this.portFee = portFee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public String getMovinContract() {
		return movinContract;
	}

	public void setMovinContract(String movinContract) {
		this.movinContract = movinContract;
	}

	public double getPortFee() {
		return portFee;
	}

	public void setPortFee(double portFee) {
		this.portFee = portFee;
	}
}
