package com.example.quanlykho.Activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import com.example.quanlykho.Activiy.XuatKho.AddXuatKhoActivity;
import com.example.quanlykho.Activiy.XuatKho.UpdateXuatKhoActivity;
import com.example.quanlykho.Adapter.XuatKhoAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.XuatKho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class XuatKhoActivity extends AppCompatActivity {

    private Button btnexit;
    private FloatingActionButton fab;
    private ListView listView;
    private DBHelper dbHelper;
    private List<XuatKho> list;
    private XuatKhoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuat_kho);
        anhxa();
        dbHelper = new DBHelper(this);
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(XuatKhoActivity.this, HomeActivity.class));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(XuatKhoActivity.this, AddXuatKhoActivity.class));
            }
        });


        list =dbHelper.INFOXK();
        setApapter();

        //Click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                XuatKho selectedXK = list.get(position);
                Intent intent = new Intent(XuatKhoActivity.this, UpdateXuatKhoActivity.class);
                intent.putExtra("idxuatkho", selectedXK.getId());
                intent.putExtra("tensanpham", selectedXK.getName());
                intent.putExtra("ngayxuatsanpham", selectedXK.getDate());
                intent.putExtra("soluongxuatkho", selectedXK.getQuantity());
                intent.putExtra("giaxuat", selectedXK.getPrice());

                startActivity(intent);
            }
        });
    }
    private void setApapter() {
        if (adapter == null) {
            adapter = new XuatKhoAdapter(XuatKhoActivity.this, R.layout.xk_item, list);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
    private void anhxa() {
        btnexit = findViewById(R.id.buttonExit);
        fab = findViewById(R.id.floatingActionButton);
        listView = findViewById(R.id.lvXK);
    }
}