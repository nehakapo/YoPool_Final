package com.example.yopool.data;

import java.util.ArrayList;

public class AvailableDrivers {
	private static ArrayList<String> names = new ArrayList<String>();
	private static ArrayList<String> pickup_times = new ArrayList<String>();
	private static ArrayList<String> car_models = new ArrayList<String>();
	private static ArrayList<String> license_nos= new ArrayList<String>();
	private static ArrayList<String> pic_urls = new ArrayList<String>();
	private static ArrayList<String> phnos = new ArrayList<String>();
	
	public static void loadData() {
		names.add("Ramya Balaraman");
		names.add("Vidya Pissaye");
		names.add("Vidya Ravivarma");
		
		pickup_times.add("9.10 am");
		pickup_times.add("9.30 am");
		pickup_times.add("9.40 am");
		
		car_models.add("Mazda3");
		car_models.add("Honda Civic");
		car_models.add("Infiniti G37");
		
		license_nos.add("7ZFGH34");
		license_nos.add("4598H47");
		license_nos.add("B158ZGH");
		
		pic_urls.add("ramya_pic");
		pic_urls.add("vpissaye_pic");
		pic_urls.add("vidyarma_pic");
		
		phnos.add("4124827963");
		phnos.add("4802494877");
		phnos.add("9155438423");
		
	}

	public static ArrayList<String> getNames() {
		return names;
	}

	public static ArrayList<String> getPickup_times() {
		return pickup_times;
	}

	public static ArrayList<String> getCar_models() {
		return car_models;
	}

	public static ArrayList<String> getLicense_nos() {
		return license_nos;
	}
	
	public static ArrayList<String> getPic_urls() {
		return pic_urls;
	}

	public static ArrayList<String> getPhnos() {
		return phnos;
	}
	
}
