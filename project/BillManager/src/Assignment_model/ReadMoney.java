/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment_model;

import java.util.Scanner;

/**
 *
 * @author dangt
 */
public class ReadMoney {

    String num = "400";

    public String doc_so(char num) {
        if (num == '1') {
            return "Mot ";
        }
        if (num == '2') {
            return "Hai ";
        }
        if (num == '3') {
            return "Ba ";
        }
        if (num == '4') {
            return "Bon ";
        }
        if (num == '5') {
            return "Nam ";
        }
        if (num == '6') {
            return "Sau ";
        }
        if (num == '7') {
            return "Bay ";
        }
        if (num == '8') {
            return "Tam ";
        }
        if (num == '9') {
            return "Chin ";
        }
        return "Khong ";
    }

    public String doc_hang_chuc(String s) { //06  | 24  |009 090090
        if (s.length()>2) {
            s = s.substring(0,2);
        }
        String number_temp = "";
        if (s.charAt(0) == '1') {
            //System.out.print("Muoi ");
            number_temp = number_temp + "Muoi ";
            if (s.charAt(1) != '0') {
                if (s.charAt(1) == '5') {
                    number_temp = number_temp + "Lam ";
                } else if (s.charAt(1) == '1') {
                    number_temp = number_temp + "Mot ";
                } else {
                    number_temp = number_temp + doc_so(s.charAt(1));
                }
                // doc_so(s.charAt(1));
            }
        } else {
            if (s.charAt(0) == '0') {
                //System.out.print("Linh ");
                number_temp = doc_so(s.charAt(1));
            } else {
                number_temp = doc_so(s.charAt(0));
                //System.out.print("Muoi ");
                number_temp = number_temp + "Muoi ";
                if (s.charAt(1) != '0') {
                    if (s.charAt(1) == '5') {
                        number_temp = number_temp + "Lam ";
                    } //System.out.print("Lam ");
                    else if (s.charAt(1) == '1') {
                        number_temp = number_temp + "Mot ";
                    } else {
                        number_temp = number_temp + doc_so(s.charAt(1));
                    }
                }
            }
        }
        return number_temp;
    }

    public String doc_hang_tram(String s) { //600  || 910  |009 090090  |910
        if (s.length()>3) {
            s = s.substring(0,3);
        }
        String number_temp = "";
        String number = "";
            if(s.equals("000"))
                return "";
            if (s.substring(1, 3).equals("00")) {
                number_temp = doc_so(s.charAt(0)) + "Tram ";
                return number_temp;
            }
        
        number_temp = "";
        number = "";
        number_temp = doc_so(s.charAt(0)) + "Tram ";
        //System.out.print("Tram ");
        if (s.charAt(1) == '0') {
            number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
            //System.out.print("Linh ");
            //doc_so(s.charAt(s.length() - 1));
        } else {
            if (s.substring(1)== "10") 
                number_temp = number_temp + "Linh Mưoi";
            else {
                number = number + s.substring(1);
                number_temp = number_temp + doc_hang_chuc(number);
            //doc_hang_chuc(number_temp);
            }
            
        }
        return number_temp;
    }

