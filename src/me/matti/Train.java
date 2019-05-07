package me.matti;

import java.util.ArrayList;

public class Train {
	
	private int trainID;
	private String type;
	private int stSeating; // Standard seats
	private int fcSeating; // First class seats
	private ArrayList<Route> route;
	
	public Train(int trainID, String type, int stSeating, int fcSeating) {
		this.trainID = trainID;
		this.type = type;
		this.stSeating = stSeating;
		this.fcSeating = fcSeating;
		this.route = new ArrayList<Route>();
	}
	
	public void printBasic(String msg) {
		System.out.println("--------------------------");
		System.out.println(msg + " * TrainID: " + trainID);
		System.out.println("--------------------------");
		System.out.println(msg + " Type: " + type);
		System.out.println(msg + " Standard Seats: " + stSeating);
		System.out.println(msg + " First Class Seats: " + fcSeating);
	}
	
	public void print() {
		System.out.println();
		System.out.println("==========================");
		System.out.println(" * TrainID: " + trainID);
		System.out.println("==========================");
		System.out.println(" Type: " + type);
		System.out.println(" Standard Seats: " + stSeating);
		System.out.println(" First Class Seats: " + fcSeating);
		System.out.println("--------------------------");
		System.out.println("-  Routes");
		for (int i = 0; i < route.size(); i++) {
			Route r = route.get(i);
			r.print("- ");
		}
	}
	
	public void print(Station station) {
		printBasic("- ");
		System.out.println("--------------------------");
		System.out.println("-  Routes");
		for (int i = 0; i < route.size(); i++) {
			Route r = route.get(i);
			if (r.getFrom() == station) r.print("- ");
		}
	}

	public boolean checkRoute(Route route) {
		for (int i = 0; i < this.route.size(); i++) {
			Route r = this.route.get(i);
			if (r == route) return true;
		}
		return false;
	}
	
	public boolean addRoute(Route route) {
		for (int i = 0; i < this.route.size(); i++) {
			Route r = this.route.get(i);
			if (r.getRouteID() == route.getRouteID()) return false;
		}
		this.route.add(route);
		return true;
	}
	
	public int getTrainID() {
		return trainID;
	}

	public ArrayList<Route> getRoute() {
		return route;
	}

	public String getType() {
		return type;
	}

	public int getSt_seating() {
		return stSeating;
	}

	public int getFc_seating() {
		return fcSeating;
	}
	
}
