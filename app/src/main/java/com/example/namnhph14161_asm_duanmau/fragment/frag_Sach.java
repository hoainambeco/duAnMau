package com.example.namnhph14161_asm_duanmau.fragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.namnhph14161_asm_duanmau.Adapter.LoaiSachSpinnerAdapter;
import com.example.namnhph14161_asm_duanmau.Adapter.SachAdapter;
import com.example.namnhph14161_asm_duanmau.Adapter.ThanhVienAdapter;
import com.example.namnhph14161_asm_duanmau.DAO.DAOLoaiSach;
import com.example.namnhph14161_asm_duanmau.DAO.DAOsach;
import com.example.namnhph14161_asm_duanmau.Model.LoaiSach;
import com.example.namnhph14161_asm_duanmau.Model.Sach;
import com.example.namnhph14161_asm_duanmau.Model.ThanhVien;
import com.example.namnhph14161_asm_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frag_Sach extends Fragment {
    ListView lv;
    ArrayList<Sach> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach, edTenSach, edGiaThue, edTrangSach;
    Spinner spinner;
    Button btnSave, btnCancel;
    static DAOsach dap;
    SachAdapter adapter;
    Sach item;
    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    DAOLoaiSach loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach, position;
    EditText edSearch;
    ImageButton btnSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_frag_sach,container,false);
        lv = view.findViewById(R.id.lvSach);
        fab = view.findViewById(R.id.fla_sach);
        edSearch = view.findViewById(R.id.ed_search);
        btnSearch = view.findViewById(R.id.btnSearch);
        dap = new DAOsach(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);
            }
        });
//        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                item = list.get(position);
//                openDialog(getActivity(),1);
//                return false;
//            }
//        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = edSearch.getText().toString();
                if (search.length()>0) {
                    ArrayList<Sach> sachArrayList = dap.getsearch(search);
                    adapter = new SachAdapter(getContext(),frag_Sach.this,sachArrayList);
                    lv.setAdapter(adapter);
                }
                else {
                    capNhatLv();
                }
            }
        });
        return view;
    }
    public void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.frag_addsach);
        edMaSach = dialog.findViewById(R.id.ed_masach);
        edTenSach = dialog.findViewById(R.id.ed_tensach);
        edGiaThue = dialog.findViewById(R.id.ed_giathue_sach);
        edTrangSach = dialog.findViewById(R.id.ed_Trang_sach);
        spinner = dialog.findViewById(R.id.sp_loaisach);
        btnCancel = dialog.findViewById(R.id.btn_huy_sach);
        btnSave = dialog.findViewById(R.id.btn_add_sach);
        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDAO = new DAOLoaiSach(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getall();
        spinnerAdapter = new LoaiSachSpinnerAdapter(context,listLoaiSach);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maLoaiSach = listLoaiSach.get(position).getMaLoai();
                Toast.makeText(context,"chọn "+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edMaSach.setEnabled(false);
        if (type!=0){
            edMaSach.setText(String.valueOf(item.getMaSach()));
            edTenSach.setText(String.valueOf(item.getTenSach()));
            edGiaThue.setText(String.valueOf(item.getGiaThue()));
            edTrangSach.setText(String.valueOf(item.getTrang()));
            for (int i = 0; i < listLoaiSach.size(); i++) {
                if (item.getMaLoai() == (listLoaiSach.get(i).getMaLoai())){
                    position = i;
                }
                Log.i("demo","posSach" + position);
                spinner.setSelection(position);
            }
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new Sach();
                item.setTenSach(edTenSach.getText().toString());
                item.setGiaThue(Integer.parseInt(edGiaThue.getText().toString()));
                item.setTrang(Integer.parseInt(edTrangSach.getText().toString()));
                item.setMaLoai(maLoaiSach);
                if (validate()>0){
                    if (type == 0){
                        if (dap.insert(item)>0){
                            Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
                        if (dap.update(item)>0){
                            Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dap.delete(id);
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
    void capNhatLv(){
        list =(ArrayList<Sach>) dap.getall();
        adapter = new SachAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if (edTenSach.getText().length() == 0|| edGiaThue.getText().length() == 0){
            Toast.makeText(getContext(),"Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}