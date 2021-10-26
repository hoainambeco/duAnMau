package com.example.namnhph14161_asm_duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namnhph14161_asm_duanmau.DAO.DAOLoaiSach;
import com.example.namnhph14161_asm_duanmau.Model.LoaiSach;
import com.example.namnhph14161_asm_duanmau.R;

import android.content.DialogInterface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.LSachViewHolder> {
    private Context context;
    private ArrayList<LoaiSach> loaiSachArrayList;
    DAOLoaiSach daoLoaiSach;
    LoaiSachAdapter loaiSachAdapter;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> loaiSachArrayList) {
        this.context = context;
        this.loaiSachArrayList = loaiSachArrayList;
        daoLoaiSach = new DAOLoaiSach(context);
    }

    @NonNull
    @Override
    public LSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_view_lsach, null);
        return new LSachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachAdapter.LSachViewHolder holder, int position) {
        LoaiSach loaiSach = loaiSachArrayList.get(position);
        holder.tv_maLsach.setText("Mã loại: " + loaiSach.getMaLoai());
        holder.tv_tenLsach.setText("Tên loại: " + loaiSach.getTenLoai());
        holder.sua_Lsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.frag_sua_lsach, null);
                builder.setView(view1);
                builder.setCancelable(true);

                EditText ed_sua_maLsach = view1.findViewById(R.id.ed_sua_maLsach);
                TextInputLayout ed_sua_tenLsach = view1.findViewById(R.id.ed_sua_tenLsach);
                Button btn_sua_Lsach = view1.findViewById(R.id.btn_sua_Lsach);
                Button btn_huy_sua_Lsach = view1.findViewById(R.id.btn_huy_sua_Lsach);
                AlertDialog dialog = builder.create();
                dialog.show();
                btn_huy_sua_Lsach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ed_sua_tenLsach.getEditText().setText("");
                    }
                });

                btn_sua_Lsach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ed_sua_tenLsach.getEditText().getText().toString().isEmpty()) {
                            Toast.makeText(context, "Dữ liệu không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (ed_sua_tenLsach.getEditText().getText().toString().length() < 5 || ed_sua_tenLsach.getEditText().getText().toString().length() > 20) {
                            Toast.makeText(context, "Tên sách phải có từ 5 đến 20 ký tự", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!isNumeric(ed_sua_tenLsach.getEditText().getText().toString().substring(0, 1))) {
                            Toast.makeText(context, "Tên loại sách phải bắt đầu bằng số", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        LoaiSach loaiSach = new LoaiSach();
                        loaiSach.setTenLoai(ed_sua_tenLsach.getEditText().getText().toString());

                        long result = daoLoaiSach.update(loaiSach);
                        if (result > 0) {
                            loaiSachArrayList.clear();
                            loaiSachArrayList.addAll(daoLoaiSach.getall());
                            loaiSachAdapter.notifyDataSetChanged();
                            Toast.makeText(context, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        } else {
                            Toast.makeText(context, "Sua that bai", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }
                });
            }
        });

        holder.imgdelete_Lsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int result = daoLoaiSach.delete(String.valueOf(loaiSach.getMaLoai()));
                        if (result > 0) {
                            loaiSachArrayList.clear();
                            loaiSachArrayList.addAll(daoLoaiSach.getall());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        } else {
                            Toast.makeText(context, "Xoa that bai", Toast.LENGTH_SHORT).show();
                            dialog.cancel();

                        }
                    }

                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return loaiSachArrayList.size();
    }

    public class LSachViewHolder extends RecyclerView.ViewHolder {
        TextView tv_maLsach;
        TextView tv_tenLsach;
        ImageView imgdelete_Lsach;
        LinearLayout sua_Lsach;

        public LSachViewHolder(View itemView) {
            super(itemView);
            tv_maLsach = itemView.findViewById(R.id.tv_maLsach);
            tv_tenLsach = itemView.findViewById(R.id.tv_tenLsach);
            imgdelete_Lsach = itemView.findViewById(R.id.imgdelete_Lsach);
            sua_Lsach = itemView.findViewById(R.id.sua_Lsach);

        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
