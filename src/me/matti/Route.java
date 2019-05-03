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
		System.out.println("==========================");
		System.out.println(" RouteID: " + routeID);
		System.out.println(" From: " + from.getName());
		System.out.println("  Platform: " + fromPlatform);
		System.out.println("  Leaves: " + Main.sdf.format(leaves));
		System.out.println(" To: " + to.getName());
		System.out.println("  Platform: " + toPlatform);
		System.out.println("  Arrives: " + Main.sdf.format(arrives));
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
