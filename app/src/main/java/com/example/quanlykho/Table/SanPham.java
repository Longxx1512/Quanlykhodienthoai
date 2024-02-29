package com.example.quanlykho.Table;

public class SanPham {
    //table name
    public static final String TABLE_SANPHAM = "SANPHAM";

    //table column or field name
    public static final String SP_ID = "idsanpham";
    public static final String SP_TEN = "tensanpham";
    public static final String SP_MOTA = "motasanpham";
    public static final String SP_GIA = "giasanpham";
    public static final String SP_SOLUONG = "soluongsanpham";
    public static final String SP_NHASX = "nhasxsanpham";
    public static final String SP_NAMSX = "namsxsanpham";
    public static final String SP_ANH = "anh";
    // qurey for crate table
    public static final String CREATE_SANPHAM = "CREATE TABLE " + TABLE_SANPHAM + "("
            + SP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SP_TEN+ " TEXT, "
            + SP_MOTA+ " TEXT, "
            + SP_GIA+ " REAL, "
            + SP_SOLUONG+ " INTEGER, "
            + SP_NHASX+ " TEXT, "
            + SP_NAMSX+ " INTEGER, "
            + SP_ANH+ " BLOB)";
    //create database helper class for CRUD Query And Database Creation


    private String id,name,info;
    private double price;
    private Integer quantity;
    private  String manufacturers;
    private Integer date;
    byte[] image;

    public SanPham() {
    }

    public SanPham(String id, String name, String info, double price, Integer quantity, String manufacturers, Integer date, byte[] image) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.price = price;
        this.quantity = quantity;
        this.manufacturers = manufacturers;
        this.date = date;
        this.image = image;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(String manufacturers) {
        this.manufacturers = manufacturers;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
