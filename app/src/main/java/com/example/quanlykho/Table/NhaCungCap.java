package com.example.quanlykho.Table;

public class NhaCungCap {


    //table name
    public static final String TABLE_NHACC = "NHACUNGCAP";

    //table column or field name
    public static final String NCC_ID = "idnhacungcap";
    public static final String NCC_TEN = "tennhacungcap";
    public static final String NCC_SDT = "sdtnhacungcap";
    public static final String NCC_EMAIL = "emailnhacungcap";
    public static final String NCC_DIACHI = "diachinhacungcap";
    // qurey for crate table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NHACC + "("
            + NCC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NCC_TEN+ " TEXT, "
            + NCC_SDT+ " TEXT, "
            + NCC_EMAIL+ " TEXT, "
            + NCC_DIACHI+ " TEXT )";
    //create database helper class for CRUD Query And Database Creation


    private String id,name,phone,email,address;

    public NhaCungCap() {
    }

    public NhaCungCap(String id, String name, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
