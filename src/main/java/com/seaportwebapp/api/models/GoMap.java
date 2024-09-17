package com.seaportwebapp.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblGoMap")
public class GoMap {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
//	@Column(nullable = false, unique = true, length = 255)
	private String nameGoMapTPHCM;
//	@Column(nullable = false, unique = true, length = 255)
	private String nameGoMapTNB;
//	@Column(nullable = false, unique = true, length = 255)
	private String nameGoMapDNB;
//	@Column(nullable = false, unique = true, length = 255)
	private String nameGoMapMTTN;

	public GoMap() {
	}

	public GoMap(String nameGoMapTPHCM, String nameGoMapTNB, String nameGoMapDNB, String nameGoMapMTTN) {
		this.nameGoMapTPHCM = nameGoMapTPHCM;
		this.nameGoMapTNB = nameGoMapTNB;
		this.nameGoMapDNB = nameGoMapDNB;
		this.nameGoMapMTTN = nameGoMapMTTN;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameGoMapTPHCM() {
		return nameGoMapTPHCM;
	}

	public void setNameGoMapTPHCM(String nameGoMapTPHCM) {
		this.nameGoMapTPHCM = nameGoMapTPHCM;
	}

	public String getNameGoMapTNB() {
		return nameGoMapTNB;
	}

	public void setNameGoMapTNB(String nameGoMapTNB) {
		this.nameGoMapTNB = nameGoMapTNB;
	}

	public String getNameGoMapDNB() {
		return nameGoMapDNB;
	}

	public void setNameGoMapDNB(String nameGoMapDNB) {
		this.nameGoMapDNB = nameGoMapDNB;
	}

	public String getNameGoMapMTTN() {
		return nameGoMapMTTN;
	}

	public void setNameGoMapMTTN(String nameGoMapMTTN) {
		this.nameGoMapMTTN = nameGoMapMTTN;
	}

}
