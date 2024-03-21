package com.demo.Do;



public class SanPham {
	public String maSP;
	public String name;
	public int soLuong;
	public double gia;
	
	public SanPham(String maSP, String name, int soLuong, double gia) {
		this.maSP = maSP;
		this.name = name;
		this.gia = gia;
		this.soLuong = soLuong;
	}

	public double getGia() {
		return gia;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}
	
	
}
