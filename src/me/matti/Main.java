package me.matti;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	
	private static DDLink booking;
	private static ArrayList<Train> trains;
	private static ArrayList<Route> routes;
	private static ArrayList<Station> stations;
	
	public static DateFormat sdf = new SimpleDateFormat("HH:mm");
	
	private static Scanner sc = new Scanner(System.in);
	
	// DOUBLE LINKED LIST !!!
	public static void main(String[] args) {

		booking = new DDLink();
		trains = new ArrayList<Train>();
		routes = new ArrayList<Route>();
		stations = new ArrayList<Station>();

		HashMap<String, HashMap<String,Date>> openHours = new HashMap<String, HashMap<String,Date>>();

		HashMap<String,Date> hours1 = new HashMap<String,Date>();
		hours1.put("Opnar", tf("08:00"));
		hours1.put("Lokar", tf("22:00"));
		HashMap<String,Date> hours2 = new HashMap<String,Date>();
		hours2.put("Opnar", tf("09:00"));
		hours2.put("Lokar", tf("20:00"));
		
		openHours.put("Mánudagur", hours1);
		openHours.put("Þriðjudagur", hours1);
		openHours.put("Miðvikudagur", hours1);
		openHours.put("Fimmtudagur", hours1);
		openHours.put("Föstudagur", hours1);
		openHours.put("Laugardagur", hours2);
		openHours.put("Sunnudagur", hours2);
		

		stations.add(new Station("Swansea",51.6253,-3.9409,openHours));
		stations.add(new Station("Eaglescliffe",54.5294,-1.3494,openHours));
		
		stations.add(new Station("Queenborough",51.4158,0.7496,openHours));
		stations.add(new Station("Umberleigh",50.9964,-3.9831,openHours));

		routes.add(new Route(1,getStation("Swansea"),2,tf("10:30"),getStation("Eaglescliffe"),4,tf("14:00")));
		routes.add(new Route(2,getStation("Eaglescliffe"),4,tf("14:30"),getStation("Swansea"),2,tf("18:00")));

		routes.add(new Route(3,getStation("Queenborough"),1,tf("12:00"),getStation("Umberleigh"),5,tf("17:00")));
		routes.add(new Route(4,getStation("Umberleigh"),5,tf("17:30"),getStation("Queenborough"),1,tf("22:30")));

		trains.add(new Train(1,"High Speed Train",300,40));
		trains.add(new Train(2,"High Speed Train",500,80));

		getTrain(1).addRoute(getRoute(1));
		getTrain(1).addRoute(getRoute(2));
		
		getTrain(2).addRoute(getRoute(3));
		getTrain(2).addRoute(getRoute(4));

		getStation("Swansea").addTrain(getTrain(1));
		getStation("Eaglescliffe").addTrain(getTrain(1));
		
		getStation("Queenborough").addTrain(getTrain(2));
		getStation("Umberleigh").addTrain(getTrain(2));

		booking.add(1, getTrain(1), getRoute(2), getStation("Eaglescliffe"));
		booking.add(2, getTrain(2), getRoute(3), getStation("Umberleigh"));
		
		while(true) {
			System.out.println("==========================");
			System.out.println(" 1. View information");
			System.out.println(" 2. Add new booking");
			System.out.println(" 3. Delete booking");
			System.out.println(" 4. Modify booking");
			System.out.println(" 5. Quit");
			System.out.println("==========================");
			System.out.println(" Select 1,2,3,4 or 5 quit.");
			System.out.println("==========================");
			System.out.print(" ");
			String val = sc.nextLine();
			if (val.equals("1")) { // View all bookings
				while(true) {
					System.out.println("==========================");
					System.out.println(" 1. View all bookings");
					System.out.println(" 2. View all stations");
					System.out.println(" 3. View all routes");
					System.out.println(" 4. Quit");
					System.out.println("==========================");
					System.out.println(" Select 1,2,3 or 4 quit.");
					System.out.println("==========================");
					System.out.print(" ");
					String val2 = sc.nextLine();
					if (val2.equals("1")) { // View all bookings
						booking.print();
					}
					else if (val2.equals("2")) { // View all stations
						for (int i = 0; i < stations.size(); i++) {
							Station s = stations.get(i);
							System.out.println();
							s.print();
						}
					}
					else if (val2.equals("3")) { // View all routes
						for (int i = 0; i < routes.size(); i++) {
							Route r = routes.get(i);
							r.print();
						}
					}
					else if (val2.equals("4")) { // Quit
						break;
					}
					else {
						System.out.println(" ** Rangur innsláttur **");
						System.out.println(" **    Reyndu aftur   **");
					}
				}
			}
			else if (val.equals("2")) { // Add new booking
				System.out.println();
				System.out.println("==========================");
				System.out.println("  Fill in the fields");
				System.out.println("  for the new booking");
				System.out.println("==========================");
				System.out.print(" Booking ID (new): ");
				String bID = sc.nextLine();
				System.out.print(" Train ID: ");
				String tID = sc.nextLine();
				System.out.print(" Route ID: ");
				String rID = sc.nextLine();
				System.out.print(" Station Name: ");
				String sName = sc.nextLine();
				
				
			}
			else if (val.equals("3")) { // Delete booking
				continue;
			}
			else if (val.equals("4")) { // Modify booking
				continue;
			}
			else if (val.equals("5")) { // Quit
				break;
			}
			else {
				System.out.println(" ** Rangur innsláttur **");
				System.out.println(" **    Reyndu aftur   **");
			}
		}
		
		
		// ** Booking error checking **

		
		//getStation("Queenborough").print();
		//getStation("Eaglescliffe").print();
		
		// Check if routeID exists else prohibit
		// Check if trainID exists else prohibit		
	}

	public static Date tf(String time) {
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			System.out.println("Error converting time");
		}
		return date;
	}
	
	public static Station getStation(String name) {
		for (int i = 0; i < stations.size(); i++) {
			Station s = stations.get(i);
			if (s.getName().equals(name)) return s;
		}
		return null;
	}
	
	public static Train getTrain(int id) {
		for (int i = 0; i < trains.size(); i++) {
			Train t = trains.get(i);
			if (t.getTrainID() == id) return t;
		}
		return null;
	}
	
	public static Route getRoute(int id) {
		for (int i = 0; i < routes.size(); i++) {
			Route r = routes.get(i);
			if (r.getRouteID() == id) return r;
		}
		return null;
	}
	
}
