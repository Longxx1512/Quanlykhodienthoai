package com.example.quanlykho.Table;

public class NhanVien {
    //table name
    public static final String TABLE_NHANVIEN = "NHANVIEN";

    //table column or field name
    public static final String NV_ID = "idnhanvien";
    public static final String NV_TEN = "tennhanvien";
    public static final String NV_CHUCVU = "chucvu";
    public static final String NV_SDT = "sodienthoainhanvien";
    public static final String NV_DIACHI = "diachinhanvien";
    // qurey for crate table
    public static final String CREATE_NHANVIEN = "CREATE TABLE " + TABLE_NHANVIEN + "("
            + NV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NV_TEN+ " TEXT, "
            + NV_CHUCVU+ " TEXT, "
            + NV_SDT+ " TEXT, "
            + NV_DIACHI+ " TEXT )";
    //create database helper class for CRUD Query And Database Creation
    private String id,name,duty,phone,address;

    public NhanVien() {
    }

    public NhanVien(String id, String name, String duty, String phone, String address) {
        this.id = id;
        this.name = name;
        this.duty = duty;
        this.phone = phone;
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

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
