
package Assignment_model;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author dangt
 */
public class CalendarFields implements DateValidator {
    
    private String dateFormat;
    
    public CalendarFields(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    
    public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    public void xuatNgay() {
        // Tạo một đối tượng Calendar (Lịch) mặc định.
		// Với time zone (múi giờ) và locale mặc định.
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);

		// Trả về giá trị từ 0 - 11
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		int millis = c.get(Calendar.MILLISECOND);

		System.out.println("Year: " + year);
		System.out.println("Month: " + (month + 1));
		System.out.println("Day: " + day);
		System.out.println("Hour: " + hour);
		System.out.println("Minute: " + minute);
		System.out.println("Second: " + second);
		System.out.println("Minute: " + minute);
		System.out.println("Milli Second: " + millis);
    }
    
    public String dinhdangNgay(String day_month_year) {
        String day = day_month_year.substring(0,2);
            String month = day_month_year.substring(3,5);
            String year = day_month_year.substring(6);
        return day+month+year;
    }
    
    public String now() {
        Calendar c = Calendar.getInstance();
        boolean ketQua = true;
        String year_now = String.valueOf(c.get(Calendar.YEAR)) ;
        String month_now = String.valueOf(c.get(Calendar.MONTH)+1);
        String day_now = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        if (month_now.length()==1)
            month_now ="0" + month_now;
        if (day_now.length()==1)
            day_now ="0" + day_now;
        String day = day_now +"-" + month_now +"-" +year_now;
        return day;
    }
    
    public void check_day(String day_month_year) {
        String year_temp, month_temp, day_temp;
	Calendar c = Calendar.getInstance();
        boolean ketQua = true;
        String year_now = String.valueOf(c.get(Calendar.YEAR)) ;
        String month_now = String.valueOf(c.get(Calendar.MONTH)+1);
        String day_now = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        if (month_now.length()==1)
            month_now ="0" + month_now;
        if (day_now.length()==1)
            day_now ="0" + day_now;
        //System.out.println(day_now+month_now+year_now);
    }
    
    public boolean xuLy(String day1, String day2){
        boolean ketQua = true;
        day1= dinhdangNgay(day1);
        day2=dinhdangNgay(day2);
        //System.out.println(day1.substring(4)+" "+day1.substring(2,4)+" "+day1.substring(0, 2));
        //System.out.println(day2.substring(4)+" "+day2.substring(2,4)+" "+day2.substring(0, 2));
        if( Integer.parseInt(day1.substring(4)) > Integer.parseInt(day2.substring(4)) )
            //so sanh nam
            return false;
        else {
            if( Integer.parseInt(day1.substring(4)) < Integer.parseInt(day2.substring(4)) )
            //so sanh nam
                return true;
            else {
                if ( Integer.parseInt( day1.substring(2,4)) >  Integer.parseInt( day2.substring(2,4) ) )
                //so sanh thang
                return false;
                else {
                    if ( Integer.parseInt( day1.substring(2,4)) <  Integer.parseInt( day2.substring(2,4) ) )
                    //so sanh thang
                    return true;
                    else {
                        if ( Integer.parseInt(day1.substring(0, 2)) > Integer.parseInt(day2.substring(0, 2)))
                            //so sanh ngay
                            return false;
                        else {
                            if ( Integer.parseInt(day1.substring(0, 2)) < Integer.parseInt(day2.substring(0, 2)))
                            //so sanh ngay
                            return true;
                        }
                    }
                }
            } 
        }
        return ketQua;
    }
    

    
    public CalendarFields() {
    }
    
    
    
    public static void main(String[] args) {
	String day = "11-06-2023";
        //CalendarFields cf = new CalendarFields();
        //cal.xuatNgay();
//        cal.check_day(day);
//        cal.xuatNgay();\
//        System.out.println(cf.now());
//        System.out.println(cf.isValid(day));
        //System.out.println(cal.dinhdangNgay("11/12/2003"));
        //System.out.println(xuLy("18-06-2022", Validator.now()));
        CalendarFields validator = new CalendarFields("dd-MM-yyyy");
        System.out.println(validator.isValid(day));
        System.out.println(validator.xuLy(day, validator.now()));
        //assertTrue(validator.isValid("02/28/2019"));        
        //assertFalse(validator.isValid("02/30/2019"));
        //System.out.println(validator.isValid("28-02-2019"));
    }
}
