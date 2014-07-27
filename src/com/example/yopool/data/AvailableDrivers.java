package com.example.yopool.data;

import java.util.ArrayList;

public class AvailableDrivers {
	private static ArrayList<String> names = new ArrayList<String>();
	private static ArrayList<String> pickup_times = new ArrayList<String>();
	private static ArrayList<String> car_models = new ArrayList<String>();
	private static ArrayList<String> license_nos= new ArrayList<String>();
	private static ArrayList<String> pic_urls = new ArrayList<String>();
	
	public static void loadData() {
		names.add("Ramya Balaraman");
		names.add("Neha Kapoor");
		
		pickup_times.add("11.15 am");
		pickup_times.add("11.40 am");
		
		car_models.add("Mazda3");
		car_models.add("Honda Civic");
		
		license_nos.add("1B098764L");
		license_nos.add("VG6754325");
		
		pic_urls.add("ramya_pic");
		pic_urls.add("nkapoor_pic");
		
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
	
}
