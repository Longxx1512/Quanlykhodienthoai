package com.example.quanlykho.Activiy.NhaCungCap;

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

import com.example.quanlykho.Activiy.HomeActivity;
import com.example.quanlykho.Activiy.LoginActivity;
import com.example.quanlykho.Activiy.NhaCungCapActivity;
import com.example.quanlykho.Adapter.NCCAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.NhaCungCap;

import java.util.List;

public class UpdateNhaCCActivity extends AppCompatActivity {

    private Button btnupdate,btndelete,btnexit;
    private DBHelper dbHelper;
    private List<NhaCungCap> list;
    private NCCAdapter adapter;
    private ListView listView;
    private EditText  edtma,edtten,edtsdt,edtemail,edtdiachi;
    private String name,phone,Email,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nha_ccactivity);
        dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("idnhacungcap");
        String ten = intent.getStringExtra("tennhacungcap");
        String sdt = intent.getStringExtra("sdtnhacungcap");
        String email = intent.getStringExtra("emailnhacungcap");
        String diachi = intent.getStringExtra("diachinhacungcap");
        anhxa();
        // Kiểm tra có khác null không
        if (id != null && ten != null && sdt != null && email != null && diachi != null) {
            edtma.setText(id);
            edtten.setText(ten);
            edtsdt.setText(sdt);
            edtemail.setText(email);
            edtdiachi.setText(diachi);
            edtma.setEnabled(false);
            edtten.setEnabled(false);
        }
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateNhaCCActivity.this, NhaCungCapActivity.class));
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setId(edtma.getText().toString());
                ncc.setName(edtten.getText().toString());
                ncc.setPhone(edtsdt.getText().toString());
                ncc.setEmail(edtemail.getText().toString());
                ncc.setAddress(edtdiachi.getText().toString());


                if (ncc.getId().isEmpty() || ncc.getName().isEmpty() || ncc.getPhone().isEmpty() || ncc.getEmail().isEmpty() || ncc.getAddress().isEmpty()) {
                    Toast.makeText(UpdateNhaCCActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    int result = dbHelper.UpdateNCC(ncc);
                    if (result > 0) {
                        Toast.makeText(UpdateNhaCCActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        updateListNCC();
                    } else {
                        Toast.makeText(UpdateNhaCCActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
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
        btnupdate = findViewById(R.id.BTNUPDATE);
        btndelete = findViewById(R.id.BTNDELETE);
        btnexit = findViewById(R.id.BTNEXIT);
        edtma = findViewById(R.id.idNCC);
        edtten = findViewById(R.id.tenNCC);
        edtsdt = findViewById(R.id.SDT);
        edtemail = findViewById(R.id.Email);
        edtdiachi = findViewById(R.id.DiaChi);
    }
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateNhaCCActivity.this);
        builder.setMessage("Bạn có chắc chắn muốn xóa?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NhaCungCap ncc = new NhaCungCap();
                        ncc.setId(edtma.getText().toString());
                        ncc.setName(edtten.getText().toString());
                        ncc.setPhone(edtsdt.getText().toString());
                        ncc.setEmail(edtemail.getText().toString());
                        ncc.setAddress(edtdiachi.getText().toString());

                        int result = dbHelper.DeleteNCC(Integer.parseInt(ncc.getId()));
                        if (result >0) {
                            Toast.makeText(UpdateNhaCCActivity.this,"Xóa thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateNhaCCActivity.this,NhaCungCapActivity.class));
                            updateListNCC();
                        }else {
                            Toast.makeText(UpdateNhaCCActivity.this,"Xóa thất bại", Toast.LENGTH_SHORT).show();
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