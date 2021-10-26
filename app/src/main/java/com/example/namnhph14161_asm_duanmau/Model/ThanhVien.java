package com.example.namnhph14161_asm_duanmau.Model;

public class ThanhVien {
    private int maTV;
    private String hoTenTV;
    private String namSinh;
    private int STK;

    public int getSTK() {
        return STK;
    }

    public void setSTK(int STK) {
        this.STK = STK;
    }

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String hoTenTV, String namSinh,int stk) {
        this.maTV = maTV;
        this.hoTenTV = hoTenTV;
        this.namSinh = namSinh;
        this.STK = stk;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTenTV() {
        return hoTenTV;
    }

    public void setHoTenTV(String hoTenTV) {
        this.hoTenTV = hoTenTV;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
    @Override
    public String toString() {
        return getMaTV()+". "+getHoTenTV();
    }
}
