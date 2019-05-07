package me.matti;

import java.util.Date;

public class Route {
	
	private int routeID;
	private Station from;
	private int fromPlatform;
	private Date leaves;
	private Station to;
	private int toPlatform;
	private Date arrives;
	
	public Route(int routeID, Station from, int fromPlatform, Date leaves, Station to, int toPlatform, Date arrives) {
		this.routeID = routeID;
		this.from = from;
		this.fromPlatform = fromPlatform;
		this.leaves = leaves;
		this.to = to;
		this.toPlatform = toPlatform;
		this.arrives = arrives;
	}
	
	public void print() {
		print("");
	}
	
	public void print(String msg) {
		if (msg.equals("")) {
			System.out.println();
			System.out.println("==========================");
		}
		else {
			System.out.println("--------------------------");
		}
		System.out.println(msg + " * RouteID: " + routeID);
		if (msg.equals("")) {
			System.out.println("==========================");
		}
		else {
			System.out.println("--------------------------");
		}
		System.out.println(msg + " From: " + from.getName());
		System.out.println(msg + "  Platform: " + fromPlatform);
		System.out.println(msg + "  Leaves: " + Main.sdf.format(leaves));
		System.out.println(msg + " To: " + to.getName());
		System.out.println(msg + "  Platform: " + toPlatform);
		System.out.println(msg + "  Arrives: " + Main.sdf.format(arrives));
	}

	public int getRouteID() {
		return routeID;
	}
	
	public Station getFrom() {
		return from;
	}

	public int getFromPlatform() {
		return fromPlatform;
	}

	public Date getLeaves() {
		return leaves;
	}

	public Station getTo() {
		return to;
	}

	public int getToPlatform() {
		return toPlatform;
	}

	public Date getArrives() {
		return arrives;
	}
	
	
	
}
