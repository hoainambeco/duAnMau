package com.example.namnhph14161_asm_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.namnhph14161_asm_duanmau.DB;
import com.example.namnhph14161_asm_duanmau.Model.LoaiSach;

import java.util.ArrayList;

public class DAOLoaiSach {
    private SQLiteDatabase db;

    public DAOLoaiSach(Context context){
        DB dbSql=new DB(context);
        db=dbSql.getWritableDatabase();
    }
    public long insert(LoaiSach loaiSach){
        ContentValues values=new ContentValues();
        values.put("tenLoai",loaiSach.getTenLoai());

        return db.insert("LoaiSach",null,values);

    }

    public int update(LoaiSach loaiSach){
        ContentValues values=new ContentValues();
        values.put("tenLoai",loaiSach.getTenLoai());

        return db.update("LoaiSach",values,"maLoai=?",new String[]{String.valueOf(loaiSach.getMaLoai())});

    }

    public int delete(String id){

        return db.delete("LoaiSach","maLoai=?",new String[]{id});

    }

    // get data nhieu tham so
    public ArrayList<LoaiSach> getdata(String sql, String ...a){
        ArrayList<LoaiSach> list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,a);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int maloai= cursor.getInt(0);
            String tenloai= cursor.getString(1);
            LoaiSach loaiSach=new LoaiSach(maloai,tenloai);
            list.add(loaiSach);
            cursor.moveToNext();
        }

        cursor.close();
        return list;
    }

    // get all data
    public ArrayList<LoaiSach> getall(){
        String sql=" SELECT * FROM LoaiSach ";
        return getdata(sql);
    }

    // get theo id
    public LoaiSach getID(String id){
        String sql=" SELECT * FROM LoaiSach WHERE maLoai=? ";
        ArrayList<LoaiSach> lists=getdata(sql, id);
        return lists.get(0);
    }
}
