package com.example.namnhph14161_asm_duanmau.fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.namnhph14161_asm_duanmau.DAO.DAOthuthu;
import com.example.namnhph14161_asm_duanmau.Model.ThuThu;
import com.example.namnhph14161_asm_duanmau.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class frag_ThemNguoiDung extends Fragment {
    EditText edUser, edHoten, edpass, edRePass;
    Button btnSave, btnCancel;
    DAOthuthu dao;
    TextInputLayout user,name,pass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_nguoi_dung, container, false);
        user = view.findViewById(R.id.layoutUser);
        name = view.findViewById(R.id.LayoutName);
        pass = view.findViewById(R.id.LayoutPass);
        edUser = view.findViewById(R.id.edUser);
        edHoten = view.findViewById(R.id.edHoTen);
        edpass = view.findViewById(R.id.edPass);
        edRePass = view.findViewById(R.id.edRePass);
        btnSave = view.findViewById(R.id.btn_save_thuthu);
        btnCancel = view.findViewById(R.id.btn_huy_thuthu);
        dao = new DAOthuthu(getActivity());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edUser.setText("");
                edHoten.setText("");
                edpass.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThuThu thuThu = new ThuThu();
                thuThu.setMaTT(edUser.getText().toString());
                thuThu.setHoTen(edHoten.getText().toString());
                thuThu.setMatKhau(edpass.getText().toString());
                if (validate() > 0) {
                    if (dao.insert(thuThu) > 0) {
                        Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                        edUser.setText("");
                        edHoten.setText("");
                        edpass.setText("");
                        edRePass.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;

    }

    private int validate() {
        int check = 1;

        if (edUser.getText().toString().length() == 0 || edHoten.getText().toString().length() == 0 || edpass.getText().toString().length() == 0 || edRePass.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edpass.getText().toString();
            String repass = edRePass.getText().toString();
            if (!pass.equals(repass)) {
                Toast.makeText(getActivity(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (edHoten.getText().toString().length() <= 5 || edHoten.getText().toString().length() > 15){
                name.setError("Họ tên phải lớn hơn 5 ký tự và nhỏ hơn 15 ký tự");
                check = -1;
            }
        }
        return check;
    }

}