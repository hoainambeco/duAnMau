package com.example.namnhph14161_asm_duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.namnhph14161_asm_duanmau.DAO.DAOthuthu;

public class Login extends AppCompatActivity {
    DAOthuthu dao;
    EditText edUserName, edPassword;
    Button btnLogin,btnCancel;
    CheckBox chkLuuPass;
    String strUser,strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("ĐĂNG NHẬP");
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPasswosrd);
        btnLogin = findViewById(R.id.btn_login);
        btnCancel = findViewById(R.id.btn_huy);
        chkLuuPass = findViewById(R.id.chk_luumk);

        dao = new DAOthuthu(this);
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edUserName.setText(pref.getString("USERNAME",""));
        edPassword.setText(pref.getString("PASSWORD",""));
        chkLuuPass.setChecked(pref.getBoolean("REMEMBER",false));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edUserName.setText("");
                edPassword.setText("");
            }
        });
    }
//|| (strUser.equals("admin")) && (strPass.equals("admin"))
    // user admin pass admin123
    private void checkLogin() {
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();

        if (strUser.isEmpty() || strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập vào mật khẩu không được để trống",Toast.LENGTH_SHORT).show();
        }
        else{
            if (dao.checkLogin(strUser,strPass)>0  || (strUser.equals("admin")) && (strPass.equals("admin"))){
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,chkLuuPass.isChecked());
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("user",strUser);
                startActivity(i);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void rememberUser(String strUser, String strPass, boolean checked) {
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!checked){
            editor.clear();
        }
        else {
            editor.putString("USERNAME",strUser);
            editor.putString("PASSWORD",strPass);
            editor.putBoolean("REMEMBER",checked);
        }
        editor.commit();
    }
}