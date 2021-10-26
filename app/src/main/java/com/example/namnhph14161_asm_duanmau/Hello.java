package com.example.namnhph14161_asm_duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Hello extends AppCompatActivity {
ImageButton img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                startActivity( new Intent(getApplicationContext(), Login.class));
//                finish();
                Toast.makeText(getApplicationContext(),"vui lòng ấn vào logo để chuyển sang trang tiếp",Toast.LENGTH_LONG).show();
            }
        }, 2000);
        img = findViewById(R.id.btnAnh);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
    }
}