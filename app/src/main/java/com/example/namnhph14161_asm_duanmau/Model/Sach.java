package com.example.namnhph14161_asm_duanmau.Model;

public class Sach {
    private int maSach;
    private String tenSach;
    private int giaThue;
    private int maLoai;
    private int Trang;

    public Sach() {
    }

    public Sach(int maSach, String tenSach, int giaThue, int maLoai, int trang) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
        this.Trang = trang;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getTrang() {
        return Trang;
    }

    public void setTrang(int trang) {
        Trang = trang;
    }

    @Override
    public String toString() {
        return getMaSach()+". "+getTenSach();
    }
}