    //
    public String read_money(String s) {
        //String s = document.querySelector('#number').value;
        String number_temp = "";
        String number = "";
        if (s.length() == 1) {
            return doc_so(s.charAt(0));
            //System.out.print("nghin dong!");
        }
        if (s.length() == 2) {
            return doc_hang_chuc(s);
            //System.out.print("nghin dong!");
        }
        if (s.length() == 3) {
            //System.out.print("nghin dong!");
            return doc_hang_tram(s);

        }
        if (s.length() == 4) {  //400
            if (s.substring(1).equals("000")) {
                number_temp = doc_so(s.charAt(0)) + "Nghin ";
                return number_temp;
            }
//            if (s.substring(1)=="00")
//                return doc_so(s.charAt(0)) + "nghìn";
//            if (s.substring(1)=="000")
//                return doc_so(s.charAt(0)) + "trăm";
            return doc_so(s.charAt(0)) + "Nghin " + doc_hang_tram(s);
//                    //+ doc_so(s.charAt(1)) + "Trăm ";
//            number = s.substring(2);
//            if (number.charAt(0) == '0') {
//                number_temp = number_temp + "Linh ";
//            }
//            number_temp = number_temp + doc_hang_chuc(number);
            //System.out.print("VND!");
        }
        if (s.length() == 5) {      //98 765 
            if (s.substring(1).equals("0000")) {
                number_temp = doc_hang_chuc(s) + "Nghin ";
                return number_temp;
            }
            number_temp = "";
            number = "";
            number_temp = doc_hang_chuc(s) + "Nghin ";
            //System.out.print("Trieu ");
            s = s.substring(2);
            // doc_so(s.charAt(0));
            number_temp = number_temp + doc_hang_tram(s);
            return number_temp;
            //System.out.print("VND!");
        }
        if (s.length() == 6) { //600 910  | 678 567  | 691 000
            if (s.substring(1).equals("00000")) {
                return doc_hang_tram(s) + "Nghin ";

            }
            return doc_hang_tram(s) + "Nghin " + doc_hang_tram(s.substring(3)) ;
//            //System.out.print("Tram ");
//            if (s.charAt(1) == '0') {
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(2));
//                //System.out.print("Linh ");
//                //doc_so(s.charAt(2));
//            } else {
//                number = s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//            }
//            //System.out.print("Trieu ");
//            number_temp = number_temp + "Nghìn ";
//
//            number = s.substring(1);
//            s = number.substring(2); //s = 505
//            number_temp = number_temp + doc_so(s.charAt(0)) + "Trăm ";
//            // System.out.print("Tram ");
//
//            if (s.charAt(1) == '0') {
//                //System.out.print("Linh ");
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
//                // doc_so(s.charAt(s.length - 1));
//            } else {
//                number = s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//            }
//            //System.out.print("VND!");
        }
        if (s.length() == 7) {  //5 070 090
            if (s.substring(1).equals("000000")) {
                return doc_hang_tram(s) + "Trieu ";

            }            
            return doc_so(s.charAt(0)) + "Trieu " + doc_hang_tram(s.substring(1)) + 
                    "Nghin " + doc_hang_tram(s.substring(4)) ; //1.  |000.001
            
//            s = s.substring(1); //s = 000.001   1.004.561
//            //read_money(s);
//            number_temp = number_temp + doc_so(s.charAt(0)) + "Trăm "; //một triệu không trăm
//            // System.out.print("Tram ");
//
//            if (s.charAt(1) == '0') {
//                //System.out.print("Linh ");
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(2));
//                // doc_so(s.charAt(s.length - 1));
//            } else {
//                number = s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//            }
//            // document.write(s); s = 010
//            s = s.substring(3);
//            //document.write(s);
//            number_temp = number_temp + "Nghìn " + doc_so(s.charAt(0)) + "Trăm ";
//            // number = s.substring(1);
//            // if (number.charAt(0) == '0' || number.charAt(0) == '1') {
//            //     if (s.charAt(0) == '0' && s.charAt(1) != 0) {
//            //         number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length - 1));
//            //     }
//            // }
//            if (s.charAt(1) == '0') {
//                //System.out.print("Linh ");
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
//                // doc_so(s.charAt(s.length - 1));
//            } else {
//                number = s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//            }
        }
        if (s.length() == 8) {   //80 060 008
            if (s.substring(1).equals("0000000")) {
                return doc_hang_chuc(s) + "Trieu ";
            }
            return doc_hang_chuc(s) + "Trieu " + doc_hang_tram(s.substring(2)) + "Nghin " + doc_hang_tram(s.substring(5));
            //s = s.substring(2);
            //number_temp = number_temp ;
             //number_temp;
//            if (s.charAt(1) == '0') {
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
//            } else {
//                number = s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//            }
//            s = s.substring(3);
//            number_temp = number_temp + "Nghìn " + doc_so(s.charAt(0)) + "Trăm ";
//            if (s.charAt(1) == '0') {
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
//            } else {
//                number = s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//            }
        }
        if (s.length() == 9) {   //900 340 056
            if (s.substring(1).equals("00000000")) {
                return doc_hang_tram(s) + "Trieu ";
            }
            return doc_hang_tram(s) + "Trieu " + doc_hang_tram(s.substring(3)) + "Nghin " + doc_hang_tram(s.substring(6));
            //s = s.substring(1);
//            s = s.substring(2);
//            number_temp = number_temp + doc_so(s.charAt(0)) + "Trăm ";
//            if (s.charAt(1) == '0') {
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
//            } else {
//                number = s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//            }
//            s = s.substring(3);
//            number_temp = number_temp + "Nghìn " + doc_so(s.charAt(0)) + "Trăm ";
//            if (s.charAt(1) == '0') {
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
//            } else {
//                number = s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//            }
        }
        if (s.length() == 10) {  //9 009 090 090
            if (s.substring(1).equals("000000000")) {
                return doc_hang_tram(s) + "Ti ";
            }
            return doc_so(s.charAt(0)) + "Ti " + doc_hang_tram(s.substring(1)) + "Trieu " 
                    + doc_hang_tram(s.substring(4) + "Nghin " + doc_hang_tram(s.substring(7)) ) ; //1.  |2  |89.585.760

//            s = s.substring(1);
//            number_temp = number_temp + doc_so(s.charAt(0)) + "Trăm ";
//            s = s.substring(1);
//            number_temp = number_temp + doc_hang_chuc(s) + "Triệu ";
//            s = s.substring(2);
//            number_temp = number_temp + doc_so(s.charAt(0)) + "Trăm ";
//            if (s.charAt(1) == '0') {
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
//            } else {
//                number = s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//            }
//            s = s.substring(3);
//            number_temp = number_temp + "Nghìn " + doc_so(s.charAt(0)) + "Trăm ";
//            if (s.charAt(1) == '0') {
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
//            } else {
//                number = s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//            }
        }
        if (s.length() == 11) {  //90 060 908 450
            if (s.substring(1).equals("0000000000")) {
                return doc_hang_chuc(s) + "Ti ";
            }
            return doc_hang_chuc(s) + "Ti " + doc_hang_tram(s.substring(2)) + "Trieu " 
                    + doc_hang_tram(s.substring(6)) + "Nghin " + doc_hang_tram(s.substring(8)) ;
            
//            s = s.substring(2);
            //number_temp = number_temp + doc_so(s.charAt(0)) + "Ty "; //1.  |2  |89.585.760
            //s = s.substring(1);
//            number_temp = number_temp + doc_so(s.charAt(0)) + "Trăm ";
//            s = s.substring(1);
//            number_temp = number_temp + doc_hang_chuc(s) + "Triệu ";
//            s = s.substring(2);
//            number_temp = number_temp + doc_so(s.charAt(0)) + "Trăm ";
//            if (s.charAt(1) == '0') {
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
//            } else {
//                number = s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//            }
//            s = s.substring(3);
//            number_temp = number_temp + "Nghìn " + doc_so(s.charAt(0)) + "Trăm ";
//            if (s.charAt(1) == '0') {
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
//            } else {
//                number = s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//            }
        }
        if (s.length() == 12) {     //900 567 034 120
            if (s.substring(1).equals("00000000000")) {
                return doc_hang_tram(s) + "Ti " ;
            }
            return doc_hang_tram(s) + "Ti " + doc_hang_tram(s.substring(3)) + "Trieu " 
                        + doc_hang_tram(s.substring(6)) + "Nghin " + doc_hang_tram(s.substring(9));
            //System.out.print("Tram ");
//            if (s.charAt(1) == '0') {
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
//                //System.out.print("Linh ");
//                //doc_so(s.charAt(s.length() - 1));
//            } else {
//                number = number + s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//                //doc_hang_chuc(number_temp);
//            }
//            number_temp = number_temp + "Tỷ "; //891.289.585.760
//            s = s.substring(3);
//            number_temp = number_temp + doc_so(s.charAt(0)) + "Trăm ";
//            s = s.substring(1);
//            number_temp = number_temp + doc_hang_chuc(s) + "Triệu ";
//            s = s.substring(2);
//            number_temp = number_temp + doc_so(s.charAt(0)) + "Trăm ";
//            if (s.charAt(1) == '0') {
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
//            } else {
//                number = s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//            }
//            s = s.substring(3);
//            number_temp = number_temp + "Nghìn " + doc_so(s.charAt(0)) + "Trăm ";
//            if (s.charAt(1) == '0') {
//                number_temp = number_temp + "Linh " + doc_so(s.charAt(s.length() - 1));
//            } else {
//                number = s.substring(1);
//                number_temp = number_temp + doc_hang_chuc(number);
//            }
        } else {
            number_temp = "Khong the doc!";
            //return "Vượt quá giới hạn đọc số!"
        }
        //document.querySelector('#doc_so').innerHTML = number_temp;
        return number_temp;
    }

    public static void main(String[] args) {
        String day = "18-06-2022";
        CalendarFields cf = new CalendarFields();
        System.out.println(cf.now());
        //System.out.println(cf.xuLy(day, cf.now()));
        
    }
}
