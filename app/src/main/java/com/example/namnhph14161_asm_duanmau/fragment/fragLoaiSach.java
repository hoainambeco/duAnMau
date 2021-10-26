package com.example.namnhph14161_asm_duanmau.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.namnhph14161_asm_duanmau.Adapter.LoaiSachAdapter;
import com.example.namnhph14161_asm_duanmau.DAO.DAOLoaiSach;
import com.example.namnhph14161_asm_duanmau.Model.LoaiSach;
import com.example.namnhph14161_asm_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import com.google.android.material.textfield.TextInputLayout;


public class fragLoaiSach extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton actionButton;
    DAOLoaiSach daoLoaiSach;
    ArrayList<LoaiSach> loaiSachArrayList;
    LoaiSachAdapter loaiSachAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_frag_loai_sach,container,false);

        recyclerView = view.findViewById(R.id.recy_Lsach);
        actionButton = view.findViewById(R.id.fla_Lsach);
        daoLoaiSach = new DAOLoaiSach(getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        loaiSachArrayList = new ArrayList<>();
        loaiSachArrayList = daoLoaiSach.getall();
        loaiSachAdapter= new LoaiSachAdapter(getContext(),loaiSachArrayList);
        recyclerView.setAdapter(loaiSachAdapter);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.frag_add_lsach);
                EditText ed_maLsach=dialog.findViewById(R.id.ed_maLsach);
                TextInputLayout ed_tenLsach=dialog.findViewById(R.id.ed_tenLsach);
                Button btn_add_Lsach=dialog.findViewById(R.id.btn_add_Lsach);
                Button btn_huy_Lsach=dialog.findViewById(R.id.btn_huy_Lsach);
                dialog.show();

                btn_huy_Lsach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ed_tenLsach.getEditText().setText("");
                        dialog.cancel();
                    }
                });

                btn_add_Lsach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ed_tenLsach.getEditText().getText().toString().isEmpty()){
                            Toast.makeText(getContext(), "Du lieu khong duoc trong", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (ed_tenLsach.getEditText().getText().toString().length() < 5 || ed_tenLsach.getEditText().getText().toString().length() > 20) {
                            Toast.makeText(getContext(), "Tên sách phải có từ 5 đến 20 ký tự", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (! isNumber(ed_tenLsach.getEditText().getText().toString().substring(0, 1))) {
                            Toast.makeText(getContext(), "Tên loại sách phải bắt đầu bằng số", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        LoaiSach loaiSach=new LoaiSach();
                        loaiSach.setTenLoai(ed_tenLsach.getEditText().getText().toString());

                        long result= daoLoaiSach.insert(loaiSach);
                        if(result>0){
                            loaiSachArrayList.clear();
                            loaiSachArrayList.addAll(daoLoaiSach.getall());
                            loaiSachAdapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }else {
                            Toast.makeText(getContext(), "Them that bai", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }
                });

            }
        });
        return view;
    }
    private static boolean isNumber(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}