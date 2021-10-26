package com.example.namnhph14161_asm_duanmau.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.namnhph14161_asm_duanmau.Adapter.PhieuMuonAdapter;
import com.example.namnhph14161_asm_duanmau.Adapter.SachSpinnerAdapter;
import com.example.namnhph14161_asm_duanmau.Adapter.ThanhVienSpinnerAdapter;
import com.example.namnhph14161_asm_duanmau.DAO.DAOphieumuon;
import com.example.namnhph14161_asm_duanmau.DAO.DAOsach;
import com.example.namnhph14161_asm_duanmau.DAO.ThanhVienDAO;
import com.example.namnhph14161_asm_duanmau.Model.PhieuMuon;
import com.example.namnhph14161_asm_duanmau.Model.Sach;
import com.example.namnhph14161_asm_duanmau.Model.ThanhVien;
import com.example.namnhph14161_asm_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static java.time.LocalDate.now;

public class frag_PhieuMuon extends Fragment {
    ListView lv;
    ArrayList<PhieuMuon> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spSach;
    TextView tvNgay, tvTienThue;
    CheckBox chkTraSach;
    Button btnSavem, btnCancel;
    static DAOphieumuon dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;
    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    DAOsach sachDAO;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;
    final Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_frag_phieu_muon,container,false);
        lv = view.findViewById(R.id.lvPhieuMuon);
        fab = view.findViewById(R.id.fab);
        dao = new DAOphieumuon(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return view;
    }

    private void openDialog(final Context context,final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.frag_addphieumuon);
        edMaPM = dialog.findViewById(R.id.ed_mapm);
        spTV = dialog.findViewById(R.id.sp_tvien_pm);
        spSach = dialog.findViewById(R.id.sp_tensach_pm);
        tvNgay = dialog.findViewById(R.id.ed_ngaythue_pm);
        tvNgay.setText(""+calendar.getTime());
        tvTienThue = dialog.findViewById(R.id.ed_giathue_pm);
        chkTraSach = dialog.findViewById(R.id.chk_pm);
        btnCancel=dialog.findViewById(R.id.btn_huy_pm);
        btnSavem=dialog.findViewById(R.id.btn_add_pm);
        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getall();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context,listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maThanhVien = listThanhVien.get(i).getMaTV();
                Toast.makeText(context,"Chọn "+listThanhVien.get(i).getHoTenTV(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sachDAO = new DAOsach(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.getall();
        sachSpinnerAdapter = new SachSpinnerAdapter(context,listSach);
        spSach.setAdapter(sachSpinnerAdapter);
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maSach = listSach.get(i).getMaSach();
                tienThue = listSach.get(i).getGiaThue();
                Toast.makeText(context,"Chọn "+listSach.get(i).getTenSach(),Toast.LENGTH_SHORT).show();
                tvTienThue.setText(""+tienThue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edMaPM.setEnabled(false);
        if (type!=0){
            edMaPM.setText(String.valueOf(item.getMaPM()));
            for (int i = 0; i < listThanhVien.size(); i++) {
                if (item.getMaTV() == (listThanhVien.get(i).getMaTV())){
                    positionTV = i;
                }
            }
            spSach.setSelection(positionSach);
            tvNgay.setText("Ngày Thuê: "+item.getNgay());
            tvTienThue.setText("Tiền thuê: "+item.getTienThue());
            if (item.getTraSach()==1){
                chkTraSach.setChecked(true);
            }
            else{
                chkTraSach.setChecked(false);
            }
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSavem.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaTV(maThanhVien);
                item.setNgay(String.valueOf(sdf.format(calendar.getTime())));
                item.setTienThue(tienThue);
                if (chkTraSach.isChecked()){
                    item.setTraSach(1);
                }
                else {
                    item.setTraSach(0);
                }
                if (validate()>0){
                    if (type == 0 ){
                        if (dao.insert(item)>0){
                            Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        item.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                        if (dao.update(item)>0){
                            Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    private int validate() {
        int check = 1;
        return check;
    }

    private void capNhatLv() {
        list =(ArrayList<PhieuMuon>) dao.getall();
        adapter = new PhieuMuonAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.delete(Id);
                capNhatLv();
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog= builder.create();
        builder.show();
    }
}