package com.seaportwebapp.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblPhanLoai")
public class PhanLoai {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String tenLoai;
	public PhanLoai() {
	}
	public PhanLoai(String tenLoai) {
		super();
		this.tenLoai = tenLoai;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
}
