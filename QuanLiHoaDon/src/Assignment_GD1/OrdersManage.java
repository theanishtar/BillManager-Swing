package Assignment_GD1;

//import Assignment.model.Orders;
import Assignment_model.Orders;
import Assignment_model.CalendarFields;
import Assignment_model.ManipulateFile;
import Assignment_model.Standardization;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowEvent;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dangt
 */
public class OrdersManage extends javax.swing.JFrame {

    List<Orders> danhSach = new ArrayList<>();
    DefaultTableModel model;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    int timerun = 0;
    int index = 0;
    public int index_print = 0;
    boolean checkAdmin = false;
    String error = null;
    float thanhTien = 0;

    //
    /**
     * Creates new form OrdersManage
     */
    public OrdersManage(boolean admin) {
        this.checkAdmin = admin;
        initComponents();
        chu_chay();
        TimeRun();
        //loadData();
        btnDelete.setEnabled(false);
        btnSave.setEnabled(false);
        btnPrint.setEnabled(false);
        btnSort.setEnabled(false);

        //Set Icon => JFrame
        URL urlIconMain = OrdersManage.class.getResource("/Assignment_GD1/icon/cup.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconMain);
        this.setIconImage(img);
        btnFirst.setEnabled(false);
        btnPrevious.setEnabled(false);
        btnLast.setEnabled(false);
        btnNext.setEnabled(false);
        btnPrint.setEnabled(false);
        btnDelete.setEnabled(false);
        //BAT LOI NULL CAC TRUONG
        HideErrorThongTinNull();

        Main_Form frame_login = new Main_Form();
        if (admin == false) {
            //JOptionPane.showMessageDialog(this, "Bạn đã không đăng nhập với quyền quản trị nên chỉ có thể xem dữ liệu!");
            int ketQua = JOptionPane.showConfirmDialog(this, "Bạn đã không đăng nhập với quyền quản trị nên chỉ có thể xem dữ liệu! \n Bạn có muốn đăng nhập lại không?", "Thông báo", JOptionPane.YES_OPTION);
            if (ketQua == JOptionPane.YES_OPTION) {
                frame_login.setVisible(true);
                close();
            } else {
                txtNgayOrder.setEnabled(false);
                txtThanhTien.setEnabled(false);
                txtGia.setEnabled(false);
                txtMaSp.setEnabled(false);
                //txtRecord.setEnabled(false);

//
                txtTenTN.setEnabled(false);
//                jplHideTenThuNgan.setForeground(new Color(0,0,0,64));
//                jplHideTenThuNgan.setVisible(true);
                txtTenSP.setEnabled(false);
                txtSoLuong.setEnabled(false);
                //
                //tblList.setEnabled(false);
                //
                //btnSort.setEnabled(false);

                btnDelete.setEnabled(false);
                btnPrint.setEnabled(false);
                btnFind.setEnabled(false);
                btnPrint.setEnabled(false);
                btnFirst.setEnabled(false);
                btnLast.setEnabled(false);
                btnNew.setEnabled(false);
                btnNext.setEnabled(false);
                //btnOpen.setEnabled(false);
                btnPrevious.setEnabled(false);
                //btnSave.setEnabled(false);
            }
        }
    }

    OrdersManage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void HideErrorThongTinNull() {
        txtErrorGia.setVisible(false);
        txtErrorMaSP.setVisible(false);
        txtErrorNgayOrder.setVisible(false);
        txtErrorSoLuong.setVisible(false);
        txtErrorTenSP.setVisible(false);
        txtErrorTenThuNgan.setVisible(false);
    }

    public void ShowErrorThongTinNull() {

        txtErrorGia.setVisible(true);
        txtErrorMaSP.setVisible(true);
        txtErrorNgayOrder.setVisible(true);
        txtErrorSoLuong.setVisible(true);
        txtErrorTenSP.setVisible(true);
        txtErrorTenThuNgan.setVisible(true);
    }

    public void blockButtonAnchor(int index) {
        if (index == danhSach.size() - 1) {
            btnLast.setEnabled(false);
            btnNext.setEnabled(false);
            btnFirst.setEnabled(true);
            btnPrevious.setEnabled(true);
        } //        else {
        //            btnLast.setEnabled(true);
        //            btnNext.setEnabled(true);
        //        }
        else if (index == 0) {
            btnFirst.setEnabled(false);
            btnPrevious.setEnabled(false);
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);
        } else {
            btnFirst.setEnabled(true);
            btnPrevious.setEnabled(true);
            btnLast.setEnabled(true);
            btnNext.setEnabled(true);
        }

    }

