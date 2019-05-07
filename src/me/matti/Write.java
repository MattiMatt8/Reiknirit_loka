package me.matti;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Write {
	public static void all() {
		hours();
		dayHours();
		stations();
		routes();
		trains();
		booking();
	}
	public static void hours() {
		ArrayList<HashMap<String,Date>> hours = Main.getHours();
		try {
			String filename = "hours.csv";

			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			for (int i = 0; i < hours.size(); i++) {
				HashMap<String,Date> h = hours.get(i);
				pw.println((i+1) + ";Opens;" + Main.sdf.format(h.get("Opens")) + ";Closes;" + Main.sdf.format(h.get("Closes")));
			}
			
			pw.flush();
			pw.close();
			fw.close();
			bw.close();
		} catch (Exception e) {
			System.out.println(" ** Villa við vistun á gögnum **");
		}
	}
	public static void dayHours() {
		ArrayList<HashMap<String,HashMap<String,Date>>> openHours = Main.getOpenHours();
		try {
			String filename = "dayHours.csv";

			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			for (int i = 0; i < openHours.size(); i++) {
				HashMap<String,HashMap<String,Date>> oh = openHours.get(i);
				
				String dagar = "";
				dagar += (i+1) + ";";
				ArrayList<String> d = Station.getDagar();
				for (int z = 0; z < d.size(); z++) {
					String dagur = d.get(z);
					HashMap<String,Date> sd = oh.get(dagur);
					
					dagar += dagur + ";" + Main.getHours().indexOf(sd);
					
					if (z+1 != d.size()) dagar += ";";
				}
				
				pw.println(dagar);
			}
			
			pw.flush();
			pw.close();
			fw.close();
			bw.close();
		} catch (Exception e) {
			System.out.println(" ** Villa við vistun á gögnum **");
		}
	}
	public static void stations() {
		ArrayList<Station> stations = Main.getStations();
		try {
			String filename = "stations.csv";

			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			for (int i = 0; i < stations.size(); i++) {
				Station s = stations.get(i);
				
				pw.println((i+1) + ";" + s.getName() + ";" + s.getLatitude() + ";" + s.getLongitude() + ";" + Main.getOpenHours().indexOf(s.getOpenHours()));
			}
			
			pw.flush();
			pw.close();
			fw.close();
			bw.close();
		} catch (Exception e) {
			System.out.println(" ** Villa við vistun á gögnum **");
		}
	}
	public static void routes() {
		ArrayList<Route> routes = Main.getRoutes();
		try {
			String filename = "routes.csv";

			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			for (int i = 0; i < routes.size(); i++) {
				Route r = routes.get(i);
				
				pw.println(r.getRouteID() + ";" + r.getFrom().getName() + ";" + r.getFromPlatform() + ";" + Main.sdf.format(r.getLeaves()) + ";" + r.getTo().getName() + ";" + r.getToPlatform() + ";" + Main.sdf.format(r.getArrives()));
			}
			
			pw.flush();
			pw.close();
			fw.close();
			bw.close();
		} catch (Exception e) {
			System.out.println(" ** Villa við vistun á gögnum **");
		}
	}
	public static void trains() {
		ArrayList<Train> trains = Main.getTrains();
		try {
			String filename = "trains.csv";

			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			for (int i = 0; i < trains.size(); i++) {
				Train t = trains.get(i);
				String strengur = t.getTrainID() + ";" + t.getType() + ";" + t.getSt_seating() + ";" + t.getFc_seating() + ";[";
				ArrayList<Route> routes = t.getRoute();
				
				for (int x = 0; x < routes.size(); x++) {
					Route r = routes.get(x);
					strengur += r.getRouteID();
					if (x+1 != routes.size()) strengur += ",";
				}
				strengur += "]";
				
				pw.println(strengur);
			}
			
			pw.flush();
			pw.close();
			fw.close();
			bw.close();
		} catch (Exception e) {
			System.out.println(" ** Villa við vistun á gögnum **");
		}
	}
	public static void booking() {
		DDLink booking = Main.getBooking();
		try {
			String filename = "booking.csv";

			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			ArrayList<Booking> bDump = booking.dump();
			
			for (int i = 0; i < bDump.size(); i++) {
				Booking b = bDump.get(i);
				
				pw.println(b.getId() + ";" + b.getTrain().getTrainID() + ";" + b.getRoute().getRouteID());
			}
			
			pw.flush();
			pw.close();
			fw.close();
			bw.close();
		} catch (Exception e) {
			System.out.println(" ** Villa við vistun á gögnum **");
		}
	}
}
