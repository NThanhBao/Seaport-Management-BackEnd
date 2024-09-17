package com.seaportwebapp.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblGioHang")
public class GioHang {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long sanPhamId;

	public GioHang() {
	}

	public GioHang(Long sanPhamId) {
		this.sanPhamId = sanPhamId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSanPhamId() {
		return sanPhamId;
	}

	public void setSanPhamId(Long sanPhamId) {
		this.sanPhamId = sanPhamId;
	}
}
