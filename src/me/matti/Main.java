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
	
	private static ArrayList<HashMap<String,HashMap<String,Date>>> openHours;
	private static ArrayList<HashMap<String,Date>> hours;
	
	public static DateFormat sdf = new SimpleDateFormat("HH:mm");
	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			booking = new DDLink();
			trains = new ArrayList<Train>();
			routes = new ArrayList<Route>();
			stations = new ArrayList<Station>();
			
			openHours = new ArrayList<HashMap<String, HashMap<String,Date>>>();
			hours = new ArrayList<HashMap<String,Date>>();
			
			Read.all();
			
			while(true) {
				String val = print(new String[]{"View information","Reserve seats","Delete reserved seats","Manage bookings"});
				if (val.equals("1")) { // View all bookings
					while(true) {
						String val2 = print(new String[]{"View all bookings","View all stations","View all trains","View all routes"});
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
						else if (val2.equals("3")) { // View all trains
							for (int i = 0; i < trains.size(); i++) {
								Train t = trains.get(i);
								t.print();
							}
						}
						else if (val2.equals("4")) { // View all routes
							for (int i = 0; i < routes.size(); i++) {
								Route r = routes.get(i);
								r.print();
							}
						}
						else if (val2.equals("5")) { // Quit
							break;
						}
						else {
							System.out.println(" ** Rangur innsláttur **");
							System.out.println(" **    Reyndu aftur   **");
						}
					}
				}
				else if (val.equals("2")) { // Reserve seats
					while(true) {
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
				else if (val.equals("3")) { // Delete reserved seats
					while(true) {
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
				else if (val.equals("4")) { // Manage bookings
					while(true) {
						String val2 = print(new String[]{"Add booking","Modify booking","Delete booking","Find booking","Sort bookings"});
						if (val2.equals("1")) { // Add new booking
							while(true) {
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
								
								if (booking.find(train, route)) {
									String tryAgain = error("Booking already exists");
									if (tryAgain.equalsIgnoreCase("no")) {
										break;
									}
									continue;
								}
								
								booking.add(bIDn, train, route);
								System.out.println();
								System.out.println(" ** Booking was added");
								System.out.println(" ** successfully");
								System.out.println();
								break;
							}
						}
						else if (val2.equals("2")) { // Modify booking
							while(true) {
								System.out.println("==========================");
								System.out.println("  Modify booking");
								System.out.println("  Fill in the field");
								System.out.println("==========================");
								System.out.print(" Booking ID: ");
								String oID = sc.nextLine();
								
								int oIDn = toInt(oID);
								if (oIDn == -1) break;
								if (oIDn == -2) continue;
								
								Booking b = booking.find(oIDn);
								
								if (b == null) {
									String tryAgain = error("Booking not found");
									if (tryAgain.equalsIgnoreCase("no")) {
										break;
									}
									continue;
								}
								
								while(true) {
									String val3 = print(new String[]{"Change train","Change route","Change both"});
									if (val3.equals("1")) { // Change train
										System.out.println("==========================");
										System.out.println("  Type in a new train");
										System.out.println("  for the booking");
										System.out.println("==========================");
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
										if (!train.checkRoute(b.getRoute())) {
											String tryAgain = error("Invalid train selected");
											if (tryAgain.equalsIgnoreCase("no")) {
												break;
											}
											continue;
										}
										
										if (booking.find(train, b.getRoute())) {
											String tryAgain = error("Booking already exists");
											if (tryAgain.equalsIgnoreCase("no")) {
												break;
											}
											continue;
										}
										
										b.setTrain(train);
										System.out.println();
										System.out.println(" ** Train updated");
										System.out.println(" ** successfully");
										System.out.println();
										break;
									}
									else if (val3.equals("2")) { // Change route
										System.out.println("==========================");
										System.out.println("  Type in a new route");
										System.out.println("  for the booking");
										System.out.println("==========================");
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
										if (!b.getTrain().checkRoute(route)) {
											String tryAgain = error("Invalid route selected");
											if (tryAgain.equalsIgnoreCase("no")) {
												break;
											}
											continue;
										}
										
										if (booking.find(b.getTrain(), route)) {
											String tryAgain = error("Booking already exists");
											if (tryAgain.equalsIgnoreCase("no")) {
												break;
											}
											continue;
										}
										
										b.setRoute(route);
										System.out.println();
										System.out.println(" ** Route updated");
										System.out.println(" ** successfully");
										System.out.println();
									}
									else if (val3.equals("3")) { // Change both
										System.out.println("==========================");
										System.out.println("  Type in a new train &");
										System.out.println("  route for the booking");
										System.out.println("==========================");
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
										
										if (booking.find(train, route)) {
											String tryAgain = error("Booking already exists");
											if (tryAgain.equalsIgnoreCase("no")) {
												break;
											}
											continue;
										}

										b.setRoute(route);
										b.setTrain(train);
										System.out.println();
										System.out.println(" ** Booking was updated");
										System.out.println(" ** successfully");
										System.out.println();
									}
									else if (val3.equals("4")) { // Quit
										break;
									}
									else {
										System.out.println(" ** Rangur innsláttur **");
										System.out.println(" **    Reyndu aftur   **");
									}
								}
								break;
							}
						}
						else if (val2.equals("3")) { // Delete booking
							while(true) {
								System.out.println("==========================");
								System.out.println("  Delete booking");
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
						else if (val2.equals("4")) { // Find booking
							while(true) {
								System.out.println("==========================");
								System.out.println("  Find booking");
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
								b.print();
								break;
							}
						}
						else if (val2.equals("5")) { // Sort bookings
							while(true) {
								boolean worked = booking.sort();
								System.out.println();
								if (worked) {
									System.out.println(" ** Bookings were");
									System.out.println(" ** successfully sorted");
								}
								else {
									System.out.println(" ** No bookings");
									System.out.println(" ** to sort");
								}
								System.out.println();
								break;
							}
						}
						else if (val2.equals("6")) { // Quit
							break;
						}
						else {
							System.out.println(" ** Rangur innsláttur **");
							System.out.println(" **    Reyndu aftur   **");
						}
					}
				}
				else if (val.equals("5")) { // Quit
					break;
				}
				else {
					System.out.println(" ** Rangur innsláttur **");
					System.out.println(" **    Reyndu aftur   **");
				}
			}
		} catch (Exception e) {
			System.out.println(" ** Villa við keyrslu **");
		} finally {
			Write.all();
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
	
	public static int toIntSilent(String string) {
		int num = 0;
		try {
			num = Integer.parseInt(string);	
		} catch (Exception e) {
			System.out.println(" ** Villa við lestur á gögnum **");
		}
		return num;
	}
	
	public static double toDoubleSilent(String string) {
		double num = 0;
		try {
			num = Double.parseDouble(string);	
		} catch (Exception e) {
			System.out.println(" ** Villa við lestur á gögnum **");
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

	public static DDLink getBooking() {
		return booking;
	}


	public static ArrayList<Train> getTrains() {
		return trains;
	}


	public static ArrayList<Route> getRoutes() {
		return routes;
	}


	public static ArrayList<Station> getStations() {
		return stations;
	}


	public static ArrayList<HashMap<String, HashMap<String, Date>>> getOpenHours() {
		return openHours;
	}


	public static ArrayList<HashMap<String, Date>> getHours() {
		return hours;
	}
}
