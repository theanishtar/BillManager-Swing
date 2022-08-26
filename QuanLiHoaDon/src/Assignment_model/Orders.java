/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment_model;

import java.io.Serializable;

/**
 *
 * @author dangt
 */
public class Orders implements Serializable {
    
    public String tenThuNgan;
    public String tenSanPham;
    public String maSanPham;
    public String ngayOrder;
    public float gia;
    public float thanhTien;
    public int soLuong;
    
    
    //public static boolean admin = false;
    public Orders() {
    }

    public Orders(String tenThuNgan, String tenSanPham, String maSanPham, float gia, int soLuong, String ngayOrder, float thanhTien) {
        this.tenThuNgan = tenThuNgan;
        this.tenSanPham = tenSanPham;
        this.maSanPham = maSanPham;
        this.gia = gia;
        this.soLuong = soLuong;
        this.ngayOrder = ngayOrder;
        this.thanhTien = thanhTien;
    }


    public String getTenThuNgan() {
        return tenThuNgan;
    }

    public void setTenThuNgan(String tenThuNgan) {
        this.tenThuNgan = tenThuNgan;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getNgayOrder() {
        return ngayOrder;
    }

    public void setNgayOrder(String ngayOrder) {
        this.ngayOrder = ngayOrder;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    
}
