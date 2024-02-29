package com.example.quanlykho.Activiy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quanlykho.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();
        Toast.makeText(getApplicationContext(), "Chào mừng "+username, Toast.LENGTH_SHORT).show();
        CardView exit = findViewById(R.id.cardDangXuat);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitDialog();
               SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
//                startActivity(new Intent(HomeActivity.this, LoginActivity.class));

            }
        });
        CardView sanpham = findViewById(R.id.cardPhone);
        sanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SanPhamActivity.class));
            }
        });
        CardView nhacungcap = findViewById(R.id.cardNhaCungCap);
        nhacungcap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NhaCungCapActivity.class));
            }
        });
        CardView nhaphang = findViewById(R.id.cardNhapHang);
        nhaphang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NhapKhoActivity.class));
            }
        });
        CardView xuathang = findViewById(R.id.cardXuatHang);
        xuathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, XuatKhoActivity.class));
            }
        });
        CardView nhanvien = findViewById(R.id.cardNhanVien);
        nhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NhanVienActivity.class));
            }
        });
    }
    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Bạn có chắc chắn muốn đăng xuất?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //finish();
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}