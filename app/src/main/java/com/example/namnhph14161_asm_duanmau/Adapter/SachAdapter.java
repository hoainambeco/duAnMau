package com.example.namnhph14161_asm_duanmau.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.namnhph14161_asm_duanmau.DAO.DAOLoaiSach;
import com.example.namnhph14161_asm_duanmau.Model.LoaiSach;
import com.example.namnhph14161_asm_duanmau.Model.Sach;
import com.example.namnhph14161_asm_duanmau.R;
import com.example.namnhph14161_asm_duanmau.fragment.frag_Sach;

import java.util.ArrayList;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    frag_Sach fragment;
    private ArrayList<Sach> lists;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai,tvTrang;
    ImageView imgdel;

    public SachAdapter(@NonNull Context context, frag_Sach fragment, ArrayList<Sach> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.custom_view_sach, null);

        }
        final Sach item = lists.get(position);
        if (item != null){
            DAOLoaiSach loaiSachDAO = new DAOLoaiSach(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));
            tvMaSach = v.findViewById(R.id.tv_masach);
            tvMaSach.setText("Mã Sách: "+ item.getMaSach());
            tvTenSach = v.findViewById(R.id.tv_tensach);
            tvTenSach.setText("Tên Sách: "+ item.getTenSach());
            tvTrang = v.findViewById(R.id.tv_trang);
            tvTrang.setText("Số trang: "+ item.getTrang());
            if (item.getTrang() >1000 ){
                tvTrang.setTypeface(null, Typeface.BOLD);
            }
            tvGiaThue = v.findViewById(R.id.ed_giathue_sach);
            tvGiaThue.setText("Giá thuê: "+item.getGiaThue());
            tvLoai = v.findViewById(R.id.tv_loaisach);
            tvLoai.setText("Loại sách: " +loaiSach.getTenLoai());
            imgdel = v.findViewById(R.id.imgdelete_sach);
        }
        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.xoa(String.valueOf(item.getMaSach()));
            }
        });
        return v;
    }
}
