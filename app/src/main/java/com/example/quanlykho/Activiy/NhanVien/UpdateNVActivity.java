package com.example.quanlykho.Activiy.NhanVien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlykho.Activiy.NhaCungCap.UpdateNhaCCActivity;
import com.example.quanlykho.Activiy.NhaCungCapActivity;
import com.example.quanlykho.Activiy.NhanVienActivity;
import com.example.quanlykho.Adapter.NCCAdapter;
import com.example.quanlykho.Adapter.NhanVienAdapter;
import com.example.quanlykho.Adapter.NhapKhoAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.NhaCungCap;
import com.example.quanlykho.Table.NhanVien;

import java.util.List;

public class UpdateNVActivity extends AppCompatActivity {

    private Button btnupdate,btndelete,btnexit;
    private DBHelper dbHelper;
    private List<NhanVien> list;
    private NhanVienAdapter adapter;
    private ListView listView;
    private EditText edtma,edtten,edtsdt,edtchucvu,edtdiachi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nvactivity);
        dbHelper = new DBHelper(this);
        // Trong UpdateNVActivity
        Intent intent = getIntent();
        String id = intent.getStringExtra("idnhanvien");
        String ten = intent.getStringExtra("tennhanvien");
        String sdt = intent.getStringExtra("sodienthoainhanvien");
        String chucvu = intent.getStringExtra("chucvu");
        String diachi = intent.getStringExtra("diachinhanvien");


        anhxa();

        // Kiểm tra có khác null không
        if (id != null && ten != null && sdt != null && chucvu != null && diachi != null) {
            edtma.setText(id);
            edtten.setText(ten);
            edtsdt.setText(sdt);
            edtchucvu.setText(chucvu);
            edtdiachi.setText(diachi);
            edtma.setEnabled(false);
        }
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateNVActivity.this, NhanVienActivity.class));
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nv = new NhanVien();
                nv.setId(edtma.getText().toString());
                nv.setName(edtten.getText().toString());
                nv.setPhone(edtsdt.getText().toString());
                nv.setDuty(edtchucvu.getText().toString());
                nv.setAddress(edtdiachi.getText().toString());

                // Kiểm tra xem có bất kỳ trường nào trống không
                if (nv.getId().isEmpty() || nv.getName().isEmpty() || nv.getPhone().isEmpty() || nv.getDuty().isEmpty() || nv.getAddress().isEmpty()) {
                    Toast.makeText(UpdateNVActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    int result = dbHelper.UpdateNV(nv);
                    if (result > 0) {
                        Toast.makeText(UpdateNVActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        updateListNV();
                    } else {
                        Toast.makeText(UpdateNVActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
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
        btnupdate = findViewById(R.id.BTNUPDATE);
        btndelete = findViewById(R.id.BTNDELETE);
        btnexit = findViewById(R.id.BTNEXIT);
        edtma = findViewById(R.id.idNVien);
        edtten = findViewById(R.id.HotenNV);
        edtsdt = findViewById(R.id.SDTNV);
        edtchucvu = findViewById(R.id.ChucVuNV);
        edtdiachi = findViewById(R.id.DiaChiNV);
    }
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateNVActivity.this);
        builder.setMessage("Bạn có chắc chắn muốn xóa?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NhanVien nv = new NhanVien();
                        nv.setId(edtma.getText().toString());
                        nv.setName(edtten.getText().toString());
                        nv.setPhone(edtsdt.getText().toString());
                        nv.setDuty(edtchucvu.getText().toString());
                        nv.setAddress(edtdiachi.getText().toString());

                        int result = dbHelper.DeleteNV(Integer.parseInt(nv.getId()));
                        if (result >0) {
                            Toast.makeText(UpdateNVActivity.this,"Xóa thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateNVActivity.this, NhanVienActivity.class));
                            updateListNV();
                        }else {
                            Toast.makeText(UpdateNVActivity.this,"Xóa thất bại", Toast.LENGTH_SHORT).show();
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