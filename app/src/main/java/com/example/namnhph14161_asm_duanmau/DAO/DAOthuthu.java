package com.example.namnhph14161_asm_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.namnhph14161_asm_duanmau.DB;
import com.example.namnhph14161_asm_duanmau.Model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class DAOthuthu {
    private SQLiteDatabase db;
    public DAOthuthu(Context context){
        DB dbSql=new DB(context);
        db=dbSql.getWritableDatabase();
    }

    public long insert(ThuThu thuThu){
        ContentValues values=new ContentValues();
        values.put("maTT",thuThu.getMaTT());
        values.put("hoTen",thuThu.getHoTen());
        values.put("matKhau",thuThu.getMatKhau());

        return db.insert("ThuThu",null,values);

    }
    public int update(ThuThu thuThu){
        ContentValues values=new ContentValues();
        values.put("maTT",thuThu.getMaTT());
        values.put("hoTen",thuThu.getHoTen());
        values.put("matKhau",thuThu.getMatKhau());

        return db.update("ThuThu",values,"maTT=?",new String[]{thuThu.getMaTT()});

    }
    public int delete(String id){

        return db.delete("ThuThu","maTT=?",new String[]{id});

    }


    // get data nhieu tham so
    public List<ThuThu> getdata(String sql,String ...a){
        List<ThuThu> list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,a);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String matt= cursor.getString(0);
            String tentt= cursor.getString(1);
            String mk= cursor.getString(2);
            ThuThu thuThu=new ThuThu(matt,tentt,mk);
            list.add(thuThu);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    // get all data
    public List<ThuThu> getall(){
        String sql=" SELECT * FROM ThuThu ";
        return getdata(sql);
    }

    // get theo id
    public ThuThu getID(String id){
        String sql=" SELECT * FROM ThuThu WHERE maTT=? ";
        List<ThuThu> list=getdata(sql,id);
        return list.get(0);
    }

    public int checkLogin(String id, String password){
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getdata(sql,id,password);
        if (list.size()==0 ){
            return -1;
        }
        return 1;
    }
}