    public boolean checkValidate() {
        Standardization std = new Standardization();
        error = null;
        boolean checkValidate = true;
        thanhTien = 0;
        String reMaSP = txtMaSp.getText();
        HideErrorThongTinNull();

        if (txtGia.getText().equals("") || txtMaSp.getText().equals("") || txtNgayOrder.getText().equals("")
                || txtSoLuong.getText().equals("") || txtTenSP.getText().equals("") || txtTenTN.getText().equals("")) {
            if (txtGia.getText().equals("")) {
                txtErrorGia.setVisible(true);
                txtErrorGia.requestFocus();
            }
            if (txtMaSp.getText().equals("")) {
                txtErrorMaSP.setVisible(true);
                txtMaSp.requestFocus();
            }
            if (txtNgayOrder.getText().equals("")) {
                txtErrorNgayOrder.setVisible(true);
                txtNgayOrder.requestFocus();
            }
            if (txtSoLuong.getText().equals("")) {
                txtErrorSoLuong.setVisible(true);
                txtSoLuong.requestFocus();
            }
            if (txtTenSP.getText().equals("")) {
                txtErrorTenSP.setVisible(true);
                txtTenSP.requestFocus();
            }
            if (txtTenTN.getText().equals("")) {
                txtErrorTenThuNgan.setVisible(true);
                txtTenTN.requestFocus();
            }
            error = "Không được để trống các trường dữ liệu";
            return false;
        }

//        if (txtMaSp.getText().length() < 4 || txtMaSp.getText().length() > 6) {
//            error = "Vui lòng nhập Mã SP đúng định dạng: [a-Z][0-9] và gồm 4-5 ký tự! ";
//            //checkValidate = false;
//            return false;
//            //break;
//        }
        reMaSP = reMaSP.toUpperCase();
        if (!std.maSP(reMaSP)) {
            error = "Vui lòng nhập Mã SP đúng định dạng: hai chữ cái đầu còn lại là số và gồm 4-5 ký tự! ";
            return false;
        }
        txtMaSp.setText(reMaSP);

        CalendarFields validator = new CalendarFields("dd-MM-yyyy");
        String day = txtNgayOrder.getText();
        //System.out.println(day);
        //cf.validator.is()
//        System.out.println(cf.isValid(day));
//        System.out.println(cf.xuLy(day, cf.now()));
//check date    12-11-1200
        String reDate = txtNgayOrder.getText();
        if (reDate.charAt(2) != '-' || reDate.charAt(5) != '-' || reDate.length() != 10) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng \n \tdd-MM-yyyy!");
            //checkValidate = false;
            return false;
        }

        if (!validator.isValid(day) || !validator.xuLy(day, validator.now())) {
            //JOptionPane.showMessageDialog(this, "Ngày tháng không hợp lệ!");
            error = "Ngày tháng không hợp lệ";
            //checkValidate = false;
            return false;
        }

//        String reNameTN = txtTenTN.getText();
//        for (int i = 0; i < reNameTN.length(); i++) {
//            if ((reNameTN.charAt(i) >= 0 && reNameTN.charAt(i) <= 31) || (reNameTN.charAt(i) >= 33 && reNameTN.charAt(i) <= 64)
//                    || (reNameTN.charAt(i) >= 91 && reNameTN.charAt(i) <= 96)
//                    || (reNameTN.charAt(i) >= 123 && reNameTN.charAt(i) <= 127)) {
//                //System.out.println("'"+txtTenTN.getText()+"'");
//                //JOptionPane.showMessageDialog(this, "Tên Thu ngân không phù hợp!");
//                error = "Tên Thu ngân không hợp lệ";
//                //checkValidate = false;
//                //break;
//                return false;
//            }
//        }
        txtTenTN.setText(std.hoTen(txtTenTN.getText()));
        //checkSoLuong
        try {
            int soLuong = Integer.valueOf(txtSoLuong.getText());
        } catch (Exception e) {
            //System.out.println("Không nhập chữ!");
            error = "Số lượng không hợp lệ!";
            txtErrorSoLuong.setVisible(true);
            return false;
        }

        try {
            float gia = Float.valueOf(txtGia.getText());
        } catch (Exception e) {
            //System.out.println("Không nhập chữ!");
            error = "Giá không hợp lệ!";
            txtErrorGia.setVisible(true);
            return false;
        }

        thanhTien = Integer.valueOf(txtSoLuong.getText()) * Float.valueOf(txtGia.getText());

