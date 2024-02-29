package com.example.quanlykho.Activiy.NhaCungCap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlykho.Activiy.NhaCungCapActivity;
import com.example.quanlykho.Adapter.NCCAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.NhaCungCap;

import java.util.List;

public class AddNhaCCActivity extends AppCompatActivity {

    private Button btnadd,btnexit;
    private DBHelper dbHelper;
    private List<NhaCungCap> list;
    private NCCAdapter adapter;
    private ListView listView;
    private EditText edtten,edtsdt,edtemail,edtdiachi;
    private String name,phone,email,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nha_ccactivity);
        dbHelper = new DBHelper(this);
        anhxa();
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNhaCCActivity.this, NhaCungCapActivity.class));
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
        email = edtemail.getText().toString();
        address = edtdiachi.getText().toString();

        if(!name.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !address.isEmpty()){
            String Name = edtten.getText().toString();
            String Phone = edtsdt.getText().toString();
            String Email = edtemail.getText().toString();
            String Address = edtdiachi.getText().toString();
            NhaCungCap ncc = new NhaCungCap();
            ncc.setName(Name);
            ncc.setPhone(Phone);
            ncc.setEmail(Email);
            ncc.setAddress(Address);

            dbHelper.insertNCC(ncc);

            Toast.makeText(getApplicationContext(),"Thêm thành công", Toast.LENGTH_SHORT).show();
            updateListNCC();
            edtten.setText("");
            edtsdt.setText("");
            edtdiachi.setText("");
            edtemail.setText("");
        } else {
            Toast.makeText(getApplicationContext(),"Thêm thất bại, vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateListNCC() {
        if (list != null && dbHelper != null) {
            list.clear();
            list.addAll(dbHelper.INFONCC());

            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void anhxa() {
        btnadd = findViewById(R.id.BTNADD);
        btnexit = findViewById(R.id.BTNEXIT);
        edtten = findViewById(R.id.tenNCC);
        edtsdt = findViewById(R.id.SDT);
        edtemail = findViewById(R.id.Email);
        edtdiachi = findViewById(R.id.DiaChi);
    }

}