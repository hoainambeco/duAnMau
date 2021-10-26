package com.example.namnhph14161_asm_duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.namnhph14161_asm_duanmau.DAO.DAOthuthu;
import com.example.namnhph14161_asm_duanmau.Model.ThuThu;
import com.example.namnhph14161_asm_duanmau.fragment.Frag_ChangePass;
import com.example.namnhph14161_asm_duanmau.fragment.fragLoaiSach;
import com.example.namnhph14161_asm_duanmau.fragment.frag_PhieuMuon;
import com.example.namnhph14161_asm_duanmau.fragment.frag_Sach;
import com.example.namnhph14161_asm_duanmau.fragment.frag_ThanhVien;
import com.example.namnhph14161_asm_duanmau.fragment.frag_ThemNguoiDung;
import com.example.namnhph14161_asm_duanmau.fragment.frag_Top10;
import com.example.namnhph14161_asm_duanmau.fragment.frang_DoanhThu;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
/**
 * Họ tên: Nguyễn Hoài Nam
 * Mã Sinh Viên: Ph14161
 * Lớp: CP16305
 */
public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    MaterialToolbar toolbarr;
    FragmentManager fragmentManager;
    View mHeaderView;
    TextView edUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.dra_nav);
        navigationView = findViewById(R.id.nav_View);
        toolbarr = findViewById(R.id.toolbar);

        setSupportActionBar(toolbarr);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.reorder);
        fragmentManager = getSupportFragmentManager();
        frag_PhieuMuon fragPhieumuon = new frag_PhieuMuon();
        fragmentManager.beginTransaction().replace(R.id.containerView,fragPhieumuon).commit();

        mHeaderView = navigationView.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.tvUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
//        DAOthuthu thuThuDAO = new DAOthuthu(this);
//        ThuThu thuThu = thuThuDAO.getID(user);
//        String username = thuThu.getHoTen();
        edUser.setText("Wellcome " + user + "!");
        if (user.equalsIgnoreCase("admin")){
            navigationView.getMenu().findItem(R.id.nd_addnd).setVisible(true);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ql_pm:
                        setTitle("Quản lý phiếu mượn");
                        frag_PhieuMuon fragPhieuMuon = new frag_PhieuMuon();
                        fragmentManager.beginTransaction().replace(R.id.containerView,fragPhieuMuon).commit();
                        break;
                    case R.id.ql_ls:
                        setTitle("Quản lý loại sách");
                        fragLoaiSach fragLoaiSach = new fragLoaiSach();
                        fragmentManager.beginTransaction().replace(R.id.containerView,fragLoaiSach).commit();
                        break;
                    case R.id.ql_s:
                        setTitle("Quản lý sách");
                        frag_Sach fragSach=new frag_Sach();
                        fragmentManager.beginTransaction().replace(R.id.containerView,fragSach).commit();
                        break;
                    case R.id.ql_tv:
                        setTitle("Quản lý thành viên");
                        frag_ThanhVien fragThanhvien=new frag_ThanhVien();
                        fragmentManager.beginTransaction().replace(R.id.containerView,fragThanhvien).commit();
                        break;
                    case R.id.tk_10:
                        setTitle("10 sách mượn nhiều nhất");
                        frag_Top10 fragTop10=new frag_Top10();
                        fragmentManager.beginTransaction().replace(R.id.containerView,fragTop10).commit();
                        break;
                    case R.id.tk_doanhthu:
                        setTitle("Thống kê doanh thu");
                        frang_DoanhThu fragDoanhthu=new frang_DoanhThu();
                        fragmentManager.beginTransaction().replace(R.id.containerView,fragDoanhthu).commit();
                        break;
                    case R.id.nd_addnd:
                        setTitle("Thêm người dùng");
                        frag_ThemNguoiDung fragAddnguoidung=new frag_ThemNguoiDung();
                        fragmentManager.beginTransaction().replace(R.id.containerView,fragAddnguoidung).commit();
                        break;
                    case R.id.nd_doimk:
                        setTitle("Thay đổi mật khẩu");
                        Frag_ChangePass frag_changePass=new Frag_ChangePass();
                        fragmentManager.beginTransaction().replace(R.id.containerView,frag_changePass).commit();
                        break;
                    case R.id.nd_out:
                        startActivity(new Intent(MainActivity.this,Login.class));
                        finish();
                        break;
                }
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            drawerLayout.openDrawer(navigationView);
        }
        return super.onOptionsItemSelected(item);
    }
}