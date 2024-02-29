package com.example.quanlykho.Activiy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.quanlykho.Activiy.NhaCungCap.AddNhaCCActivity;
import com.example.quanlykho.Activiy.NhaCungCap.UpdateNhaCCActivity;
import com.example.quanlykho.Adapter.NCCAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.NhaCungCap;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NhaCungCapActivity extends AppCompatActivity {

   private Button btnexit;
   private FloatingActionButton fab;
   private ListView listView;
   private DBHelper dbHelper;
   private List<NhaCungCap> list;
   private NCCAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nha_cung_cap);
        anhxa();
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NhaCungCapActivity.this, HomeActivity.class));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NhaCungCapActivity.this, AddNhaCCActivity.class));
            }
        });
        dbHelper = new DBHelper(NhaCungCapActivity.this);
        list = dbHelper.INFONCC();
        setApapter();

        //Click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhaCungCap selectedNCC = list.get(position);
                Intent intent = new Intent(NhaCungCapActivity.this, UpdateNhaCCActivity.class);
                intent.putExtra("idnhacungcap", selectedNCC.getId());
                intent.putExtra("tennhacungcap", selectedNCC.getName());
                intent.putExtra("sdtnhacungcap", selectedNCC.getPhone());
                intent.putExtra("emailnhacungcap", selectedNCC.getEmail());
                intent.putExtra("diachinhacungcap", selectedNCC.getAddress());
                startActivity(intent);
            }
        });


    }

    private void setApapter() {
        if (adapter == null) {
            adapter = new NCCAdapter(NhaCungCapActivity.this, R.layout.ncc_item, list);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }


    private void anhxa() {
        btnexit = findViewById(R.id.buttonExit);
        fab = findViewById(R.id.floatingActionButton);
        listView = findViewById(R.id.lvNCC);
    }


}