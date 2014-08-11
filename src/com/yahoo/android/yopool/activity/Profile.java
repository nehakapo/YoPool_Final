package com.yahoo.android.yopool.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Profile {
	private static String pic;
	private static String name;
	private static String phno;
	private static String homeAddr;
	private static String workAddr;
	private static String toStartStr;
	private static String toEndStr;
	private static String fromStartStr;
	private static String fromEndStr;
	private static boolean isDriver;
	private static String carModel;
	private static String licenseNo;
	
	public static String getPic() {
		return pic;
	}
	public static void setPic(String pic) {
		Profile.pic = pic;
	}
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		Profile.name = name;
	}
	public static String getPhno() {
		return phno;
	}
	public static void setPhno(String phno) {
		Profile.phno = phno;
	}
	public static String getHomeAddr() {
		return homeAddr;
	}
	public static void setHomeAddr(String homeAddr) {
		Profile.homeAddr = homeAddr;
	}
	public static String getWorkAddr() {
		return workAddr;
	}
	public static void setWorkAddr(String workAddr) {
		Profile.workAddr = workAddr;
	}
	
	public static String getToStartStr() {
        return toStartStr;
    }
    public static void setToStartStr(String toStartStr) {
        Profile.toStartStr = toStartStr;
    }
    public static String getToEndStr() {
        return toEndStr;
    }
    public static void setToEndStr(String toEndStr) {
        Profile.toEndStr = toEndStr;
    }
    public static String getFromStartStr() {
        return fromStartStr;
    }
    public static void setFromStartStr(String fromStartStr) {
        Profile.fromStartStr = fromStartStr;
    }
    public static String getFromEndStr() {
        return fromEndStr;
    }
    public static void setFromEndStr(String fromEndStr) {
        Profile.fromEndStr = fromEndStr;
    }
    public static boolean isDriver() {
		return isDriver;
	}
	public static void setDriver(boolean isDriver) {
		Profile.isDriver = isDriver;
	}
	public static String getCarModel() {
		return carModel;
	}
	public static void setCarModel(String carModel) {
		Profile.carModel = carModel;
	}
	public static String getLicenseNo() {
		return licenseNo;
	}
	public static void setLicenseNo(String licenseNo) {
		Profile.licenseNo = licenseNo;
	}
	
	// Helper functions to get hour and min from time strings
	
	public static int getHour(String timeString) {
	    int hour = 0;
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("K:mm a");
            Date dateObj = dateFormatter.parse(timeString);
            String outputTime = new SimpleDateFormat("H:mm").format(dateObj);
            String[] timeParts = outputTime.split(":");
            hour = Integer.parseInt(timeParts[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hour;        
	}
	
	public static int getMin(String timeString) {
	    int min = 0;
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("K:mm a");
            Date dateObj = dateFormatter.parse(timeString);
            String outputTime = new SimpleDateFormat("H:mm").format(dateObj);
            String[] timeParts = outputTime.split(":");
            min = Integer.parseInt(timeParts[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return min;        
	}
	
	

}
