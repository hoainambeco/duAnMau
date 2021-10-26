package com.example.namnhph14161_asm_duanmau;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    public DB(Context context) {
        super(context, "PNLIB", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // tao bang thu thu
        String tb_thuthu=" CREATE TABLE ThuThu ( " +
                "maTT TEXT PRIMARY KEY," +
                "hoTen TEXT NOT NULL," +
                "matKhau TEXT NOT NULL) ";
        sqLiteDatabase.execSQL(tb_thuthu);

        // tao bang thanh vien
        String tb_thanhvien=" CREATE TABLE ThanhVien ( " +
                "maTV INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hoTenTV TEXT NOT NULL," +
                "namSinh TEXT NOT NULL," +
                "stk INTEGER NOT NULL) ";
        sqLiteDatabase.execSQL(tb_thanhvien);

        // tao bang loai sach
        String tb_loaisach=" CREATE TABLE LoaiSach ( " +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL )";
        sqLiteDatabase.execSQL(tb_loaisach);

        // tao bang sach
        String tb_sach=" CREATE TABLE Sach ( " +
                "maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSach TEXT NOT NULL," +
                "giaThue INTEGER NOT NULL," +
                "maLoai INTEGER REFERENCES LoaiSach(maLoai)," +
                "trang INTEGER NOT NULL )";
        sqLiteDatabase.execSQL(tb_sach);

        // tao bang phieu muon
        String tb_phieumuon=" CREATE TABLE PhieuMuon ( " +
                "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maTT INTEGER REFERENCES ThuThu(maTT)," +
                "maTV INTEGER REFERENCES ThanhVien(maTV)," +
                "maSach INTEGER REFERENCES Sach(maSach)," +
                "tienThue INTEGER NOT NULL," +
                "ngay DATE NOT NULL," +
                "traSach INTEGER NOT NULL)";

        sqLiteDatabase.execSQL(tb_phieumuon);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ThuThu");

        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS ThanhVien");

        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS LoaiSach");

        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS Sach");

        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS PhieuMuon");

        onCreate(sqLiteDatabase);
    }
}
