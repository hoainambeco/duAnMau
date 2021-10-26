package com.example.namnhph14161_asm_duanmau.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.namnhph14161_asm_duanmau.Adapter.ThanhVienAdapter;
import com.example.namnhph14161_asm_duanmau.DAO.ThanhVienDAO;
import com.example.namnhph14161_asm_duanmau.Model.ThanhVien;
import com.example.namnhph14161_asm_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frag_ThanhVien extends Fragment {
    ListView lv;
    ArrayList<ThanhVien> list;
    FloatingActionButton  fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh,edStk;
    Button btnSave, btnCancel;
    static ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    ThanhVien item;
//    SearchView searchView;
    EditText edSearch;
    ImageButton btnSearch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_frag_thanh_vien,container,false);
        lv  = view.findViewById(R.id.lvThanhVien);
        fab = view.findViewById(R.id.fla_Lsach);
        edSearch = view.findViewById(R.id.ed_search);
        btnSearch = view.findViewById(R.id.btnSearch);
        dao = new ThanhVienDAO(getActivity());
        capNhatLv();
        list = dao.getall();
        adapter = new ThanhVienAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);
//        searchView = (SearchView) view.findViewById(R.id.id_seach);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
////                adapter.getFilter().filter(query);
//                adapter.filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.filter(newText);
////                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = edSearch.getText().toString();
                if (search.length()>0) {
                    ArrayList<ThanhVien> thanhVienArrayList = dao.getSearch(search);
                    adapter = new ThanhVienAdapter(getContext(),frag_ThanhVien.this,thanhVienArrayList);
                    lv.setAdapter(adapter);
                }
                else {
                    capNhatLv();
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(getActivity(), 1);
            }
        });
        return view;
    }


    private void openDialog(final Context context, final int type) {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.frag_add_tvien);
        edMaTV = dialog.findViewById(R.id.ed_maTvien);
        edTenTV = dialog.findViewById(R.id.ed_tenTvien);
        edNamSinh = dialog.findViewById(R.id.ed_namsinhTvien);
        edStk = dialog.findViewById(R.id.ed_STK);

        btnSave = dialog.findViewById(R.id.btn_add_Tvien);
        btnCancel = dialog.findViewById(R.id.btn_huy_Tvien);
        dialog.show();

        edMaTV.setEnabled(false);
        if (type != 0) {
            edMaTV.setText(String.valueOf(item.getMaTV()));
            edTenTV.setText(String.valueOf(item.getHoTenTV()));
            edNamSinh.setText(String.valueOf(item.getNamSinh()));
            edStk.setText(String.valueOf(item.getSTK()));
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
                item = new ThanhVien();
                item.setHoTenTV(edTenTV.getText().toString());
                item.setNamSinh(edNamSinh.getText().toString());
                item.setSTK(Integer.parseInt(edStk.getText().toString()));
                if (validate() > 0){
                    if (type == 0 ){
                        if (dao.insert(item) > 0){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }else  {
                        item.setMaTV(Integer.parseInt(edMaTV.getText().toString()));
                        if (dao.update(item) >0 ) {
                            Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
    }
    public void xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("bạn có muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.delete(id);
                capNhatLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog= builder.create();
        builder.show();
    }
    void capNhatLv(){
        list = dao.getall();
        adapter = new ThanhVienAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);
    }
    public  int validate(){
        int check =1;
        if (edTenTV.getText().toString().length() == 0 || edNamSinh.getText().toString().length() == 0 || edStk.getText().toString().length() ==0){
            Toast.makeText(getContext(),"Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        if (edTenTV.getText().length()==0|| edNamSinh.getText().toString().length()==0){
            Toast.makeText(getContext(),"Không được để trống họ tên và năm sinh", Toast.LENGTH_SHORT).show();
            check = -1;
        }else if (edTenTV.getText().toString().length() <5 ||edTenTV.getText().toString().length() >15  ){
            Toast.makeText(getContext(),"Độ dài tên từ 5-15", Toast.LENGTH_SHORT).show();
            check = -1;
        }else if (!edTenTV.getText().toString().substring(0, 1).toUpperCase().equals(edTenTV.getText().toString().substring(0, 1))) {
            Toast.makeText(getActivity(), "Chữ cái đầu viết hoa", Toast.LENGTH_SHORT).show();
            check = -1;
        }else{
            check = 1;
        }
        return check;
    }
}