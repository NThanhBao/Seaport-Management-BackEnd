package com.seaportwebapp.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblSanPham")
public class SanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long phanLoaiId;
	private String tenSanPham;
	private String moTaSanPham;
	private double giaBan;
	private String ngayThemVao;
	private String hinhSanPham;

	public SanPham() {
	}

	public SanPham(Long phanLoaiId, String tenSanPham, String moTaSanPham, double giaBan, String ngayThemVao,
			String hinhSanPham) {
		super();
		this.phanLoaiId = phanLoaiId;
		this.tenSanPham = tenSanPham;
		this.moTaSanPham = moTaSanPham;
		this.giaBan = giaBan;
		this.ngayThemVao = ngayThemVao;
		this.hinhSanPham = hinhSanPham;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPhanLoaiId() {
		return phanLoaiId;
	}

	public void setPhanLoaiId(Long phanLoaiId) {
		this.phanLoaiId = phanLoaiId;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public String getMoTaSanPham() {
		return moTaSanPham;
	}

	public void setMoTaSanPham(String moTaSanPham) {
		this.moTaSanPham = moTaSanPham;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public String getNgayThemVao() {
		return ngayThemVao;
	}

	public void setNgayThemVao(String ngayThemVao) {
		this.ngayThemVao = ngayThemVao;
	}

	public String getHinhSanPham() {
		return hinhSanPham;
	}

	public void setHinhSanPham(String hinhSanPham) {
		this.hinhSanPham = hinhSanPham;
	}

}
