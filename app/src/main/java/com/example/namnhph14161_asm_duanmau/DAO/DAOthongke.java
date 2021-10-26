package com.example.namnhph14161_asm_duanmau.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.namnhph14161_asm_duanmau.DB;
import com.example.namnhph14161_asm_duanmau.Model.Sach;
import com.example.namnhph14161_asm_duanmau.Model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DAOthongke {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public DAOthongke(Context context){
        this.context=context;
        DB dbSql=new DB(context);
        db=dbSql.getWritableDatabase();
    }



    // thong ke top10
    public List<Top> gettop(){
        List<Top> list=new ArrayList<>();
        String sql=" SELECT maSach, count(maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10 ";
        DAOsach daOsach=new DAOsach(context);
        Cursor cursor=db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Top top=new Top();
            Sach sach= daOsach.getid(cursor.getString(cursor.getColumnIndex("maSach")));
            top.tenSach= sach.getTenSach();
            top.soLuong=Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong")));

            list.add(top);

            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    // thong ke doanh thu
    public int getDoanhthu(String tuNgay,String denNgay){
        String sql=" SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ? ";
        List<Integer> list=new ArrayList<>();
        Cursor cursor= db.rawQuery(sql,new String[]{tuNgay,denNgay});
        while (cursor.moveToNext()){
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            }catch (Exception ex){
                list.add(0);
            }
        }
        return list.get(0);
    }



}
