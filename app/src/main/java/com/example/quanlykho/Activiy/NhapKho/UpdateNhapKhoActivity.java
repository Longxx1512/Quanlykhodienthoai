package com.example.quanlykho.Activiy.NhapKho;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlykho.Activiy.NhaCungCap.UpdateNhaCCActivity;
import com.example.quanlykho.Activiy.NhanVien.UpdateNVActivity;
import com.example.quanlykho.Activiy.NhanVienActivity;
import com.example.quanlykho.Activiy.NhapKhoActivity;
import com.example.quanlykho.Adapter.NhapKhoAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.NhaCungCap;
import com.example.quanlykho.Table.NhanVien;
import com.example.quanlykho.Table.NhapKho;
import com.example.quanlykho.Table.SanPham;


import java.util.ArrayList;
import java.util.List;

public class UpdateNhapKhoActivity extends AppCompatActivity {

    private Button btnupdate, btndelete, btnexit;
    private DBHelper dbHelper;
    private List<NhapKho> list;

    private NhapKhoAdapter adapter;

    private EditText edtid, edtname, edtdate, edtprice, edtquantity;
    private List<String> tenNhaCungCapList = new ArrayList<>();

    private Spinner edtnamencc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nhap_kho);
        dbHelper = new DBHelper(this);
        anhxa();
        loadTenNhaCungCapSpinner();
        Intent intent = getIntent();
        String id = intent.getStringExtra("idnhapkho");
        String ten = intent.getStringExtra("tensanpham");
        String tenncc = intent.getStringExtra("tennhacungcapsanpham");
        String ngay = intent.getStringExtra("ngaynhapsanpham");
        int sl = intent.getIntExtra("soluongnhapkho", 0);
        double gia = intent.getDoubleExtra("gianhap", 0.0);

        if (edtid != null) {
            edtid.setText(id);
        }

        if (tenncc != null) {
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) edtnamencc.getAdapter();
            int position = adapter.getPosition(tenncc);
            edtnamencc.setSelection(position);
        }

        edtname.setText(ten);
        edtdate.setText(ngay);
        edtquantity.setText(String.valueOf(sl));
        edtprice.setText(String.valueOf(gia));
        edtid.setEnabled(false);
        edtname.setEnabled(false);
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateNhapKhoActivity.this, NhapKhoActivity.class));
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhapKho nk = new NhapKho();
                nk.setId(edtid.getText().toString());
                nk.setName(edtname.getText().toString());
                nk.setNameNCC(String.valueOf(edtnamencc.getSelectedItem()));
                nk.setDate(edtdate.getText().toString());
                nk.setPrice(Double.parseDouble(edtprice.getText().toString()));
                nk.setQuantity(Integer.parseInt(edtquantity.getText().toString()));


                if (nk.getId().isEmpty() || nk.getName().isEmpty() || nk.getDate().isEmpty() || nk.getPrice() <= 0 || nk.getQuantity() <= 0 ){
                    Toast.makeText(UpdateNhapKhoActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    int result = dbHelper.UpdateNK(nk);
                    if (result > 0) {
                        Toast.makeText(UpdateNhapKhoActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        updateListNK();
                    } else {
                        Toast.makeText(UpdateNhapKhoActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDialog();
            }
        });

    }

    private void anhxa() {
        btnupdate = findViewById(R.id.BTNUPDATE);
        btndelete = findViewById(R.id.BTNDELETE);
        btnexit = findViewById(R.id.BTNEXIT);
        edtid = findViewById(R.id.idNK);
        edtname = findViewById(R.id.tenSPNK);
        edtnamencc = findViewById(R.id.nccSPNK);
        edtdate = findViewById(R.id.ngaySPNK);
        edtquantity = findViewById(R.id.SLSPNK);
        edtprice = findViewById(R.id.GiaSPNK);
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

    private void loadTenNhaCungCapSpinner() {
        DBHelper dbHelper = new DBHelper(this);
        List<NhaCungCap> listNCC = dbHelper.INFONCC();

        tenNhaCungCapList.clear(); // Xóa dữ liệu cũ
        for (NhaCungCap nhaCungCap : listNCC) {
            tenNhaCungCapList.add(nhaCungCap.getName());
        }

        if (edtnamencc != null) {
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tenNhaCungCapList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            edtnamencc.setAdapter(spinnerAdapter);
        }
    }
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateNhapKhoActivity.this);
        builder.setMessage("Bạn có chắc chắn muốn xóa?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NhapKho nk = new NhapKho();
                        nk.setId(edtid.getText().toString());
                        nk.setName(edtname.getText().toString());
                        nk.setNameNCC(String.valueOf(edtnamencc.getSelectedItem()));
                        nk.setDate(edtdate.getText().toString());
                        nk.setPrice(Double.parseDouble(edtprice.getText().toString()));
                        nk.setQuantity(Integer.parseInt(edtquantity.getText().toString()));


                        int result = dbHelper.DeleteNK(Integer.parseInt(nk.getId()));
                        if (result > 0) {
                            Toast.makeText(UpdateNhapKhoActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateNhapKhoActivity.this, NhapKhoActivity.class));
                            updateListNK();
                        } else {
                            Toast.makeText(UpdateNhapKhoActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
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