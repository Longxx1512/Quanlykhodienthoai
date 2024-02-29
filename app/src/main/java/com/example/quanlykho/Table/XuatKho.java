package com.example.quanlykho.Table;

public class XuatKho {
    public static final String TABLE_XUATKHO = "XUATKHO";

    //table column or field name
    public static final String XK_ID = "idxuatkho";
    public static final String XK_TENSP = "tensanpham";
    public static final String XK_NGAYXUAT = "ngayxuatsanpham";
    public static final String XK_SOLUONG = "soluongsanpham";
    public static final String XK_GIA = "giaxuatsanpham";

    // qurey for crate table
    public static final String CREATE_XUATKHO = "CREATE TABLE " + TABLE_XUATKHO + "("
            + XK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + XK_TENSP+ " TEXT, "
            + XK_NGAYXUAT+ " TEXT, "
            + XK_SOLUONG+ " INTEGER, "
            + XK_GIA+ " READ )";
    //create database helper class for CRUD Query And Database Creation


    private String id,name,date;
    private Integer quantity;
    private double price;

    public XuatKho() {
    }

    public XuatKho(String id, String name, String date, Integer quantity, double price) {
        this.id = id;
        this.name = name;
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