        for (Orders x : danhSach) {
            if (x.getMaSanPham().equalsIgnoreCase(reMaSP)) {
                index = danhSach.indexOf(x);
                //showDetail(index);
                //isFind = true;
                //JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại!");
                int ketQua = JOptionPane.showConfirmDialog(this, "Mã nhân viên đã tồn tại \n Bạn có muốn cập nhật lại không?", "Thông báo", JOptionPane.YES_OPTION);
                if (ketQua == JOptionPane.YES_OPTION) {
                    //JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    error = "Cập nhật thành công!";
                    //x.setMaSanPham(reMaSP);
                    x.setTenThuNgan(txtTenTN.getText());
                    x.setMaSanPham(txtMaSp.getText());
                    x.setTenSanPham(txtTenSP.getText());
                    x.setNgayOrder(txtNgayOrder.getText());
                    x.setGia(Float.valueOf(txtGia.getText()));
                    x.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
                    x.setThanhTien(thanhTien);       //Float.valueOf(txtThanhTien.getText())
                    //checkValidate = false;
                    model.setRowCount(0);
                    //Đẩy Data từ List --> model
                    fillToTable();
                    //break;
                    showDetail(index);
                }
                return false;
//                if (ketQua == JOptionPane.NO_OPTION) {
//                    //checkValidate = false;
//                    //break;
//                    return false;
//                }
            }
        }

//        try {
//            thanhTien = Float.valueOf(txtGia.getText()) * Float.valueOf(txtSoLuong.getText());
//        } catch (Exception e) {
//            error = "Số lượng không phù hợp";
//        }
        //check null
        //return checkValidate;
        return true;
    }

    public void loadData() {
        //btnLoad.setEnabled(false);

        model = new DefaultTableModel();

        //Thêm dữ liệu vào list
//        danhSach.add(new Orders("Trần Thị Mỹ Duyên", "Trà hoa cúc (đá)", "TH79", 32000, 7, "13-06-2022", 224000));
//        danhSach.add(new Orders("Nguyễn Khánh Đan", "Bạc xỉu (đá)", "BX44", 20000, 1, "11-05-2022", 20000));
//        danhSach.add(new Orders("Phùng Quốc Vinh", "Matcha đá xay", "MC043", 28000, 6, "25-04-2022", 168000));
//        danhSach.add(new Orders("Đoàn Hiệp Sỹ", "Atiso đường phèn (nóng)", "AT57", 22000, 5, "07-05-2022", 110000));
//        danhSach.add(new Orders("Lê Bích Vi", "Đen đá (không đường)", "DD05", 18000, 9, "14-06-2022", 162000));
//        danhSach.add(new Orders("Lê Thị Ngọc Hân", "Cappuccino (nóng)", "CP31", 40000, 7, "15-06-2022", 280000));
//        danhSach.add(new Orders("Nguyễn Thị Diễm Ngân", "SET Bánh ngọt 4C (matcha)", "BN789", 240000, 3, "09-06-2022", 720000));
//        danhSach.add(new Orders("Hồ Phước Lộc", "Sữa tươi trân châu", "ST223", 28000, 9, "25-02-2022", 250000));

        model = new DefaultTableModel();
        //Tạo các cột
        model.addColumn("TÊN THU NGÂN");
        model.addColumn("TÊN SẢN PHẨM");
        model.addColumn("MÃ SẢN PHẨM");
        model.addColumn("GIÁ");
        model.addColumn("SỐ LƯỢNG");
        model.addColumn("NGÀY ORDER");
        model.addColumn("THÀNH TIỀN");

        //lưu file
        readFile();

        //Đẩy Data từ List --> model
        fillToTable();

        //Đẩy Data từ Model --> Table
        tblList.setModel(model);

    }

    public void fillToTable() {
        model.setRowCount(0);
        for (Orders index : danhSach) {
            //model.addRow(new Object[]{index.MSSV, index.hoTen, index.lop, index.moHoc, index.diem});
            model.addRow(new Object[]{index.tenThuNgan, index.tenSanPham, index.maSanPham, index.gia, index.soLuong, index.ngayOrder, index.thanhTien});
        }
    }

    //Hiển thị Danh Sách lên Form
    private void showDetail(int index) {
        txtTenTN.setText(danhSach.get(index).getTenThuNgan());
        txtTenSP.setText(danhSach.get(index).getTenSanPham());
        txtMaSp.setText(danhSach.get(index).getMaSanPham());
        txtGia.setText(danhSach.get(index).getGia() + "");
        txtSoLuong.setText(danhSach.get(index).getSoLuong() + "");
        txtNgayOrder.setText(danhSach.get(index).getNgayOrder());
        txtThanhTien.setText(danhSach.get(index).getThanhTien() + "");
        txtRecord.setText("Records: " + (index + 1) + " of " + danhSach.size());
        tblList.setRowSelectionInterval(index, index);
//        txtTenTN.setText(tblList.getValueAt(index, 0).toString());
//        txtTenSP.setText(tblList.getValueAt(index, 1).toString());
//        txtMaSp.setText(tblList.getValueAt(index, 2).toString());
//        txtGia.setText(tblList.getValueAt(index, 3).toString());
//        txtSoLuong.setText(tblList.getValueAt(index, 4).toString());
//        txtNgayOrder.setText(tblList.getValueAt(index, 5).toString());
//        txtThanhTien.setText(tblList.getValueAt(index, 6).toString());
//        txtRecord.setText("Records: " + (index + 1) + " of " + danhSach.size());
//        tblList.setRowSelectionInterval(index, index);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnFind = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnSort = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        lblTime = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtRecord = new javax.swing.JLabel();
        btnNext = new javax.swing.JLabel();
        btnFirst = new javax.swing.JLabel();
        btnPrevious = new javax.swing.JLabel();
        btnLast = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaSp = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        txtTenTN = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblChuChay = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jplLose = new javax.swing.JPanel();
        jlbClose = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        txtNgayOrder = new javax.swing.JTextField();
        cboSort = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rdbAZ = new javax.swing.JRadioButton();
        rdbZA = new javax.swing.JRadioButton();
        txtErrorTenThuNgan = new javax.swing.JLabel();
        txtErrorMaSP = new javax.swing.JLabel();
        txtErrorGia = new javax.swing.JLabel();
        txtErrorSoLuong = new javax.swing.JLabel();
        txtErrorTenSP = new javax.swing.JLabel();
        txtErrorNgayOrder = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/coffee (1).png"))); // NOI18N
        jLabel3.setText("TÊN SẢN PHẨM");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 150, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/price-tag (1).png"))); // NOI18N
        jLabel4.setText("GIÁ");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 70, 30));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFind.setBackground(new java.awt.Color(51, 51, 51));
        btnFind.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnFind.setForeground(new java.awt.Color(255, 255, 255));
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/search.png"))); // NOI18N
        btnFind.setText("FIND");
        btnFind.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnFind.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFindMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFindMouseExited(evt);
            }
        });
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });
        jPanel2.add(btnFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 110, 40));

        btnDelete.setBackground(new java.awt.Color(51, 51, 51));
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/delete.png"))); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteMouseExited(evt);
            }
        });
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 110, 40));

        btnSave.setBackground(new java.awt.Color(51, 51, 51));
        btnSave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/diskette.png"))); // NOI18N
        btnSave.setText("SAVE");
        btnSave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveMouseExited(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel2.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 110, 40));

        btnPrint.setBackground(new java.awt.Color(51, 51, 51));
        btnPrint.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/printer.png"))); // NOI18N
        btnPrint.setText("PRINT");
        btnPrint.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPrintMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrintMouseExited(evt);
            }
        });
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel2.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 110, 40));

        btnSort.setBackground(new java.awt.Color(51, 51, 51));
        btnSort.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSort.setForeground(new java.awt.Color(255, 255, 255));
        btnSort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/sort.png"))); // NOI18N
        btnSort.setText("SORT");
        btnSort.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnSort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSortMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSortMouseExited(evt);
            }
        });
        btnSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortActionPerformed(evt);
            }
        });
        jPanel2.add(btnSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 110, 40));

        btnNew.setBackground(new java.awt.Color(51, 51, 51));
        btnNew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/edit (1).png"))); // NOI18N
        btnNew.setText("NEW");
        btnNew.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewMouseExited(evt);
            }
        });
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel2.add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 110, 40));

        btnOpen.setBackground(new java.awt.Color(51, 51, 51));
        btnOpen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpen.setForeground(new java.awt.Color(255, 255, 255));
        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/open.png"))); // NOI18N
        btnOpen.setText("OPEN");
        btnOpen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnOpen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnOpenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnOpenMouseExited(evt);
            }
        });
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        jPanel2.add(btnOpen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 40));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 80, 130, 360));

        lblTime.setBackground(new java.awt.Color(51, 51, 0));
        lblTime.setFont(new java.awt.Font("Segoe UI Semilight", 1, 20)); // NOI18N
        lblTime.setForeground(new java.awt.Color(51, 51, 255));
        lblTime.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/stopwatch.png"))); // NOI18N
        lblTime.setText("00:00 AM");
        jPanel3.add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 150, 44));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/products (1).png"))); // NOI18N
        jLabel5.setText("SỐ LƯỢNG");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 100, 30));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtRecord.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtRecord.setForeground(new java.awt.Color(255, 51, 51));
        txtRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/visits (1).png"))); // NOI18N
        txtRecord.setText("Record:");
        jPanel4.add(txtRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 180, 30));

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/arrow-right.png"))); // NOI18N
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextMouseClicked(evt);
            }
        });
        jPanel4.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/previous.png"))); // NOI18N
        btnFirst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFirstMouseClicked(evt);
            }
        });
        jPanel4.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/left-arrow.png"))); // NOI18N
        btnPrevious.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreviousMouseClicked(evt);
            }
        });
        jPanel4.add(btnPrevious, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/next.png"))); // NOI18N
        btnLast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLastMouseClicked(evt);
            }
        });
        jPanel4.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 430, 50));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/cashier (1).png"))); // NOI18N
        jLabel6.setText("TÊN THU NGÂN");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 122, -1));

        txtMaSp.setBackground(new java.awt.Color(51, 51, 51));
        txtMaSp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaSp.setForeground(new java.awt.Color(255, 255, 255));
        txtMaSp.setBorder(null);
        txtMaSp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMaSpMouseClicked(evt);
            }
        });
        jPanel3.add(txtMaSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 220, 30));

        txtTenSP.setBackground(new java.awt.Color(51, 51, 51));
        txtTenSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenSP.setForeground(new java.awt.Color(255, 255, 255));
        txtTenSP.setBorder(null);
        txtTenSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTenSPMouseClicked(evt);
            }
        });
        jPanel3.add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, 220, 30));

        txtSoLuong.setBackground(new java.awt.Color(51, 51, 51));
        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSoLuong.setForeground(new java.awt.Color(255, 255, 255));
        txtSoLuong.setBorder(null);
        txtSoLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSoLuongMouseClicked(evt);
            }
        });
        jPanel3.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 90, 30));

        txtGia.setBackground(new java.awt.Color(51, 51, 51));
        txtGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtGia.setForeground(new java.awt.Color(255, 255, 255));
        txtGia.setBorder(null);
        txtGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGiaMouseClicked(evt);
            }
        });
        jPanel3.add(txtGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 100, 30));

        txtTenTN.setBackground(new java.awt.Color(51, 51, 51));
        txtTenTN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenTN.setForeground(new java.awt.Color(255, 255, 255));
        txtTenTN.setBorder(null);
        txtTenTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTenTNMouseClicked(evt);
            }
        });
        txtTenTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenTNActionPerformed(evt);
            }
        });
        jPanel3.add(txtTenTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 220, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/bar-code (1).png"))); // NOI18N
        jLabel2.setText("MÃ SẢN PHẨM");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 130, -1));

        lblChuChay.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblChuChay.setForeground(new java.awt.Color(51, 153, 0));
        lblChuChay.setText("TIỆM TRÀ HƯƠNG THẢO");
        jPanel3.add(lblChuChay, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, 40));

        tblList.setAutoCreateRowSorter(true);
        tblList.setBackground(new java.awt.Color(71, 71, 71));
        tblList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblList.setForeground(new java.awt.Color(255, 255, 255));
        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TÊN THU NGÂN", "TÊN SẢN PHẨM", "MÃ SẢN PHẨM", "GIÁ", "SỐ LƯỢNG", "NGÀY ORDER", "THÀNH TIỀN"
            }
        ));
        tblList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblList.setRowHeight(20);
        tblList.setRowMargin(5);
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblList);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 830, 190));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 100, -1));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 220, -1));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 220, -1));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 90, 10));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 220, -1));

        jplLose.setBackground(new java.awt.Color(59, 59, 59));
        jplLose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplLoseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jplLoseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jplLoseMouseExited(evt);
            }
        });
        jplLose.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbClose.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jlbClose.setForeground(new java.awt.Color(255, 255, 255));
        jlbClose.setText(" X");
        jlbClose.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jlbCloseFocusGained(evt);
            }
        });
        jlbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlbCloseMouseExited(evt);
            }
        });
        jplLose.add(jlbClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 20, 30));

        jPanel3.add(jplLose, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 60, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/credit-card (1).png"))); // NOI18N
        jLabel7.setText("THÀNH TIỀN");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 120, -1));

        txtThanhTien.setBackground(new java.awt.Color(51, 51, 51));
        txtThanhTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtThanhTien.setForeground(new java.awt.Color(255, 255, 255));
        txtThanhTien.setBorder(null);
        jPanel3.add(txtThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, 220, 30));
        jPanel3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 350, 220, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/calendar (1).png"))); // NOI18N
        jLabel8.setText("NGÀY ORDER");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 112, -1));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, 220, -1));

        txtNgayOrder.setBackground(new java.awt.Color(51, 51, 51));
        txtNgayOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNgayOrder.setForeground(new java.awt.Color(255, 255, 255));
        txtNgayOrder.setBorder(null);
        txtNgayOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayOrderMouseClicked(evt);
            }
        });
        jPanel3.add(txtNgayOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 220, 30));

        cboSort.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên thu ngân", "Mã sản phẩm", "Giá", "Số lượng", "Thành tiền" }));
        cboSort.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        cboSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSortActionPerformed(evt);
            }
        });
        jPanel3.add(cboSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 150, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 255));
        jLabel9.setText("Sắp xếp theo: ");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 370, 100, 20));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment_GD1/icon/manager.png"))); // NOI18N
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 60, 60));

        rdbAZ.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup1.add(rdbAZ);
        rdbAZ.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbAZ.setForeground(new java.awt.Color(255, 255, 255));
        rdbAZ.setText("Tăng");
        jPanel3.add(rdbAZ, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 410, 80, -1));

        rdbZA.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup1.add(rdbZA);
        rdbZA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbZA.setForeground(new java.awt.Color(255, 255, 255));
        rdbZA.setText("Giảm");
        jPanel3.add(rdbZA, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 410, 80, -1));

        txtErrorTenThuNgan.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        txtErrorTenThuNgan.setForeground(new java.awt.Color(255, 0, 0));
        txtErrorTenThuNgan.setText("Required field");
        jPanel3.add(txtErrorTenThuNgan, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 220, 20));

        txtErrorMaSP.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        txtErrorMaSP.setForeground(new java.awt.Color(255, 0, 0));
        txtErrorMaSP.setText("Required field");
        jPanel3.add(txtErrorMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 220, 30));

        txtErrorGia.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        txtErrorGia.setForeground(new java.awt.Color(255, 0, 0));
        txtErrorGia.setText("Required field");
        jPanel3.add(txtErrorGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 100, 30));

        txtErrorSoLuong.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        txtErrorSoLuong.setForeground(new java.awt.Color(255, 0, 0));
        txtErrorSoLuong.setText("Required field");
        jPanel3.add(txtErrorSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 90, 30));

        txtErrorTenSP.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        txtErrorTenSP.setForeground(new java.awt.Color(255, 0, 0));
        txtErrorTenSP.setText("Required field");
        jPanel3.add(txtErrorTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 220, 20));

        txtErrorNgayOrder.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        txtErrorNgayOrder.setForeground(new java.awt.Color(255, 0, 0));
        txtErrorNgayOrder.setText("Required field");
        jPanel3.add(txtErrorNgayOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, 220, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 650));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void close() {
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        toolkit.getSystemEventQueue().postEvent(closeWindow);
    }

    public void chu_chay() {
        Thread threadl = new Thread() {
            @Override
            public void run() {
                String txt = " " + lblChuChay.getText() + " ";
                while (true) {
                    txt = txt.substring(1, txt.length()) + txt.charAt(0);
                    try {
                        sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OrdersManage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lblChuChay.setText(txt);
                }
            }

        };
        threadl.start();
    }

    public void TimeRun() {
        new Thread() {
            public void run() {
                while (timerun == 0) {
                    Calendar cal = new GregorianCalendar();
                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int AM_PM = cal.get(Calendar.AM_PM);
                    String day_night = "";
                    if (AM_PM == 1) {
                        day_night = "PM";
                    } else {
                        day_night = "AM";
                    }
                    String str = hour + ":" + minute + ":" + second + " " + day_night;
                    lblTime.setText(str);
                }
            }
        }.start();
    }

    //SAVE FILE 
    public void saveFile() {
        try {
            ManipulateFile.writeObj("ordersList.dat", danhSach);
            //JOptionPane.showMessageDialog(this, "Saved");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void readFile() {
        try {
            danhSach = (List<Orders>) ManipulateFile.readObj("ordersList.dat");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Nút First
    public void First() {
        index = 0;
        showDetail(index);
        blockButtonAnchor(index);
        index_print = index;
        if (this.checkAdmin) {
            btnDelete.setEnabled(true);
            btnSave.setEnabled(true);
        }
        btnPrint.setEnabled(true);
    }

    //Nút Prev
    public void Previous() {
        if (index > 0) {
            index--;
            showDetail(index);
            blockButtonAnchor(index);
            if (this.checkAdmin) {
                btnDelete.setEnabled(true);
                btnSave.setEnabled(true);
            }
            btnPrint.setEnabled(true);
        }
        index_print = index;
    }

    //Nút Next
    public void Next() {
        if (index < danhSach.size() - 1) {
            index++;
            showDetail(index);
            blockButtonAnchor(index);
            if (this.checkAdmin) {
                btnDelete.setEnabled(true);
                btnSave.setEnabled(true);
            }
            btnPrint.setEnabled(true);
        }
        index_print = index;
    }

    public void Last() {
        index = danhSach.size() - 1;
        showDetail(index);
        blockButtonAnchor(index);
        index_print = index;
        if (this.checkAdmin) {
            btnDelete.setEnabled(true);
            btnSave.setEnabled(true);
        }
        btnPrint.setEnabled(true);
    }

    //Nút Find
    public void Find() {
        try {
            String inputFind = JOptionPane.showInputDialog("Nhập Mã đơn hàng cần tìm: ");
            boolean isFind = false;
            for (Orders x : danhSach) {
                if (x.getMaSanPham().equalsIgnoreCase(inputFind)) {
                    index = danhSach.indexOf(x);
                    showDetail(index);
                    isFind = true;
                    break;
                }
            }
            if (inputFind != null) {
                if (!isFind) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy đơn hàng có Mã: " + inputFind);
                } else {
                    JOptionPane.showMessageDialog(this, "Tìm thấy có đơn hàng với Mã: " + inputFind);
                    if (this.checkAdmin) {
                        btnDelete.setEnabled(true);
                        btnSave.setEnabled(true);
                    }
                    btnPrint.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    //Nút delete
    public void Delete(int index) {
        int checkDelete = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa nhân viên này không?",
                "Thông báo", JOptionPane.YES_NO_OPTION);// == JOptionPane.YES_OPTION;
        if (checkDelete == JOptionPane.YES_OPTION) {
            danhSach.remove(index);
            model.setRowCount(0);
            //Đẩy Data từ List --> model
            fillToTable();

            //Đẩy Data từ Model --> Table
//                    tblList.setModel(model);
            New();
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            if (this.checkAdmin) {
                btnDelete.setEnabled(false);
            }

            btnPrint.setEnabled(false);
        }
//            String inputFind = JOptionPane.showInputDialog("Nhập Mã NV cần tìm: ");
//            boolean isFind = false;
//            for (Orders x : danhSach) {
//                if (x.getMaSanPham().equalsIgnoreCase(inputFind)) {
//                    index = danhSach.indexOf(x);
//                    showDetail(index);
//                    isFind = true;
//                    break;
//                }
//            }
//        //Find();
//        if (inputFind != null) {
//            if (!isFind){
//                   JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên có Mã: " + inputFind);
//                   return;
//            }
//            else {
//                JOptionPane.showMessageDialog(this, "Tìm thấy có nhân viên có Mã: " + inputFind);
//                int checkDelete = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa nhân viên này không?",
//                    "Thông báo", JOptionPane.YES_NO_OPTION);// == JOptionPane.YES_OPTION;
//                if (checkDelete == JOptionPane.YES_OPTION) {
//                    danhSach.remove(index);
//                    model.setRowCount(0);
//                    //Đẩy Data từ List --> model
//                    fillToTable();
//        
//                    //Đẩy Data từ Model --> Table
////                    tblList.setModel(model);
//                    New();
//                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
//                }
//                
//            }
//        }
    }

    public void sortGia() {
        Comparator<Orders> comp = new Comparator<Orders>() {
            public int compare(Orders s1, Orders s2) {
                if (s1.gia > s2.gia) {
                    return 1;
                }
                if (s1.gia < s2.gia) {
                    return -1;
                }
                return 0;  //s1.diemTB = s2.diemTB

            }
        };
        if (rdbAZ.isSelected() == true) {
            Collections.sort(danhSach, comp);            //tăng dần
        }
        if (rdbZA.isSelected() == true) {
            Collections.sort(danhSach, comp.reversed()); //giảm dần
        }
    }

    public void sortSoLuong() {
        Comparator<Orders> comp = new Comparator<Orders>() {
            public int compare(Orders s1, Orders s2) {
                if (s1.soLuong > s2.soLuong) {
                    return 1;
                }
                if (s1.soLuong < s2.soLuong) {
                    return -1;
                }
                return 0;  //s1.diemTB = s2.diemTB

            }
        };
        if (rdbAZ.isSelected() == true) {
            Collections.sort(danhSach, comp);            //tăng dần
        }
        if (rdbZA.isSelected() == true) {
            Collections.sort(danhSach, comp.reversed()); //giảm dần
        }
    }

    public void sortThanhTien() {
        Comparator<Orders> comp = new Comparator<Orders>() {
            public int compare(Orders s1, Orders s2) {
                if (s1.thanhTien > s2.thanhTien) {
                    return 1;
                }
                if (s1.thanhTien < s2.thanhTien) {
                    return -1;
                }
                return 0;  //s1.diemTB = s2.diemTB
            }
        };
        if (rdbAZ.isSelected() == true) {
            Collections.sort(danhSach, comp);            //tăng dần
        }
        if (rdbZA.isSelected() == true) {
            Collections.sort(danhSach, comp.reversed()); //giảm dần
        }
    }

    public void sortTenThuNgan() {
        Comparator<Orders> comp = new Comparator<Orders>() {
            public int compare(Orders s1, Orders s2) {
                return s1.tenThuNgan.compareTo(s2.tenThuNgan);
            }
        };
        if (rdbAZ.isSelected() == true) {
            Collections.sort(danhSach, comp);             //tăng dần
        }
        if (rdbZA.isSelected() == true) {
            Collections.sort(danhSach, comp.reversed());  //giảm dần
        }
    }

    public void sortMaSanPham() {
        Comparator<Orders> comp = new Comparator<Orders>() {
            public int compare(Orders s1, Orders s2) {
                return s1.maSanPham.compareTo(s2.maSanPham);
            }
        };
        if (rdbAZ.isSelected() == true) {
            Collections.sort(danhSach, comp);             //tăng dần
        }
        if (rdbZA.isSelected() == true) {
            Collections.sort(danhSach, comp.reversed());  //giảm dần
        }
    }

    public void eventSort() {
//        Tên thu ngân
//Mã sản phẩm
//Giá
//Số lượng
//Thành tiền
        int index = cboSort.getSelectedIndex();
        if (index == 0) {
            sortTenThuNgan();
        } else if (index == 1) {
            sortMaSanPham();
        } else if (index == 2) {
            sortGia();
        } else if (index == 3) {
            sortSoLuong();
        } else {
            sortThanhTien();
        }
    }

    public void New() {
        try {
            txtTenTN.setText("");
            txtTenSP.setText("");
            txtMaSp.setText("");
            txtGia.setText("");
            txtSoLuong.setText("");
            txtNgayOrder.setText("");
            txtThanhTien.setText("");
            txtRecord.setText("Records: ");
            index = -1;
        } catch (Exception e) {
        }
        //showDetail(index);
    }

    //BẮT LỖI
//    public void validate(){
//        
//    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (checkValidate()) {
            danhSach.add(new Orders(txtTenTN.getText(), txtTenSP.getText(), txtMaSp.getText(), Float.parseFloat(txtGia.getText()), Integer.parseInt(txtSoLuong.getText()), txtNgayOrder.getText(), thanhTien));
            model.setRowCount(0);
            fillToTable();
            JOptionPane.showMessageDialog(this, "Lưu thành công!");
            showDetail(danhSach.size()-1);
            saveFile();
        } else {
            JOptionPane.showMessageDialog(this, error);
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        //HideErrorThongTinNull();
        int index = tblList.getSelectedRow();
        Delete(index);
        saveFile();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        //HideErrorThongTinNull();
        Find();
    }//GEN-LAST:event_btnFindActionPerformed

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        HideErrorThongTinNull();
        int index = tblList.getSelectedRow();
        this.index_print = index;
        showDetail(index);
        blockButtonAnchor(index);
        if (this.checkAdmin) {
            btnDelete.setEnabled(true);
            btnSave.setEnabled(true);
        }
        btnPrint.setEnabled(true);
        btnFirst.setEnabled(true);
        btnPrevious.setEnabled(true);
        btnLast.setEnabled(true);
        btnNext.setEnabled(true);
    }//GEN-LAST:event_tblListMouseClicked

    private void jlbCloseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jlbCloseFocusGained
        //jplLose.setBackground(Color.red);
        System.exit(0);
    }//GEN-LAST:event_jlbCloseFocusGained

    private void jlbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCloseMouseClicked
//        int ketQua = JOptionPane.showConfirmDialog(this, 
//                "Trước khi thoát bạn có muốn lưu file không?", "Thông báo", JOptionPane.YES_OPTION);
//        if (ketQua == JOptionPane.YES_OPTION) {
//            close();
//        }
        System.exit(0);
    }//GEN-LAST:event_jlbCloseMouseClicked

    private void jplLoseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplLoseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jplLoseMouseClicked

    private void jplLoseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplLoseMouseEntered
        jplLose.setBackground(Color.red);
        //jlbClose.setForeground(Color.white);
    }//GEN-LAST:event_jplLoseMouseEntered

    private void jplLoseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplLoseMouseExited
        // TODO add your handling code here:
        jplLose.setBackground(new Color(59, 59, 59));
    }//GEN-LAST:event_jplLoseMouseExited

    private void jlbCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCloseMouseEntered
        // TODO add your handling code here:
        jplLose.setBackground(Color.red);
    }//GEN-LAST:event_jlbCloseMouseEntered

    private void jlbCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCloseMouseExited
        // TODO add your handling code here:
        jplLose.setBackground(new Color(59, 59, 59));
    }//GEN-LAST:event_jlbCloseMouseExited

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        //jplSave.setBackground(new Color(0,102,102));
        btnSave.setBackground(new Color(0, 102, 102));
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited
        //jplSave.setBackground(new Color(51,51,51));
        btnSave.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btnSaveMouseExited

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        //jplDelete.setBackground(new Color(0,102,102));
        btnDelete.setBackground(new Color(0, 102, 102));
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        //jplDelete.setBackground(new Color(51,51,51));
        btnDelete.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btnDeleteMouseExited

    private void btnFindMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFindMouseEntered
        //jplFind.setBackground(new Color(0,102,102));
        btnFind.setBackground(new Color(0, 102, 102));
    }//GEN-LAST:event_btnFindMouseEntered

    private void btnFindMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFindMouseExited
        //jplFind.setBackground(new Color(51,51,51));
        btnFind.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btnFindMouseExited

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        //HideErrorThongTinNull();
        New();
        txtTenTN.requestFocus();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnNewMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewMouseExited
        //jplNew.setBackground(new Color(51,51,51));
        btnNew.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btnNewMouseExited

    private void btnNewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewMouseEntered
        ///jplNew.setBackground(new Color(0,102,102));
        btnNew.setBackground(new Color(0, 102, 102));
    }//GEN-LAST:event_btnNewMouseEntered

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        //HideErrorThongTinNull();
        //if (txtTenTN.getText().equals("") || txtMaSp.getText().equals("") || txtTenSP.getText().equals("")
        //|| txtGia.getText().equals("") || txtSoLuong.getText().equals("") || txtNgayOrder.getText().equals("")) {
        Bill_Form bill = new Bill_Form(txtTenTN.getText(), txtMaSp.getText(), txtTenSP.getText(), txtGia.getText(), txtSoLuong.getText(), txtNgayOrder.getText());
        bill.setVisible(true);
        //}
        //Bill_Form bill = new Bill_Form(txtTenTN.getText(), txtMaSp.getText(), txtTenSP.getText(), txtGia.getText(), txtSoLuong.getText(), txtNgayOrder.getText());
        //int index = tblList.getSelectedRow();
        //
