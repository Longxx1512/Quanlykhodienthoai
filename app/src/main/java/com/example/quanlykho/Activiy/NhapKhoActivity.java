package com.example.quanlykho.Activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.quanlykho.Activiy.NhapKho.AddNhapKhoActivity;
import com.example.quanlykho.Activiy.NhapKho.UpdateNhapKhoActivity;
import com.example.quanlykho.Activiy.XuatKho.UpdateXuatKhoActivity;
import com.example.quanlykho.Adapter.NhapKhoAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.NhapKho;
import com.example.quanlykho.Table.XuatKho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NhapKhoActivity extends AppCompatActivity {

    private Button btnexit;
    private FloatingActionButton fab;
    private ListView listView;
    private DBHelper dbHelper;
    private List<NhapKho> list;
    private  NhapKhoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_kho);


        anhxa();
        dbHelper = new DBHelper(this);
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NhapKhoActivity.this, HomeActivity.class));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NhapKhoActivity.this, AddNhapKhoActivity.class));
            }
        });
        list =dbHelper.INFONK();
        setApapter();

        //Click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhapKho selectedNK = list.get(position);
                Intent intent = new Intent(NhapKhoActivity.this, UpdateNhapKhoActivity.class);
                intent.putExtra("idnhapkho", selectedNK.getId());
                intent.putExtra("tensanpham", selectedNK.getName());
                intent.putExtra("tennhacungcapsanpham", selectedNK.getNameNCC());
                intent.putExtra("ngaynhapsanpham", selectedNK.getDate());
                intent.putExtra("soluongnhapkho", selectedNK.getQuantity());
                intent.putExtra("gianhap", selectedNK.getPrice());

                startActivity(intent);
            }
        });
    }
    private void setApapter() {
        if (adapter == null) {
           adapter = new NhapKhoAdapter(NhapKhoActivity.this, R.layout.nk_item,list);
           listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
    private void anhxa() {
        btnexit = findViewById(R.id.buttonExit);
        fab = findViewById(R.id.floatingActionButton);
        listView = findViewById(R.id.lvNK);
    }
}