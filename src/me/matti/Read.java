package me.matti;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Read {

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
			String filepath = "hours.csv";
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			
			String line = br.readLine();
			while (line != null) {
				String[] split = line.split(";");
				
				HashMap<String,Date> tmp = new HashMap<String,Date>();
				tmp.put(split[1], Main.tf(split[2]));
				tmp.put(split[3], Main.tf(split[4]));
				hours.add(tmp);
				
				line = br.readLine();
			}
			
			br.close();
			fr.close();
			
		} catch (Exception e) {
			System.out.println(" ** Villa við lestur á gögnum **");
		}
	}
	public static void dayHours() {
		ArrayList<HashMap<String,Date>> hours = Main.getHours();
		ArrayList<HashMap<String,HashMap<String,Date>>> openHours = Main.getOpenHours();
		try {
			String filepath = "dayHours.csv";
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			
			String line = br.readLine();
			while (line != null) {
				String[] split = line.split(";");
				
				HashMap<String, HashMap<String,Date>> tmp = new HashMap<String, HashMap<String,Date>>();

				for (int i = 1; i < split.length; i+=2) {
					String s = split[i];
					int t = Main.toIntSilent(split[i+1]);
					
					tmp.put(s, hours.get(t));
				}
				
				openHours.add(tmp);
				line = br.readLine();
			}
			
			br.close();
			fr.close();
			
		} catch (Exception e) {
			System.out.println(" ** Villa við lestur á gögnum **");
		}
	}
	public static void stations() {
		ArrayList<Station> stations = Main.getStations();
		ArrayList<HashMap<String,HashMap<String,Date>>> openHours = Main.getOpenHours();
		try {
			String filepath = "stations.csv";
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			
			String line = br.readLine();
			while (line != null) {
				String[] split = line.split(";");
				Station s = new Station(split[1],Main.toDoubleSilent(split[2]),Main.toDoubleSilent(split[3]),openHours.get(Main.toIntSilent(split[4])));
				stations.add(s);
				line = br.readLine();
			}
			
			br.close();
			fr.close();
			
		} catch (Exception e) {
			System.out.println(" ** Villa við lestur á gögnum **");
		}
	}
	public static void routes() {
		ArrayList<Route> routes = Main.getRoutes();
		try {
			String filepath = "routes.csv";
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			
			String line = br.readLine();
			while (line != null) {
				String[] split = line.split(";");
				Route r = new Route(Main.toIntSilent(split[0]),Main.getStation(split[1]),Main.toIntSilent(split[2]),Main.tf(split[3]),Main.getStation(split[4]),Main.toIntSilent(split[5]),Main.tf(split[6]));
				routes.add(r);
				line = br.readLine();
			}
			
			br.close();
			fr.close();
			
		} catch (Exception e) {
			System.out.println(" ** Villa við lestur á gögnum **");
		}
	}
	public static void trains() {
		ArrayList<Train> trains = Main.getTrains();
		try {
			String filepath = "trains.csv";
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			
			String line = br.readLine();
			while (line != null) {
				String[] split = line.split(";");

				Train t = new Train(Main.toIntSilent(split[0]),split[1],Main.toIntSilent(split[2]),Main.toIntSilent(split[3]));
				trains.add(t);
				
				String tt = split[4].substring(1, split[4].length()-1);
				String[] ttSplit = tt.split(",");

				for (int i = 0; i < ttSplit.length; i++) {
					String s = ttSplit[i];
					int num = Main.toIntSilent(s);
					
					Route r = Main.getRoute(num);
					t.addRoute(r);
					r.getFrom().addTrain(t);
					r.getTo().addTrain(t);
				}
				
				line = br.readLine();
			}
			
			br.close();
			fr.close();
			
		} catch (Exception e) {
			System.out.println(" ** Villa við lestur á gögnum **");
		}
	}
	public static void booking() {
		DDLink booking = Main.getBooking();
		try {
			String filepath = "booking.csv";
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			
			String line = br.readLine();
			while (line != null) {
				String[] split = line.split(";");
				
				booking.add(Main.toIntSilent(split[0]), Main.getTrain(Main.toIntSilent(split[1])), Main.getRoute(Main.toIntSilent(split[2])));
				
				line = br.readLine();
			}
			
			br.close();
			fr.close();
			
		} catch (Exception e) {
			System.out.println(" ** Villa við lestur á gögnum **");
		}
	}
}
