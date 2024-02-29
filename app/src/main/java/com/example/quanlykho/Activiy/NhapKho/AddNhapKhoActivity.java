package com.example.quanlykho.Activiy.NhapKho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlykho.Activiy.NhapKhoActivity;
import com.example.quanlykho.Adapter.NhapKhoAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.NhaCungCap;
import com.example.quanlykho.Table.NhapKho;



import java.util.ArrayList;
import java.util.List;

public class AddNhapKhoActivity extends AppCompatActivity {

    private Button btnadd,btnexit;
    private DBHelper dbHelper;
    private List<NhapKho> list;

    private NhapKhoAdapter adapter;
    private ListView listView;

    private EditText edtname,edtdate,edtprice,edtquantity;
    private String name,namencc,date,price,quantity;
    private Spinner edtnamencc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nhap_kho);
        dbHelper = new DBHelper(this);
        anhxa();
        loadTenNhaCungCapSpinner();
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNhapKhoActivity.this, NhapKhoActivity.class));
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savaData();
                edtname.setText("");
                edtquantity.setText("");
                edtprice.setText("");
                edtdate.setText("");
            }
        });
    }


    private void loadTenNhaCungCapSpinner() {
        DBHelper dbHelper = new DBHelper(this);
        List<NhaCungCap> listNCC = dbHelper.INFONCC();

        List<String> tenNhaCungCapList = new ArrayList<>();
        for (NhaCungCap nhaCungCap : listNCC) {
            tenNhaCungCapList.add(nhaCungCap.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tenNhaCungCapList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtnamencc.setAdapter(adapter);
    }
    private void savaData(){
        name = edtname.getText().toString();
        namencc = edtnamencc.getSelectedItem().toString(); // Thay đổi dòng này
        date = edtdate.getText().toString();
        quantity = edtquantity.getText().toString();
        price = edtprice.getText().toString();
        if (!name.isEmpty() && !namencc.isEmpty() && !price.isEmpty() && !quantity.isEmpty() && !date.isEmpty()) {

            String Name = edtname.getText().toString();
            String Date = edtdate.getText().toString();
            double Price = Double.parseDouble(edtprice.getText().toString());
            int Quantity = Integer.parseInt(edtquantity.getText().toString());

            NhapKho nk =new NhapKho();
            nk.setName(Name);
            nk.setNameNCC(namencc); // Sử dụng biến 'namencc' đã lấy từ Spinner
            nk.setDate(Date);
            nk.setPrice(Price);
            nk.setQuantity(Quantity);

            dbHelper.insertNK(nk);
            Toast.makeText(getApplicationContext(),"Thêm thành công", Toast.LENGTH_SHORT).show();
            updateListNK();

        } else {
            Toast.makeText(getApplicationContext(),"Thêm thất bại, vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateListNK() {
        if (list != null && dbHelper != null) {
            list.clear();
            list.addAll(dbHelper.INFONK());

            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }
    private void anhxa() {
        btnadd = findViewById(R.id.BTNADD);
        btnexit = findViewById(R.id.BTNEXIT);
        edtname = findViewById(R.id.tenSP);
        edtnamencc = findViewById(R.id.tenNCC);
        edtdate = findViewById(R.id.NgayNhap);
        edtquantity = findViewById(R.id.SoLuongNK);
        edtprice = findViewById(R.id.GiaNK);
    }
}