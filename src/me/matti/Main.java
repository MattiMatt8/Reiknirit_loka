package me.matti;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.DefaultBoundedRangeModel;

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

		booking.add(1, getTrain(1), getRoute(2));
		booking.add(2, getTrain(2), getRoute(3));
		
		while(true) {
			String val = print(new String[]{"View information","Add new booking","Delete booking","Reserve seats","Remove reserved seats"});
			if (val.equals("1")) { // View all bookings
				while(true) {
					String val2 = print(new String[]{"View all bookings","View all stations","View all routes"});
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
				while(true) {
					System.out.println();
					System.out.println("==========================");
					System.out.println("  Fill in the fields");
					System.out.println("  for the new booking");
					System.out.println("==========================");
					System.out.print(" Booking ID (new): ");
					String bID = sc.nextLine();
					
					int bIDn = toInt(bID);
					if (bIDn == -1) break;
					if (bIDn == -2) continue;
					if (booking.find(bIDn) != null) {
						String tryAgain = error("Booking ID already exists");
						if (tryAgain.equalsIgnoreCase("no")) {
							break;
						}
						continue;
					}
					else if (bIDn <= 0) {
						String tryAgain = error("ID needs to be greater than 0");
						if (tryAgain.equalsIgnoreCase("no")) {
							break;
						}
						continue;
					}
					
					System.out.print(" Route ID: ");
					String rID = sc.nextLine();

					int rIDn = toInt(rID);
					if (rIDn == -1) break;
					if (rIDn == -2) continue;
					Route route = getRoute(rIDn);
					if (route == null) {
						String tryAgain = error("Route doesn't exist");
						if (tryAgain.equalsIgnoreCase("no")) {
							break;
						}
						continue;
					}
					
					System.out.print(" Train ID: ");
					String tID = sc.nextLine();

					int tIDn = toInt(tID);
					if (tIDn == -1) break;
					if (tIDn == -2) continue;
					Train train = getTrain(tIDn);
					if (train == null) {
						String tryAgain = error("Train doesn't exist");
						if (tryAgain.equalsIgnoreCase("no")) {
							break;
						}
						continue;
					}
					if (!train.checkRoute(route)) {
						String tryAgain = error("Invalid train selected");
						if (tryAgain.equalsIgnoreCase("no")) {
							break;
						}
						continue;
					}
					
					booking.add(bIDn, train, route);
					break;
				}
				
			}
			else if (val.equals("3")) { // Delete booking
				while(true) {
					System.out.println();
					System.out.println("==========================");
					System.out.println("  Delete boooking");
					System.out.println("  Fill in the field");
					System.out.println("==========================");
					System.out.print(" Booking ID: ");
					String bID = sc.nextLine();
					
					int bIDn = toInt(bID);
					if (bIDn == -1) break;
					if (bIDn == -2) continue;
					
					boolean deleted = booking.del(bIDn);
					if (!deleted) {
						String tryAgain = error("Booking not found");
						if (tryAgain.equalsIgnoreCase("no")) {
							break;
						}
						continue;
					}
					System.out.println();
					System.out.println(" ** Booking was deleted");
					System.out.println(" ** successfully");
					System.out.println();
					break;
				}
			}
			else if (val.equals("4")) { // Reserve seats
				while(true) {
					System.out.println();
					System.out.println("==========================");
					System.out.println("  Reserving seats");
					System.out.println("  Fill in the field");
					System.out.println("==========================");
					System.out.print(" Booking ID: ");
					String bID = sc.nextLine();
					
					int bIDn = toInt(bID);
					if (bIDn == -1) break;
					if (bIDn == -2) continue;
					
					Booking b = booking.find(bIDn);
					if (b == null) {
						String tryAgain = error("Booking not found");
						if (tryAgain.equalsIgnoreCase("no")) {
							break;
						}
						continue;
					}

					System.out.println("==========================");
					System.out.println("  First class or");
					System.out.println("  standard seats?");
					System.out.println("==========================");
					System.out.println(" F for First class or");
					System.out.print(" S for Standard (default) ");
					String seatType = sc.nextLine();
					System.out.println("==========================");
					System.out.print(" How many seats? ");
					String seatTotal = sc.nextLine();
					System.out.println("==========================");
					
					int st = toInt(seatTotal);
					if (st == -1) break;
					if (st == -2) continue;
					if (st <= 0) {
						String tryAgain = error("Seat total needs to be greater than 0");
						if (tryAgain.equalsIgnoreCase("no")) {
							break;
						}
						continue;
					}
					
					int total = 0;
					
					if (seatType.equalsIgnoreCase("f")) {
						total = b.reserveFc(st);
					}
					else {
						total = b.reserveSt(st);
					}


					if (total == 0) {
						System.out.println(" No seats were reserved because");
						System.out.println(" all of the seats were taken");
						break;
					}
					else if (total != st) {
						System.out.println(" The total of seats were not available");
						System.out.println(" so only " + total + " of seats were reserved");
						break;
					}
					
					System.out.print(" " + total);
					if (seatType.equalsIgnoreCase("f")) {
						System.out.print(" First class");
					}
					else {
						System.out.print(" Standard");
					}
					System.out.println(" seats were reserved");
					
					break;
				}
			}
			else if (val.equals("5")) { // Remove reserved seats
				while(true) {
					System.out.println();
					System.out.println("==========================");
					System.out.println("  Remove reserved seats");
					System.out.println("  Fill in the field");
					System.out.println("==========================");
					System.out.print(" Booking ID: ");
					String bID = sc.nextLine();
					
					int bIDn = toInt(bID);
					if (bIDn == -1) break;
					if (bIDn == -2) continue;
					
					Booking b = booking.find(bIDn);
					if (b == null) {
						String tryAgain = error("Booking not found");
						if (tryAgain.equalsIgnoreCase("no")) {
							break;
						}
						continue;
					}

					System.out.println("==========================");
					System.out.println("  First class or");
					System.out.println("  standard seats?");
					System.out.println("==========================");
					System.out.println(" F for First class or");
					System.out.print(" S for Standard (default) ");
					String seatType = sc.nextLine();
					System.out.println("==========================");
					System.out.print(" How many seats? ");
					String seatTotal = sc.nextLine();
					System.out.println("==========================");
					
					int st = toInt(seatTotal);
					if (st == -1) break;
					if (st == -2) continue;
					if (st <= 0) {
						String tryAgain = error("Seat total needs to be greater than 0");
						if (tryAgain.equalsIgnoreCase("no")) {
							break;
						}
						continue;
					}
					
					int total = 0;
					
					if (seatType.equalsIgnoreCase("f")) {
						total = b.delReservedFc(st);
					}
					else {
						total = b.delReservedSt(st);
					}


					if (total == 0) {
						System.out.println(" No seats were removed because");
						System.out.println(" there are no reserved seats");
						break;
					}
					else if (total != st) {
						System.out.println(" The total of seats to be removed were");
						System.out.println(" not availble so only " + total + " removed");
						break;
					}
					
					System.out.print(" " + total);
					if (seatType.equalsIgnoreCase("f")) {
						System.out.print(" First class");
					}
					else {
						System.out.print(" Standard");
					}
					System.out.println(" seats were removed");
					
					break;
				}
			}
			else if (val.equals("6")) { // Quit
				break;
			}
			else {
				System.out.println(" ** Rangur innsláttur **");
				System.out.println(" **    Reyndu aftur   **");
			}
		}	
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
	
	public static String print(String[] valmynd) {
		System.out.println("==========================");
		for (int i = 0; i < valmynd.length; i++) {
			int x = i+1;
			System.out.println(" " + x + ". " + valmynd[i]);
			if (x == valmynd.length) {
				System.out.println(" " + (x+1) + ". Quit");
			}
		}
		System.out.println("==========================");
		System.out.print(" Select ");
		for (int i = 0; i < valmynd.length; i++) {
			int x = i+1;
			System.out.print(x);
			if (x == valmynd.length) {
				System.out.println(" or " + (x+1) + " quit.");
			}
			else {
				System.out.print(",");
			}
		}
		System.out.println("==========================");
		System.out.print(" ");
		return sc.nextLine();
	}
	
	public static String error(String msg) {
		System.out.println();
		System.out.println(" ** Error, please try again");
		System.out.println(" ** " + msg);
		System.out.println();
		System.out.println(" Would you like to try again?");
		System.out.print(" ( yes / no ) ");
		return sc.nextLine();
	}
	
	public static int toInt(String string) {
		int num = -1;
		try {
			num = Integer.parseInt(string);	
		} catch (Exception e) {
			String tryAgain = error("Make sure to type in numbers");
			if (tryAgain.equalsIgnoreCase("no")) {
				return -1;
			}
			return -2;
		}
		return num;
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
