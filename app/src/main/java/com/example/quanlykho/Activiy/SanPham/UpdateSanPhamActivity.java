package com.example.quanlykho.Activiy.SanPham;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlykho.Activiy.NhapKho.UpdateNhapKhoActivity;
import com.example.quanlykho.Activiy.NhapKhoActivity;
import com.example.quanlykho.Activiy.SanPhamActivity;
import com.example.quanlykho.Adapter.SPAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.NhaCungCap;
import com.example.quanlykho.Table.NhapKho;
import com.example.quanlykho.Table.SanPham;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UpdateSanPhamActivity extends AppCompatActivity {

    private Button btnupdate, btndelete, btnexit;
    private DBHelper dbHelper;
    private List<SanPham> list;
    private SPAdapter adapter;
    private ListView listView;
    private EditText edtma, edtmota, edtgia, edtsl, edtnhasx, edtnamsx;

    private Spinner edtten;
    private List<String> tenSanPhamList = new ArrayList<>();

    final int REQUEST_CHOOSE_PHOTO_SP = 654;
    ImageView imgHinhDaiDienSP;
    Button btnChonHinhSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_san_pham);
        dbHelper = new DBHelper(this);
        anhxa();
        Intent intent = getIntent();
        String id = intent.getStringExtra("idsanpham");
        String ten = intent.getStringExtra("tensanpham");
        String mota = intent.getStringExtra("motasanpham");
        double gia = intent.getDoubleExtra("giasanpham", 0.0);
        int sl = intent.getIntExtra("slsanpham", 0);
        String nhasx = intent.getStringExtra("nhasxsanpham");
        int namsx = intent.getIntExtra("namsxsanpham", 0);
        byte[] imageBytes = getIntent().getByteArrayExtra("imagesanpham");
        imgHinhDaiDienSP.setImageResource(R.drawable.dt); // Đặt hình mặc định (nếu có)
        if (imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imgHinhDaiDienSP.setImageBitmap(bitmap);
        }
        edtma.setText(id);
        if (ten != null) {
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) edtten.getAdapter();
            int position = adapter.getPosition(ten);
            edtten.setSelection(position);
        }
        edtmota.setText(mota);
        edtgia.setText(String.valueOf(gia));
        edtsl.setText(String.valueOf(sl));
        edtnhasx.setText(nhasx);
        edtnamsx.setText(String.valueOf(namsx));
        edtma.setEnabled(false);
        edtsl.setEnabled(false);
        edtten.setEnabled(false);
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateSanPhamActivity.this, SanPhamActivity.class));
            }
        });
        btnChonHinhSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseOrTakePhotoForSanPham();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sp = new SanPham();
                sp.setId(edtma.getText().toString());
                sp.setName(String.valueOf(edtten.getSelectedItem()));
                sp.setInfo(edtmota.getText().toString());
                sp.setPrice(Double.parseDouble(edtgia.getText().toString()));
                sp.setQuantity(Integer.parseInt(edtsl.getText().toString()));
                sp.setManufacturers(edtnhasx.getText().toString());
                sp.setDate(Integer.parseInt(edtnamsx.getText().toString()));
                sp.setImage(convertImageViewToByteArray(imgHinhDaiDienSP));

                if(sp.getInfo().isEmpty() || sp.getQuantity() == 0 || sp.getPrice() == 0.0 || sp.getManufacturers().isEmpty() || sp.getDate() == 0){
                    Toast.makeText(UpdateSanPhamActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    int result = dbHelper.UpdateSP(sp);
                    if (result > 0) {
                        Toast.makeText(UpdateSanPhamActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        updateListSP();
                    } else {
                        Toast.makeText(UpdateSanPhamActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
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

    private void chooseOrTakePhotoForSanPham() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CHOOSE_PHOTO_SP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CHOOSE_PHOTO_SP) {
                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgHinhDaiDienSP.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private byte[] convertImageViewToByteArray(ImageView imageView) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private void anhxa() {
        btnupdate = findViewById(R.id.BTNUPDATE);
        btndelete = findViewById(R.id.BTNDELETE);
        btnexit = findViewById(R.id.BTNEXIT);
        edtma = findViewById(R.id.idSP);
        edtten = findViewById(R.id.TenSP);
        edtmota = findViewById(R.id.MoTaSP);
        edtgia = findViewById(R.id.GiaSP);
        edtsl = findViewById(R.id.SLSP);
        edtnhasx = findViewById(R.id.NhaSXSP);
        edtnamsx = findViewById(R.id.NamSXSP);
        imgHinhDaiDienSP = findViewById(R.id.imgHinhDaiDien);
        btnChonHinhSP = findViewById(R.id.btnChonHinh);

        loadTenSanPhamSpinner();
    }

    private void updateListSP() {
        if (list != null && dbHelper != null) {
            list.clear();
            list.addAll(dbHelper.INFOSP());

            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void loadTenSanPhamSpinner() {
        DBHelper dbHelper = new DBHelper(this);
        List<NhapKho> listNK = dbHelper.INFONK();

        tenSanPhamList.clear();
        for (NhapKho nhapKho : listNK) {
            tenSanPhamList.add(nhapKho.getName());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tenSanPhamList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtten.setAdapter(spinnerAdapter);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateSanPhamActivity.this);
        builder.setMessage("Bạn có chắc chắn muốn xóa?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SanPham sp = new SanPham();
                        sp.setId(edtma.getText().toString());
                        sp.setName(String.valueOf(edtten.getSelectedItem()));
                        sp.setInfo(edtmota.getText().toString());
                        sp.setPrice(Double.parseDouble(edtgia.getText().toString()));
                        sp.setQuantity(Integer.parseInt(edtsl.getText().toString()));
                        sp.setManufacturers(edtnhasx.getText().toString());
                        sp.setDate(Integer.parseInt(edtnamsx.getText().toString()));

                        int result = dbHelper.deleteSP(Integer.parseInt(sp.getId()));
                        if (result > 0) {
                            Toast.makeText(UpdateSanPhamActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateSanPhamActivity.this, SanPhamActivity.class));
                            updateListSP();
                        } else {
                            Toast.makeText(UpdateSanPhamActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
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
