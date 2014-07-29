package com.example.yopool.data;

import android.widget.Toast;

import com.example.yopool.Profile;

public class DataPopulate {
	private String username;
	private String password;
	
	//Demo usernames
	private static String neha_username = "nkapoor@yahoo-inc.com";
	private static String vidyav_username = "vidyarma@yahoo-inc.com";
	private static String vidyap_username = "vpissaye@yahoo-inc.com";
	private static String ramya_username = "ramya201@yahoo-inc.com";
	
	private static String neha_pswd = "1234";
	private static String vidyav_pswd = "1234";
	private static String vidyap_pswd = "1234";
	private static String ramya_pswd = "1234";
		
	public DataPopulate(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public void populateData() {
		if (username.equals(neha_username) && password.equals(neha_pswd)) {
			Profile.setName("Neha Kapoor");
			Profile.setPhno("513-426-330");
			Profile.setPic("nkapoor_pic");
			Profile.setHomeAddr("718 Old San Francisco Road, Sunnyvale, CA 94086");
			Profile.setWorkAddr("Bldg A, Yahoo Sunnyvale");
			Profile.setToStart("08.30 am");
			Profile.setToEnd("09.30 am");
			Profile.setFromStart("05.30 pm");
			Profile.setFromEnd("06.30 pm");
			Profile.setDriver(false);
		} else if (username.equals(ramya_username) && password.equals(ramya_pswd)) {
			Profile.setName("Ramya Balaraman");
			Profile.setPhno("412-482-7963");
			Profile.setPic("ramya_pic");
			Profile.setHomeAddr("1035 Aster Ave, Sunnyvale, CA 94086");
			Profile.setWorkAddr("Bldg B, Yahoo Sunnyvale");
			Profile.setToStart("08.30 am");
			Profile.setToEnd("09.30 am");
			Profile.setFromStart("05.30 pm");
			Profile.setFromEnd("06.30 pm");
			Profile.setDriver(true);
			Profile.setCarModel("Mazda 3");
			Profile.setLicenseNo("7ZFGH34");
		} else if (username.equals(vidyav_username) && password.equals(vidyav_pswd)) {
			Profile.setName("Vidya Ravivarma");
			Profile.setPhno("915-543-8423");
			Profile.setPic("vidyarma_pic");
			Profile.setHomeAddr("718 Old San Francisco Road, Sunnyvale, CA 94086");
			Profile.setWorkAddr("Bldg F, Yahoo Sunnyvale");
			Profile.setToStart("08.30 am");
			Profile.setToEnd("09.30 am");
			Profile.setFromStart("05.30 pm");
			Profile.setFromEnd("06.30 pm");
			Profile.setDriver(true);
			Profile.setCarModel("Infiniti G37");
			Profile.setLicenseNo("B158ZGH");
		} else if (username.equals(vidyap_username) && password.equals(vidyap_pswd)) {
			Profile.setName("Vidya Pissaye");
			Profile.setPhno("480-249-4877");
			Profile.setPic("vpissaye_pic");
			Profile.setHomeAddr("1220 N Fair Oaks Ave, Sunnyvale, CA 94086");
			Profile.setWorkAddr("Bldg A, Yahoo Sunnyvale");
			Profile.setToStart("08.30 am");
			Profile.setToEnd("09.30 am");
			Profile.setFromStart("05.30 pm");
			Profile.setFromEnd("06.30 pm");
			Profile.setDriver(true);
			Profile.setCarModel("Honda Civic");
			Profile.setLicenseNo("4598H47");
		} 
	}
	
	
}
