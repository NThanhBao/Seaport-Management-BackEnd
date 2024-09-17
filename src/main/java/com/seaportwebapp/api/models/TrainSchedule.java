package com.seaportwebapp.api.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblTrainSchedule")
public class TrainSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date timeIn;
	private Date timeOut;
	private Long destinationPort;
	private Long departurePort;
	private Long shipsId;

	public TrainSchedule() {
	}

	public TrainSchedule(Date timeIn, Date timeOut, Long destinationPort, Long departurePort, Long shipsId) {
		super();
		this.timeIn = timeIn;
		this.timeOut = timeOut;
		this.destinationPort = destinationPort;
		this.departurePort = departurePort;
		this.shipsId = shipsId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(Date timeIn) {
		this.timeIn = timeIn;
	}

	public Date getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}

	public Long getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(Long destinationPort) {
		this.destinationPort = destinationPort;
	}

	public Long getDeparturePort() {
		return departurePort;
	}

	public void setDeparturePort(Long departurePort) {
		this.departurePort = departurePort;
	}

	public Long getShipsId() {
		return shipsId;
	}

	public void setShipsId(Long shipsId) {
		this.shipsId = shipsId;
	}

}
