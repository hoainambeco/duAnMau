package com.example.namnhph14161_asm_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.namnhph14161_asm_duanmau.DB;
import com.example.namnhph14161_asm_duanmau.Model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        DB dbSql=new DB(context);
        db=dbSql.getWritableDatabase();
    }
    public long insert(ThanhVien thanhVien){
        ContentValues values=new ContentValues();
        values.put("hoTenTV",thanhVien.getHoTenTV());
        values.put("namSinh",thanhVien.getNamSinh());
        return db.insert("ThanhVien",null,values);
    }
    public long update(ThanhVien thanhVien){
        ContentValues values=new ContentValues();
        values.put("hoTenTV",thanhVien.getHoTenTV());
        values.put("namSinh",thanhVien.getNamSinh());
        return db.update("ThanhVien",values,"maTV=?",new String[]{String.valueOf(thanhVien.getMaTV())});

    }
    public int delete(String id){

        return db.delete("ThanhVien","maTV=?",new String[]{id});
    }

    // get data vao nhieu tham so
    public ArrayList<ThanhVien> getdata(String sql, String ...a){
        ArrayList<ThanhVien> list=new ArrayList<>();
        Cursor cursor= db.rawQuery(sql,a);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int matv= cursor.getInt(0);
            String hotentv= cursor.getString(1);
            String namsinh= cursor.getString(2);
            ThanhVien thanhVien=new ThanhVien(matv,hotentv,namsinh);
            list.add(thanhVien);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    // get all data
    public ArrayList<ThanhVien> getall(){
        String sql=" SELECT * FROM ThanhVien ";
        return getdata(sql);
    }
    // get data theo id
    public ThanhVien getID(String id){
        String sql=" SELECT *FROM ThanhVien WHERE maTV=? ";
        ArrayList<ThanhVien> list=getdata(sql,id);
        return list.get(0);
    }
    public ArrayList<ThanhVien> getSearch(String text){
        ArrayList<ThanhVien> list = new ArrayList<>();
        String a = "%"+text+"%";
        String sql = "SELECT * FROM ThanhVien WHERE hoTenTV LIKE ?";
        Cursor cursor = db.rawQuery(sql, new String[]{a});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int matv= cursor.getInt(0);
            String hotentv= cursor.getString(1);
            String namsinh= cursor.getString(2);
            ThanhVien thanhVien=new ThanhVien(matv,hotentv,namsinh);
            list.add(thanhVien);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
