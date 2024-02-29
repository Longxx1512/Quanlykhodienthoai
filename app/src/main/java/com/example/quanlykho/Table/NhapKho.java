package com.example.quanlykho.Table;

public class NhapKho {
    public static final String TABLE_NHAPKHO = "NHAPKHO";

    //table column or field name
    public static final String NK_ID = "idnhapkho";
    public static final String NK_TENSP = "tensanpham";
    public static final String NK_TENNCC = "tennhacungcap";
    public static final String NK_NGAYNHAP = "ngaynhapsanpham";
    public static final String NK_SOLUONG = "soluongsanpham";
    public static final String NK_GIA = "gianhaosanpham";

    // qurey for crate table
    public static final String CREATE_NHAPKHO = "CREATE TABLE " + TABLE_NHAPKHO + "("
            + NK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NK_TENSP+ " TEXT, "
            + NK_TENNCC+ " TEXT, "
            + NK_NGAYNHAP+ " TEXT, "
            + NK_SOLUONG+ " INTEGER, "
            + NK_GIA+ " READ )";
    //create database helper class for CRUD Query And Database Creation

    private String id,name,nameNCC,date;
    private Integer quantity;
    private double price;

    public NhapKho() {
    }

    public NhapKho(String id, String name, String nameNCC, String date, Integer quantity, double price) {
        this.id = id;
        this.name = name;
        this.nameNCC = nameNCC;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
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

    public String getNameNCC() {
        return nameNCC;
    }

    public void setNameNCC(String nameNCC) {
        this.nameNCC = nameNCC;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
