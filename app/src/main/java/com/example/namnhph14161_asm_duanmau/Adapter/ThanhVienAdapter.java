package com.example.namnhph14161_asm_duanmau.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.namnhph14161_asm_duanmau.Model.ThanhVien;
import com.example.namnhph14161_asm_duanmau.R;
import com.example.namnhph14161_asm_duanmau.fragment.frag_ThanhVien;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> implements Filterable {

    private Context context;
    frag_ThanhVien frag_thanhVien;
    private ArrayList<ThanhVien> lists;
    private ArrayList<ThanhVien> arrayList;//tạo mới
    TextView tvMaTV, tvTenTV, tvNamSinh;
    ImageView imgDel;

    public ThanhVienAdapter(Context context, frag_ThanhVien frag_thanhVien, ArrayList<ThanhVien> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
        this.frag_thanhVien = frag_thanhVien;
//của sarech
        this.arrayList=lists;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_view_thanhvien, null);
        }
        ThanhVien item = lists.get(position);
        if (item != null) {
            tvMaTV = view.findViewById(R.id.tv_matvien);
            tvMaTV.setText("Mã thành viên: " + item.getMaTV());

            tvTenTV = view.findViewById(R.id.tv_tentvien);
            tvTenTV.setText("Tên thành viên: " + item.getHoTenTV());

            tvNamSinh = view.findViewById(R.id.tv_namsinh_tvien);
            tvNamSinh.setText("Năm sinh: " + item.getNamSinh());
            imgDel = view.findViewById(R.id.imgdelete_tvien);
// CÂU 2
            if (position % 2 == 0){
                tvMaTV.setTextColor(Color.GREEN);
                tvTenTV.setTextColor(Color.GREEN);
                tvNamSinh.setTextColor(Color.GREEN);
            }
            else {
                tvMaTV.setTextColor(Color.RED);
                tvTenTV.setTextColor(Color.RED);
                tvNamSinh.setTextColor(Color.RED);
            }
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frag_thanhVien.xoa(String.valueOf(item.getMaTV()));
            }
        });
        return view;
    }
//
//    public void filter(String charText) {
//        charText = charText.toLowerCase(Locale.getDefault());
//        lists.clear();
//        if (charText.length() == 0) {
//            lists.addAll(arrayList);
//            lists = arrayList;
//        } else {
//            for (ThanhVien wp : lists) {
//                if (wp.getHoTenTV().toLowerCase(Locale.getDefault()).contains(charText.toLowerCase())) {
//                    lists.add(wp);
//                }
//                if (String.valueOf(wp.getMaTV()).toLowerCase(Locale.getDefault()).contains(charText.toLowerCase())) {
//                    lists.add(wp);
//                }
//                if (wp.getNamSinh().toLowerCase(Locale.getDefault()).contains(charText.toLowerCase())) {
//                    lists.add(wp);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                lists.clear();
//                String strSearch = constraint.toString();
//                if (strSearch.isEmpty()) {
//                    lists = arrayList;
//                } else {
//                    ArrayList<ThanhVien> list = new ArrayList<>();
//                    for (ThanhVien wp : arrayList) {
//                        if (wp.getHoTenTV().toLowerCase(Locale.getDefault()).contains(strSearch.toLowerCase())) {
//                            list.add(wp);
//                        }
//                        if (String.valueOf(wp.getMaTV()).toLowerCase(Locale.getDefault()).contains(strSearch.toLowerCase())) {
//                            list.add(wp);
//                        }
//                        if (wp.getNamSinh().toLowerCase(Locale.getDefault()).contains(strSearch.toLowerCase())) {
//                            list.add(wp);
//                        }
//                    }
//                    lists = list;
//                }
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = lists;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                lists = (ArrayList<ThanhVien>) results.values;
//                notifyDataSetChanged();
//            }
//        };
//    }
}
