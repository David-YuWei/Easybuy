package com.easybuy.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * date tool
 * @author weiyu
 *
 */
public class DateUtils {
	 
    /** 
     * get the date of today
     * format：xxxx-yy-zz (eg: 2007-12-05) 
     * @return String 
     * @author pure 
     */  
    public static String getToday() {  
		Calendar localTime = Calendar.getInstance();
		String strY = null;  
        String strZ = null;  
        int x = localTime.get(Calendar.YEAR);  
        int y = localTime.get(Calendar.MONTH) + 1;  
        int z = localTime.get(Calendar.DATE);  
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);  
        strZ = z >= 10 ? String.valueOf(z) : ("0" + z);  
        return x + "-" + strY + "-" + strZ;  
    }
    
    /** 
      * get the first day of the current month
      * format：xxxx-yy-zz (eg: 2007-12-01) 
      * @return String 
      * @author pure 
      */  
    public static String getMonthStart() {  
    	Calendar localTime = Calendar.getInstance();
    	String strY = null;  
        int x = localTime.get(Calendar.YEAR);  
        int y = localTime.get(Calendar.MONTH) + 1;  
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);return x + "-" + strY + "-01";  
    }
    
    /** 
     * get the date before many days
     * format: xxxx-yy-zz  
     * @return String 
     * @author pure 
     */  
   public static String getBeforeDate(int days) {  
	   	long millis = ((long)days)*86400000;
		long  thatDay = new Date().getTime() - millis;
	   	Date date = new Date(thatDay);
	   	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   	return sdf.format(date); 
   }
   
   /**
    * get the date after many days
    * format: xxxx-yy-zz
    * @param days
    * @return
    */
   public static String getAfterDate(int days){
	   SimpleDateFormat sdf=new SimpleDateFormat( "yyyy-MM-dd "); 
	   Calendar cla=Calendar.getInstance(); 
	   cla.add(Calendar.DAY_OF_YEAR,days);
	   return sdf.format(cla.getTime());
   }
    
    /** 
      * get the end day of the current month
      * format: xxxx-yy-zz (eg: 2007-12-31) 
      * @return String 
      * @author pure 
      **/  
    public static String getMonthEnd() {  
    	Calendar localTime = Calendar.getInstance();
    	String strY = null;  
        String strZ = null;  
        boolean leap = false;  
        int x = localTime.get(Calendar.YEAR);  
        int y = localTime.get(Calendar.MONTH) + 1;  
        if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {  
            strZ = "31";  
        }  
        if (y == 4 || y == 6 || y == 9 || y == 11) {  
            strZ = "30";  
        }  
        if (y == 2) {  
            leap = leapYear(x);  
            if (leap) {  
                strZ = "29";  
            }else {  
                strZ = "28";  
            }  
        }  
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);  
        return x + "-" + strY + "-" + strZ;  
    }
    
    /** 
     * check for leap year 
     * @param year 
     * @return true or false 
     * @author pure 
     */  
   public static boolean leapYear(int year) {  
       boolean leap;  
       if (year % 4 == 0) {  
           if (year % 100 == 0) {  
               if (year % 400 == 0) leap = true;  
                   else leap = false;  
               }  
           else leap = true;  
       }  
       else leap = false;  
       return leap;  
   }  
}  
