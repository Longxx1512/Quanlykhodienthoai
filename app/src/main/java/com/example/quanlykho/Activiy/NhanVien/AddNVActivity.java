package com.example.quanlykho.Activiy.NhanVien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlykho.Activiy.NhanVienActivity;
import com.example.quanlykho.Adapter.NhanVienAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.NhanVien;

import java.util.List;

public class AddNVActivity extends AppCompatActivity {


    private Button btnadd,btnexit;
    private DBHelper dbHelper;
    private List<NhanVien> list;
    private NhanVienAdapter adapter;
    private ListView listView;
    private EditText edtten,edtchucvu,edtsdt,edtdiachi;
    private String name,duty,phone,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nvactivity);
        dbHelper = new DBHelper(this);
        anhxa();
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNVActivity.this, NhanVienActivity.class));
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savaData();

            }
        });
    }


    private void savaData(){
        name = edtten.getText().toString();
        phone = edtsdt.getText().toString();
        duty = edtchucvu.getText().toString();
        address = edtdiachi.getText().toString();

        if(!name.isEmpty() && !phone.isEmpty() && !duty.isEmpty() && !address.isEmpty()){
            String Name = edtten.getText().toString();
            String Phone = edtsdt.getText().toString();
            String Duty = edtchucvu.getText().toString();
            String Address = edtdiachi.getText().toString();
            NhanVien nv = new NhanVien();
            nv.setName(Name);
            nv.setPhone(Phone);
            nv.setDuty(Duty);
            nv.setAddress(Address);

            dbHelper.insertNV(nv);

            Toast.makeText(getApplicationContext(),"Thêm thành công", Toast.LENGTH_SHORT).show();
            updateListNV();
            edtten.setText("");
            edtchucvu.setText("");
            edtdiachi.setText("");
            edtsdt.setText("");
        } else {
            Toast.makeText(getApplicationContext(),"Thêm thất bại, vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateListNV() {
        if (list != null && dbHelper != null) {
            list.clear();
            list.addAll(dbHelper.INFONV());

            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void anhxa() {
        btnadd = findViewById(R.id.BTNADD);
        btnexit = findViewById(R.id.BTNEXIT);
        edtten = findViewById(R.id.tenNV);
        edtsdt = findViewById(R.id.SDT);
        edtchucvu = findViewById(R.id.ChucVu);
        edtdiachi = findViewById(R.id.DiaChi);
    }

}