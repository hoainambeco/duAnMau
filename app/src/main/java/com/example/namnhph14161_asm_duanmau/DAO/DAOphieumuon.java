package com.example.namnhph14161_asm_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.namnhph14161_asm_duanmau.DB;
import com.example.namnhph14161_asm_duanmau.Model.PhieuMuon;

import java.util.ArrayList;

public class DAOphieumuon {
    private SQLiteDatabase db;

    public DAOphieumuon(Context context){
        DB dbSql=new DB(context);
        db=dbSql.getWritableDatabase();
    }

    public long insert(PhieuMuon phieuMuon){
        ContentValues values=new ContentValues();
        values.put("maTT",phieuMuon.getMaTT());
        values.put("maTV",phieuMuon.getMaTV());
        values.put("maSach",phieuMuon.getMaSach());
        values.put("tienThue",phieuMuon.getTienThue());
        values.put("ngay",phieuMuon.getNgay());
        values.put("traSach",phieuMuon.getTraSach());

        return db.insert("PhieuMuon",null,values);

    }
    public int update(PhieuMuon phieuMuon){
        ContentValues values=new ContentValues();
        values.put("maTT",phieuMuon.getMaTT());
        values.put("maTV",phieuMuon.getMaTV());
        values.put("maSach",phieuMuon.getMaSach());
        values.put("tienThue",phieuMuon.getTienThue());
        values.put("ngay",phieuMuon.getNgay());
        values.put("traSach",phieuMuon.getTraSach());

        return db.update("PhieuMuon",values,"maPM=?",new String[]{String.valueOf(phieuMuon.getMaPM())});

    }
    public int delete(String id){

        return db.delete("PhieuMuon","maPM=?",new String[]{id});

    }



    // get data nhieu tham so
    public ArrayList<PhieuMuon> getdata(String sql,String ...a){
        ArrayList<PhieuMuon> list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,a);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int mapm= cursor.getInt(0);
            int matt= cursor.getInt(1);
            int matv= cursor.getInt(2);
            int masach= cursor.getInt(3);
            int tienthue= cursor.getInt(4);
            String date= cursor.getString(5);
            int trasach= cursor.getInt(6);


            PhieuMuon phieuMuon=new PhieuMuon(mapm,matt,matv,masach,tienthue,date,trasach);
            list.add(phieuMuon);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    // get all data
    public ArrayList<PhieuMuon> getall(){
        String sql=" SELECT * FROM PhieuMuon ";
        return getdata(sql);
    }

    // get theo id
    public PhieuMuon getID(String id){
        String sql=" SELECT * FROM PhieuMuon WHERE maPM=? ";
        ArrayList<PhieuMuon> list=getdata(sql,id);
        return list.get(0);
    }


}
