package me.matti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Station {
	
	private String name;
	private double latitude; // x
	private double longitude; // y
	private HashMap<String, HashMap<String,Date>> openHours; // hashmap open hours {"Monday":{"opens":long time,"closes":long time}}
	private ArrayList<Train> trains; // Class Address list
	
	private ArrayList<String> dagar = new ArrayList<String>() {
		{
			add("Mánudagur");
			add("Þriðjudagur");
			add("Miðvikudagur");
			add("Fimmtudagur");
			add("Föstudagur");
			add("Laugardagur");
			add("Sunnudagur");
		}	
	};
	
	public Station(String name, double longitude, double latitude, HashMap<String, HashMap<String, Date>> openHours) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.openHours = openHours;
		this.trains = new ArrayList<Train>();
	}
	
	public void printBasic() {
		System.out.println("==========================");
		System.out.println(" Name: " + name);
		System.out.println(" Latitude: " + latitude);
		System.out.println(" Longitude: " + longitude);
		System.out.println("==========================");
		System.out.println("  Open Hours");
		System.out.println("==========================");
		for (int i = 0; i < dagar.size(); i++) {
			String d = dagar.get(i);
			HashMap<String,Date> sd = openHours.get(d);
			System.out.println(" " + d + ":");
			System.out.println(" - Opnar: " + Main.sdf.format(sd.get("Opnar")));
			System.out.println(" - Lokar: " + Main.sdf.format(sd.get("Lokar")));
		}
	}
	
	public void print() {
		printBasic();
		System.out.println("==========================");
		System.out.println("  Trains");
		for (int i = 0; i < trains.size(); i++) {
			Train t = trains.get(i);
			t.print();
		}
	}
	
	public boolean addTrain(Train train) {
		for (int i = 0; i < this.trains.size(); i++) {
			Train t = trains.get(i);
			if (t.getTrainID() == train.getTrainID()) return false;
		}
		this.trains.add(train);
		return true;
	}
	
	public String getName() {
		return name;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public HashMap<String, HashMap<String, Date>> getOpenHours() {
		return openHours;
	}

	public ArrayList<Train> getTrains() {
		return trains;
	}

	
	
}
