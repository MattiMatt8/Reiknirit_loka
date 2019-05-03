package me.matti;

public class Booking {

	private int id;
	private Train train;
	private Route route;
	private Station station;
	private int stReserved;
	private int fcReserved;
	
	public Booking(int id, Train train, Route route, Station station) {
		this.id = id;
		this.train = train;
		this.route = route;
		this.station = station;
		this.stReserved = 0;
		this.fcReserved = 0;
	}

	public void print() {
		System.out.println();
		System.out.println("==========================");
		System.out.println("  BookingID: " + id);
		System.out.println("==========================");
		System.out.println("  Train");
		train.printBasic();
		System.out.println("==========================");
		System.out.println("  Route");
		route.print();
		System.out.println("==========================");
		System.out.println("  Station");
		station.printBasic();
	}
	
	public int getId() {
		return id;
	}

	public int getStReserved() {
		return stReserved;
	}

	public int getFcReserved() {
		return fcReserved;
	}

	public Train getTrain() {
		return train;
	}

	public Route getRoute() {
		return route;
	}

	public Station getStation() {
		return station;
	}
	
	
	
	
}