//        txtTenTN.setText(danhSach.get(index).getTenThuNgan());
//        txtTenSP.setText(danhSach.get(index).getTenSanPham());
//        txtMaSp.setText(danhSach.get(index).getMaSanPham());
//        txtGia.setText(danhSach.get(index).getGia() + "");
//        txtSoLuong.setText(danhSach.get(index).getSoLuong() + "");
//        txtNgayOrder.setText(danhSach.get(index).getNgayOrder());
//        txtThanhTien.setText(danhSach.get(index).getThanhTien() + "");
        //bill.setVisible(true);
        //bill bil = new bill(index_print);
        //bil.setVisible(true);
        //bill.setVisible(true);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnPrintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseExited
        //jplPrint.setBackground(new Color(51,51,51));
        btnPrint.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btnPrintMouseExited

    private void btnPrintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseEntered
        //jplPrint.setBackground(new Color(0,102,102));
        btnPrint.setBackground(new Color(0, 102, 102));
        //btnFind.setBorderPainted(false);
    }//GEN-LAST:event_btnPrintMouseEntered

    private void btnNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseClicked
        Next();
    }//GEN-LAST:event_btnNextMouseClicked

    private void btnFirstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstMouseClicked
        First();
    }//GEN-LAST:event_btnFirstMouseClicked

    private void btnPreviousMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreviousMouseClicked
        Previous();
    }//GEN-LAST:event_btnPreviousMouseClicked

    private void btnLastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastMouseClicked
        Last();
    }//GEN-LAST:event_btnLastMouseClicked

    private void cboSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSortActionPerformed

    private void btnSortMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSortMouseEntered
        // TODO add your handling code here:
        btnSort.setBackground(new Color(0, 102, 102));
    }//GEN-LAST:event_btnSortMouseEntered

    private void btnSortMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSortMouseExited
        // TODO add your handling code here:
        btnSort.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btnSortMouseExited

    private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortActionPerformed
        //HideErrorThongTinNull();
        eventSort();
        fillToTable();
    }//GEN-LAST:event_btnSortActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
        //model.setRowCount(0);
        //System.out.println(tblList.getRowCount());
        //HideErrorThongTinNull();
        if (tblList.getRowCount() < 1) {
            //System.out.println(tblList.getRowCount());
            ///if(tblList.getRowCount()<2)
            //model.setRowCount(0);
            //fillToTable();
            New();
            loadData();
        } else {
            model.setRowCount(0);
            fillToTable();
            New();
            //loadData();
        }
        btnDelete.setEnabled(false);
        btnPrint.setEnabled(false);
        btnSort.setEnabled(true);
        btnLast.setEnabled(true);
        btnFirst.setEnabled(true);
        //btnOpen.setEnabled(false);
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnOpenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpenMouseExited
        // TODO add your handling code here:
        btnOpen.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btnOpenMouseExited

    private void btnOpenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpenMouseEntered
        // TODO add your handling code here:
        btnOpen.setBackground(new Color(0, 102, 102));
    }//GEN-LAST:event_btnOpenMouseEntered

    private void txtTenTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenTNActionPerformed

    private void txtTenTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenTNMouseClicked
        // TODO add your handling code here:
        //HideErrorThongTinNull();
        txtErrorTenThuNgan.setVisible(false);
    }//GEN-LAST:event_txtTenTNMouseClicked

    private void txtMaSpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaSpMouseClicked
        // TODO add your handling code here:
        //HideErrorThongTinNull();
        txtErrorMaSP.setVisible(false);
    }//GEN-LAST:event_txtMaSpMouseClicked

    private void txtSoLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSoLuongMouseClicked
        // TODO add your handling code here:
        //HideErrorThongTinNull();
        txtErrorSoLuong.setVisible(false);
    }//GEN-LAST:event_txtSoLuongMouseClicked

    private void txtGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiaMouseClicked
        // TODO add your handling code here:
        //HideErrorThongTinNull();
        txtErrorGia.setVisible(false);
    }//GEN-LAST:event_txtGiaMouseClicked

    private void txtNgayOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayOrderMouseClicked
        // TODO add your handling code here:
        //HideErrorThongTinNull();
        txtErrorNgayOrder.setVisible(false);
    }//GEN-LAST:event_txtNgayOrderMouseClicked

    private void txtTenSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenSPMouseClicked
        // TODO add your handling code here:
        //HideErrorThongTinNull();
        txtErrorTenSP.setVisible(false);
    }//GEN-LAST:event_txtTenSPMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrdersManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrdersManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrdersManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrdersManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrdersManage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFind;
    private javax.swing.JLabel btnFirst;
    private javax.swing.JLabel btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JLabel btnNext;
    private javax.swing.JButton btnOpen;
    private javax.swing.JLabel btnPrevious;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSort;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboSort;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel jlbClose;
    private javax.swing.JPanel jplLose;
    private javax.swing.JLabel lblChuChay;
    private javax.swing.JLabel lblTime;
    private javax.swing.JRadioButton rdbAZ;
    private javax.swing.JRadioButton rdbZA;
    private javax.swing.JTable tblList;
    private javax.swing.JLabel txtErrorGia;
    private javax.swing.JLabel txtErrorMaSP;
    private javax.swing.JLabel txtErrorNgayOrder;
    private javax.swing.JLabel txtErrorSoLuong;
    private javax.swing.JLabel txtErrorTenSP;
    private javax.swing.JLabel txtErrorTenThuNgan;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaSp;
    private javax.swing.JTextField txtNgayOrder;
    private javax.swing.JLabel txtRecord;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenTN;
    private javax.swing.JTextField txtThanhTien;
    // End of variables declaration//GEN-END:variables

}
