package com.example.namnhph14161_asm_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.namnhph14161_asm_duanmau.DB;
import com.example.namnhph14161_asm_duanmau.Model.Sach;

import java.util.ArrayList;

public class DAOsach {
    private SQLiteDatabase db;
    public DAOsach(Context context){
        DB dbSql=new DB(context);
        db=dbSql.getWritableDatabase();
    }
    public long insert(Sach sach){
        ContentValues values=new ContentValues();
        values.put("tenSach",sach.getTenSach());
        values.put("giaThue",sach.getGiaThue());
        values.put("maLoai",sach.getMaLoai());
        values.put("trang",sach.getTrang());

        return db.insert("Sach",null,values);
    }
    public int update(Sach sach){
        ContentValues values=new ContentValues();
        values.put("tenSach",sach.getTenSach());
        values.put("giaThue",sach.getGiaThue());
        values.put("maLoai",sach.getMaLoai());
        values.put("trang",sach.getTrang());

        return db.update("Sach",values,"maSach=?",new String[]{String.valueOf(sach.getMaSach())});
    }
    public int delete(String id){

        return db.delete("Sach","maSach=?",new String[]{id});
    }

    //get data vao nhieu tham so
    private ArrayList<Sach> getdata(String sql,String ...a){
        ArrayList<Sach> list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,a);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int masach= cursor.getInt(0);
            String tensach= cursor.getString(1);
            int giathue= cursor.getInt(2);
            int maloai= cursor.getInt(3);
            int trang= cursor.getInt(4);
            Sach sach=new Sach(masach,tensach,giathue,maloai,trang);
            list.add(sach);
            cursor.moveToNext();

        }
        cursor.close();
        return list;
    }
    public ArrayList<Sach> getsearch(String text){
        ArrayList<Sach> list=new ArrayList<>();
//        String sql=" SELECT * FROM Sach WHERE Sach.trang>?";
        String a = "%"+text+"%";
        String sql = "SELECT * FROM Sach WHERE tenSach LIKE ?";
        Cursor cursor=db.rawQuery(sql, new String[]{a});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int masach= cursor.getInt(0);
            String tensach= cursor.getString(1);
            int giathue= cursor.getInt(2);
            int maloai= cursor.getInt(3);
            int trang= cursor.getInt(4);
            Sach sach=new Sach(masach,tensach,giathue,maloai,trang);
            list.add(sach);
            cursor.moveToNext();

        }
        cursor.close();
        return list;
    }
    // get tat ca data
    public ArrayList<Sach> getall(){
        String sql=" SELECT * FROM Sach ";
        return getdata(sql);
    }
    // get data theo id
    public Sach getid(String id){
        String sql=" SELECT * FROM Sach WHERE maSach=? ";
        ArrayList<Sach> list=getdata(sql, id);
        return list.get(0);
    }
}
