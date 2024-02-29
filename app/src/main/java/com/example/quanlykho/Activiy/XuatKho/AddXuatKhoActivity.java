package com.example.quanlykho.Activiy.XuatKho;

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

import com.example.quanlykho.Activiy.SanPham.AddSanPhamActivity;
import com.example.quanlykho.Activiy.SanPhamActivity;
import com.example.quanlykho.Activiy.XuatKhoActivity;
import com.example.quanlykho.Adapter.XuatKhoAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.SanPham;
import com.example.quanlykho.Table.XuatKho;

import java.util.ArrayList;
import java.util.List;

public class AddXuatKhoActivity extends AppCompatActivity {

    private Button btnadd,btnexit;
    private DBHelper dbHelper;
    private  List<XuatKho> list;

    private XuatKhoAdapter adapter;
    private ListView listView;

    private EditText edtdate,edtprice,edtquantity;
    private String name,date,price,quantity;
    private Spinner edtname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_xuat_kho);
        dbHelper = new DBHelper(this);
        anhxa();
        loadTenSanPhamSpinner();
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddXuatKhoActivity.this, XuatKhoActivity.class));
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savaData();
            }
        });

    }

    private void anhxa() {
        btnadd = findViewById(R.id.BTNADD);
        btnexit = findViewById(R.id.BTNEXIT);
        edtname = findViewById(R.id.tenSP);
        edtdate = findViewById(R.id.NgayXuat);
        edtquantity = findViewById(R.id.SoLuongXK);
        edtprice = findViewById(R.id.GiaXK);
    }
    private void savaData(){
        name = String.valueOf(edtname.getSelectedItem());
        date = edtdate.getText().toString();
        quantity = edtquantity.getText().toString();
        price = edtprice.getText().toString();
        if (!name.isEmpty() && !price.isEmpty() && !quantity.isEmpty() && !date.isEmpty()) {
            String Name = String.valueOf(edtname.getSelectedItem());
            String Date = edtdate.getText().toString();
            double Price = Double.parseDouble(edtprice.getText().toString());
            int  Quantity = Integer.parseInt(edtquantity.getText().toString());

            // Kiểm tra xem số lượng xuất kho có lớn hơn số lượng có sẵn trong kho không
            int availableQuantity = dbHelper.getAvailableQuantityForProduct(Name);
            if (Quantity > availableQuantity) {
                Toast.makeText(getApplicationContext(), "Số lượng xuất kho không thể lớn hơn số lượng hiện có", Toast.LENGTH_SHORT).show();
                return; // Không tiếp tục lưu dữ liệu
            }

            XuatKho xk =new XuatKho();
            xk.setName(Name);
            xk.setDate(Date);
            xk.setPrice(Price);
            xk.setQuantity(Quantity);


            dbHelper.insertXK(xk);
            Toast.makeText(getApplicationContext(),"Thêm thành công", Toast.LENGTH_SHORT).show();
            updateListXK();
            edtdate.setText("");
            edtquantity.setText("");
            edtprice.setText("");

        }else {
            Toast.makeText(getApplicationContext(),"Thêm thất bại, Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
        }

    }
    private void updateListXK() {
        if (list != null && dbHelper != null) {
            list.clear();
            list.addAll(dbHelper.INFOXK());

            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void loadTenSanPhamSpinner() {
        DBHelper dbHelper = new DBHelper(this);
        List<SanPham> listSP = dbHelper.INFOSP();

        List<String> tenSanPhamList = new ArrayList<>();
        for (SanPham sanPham : listSP) {
            tenSanPhamList.add(sanPham.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tenSanPhamList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtname.setAdapter(adapter);
    }

}