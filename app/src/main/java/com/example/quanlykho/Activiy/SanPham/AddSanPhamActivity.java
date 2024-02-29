package com.example.quanlykho.Activiy.SanPham;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlykho.Activiy.SanPhamActivity;
import com.example.quanlykho.Adapter.SPAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.NhapKho;
import com.example.quanlykho.Table.SanPham;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddSanPhamActivity extends AppCompatActivity {

    private Button btnadd, btnexit;
    private DBHelper dbHelper;
    private List<SanPham> list;
    private SPAdapter adapter;
    private ListView listView;
    private EditText edtmota, edtgia, edtsl, edtnhasx, edtnamsx;
    private String name, info, price, quantity, manufacturers, date;
    private Spinner edtten;
    // Bổ sung vào phần khai báo biến
    final int RESQUEST_TAKE_PHOTO_SP = 456;
    final int REQUEST_CHOOSE_PHOTO_SP = 654;
    ImageView imgHinhDaiDienSP;
    Button btnChonHinhSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_san_pham);
        dbHelper = new DBHelper(this);
        anhxa();
        edtsl.setEnabled(false);
        loadTenSanPhamSpinner();
        setTenSanPhamItemSelectedListener(); // Gọi phương thức mới để đặt người nghe chọn mục
        updateRemainingQuantity();  // Thêm dòng này
        imgHinhDaiDienSP.setImageResource(R.drawable.dt); // Hình mặc định (nếu có)
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddSanPhamActivity.this, SanPhamActivity.class));
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savaData();
                updateRemainingQuantity();  // Thêm dòng này
            }
        });
        btnChonHinhSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseOrTakePhotoForSanPham();
            }
        });

    }

    // Thêm phương thức này để đặt người nghe chọn mục cho Spinner
    private void setTenSanPhamItemSelectedListener() {
        edtten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Cập nhật số lượng còn lại khi chọn một mục khác
                updateRemainingQuantity();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Không thực hiện hành động gì ở đây
            }
        });
    }

    // Thêm phương thức mới
    private void chooseOrTakePhotoForSanPham() {
        // Hiển thị dialog hoặc thực hiện hành động tương tự để chọn hoặc chụp ảnh
        // Có thể sử dụng cùng một phương thức choosePhoto() hoặc takePicture()

        // Code chọn ảnh từ thư viện
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CHOOSE_PHOTO_SP);
    }

    // Thêm phương thức mới
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CHOOSE_PHOTO_SP) {
                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    // Scale và hiển thị bitmap
                    imgHinhDaiDienSP.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }else if (requestCode == RESQUEST_TAKE_PHOTO_SP) {
                // Xử lý khi chụp ảnh từ camera
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                // Scale và hiển thị bitmap
                imgHinhDaiDienSP.setImageBitmap(bitmap);
            }

        }
    }

    private void savaData() {
        name = String.valueOf(edtten.getSelectedItem());
        info = edtmota.getText().toString();
        price = edtgia.getText().toString();
        quantity = edtsl.getText().toString();
        manufacturers = edtnhasx.getText().toString();
        date = edtnamsx.getText().toString();

        if (!name.isEmpty() && !info.isEmpty() && !price.isEmpty() && !quantity.isEmpty() && !manufacturers.isEmpty() && !date.isEmpty()) {
            String Name = String.valueOf(edtten.getSelectedItem());
            String Info = edtmota.getText().toString();
            double Price = Double.parseDouble(edtgia.getText().toString());
            int Quantity = Integer.parseInt(edtsl.getText().toString());
            String Manufacturers = edtnhasx.getText().toString();
            int Date = Integer.parseInt(edtnamsx.getText().toString());

            // Convert Bitmap to byte array
            byte[] imageData = convertBitmapToByteArray((BitmapDrawable) imgHinhDaiDienSP.getDrawable());

            SanPham sp = new SanPham();
            sp.setName(Name);
            sp.setInfo(Info);
            sp.setPrice(Price);
            sp.setQuantity(Quantity);
            sp.setManufacturers(Manufacturers);
            sp.setDate(Date);
            sp.setImage(imageData); // Đặt dữ liệu hình ảnh trong đối tượng SanPham

            dbHelper.insertSP(sp); // Chèn đối tượng SanPham vào cơ sở dữ liệu
            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            updateListSP();
            edtmota.setText("");
            edtsl.setText("");
            edtnamsx.setText("");
            edtnhasx.setText("");
            edtgia.setText("");
            imgHinhDaiDienSP.setImageResource(R.drawable.dt); // Đặt hình mặc định (nếu có)
        } else {
            Toast.makeText(getApplicationContext(), "Thêm thất bại, Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    private byte[] convertBitmapToByteArray(BitmapDrawable bitmapDrawable) {
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }


    private void loadTenSanPhamSpinner() {
        DBHelper dbHelper = new DBHelper(this);
        List<NhapKho> listNK = dbHelper.INFONK();

        List<String> tenSanPhamList = new ArrayList<>();
        for (NhapKho nhapKho : listNK) {
            tenSanPhamList.add(nhapKho.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tenSanPhamList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtten.setAdapter(adapter);
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

    private void anhxa() {
        btnadd = findViewById(R.id.BTNADD);
        btnexit = findViewById(R.id.BTNEXIT);
        edtten = findViewById(R.id.TenSP);
        edtmota = findViewById(R.id.MoTa);
        edtgia = findViewById(R.id.Gia);
        edtsl = findViewById(R.id.SoLuongTK);
        edtnhasx = findViewById(R.id.NhaSX);
        edtnamsx = findViewById(R.id.NamSX);
        imgHinhDaiDienSP = findViewById(R.id.imgHinhDaiDien);
        btnChonHinhSP = findViewById(R.id.btnChonHinh);
    }

    private void loadRemainingQuantity(String tenSanPham) {
        int remainingQuantity = dbHelper.getRemainingQuantity(tenSanPham);
        edtsl.setText(String.valueOf(remainingQuantity));
    }

    private void updateRemainingQuantity() {
        String selectedProductName = String.valueOf(edtten.getSelectedItem());
        loadRemainingQuantity(selectedProductName);
    }
}
