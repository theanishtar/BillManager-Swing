/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment_model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dangt
 */
public class ResetFile {
    public static void main(String[] args) {
        List<Orders> danhSach = new ArrayList<>();//mảng chứa dữ liệu ghi vào file
        //List<Orders> list1 = new ArrayList<>();//mảng chứa dữ liệu lấy được từ file
        String path = "ordersList.dat";//Đường dẫn file
        //them đối tượng sinh viên vào mảng để ghi vào file
        danhSach.add(new Orders("Trần Thị Mỹ Duyên", "Trà hoa cúc (đá)", "TH79", 32000, 7, "13-06-2022", 224000));
        danhSach.add(new Orders("Nguyễn Khánh Đan", "Bạc xỉu (đá)", "BX44", 20000, 1, "11-05-2022", 20000));
        danhSach.add(new Orders("Phùng Quốc Vinh", "Matcha đá xay", "MC043", 28000, 6, "25-04-2022", 168000));
        danhSach.add(new Orders("Đoàn Hiệp Sỹ", "Atiso đường phèn (nóng)", "AT57", 22000, 5, "07-05-2022", 110000));
        danhSach.add(new Orders("Lê Bích Vi", "Đen đá (không đường)", "DD05", 18000, 9, "14-06-2022", 162000));
        danhSach.add(new Orders("Lê Thị Ngọc Hân", "Cappuccino (nóng)", "CP31", 40000, 7, "15-06-2022", 280000));
        danhSach.add(new Orders("Nguyễn Thị Diễm Ngân", "SET Bánh ngọt 4C (matcha)", "BN789", 240000, 3, "09-06-2022", 720000));
        danhSach.add(new Orders("Hồ Phước Lộc", "Sữa tươi trân châu", "ST223", 28000, 9, "25-02-2022", 250000));
        System.out.println("Thao tác đã hoàn thành vui lòng xem lại file");
        try {
            //Ghi dữ liệu vào file
            ManipulateFile.writeObj(path, danhSach);

        } catch (Exception ex) {
            ex.getMessage();
        }

    }
}
