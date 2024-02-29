package com.example.quanlykho.Activiy.XuatKho;

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

import com.example.quanlykho.Activiy.NhanVien.UpdateNVActivity;
import com.example.quanlykho.Activiy.SanPham.UpdateSanPhamActivity;
import com.example.quanlykho.Activiy.SanPhamActivity;
import com.example.quanlykho.Activiy.XuatKhoActivity;
import com.example.quanlykho.Adapter.XuatKhoAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.SanPham;
import com.example.quanlykho.Table.XuatKho;

import java.util.ArrayList;
import java.util.List;

public class UpdateXuatKhoActivity extends AppCompatActivity {

    private Button btnupdate, btndelete, btnexit;
    private DBHelper dbHelper;
    private List<XuatKho> list;

    private XuatKhoAdapter adapter;

    private EditText edtid, edtdate, edtprice, edtquantity;
    private List<String> tenSanPhamList = new ArrayList<>();

    private Spinner edtname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_xuat_kho);

        dbHelper = new DBHelper(this);
        anhxa();
        loadTenSanPhamSpinner();
        Intent intent = getIntent();
        String id = intent.getStringExtra("idxuatkho");
        String ten = intent.getStringExtra("tensanpham");
        String ngay = intent.getStringExtra("ngayxuatsanpham");
        int sl = intent.getIntExtra("soluongxuatkho", 0);
        double gia = intent.getDoubleExtra("giaxuat", 0.0);

        if (edtid != null) {
            edtid.setText(id);
        }

        if (ten != null) {
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) edtname.getAdapter();
            int position = adapter.getPosition(ten);
            edtname.setSelection(position);
        }

        edtdate.setText(ngay);
        edtquantity.setText(String.valueOf(sl));
        edtprice.setText(String.valueOf(gia));

        edtid.setEnabled(false);
        edtname.setEnabled(false);
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateXuatKhoActivity.this, XuatKhoActivity.class));
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuatKho xk = new XuatKho();
                xk.setId(edtid.getText().toString());
                xk.setName(String.valueOf(edtname.getSelectedItem()));
                xk.setDate(edtdate.getText().toString());
                xk.setPrice(Double.parseDouble(edtprice.getText().toString()));
                xk.setQuantity(Integer.parseInt(edtquantity.getText().toString()));

                if(xk.getDate().isEmpty() || xk.getPrice() == 0.0 || xk.getQuantity() ==0){
                    Toast.makeText(UpdateXuatKhoActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                int result = dbHelper.UpdateXK(xk);
                if (result > 0) {
                    Toast.makeText(UpdateXuatKhoActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    updateListXK();
                } else {
                    Toast.makeText(UpdateXuatKhoActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
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

        tenSanPhamList.clear(); // Xóa dữ liệu cũ
        for (SanPham sanPham : listSP) {
            tenSanPhamList.add(sanPham.getName());
        }

        if (edtname != null) {
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tenSanPhamList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            edtname.setAdapter(spinnerAdapter);
        }
    }

    private void anhxa() {
        btnupdate = findViewById(R.id.BTNUPDATE);
        btndelete = findViewById(R.id.BTNDELETE);
        btnexit = findViewById(R.id.BTNEXIT);
        edtid = findViewById(R.id.idXK);
        edtname = findViewById(R.id.tenSPXK);
        edtdate = findViewById(R.id.ngayXK);
        edtquantity = findViewById(R.id.SLXK);
        edtprice = findViewById(R.id.GiaSPXK);
    }
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateXuatKhoActivity.this);
        builder.setMessage("Bạn có chắc chắn muốn xóa?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        XuatKho xk = new XuatKho();
                        xk.setId(edtid.getText().toString());
                        xk.setName(String.valueOf(edtname.getSelectedItem()));
                        xk.setDate(edtdate.getText().toString());
                        xk.setPrice(Double.parseDouble(edtprice.getText().toString()));
                        xk.setQuantity(Integer.parseInt(edtquantity.getText().toString()));

                        int result = dbHelper.DeleteXK(Integer.parseInt(xk.getId()));
                        if (result > 0) {
                            Toast.makeText(UpdateXuatKhoActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateXuatKhoActivity.this, XuatKhoActivity.class));
                            updateListXK();
                        } else {
                            Toast.makeText(UpdateXuatKhoActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
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
