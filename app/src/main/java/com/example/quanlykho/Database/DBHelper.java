package com.example.quanlykho.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.quanlykho.Table.NhaCungCap;
import com.example.quanlykho.Table.NhanVien;
import com.example.quanlykho.Table.NhapKho;
import com.example.quanlykho.Table.SanPham;
import com.example.quanlykho.Table.XuatKho;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {
    //database or db name
    public static final String DATABASE_NAME = "quanlykho.db";




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 7);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "create table users(username text, email text, password text)";
        db.execSQL(qry1);
        //Create table on database
        db.execSQL(NhaCungCap.CREATE_TABLE);
        db.execSQL(SanPham.CREATE_SANPHAM);
        db.execSQL(XuatKho.CREATE_XUATKHO);
        db.execSQL(NhapKho.CREATE_NHAPKHO);
        db.execSQL(NhanVien.CREATE_NHANVIEN);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //upgrade table if any structure change in db
        // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS "+NhaCungCap.TABLE_NHACC);
        db.execSQL("DROP TABLE IF EXISTS "+SanPham.TABLE_SANPHAM);
        db.execSQL("DROP TABLE IF EXISTS "+XuatKho.TABLE_XUATKHO);
        db.execSQL("DROP TABLE IF EXISTS "+NhapKho.TABLE_NHAPKHO);
        db.execSQL("DROP TABLE IF EXISTS "+NhanVien.TABLE_NHANVIEN);
        //create table again/-
        onCreate(db);
    }
    //Insert Function to insert data in database
    public void insertNCC(NhaCungCap nhaCungCap){
        //ger wirtable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();
        //create NCC class object to save data
        ContentValues values = new ContentValues();

        //id will save automatically as we write query
        values.put(NhaCungCap.NCC_TEN,nhaCungCap.getName());
        values.put(NhaCungCap.NCC_SDT,nhaCungCap.getPhone());
        values.put(NhaCungCap.NCC_EMAIL,nhaCungCap.getEmail());
        values.put(NhaCungCap.NCC_DIACHI,nhaCungCap.getAddress());
        //insert data in row
        db.insert(NhaCungCap.TABLE_NHACC,null,values);
        //close db
        db.close();
        }
    public void insertSP(SanPham sanPham) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SanPham.SP_TEN, sanPham.getName());
        values.put(SanPham.SP_MOTA, sanPham.getInfo());
        values.put(SanPham.SP_GIA, sanPham.getPrice());
        values.put(SanPham.SP_SOLUONG, sanPham.getQuantity());
        values.put(SanPham.SP_NHASX, sanPham.getManufacturers());
        values.put(SanPham.SP_NAMSX, sanPham.getDate());
        values.put(SanPham.SP_ANH, sanPham.getImage());


        db.insert(SanPham.TABLE_SANPHAM, null, values);
        db.close();
    }
    public void insertXK(XuatKho xuatKho){
        //ger wirtable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();
        //create NCC class object to save data
        ContentValues values = new ContentValues();

        //id will save automatically as we write query
        values.put(XuatKho.XK_TENSP,xuatKho.getName());
        values.put(XuatKho.XK_NGAYXUAT,xuatKho.getDate());
        values.put(XuatKho.XK_SOLUONG,xuatKho.getQuantity());
        values.put(XuatKho.XK_GIA,xuatKho.getPrice());


        //insert data in row
        db.insert(XuatKho.TABLE_XUATKHO,null,values);
        //close db
        db.close();
    }
    public void insertNK(NhapKho  nhapKho){
        //ger wirtable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();
        //create NCC class object to save data
        ContentValues values = new ContentValues();

        //id will save automatically as we write query
        values.put(NhapKho.NK_TENSP,nhapKho.getName());
        values.put(NhapKho.NK_TENNCC,nhapKho.getNameNCC());
        values.put(NhapKho.NK_NGAYNHAP,nhapKho.getDate());
        values.put(NhapKho.NK_SOLUONG,nhapKho.getQuantity());
        values.put(NhapKho.NK_GIA,nhapKho.getPrice());


        //insert data in row
        db.insert(NhapKho.TABLE_NHAPKHO,null,values);
        //close db
        db.close();
    }
    public void insertNV(NhanVien  nhanVien){
        //ger wirtable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();
        //create NCC class object to save data
        ContentValues values = new ContentValues();

        //id will save automatically as we write query
        values.put(NhanVien.NV_TEN,nhanVien.getName());
        values.put(NhanVien.NV_CHUCVU,nhanVien.getDuty());
        values.put(NhanVien.NV_SDT,nhanVien.getPhone());
        values.put(NhanVien.NV_DIACHI,nhanVien.getAddress());




        //insert data in row
        db.insert(NhanVien.TABLE_NHANVIEN,null,values);
        //close db
        db.close();
    }

    public int UpdateNCC(NhaCungCap nhaCungCap){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();

        values.put(NhaCungCap.NCC_TEN,nhaCungCap.getName());
        values.put(NhaCungCap.NCC_SDT,nhaCungCap.getPhone());
        values.put(NhaCungCap.NCC_EMAIL,nhaCungCap.getEmail());
        values.put(NhaCungCap.NCC_DIACHI,nhaCungCap.getAddress());


        int result = db.update(NhaCungCap.TABLE_NHACC,values,NhaCungCap.NCC_ID + " = ?", new String[]{String.valueOf(nhaCungCap.getId())});

        db.close();
        return result;
    }
    public int UpdateSP(SanPham sanPham) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SanPham.SP_TEN, sanPham.getName());
        values.put(SanPham.SP_MOTA, sanPham.getInfo());
        values.put(SanPham.SP_GIA, sanPham.getPrice());
        values.put(SanPham.SP_SOLUONG, sanPham.getQuantity());
        values.put(SanPham.SP_NHASX, sanPham.getManufacturers());
        values.put(SanPham.SP_NAMSX, sanPham.getDate());
        values.put(SanPham.SP_ANH, sanPham.getImage());  // Thêm cột ảnh

        int result = db.update(SanPham.TABLE_SANPHAM, values, SanPham.SP_ID + " = ?", new String[]{String.valueOf(sanPham.getId())});

        db.close();
        return result;
    }

    public int UpdateXK(XuatKho xuatKho){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(XuatKho.XK_TENSP,xuatKho.getName());
        values.put(XuatKho.XK_NGAYXUAT,xuatKho.getDate());
        values.put(XuatKho.XK_SOLUONG,xuatKho.getQuantity());
        values.put(XuatKho.XK_GIA,xuatKho.getPrice());



        int result = db.update(XuatKho.TABLE_XUATKHO,values,XuatKho.XK_ID + " = ?", new String[]{String.valueOf(xuatKho.getId())});

        db.close();
        return result;
    }
    public int UpdateNK(NhapKho nhapKho){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NhapKho.NK_TENSP,nhapKho.getName());
        values.put(NhapKho.NK_TENNCC,nhapKho.getNameNCC());
        values.put(NhapKho.NK_NGAYNHAP,nhapKho.getDate());
        values.put(NhapKho.NK_SOLUONG,nhapKho.getQuantity());
        values.put(NhapKho.NK_GIA,nhapKho.getPrice());



        int result = db.update(NhapKho.TABLE_NHAPKHO,values,NhapKho.NK_ID + " = ?", new String[]{String.valueOf(nhapKho.getId())});

        db.close();
        return result;
    }
    public int UpdateNV(NhanVien nhanVien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NhanVien.NV_TEN,nhanVien.getName());
        values.put(NhanVien.NV_CHUCVU,nhanVien.getDuty());
        values.put(NhanVien.NV_SDT,nhanVien.getPhone());
        values.put(NhanVien.NV_DIACHI,nhanVien.getAddress());



        int result = db.update(NhanVien.TABLE_NHANVIEN,values,NhanVien.NV_ID + " = ?", new String[]{String.valueOf(nhanVien.getId())});

        db.close();
        return result;
    }
    public int DeleteNCC(int maNCC){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(NhaCungCap.TABLE_NHACC, NhaCungCap.NCC_ID +" = ?", new String[]{String.valueOf(maNCC)});
        db.close();
        return result;
    }
    public int deleteSP(int maSP) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(SanPham.TABLE_SANPHAM, SanPham.SP_ID + " = ?", new String[]{String.valueOf(maSP)});
        db.close();
        return result;
    }

    public int DeleteXK(int maXK){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(XuatKho.TABLE_XUATKHO, XuatKho.XK_ID +" = ?", new String[]{String.valueOf(maXK)});
        db.close();
        return result;
    }

    public int DeleteNK(int maNK){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(NhapKho.TABLE_NHAPKHO, NhapKho.NK_ID +" = ?", new String[]{String.valueOf(maNK)});
        db.close();
        return result;
    }

    public int DeleteNV(int maNV){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(NhanVien.TABLE_NHANVIEN, NhanVien.NV_ID +" = ?", new String[]{String.valueOf(maNV)});
        db.close();
        return result;
    }
    public List<NhaCungCap> INFONCC(){
        List<NhaCungCap> listNCC = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " +NhaCungCap.TABLE_NHACC;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String idnhacungcap = cursor.getString(cursor.getColumnIndex(NhaCungCap.NCC_ID));
                @SuppressLint("Range") String tennhacungcap = cursor.getString(cursor.getColumnIndex(NhaCungCap.NCC_TEN));
                @SuppressLint("Range") String sdtnhacungcap = cursor.getString(cursor.getColumnIndex(NhaCungCap.NCC_SDT));
                @SuppressLint("Range") String emailnhacungcap = cursor.getString(cursor.getColumnIndex(NhaCungCap.NCC_EMAIL));
                @SuppressLint("Range") String diachinhacungcap = cursor.getString(cursor.getColumnIndex(NhaCungCap.NCC_DIACHI));

                NhaCungCap nhaCungCap = new NhaCungCap(idnhacungcap,tennhacungcap,sdtnhacungcap,emailnhacungcap,diachinhacungcap);
                listNCC.add(nhaCungCap);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listNCC;
    }
    public List<SanPham> INFOSP() {
        List<SanPham> listSP = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + SanPham.TABLE_SANPHAM;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String idsanpham = cursor.getString(cursor.getColumnIndex(SanPham.SP_ID));
                @SuppressLint("Range") String tensanpham = cursor.getString(cursor.getColumnIndex(SanPham.SP_TEN));
                @SuppressLint("Range") String motasanpham = cursor.getString(cursor.getColumnIndex(SanPham.SP_MOTA));
                @SuppressLint("Range") double giasanpham = cursor.getDouble(cursor.getColumnIndex(SanPham.SP_GIA));
                @SuppressLint("Range") Integer slsanpham = cursor.getInt(cursor.getColumnIndex(SanPham.SP_SOLUONG));
                @SuppressLint("Range") String nhasxsanpham = cursor.getString(cursor.getColumnIndex(SanPham.SP_NHASX));
                @SuppressLint("Range") Integer namsxsanpham = cursor.getInt(cursor.getColumnIndex(SanPham.SP_NAMSX));
                @SuppressLint("Range") byte[] anhsanpham = cursor.getBlob(cursor.getColumnIndex(SanPham.SP_ANH));

                SanPham sanPham = new SanPham(idsanpham, tensanpham, motasanpham, giasanpham, slsanpham, nhasxsanpham, namsxsanpham, anhsanpham);
                listSP.add(sanPham);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listSP;
    }
    public List<XuatKho> INFOXK(){
        List<XuatKho> listXK = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " +XuatKho.TABLE_XUATKHO;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String idxuatkho = cursor.getString(cursor.getColumnIndex(XuatKho.XK_ID));
                @SuppressLint("Range") String tensanpham = cursor.getString(cursor.getColumnIndex(XuatKho.XK_TENSP));
                @SuppressLint("Range") String ngayxuatsanpham = cursor.getString(cursor.getColumnIndex(XuatKho.XK_NGAYXUAT));
                @SuppressLint("Range") Integer soluongsanpham = cursor.getInt(cursor.getColumnIndex(XuatKho.XK_SOLUONG));
                @SuppressLint("Range") double giaxuatsanpham = cursor.getDouble(cursor.getColumnIndex(XuatKho.XK_GIA));
                XuatKho xuatKho = new XuatKho(idxuatkho,tensanpham,ngayxuatsanpham,soluongsanpham,giaxuatsanpham);
                listXK.add(xuatKho);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listXK;
    }
    public List<NhapKho> INFONK(){
        List<NhapKho> listNK = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " +NhapKho.TABLE_NHAPKHO;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String idnhapkho = cursor.getString(cursor.getColumnIndex(NhapKho.NK_ID));
                @SuppressLint("Range") String tensanpham = cursor.getString(cursor.getColumnIndex(NhapKho.NK_TENSP));
                @SuppressLint("Range") String tennhacungcapsanpham = cursor.getString(cursor.getColumnIndex(NhapKho.NK_TENNCC));
                @SuppressLint("Range") String ngaynhapsanpham = cursor.getString(cursor.getColumnIndex(NhapKho.NK_NGAYNHAP));
                @SuppressLint("Range") Integer soluongsanpham = cursor.getInt(cursor.getColumnIndex(NhapKho.NK_SOLUONG));
                @SuppressLint("Range") double gianhapsanpham = cursor.getDouble(cursor.getColumnIndex(NhapKho.NK_GIA));
                NhapKho nhapKho = new NhapKho(idnhapkho,tensanpham,tennhacungcapsanpham,ngaynhapsanpham,soluongsanpham,gianhapsanpham);
                listNK.add(nhapKho);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listNK;
    }
    public List<NhanVien> INFONV(){
        List<NhanVien> listNV = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " +NhanVien.TABLE_NHANVIEN;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String idnhanvien = cursor.getString(cursor.getColumnIndex(NhanVien.NV_ID));
                @SuppressLint("Range") String tennhanvien = cursor.getString(cursor.getColumnIndex(NhanVien.NV_TEN));
                @SuppressLint("Range") String chucvu = cursor.getString(cursor.getColumnIndex(NhanVien.NV_CHUCVU));
                @SuppressLint("Range") String sodienthoainhanvien = cursor.getString(cursor.getColumnIndex(NhanVien.NV_SDT));
                @SuppressLint("Range") String diachinhanvien = cursor.getString(cursor.getColumnIndex(NhanVien.NV_DIACHI));
                NhanVien nhanVien = new NhanVien(idnhanvien,tennhanvien,chucvu,sodienthoainhanvien,diachinhanvien);
                listNV.add(nhanVien);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listNV;
    }


    // Trong class DBHelper


    // Thêm phương thức để lấy số lượng tồn kho của một sản phẩm cụ thể
    // Thay đổi hàm getRemainingQuantity
    @SuppressLint("Range")
    public int getRemainingQuantity(String tenSanPham) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Lấy tổng số lượng nhập kho của sản phẩm
        String queryTotalNhapKho = "SELECT SUM(" + NhapKho.TABLE_NHAPKHO + "." + NhapKho.NK_SOLUONG + ") AS total_nhapkho " +
                "FROM " + NhapKho.TABLE_NHAPKHO +
                " WHERE " + NhapKho.TABLE_NHAPKHO + "." + NhapKho.NK_TENSP + " = ?";
        Cursor cursorTotalNhapKho = db.rawQuery(queryTotalNhapKho, new String[]{tenSanPham});

        int totalNhapKho = 0;
        if (cursorTotalNhapKho.moveToFirst()) {
            totalNhapKho = cursorTotalNhapKho.getInt(cursorTotalNhapKho.getColumnIndex("total_nhapkho"));
        }

        // Lấy tổng số lượng xuất kho của sản phẩm
        String queryTotalXuatKho = "SELECT SUM(" + XuatKho.TABLE_XUATKHO + "." + XuatKho.XK_SOLUONG + ") AS total_xuatkho " +
                "FROM " + XuatKho.TABLE_XUATKHO +
                " WHERE " + XuatKho.TABLE_XUATKHO + "." + XuatKho.XK_TENSP + " = ?";
        Cursor cursorTotalXuatKho = db.rawQuery(queryTotalXuatKho, new String[]{tenSanPham});

        int totalXuatKho = 0;
        if (cursorTotalXuatKho.moveToFirst()) {
            totalXuatKho = cursorTotalXuatKho.getInt(cursorTotalXuatKho.getColumnIndex("total_xuatkho"));
        }

        // Tính toán hiệu số lượng tồn kho
        int remainingQuantity = totalNhapKho - totalXuatKho;

        cursorTotalNhapKho.close();
        cursorTotalXuatKho.close();
        db.close();

        // In ra kết quả để kiểm tra
        Log.d("DBHelper", "Remaining Quantity for " + tenSanPham + ": " + remainingQuantity);

        return remainingQuantity;
    }
    // Phương thức mới để lấy số lượng có sẵn cho một sản phẩm
    @SuppressLint("Range")
    public int getAvailableQuantityForProduct(String productName) {
        SQLiteDatabase db = this.getReadableDatabase();
        int availableQuantity = 0;

        String query = "SELECT "+ SanPham.SP_SOLUONG +" FROM "+ SanPham.TABLE_SANPHAM +"  WHERE "+ SanPham.SP_TEN+" = ?";
        Cursor cursor = db.rawQuery(query, new String[]{productName});

        if (cursor.moveToFirst()) {
            availableQuantity = cursor.getInt(cursor.getColumnIndex(SanPham.SP_SOLUONG));
        }


        cursor.close();
        return availableQuantity;
    }

    //Lấy sản phẩm nhập nhiều nhất
    @SuppressLint("Range")
    public String laySanPhamNhapNhieuNhat(){
        SQLiteDatabase db = this.getReadableDatabase();
        String tensp = "";
        String query = "SELECT "+ NhapKho.NK_TENSP +  " FROM " + NhapKho.TABLE_NHAPKHO + " WHERE " + NhapKho.NK_SOLUONG + "= (SELECT MAX( "+ NhapKho.NK_SOLUONG + ") FROM  "+ NhapKho.TABLE_NHAPKHO + ")";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            tensp = cursor.getString(cursor.getColumnIndex(NhapKho.NK_TENSP));
        }
        cursor.close();
        return  tensp;
    }

    //Lấy sản phẩm xuất nhiều nhất
    @SuppressLint("Range")
    public String laySanPhamXuatNhieuNhat(){
        SQLiteDatabase db = this.getReadableDatabase();
        String tensp = "";
        String query = "SELECT "+ XuatKho.XK_TENSP +  " FROM " + XuatKho.TABLE_XUATKHO + " WHERE " + XuatKho.XK_SOLUONG + "= (SELECT MAX( "+ XuatKho.XK_SOLUONG + ") FROM  "+ XuatKho.TABLE_XUATKHO+ ")";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            tensp = cursor.getString(cursor.getColumnIndex(XuatKho.XK_TENSP));
        }
        cursor.close();
        return  tensp;
    }
    //Lấy sản phẩm nhập ít nhất
    @SuppressLint("Range")
    public String laySanPhamNhapItNhat(){
        SQLiteDatabase db = this.getReadableDatabase();
        String tensp = "";
        String query = "SELECT "+ NhapKho.NK_TENSP +  " FROM " + NhapKho.TABLE_NHAPKHO + " WHERE " + NhapKho.NK_SOLUONG + "= (SELECT MIN( "+ NhapKho.NK_SOLUONG + ") FROM  "+ NhapKho.TABLE_NHAPKHO + ")";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            tensp = cursor.getString(cursor.getColumnIndex(NhapKho.NK_TENSP));
        }
        cursor.close();
        return  tensp;
    }
    //Lấy sản phẩm xuất ít nhất
    @SuppressLint("Range")
    public String laySanPhamXuatItNhat(){
        SQLiteDatabase db = this.getReadableDatabase();
        String tensp = "";
        String query = "SELECT "+ XuatKho.XK_TENSP +  " FROM " + XuatKho.TABLE_XUATKHO + " WHERE " + XuatKho.XK_SOLUONG + "= (SELECT MIN( "+ XuatKho.XK_SOLUONG + ") FROM  "+ XuatKho.TABLE_XUATKHO+ ")";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            tensp = cursor.getString(cursor.getColumnIndex(XuatKho.XK_TENSP));
        }
        cursor.close();
        return  tensp;
    }

    public  void register(String username, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();


    }
    public int login(String username, String password){
        int result = 0;
        String str [] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username = ? and password = ?",str);
        if(c.moveToFirst()){
            result =1;
        }
        return result;
    }
}
