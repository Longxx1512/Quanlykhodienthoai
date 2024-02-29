package com.example.quanlykho.Activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import com.example.quanlykho.Activiy.NhanVien.AddNVActivity;
import com.example.quanlykho.Activiy.NhanVien.UpdateNVActivity;
import com.example.quanlykho.Adapter.NhanVienAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NhanVienActivity extends AppCompatActivity {

    private Button btnexit;
    private FloatingActionButton fab;
    private ListView listView;
    private DBHelper dbHelper;
    private List<NhanVien> list;
    private NhanVienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        anhxa();
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NhanVienActivity.this, HomeActivity.class));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NhanVienActivity.this, AddNVActivity.class));
            }
        });
        dbHelper = new DBHelper(NhanVienActivity.this);
        list = dbHelper.INFONV();
        setApapter();

        //Click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien selectedNV = list.get(position);
                // Trong NhanVienActivity
                Intent intent = new Intent(NhanVienActivity.this, UpdateNVActivity.class);
                intent.putExtra("idnhanvien", selectedNV.getId());
                intent.putExtra("tennhanvien", selectedNV.getName());
                intent.putExtra("chucvu", selectedNV.getDuty());
                intent.putExtra("sodienthoainhanvien", selectedNV.getPhone());
                intent.putExtra("diachinhanvien", selectedNV.getAddress());
                startActivity(intent);
            }
        });


    }

    private void setApapter() {
        if (adapter == null) {
            adapter = new NhanVienAdapter(NhanVienActivity.this, R.layout.nv_item, list);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }


    private void anhxa() {
        btnexit = findViewById(R.id.buttonExit);
        fab = findViewById(R.id.floatingActionButton);
        listView = findViewById(R.id.lvNV);
    }


}