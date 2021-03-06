package com.example.namnhph14161_asm_duanmau.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namnhph14161_asm_duanmau.DAO.DAOLoaiSach;
import com.example.namnhph14161_asm_duanmau.DAO.DAOphieumuon;
import com.example.namnhph14161_asm_duanmau.DAO.DAOsach;
import com.example.namnhph14161_asm_duanmau.DAO.ThanhVienDAO;
import com.example.namnhph14161_asm_duanmau.Model.PhieuMuon;
import com.example.namnhph14161_asm_duanmau.Model.Sach;
import com.example.namnhph14161_asm_duanmau.Model.ThanhVien;
import com.example.namnhph14161_asm_duanmau.R;
import com.example.namnhph14161_asm_duanmau.fragment.frag_PhieuMuon;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    frag_PhieuMuon fragment;
    private ArrayList<PhieuMuon> lists;
    TextView tvMaPM,tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach;
    ImageView imgDel;
    DAOsach sachDAO;
    ThanhVienDAO daOthanhvien;
    SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");


    public PhieuMuonAdapter(Context context,frag_PhieuMuon fragment ,ArrayList<PhieuMuon> lists) {
        super(context,0,lists);
        this.context = context;
        this.lists = lists;
        sachDAO=new DAOsach(context);
        daOthanhvien=new ThanhVienDAO(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.custom_view_phieumuon, null);
        }
        final PhieuMuon item = lists.get(position);
        if (item != null){
            tvMaPM = v.findViewById(R.id.tv_maPmuonn);
            tvMaPM.setText("M?? phi???u: "+item.getMaPM());
            sachDAO = new DAOsach(context);
            Sach sach = sachDAO.getid(String.valueOf(item.getMaSach()));
            tvTenSach = v.findViewById(R.id.tv_tensach_pm);
            tvTenSach.setText("T??n s??ch: "+sach.getTenSach());
            daOthanhvien = new ThanhVienDAO(context);
            ThanhVien thanhVien = daOthanhvien.getID(String.valueOf(item.getMaTV()));
            tvTenTV = v.findViewById(R.id.tv_tentvien_pm);
            tvTenTV.setText("Th??nh vi??n: "+thanhVien.getHoTenTV());
            tvTienThue = v.findViewById(R.id.tv_tienthue_pm);
            tvTienThue.setText("Ti???n thu??: "+ item.getTienThue());
            tvNgay = v.findViewById(R.id.ngaythue_pm);
            tvNgay.setText("Ng??y thu??: " + item.getNgay());
            tvTraSach = v.findViewById(R.id.trasach_pm);
            if (item.getTraSach()==1){
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("???? tr??? s??ch");
            }
            else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("ch??a tr??? s??ch");
            }
            imgDel= v.findViewById(R.id.imgdelete_pmuon);
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment.xoa(String.valueOf(item.getMaPM()));
                }
            });
        }
        return v;
    }
}
