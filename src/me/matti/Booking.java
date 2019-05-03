package me.matti;

public class Booking {

	private int id;
	private Train train;
	private Route route;
	private int stReserved;
	private int fcReserved;
	
	public Booking(int id, Train train, Route route) {
		this.id = id;
		this.train = train;
		this.route = route;
		this.stReserved = 0;
		this.fcReserved = 0;
	}

	public void print() {
		System.out.println();
		System.out.println("==========================");
		System.out.println("  BookingID: " + id);
		System.out.println("==========================");
		System.out.println("  Reserved Seats");
		System.out.println("==========================");
		System.out.println(" Standard: " + stReserved);
		System.out.println(" First Class: " + fcReserved);
		System.out.println("==========================");
		System.out.println("  Train");
		train.printBasic();
		System.out.println("==========================");
		System.out.println("  Route");
		route.print();
	}
	
	public int reserveSt(int total) {
		int st = train.getSt_seating();
		int available = st - stReserved;
		if (available >= total) {
			stReserved += total;
			return total;
		}
		else if (available == 0) {
			return 0;
		}
		stReserved += available;
		return available;
	}
	
	public int reserveFc(int total) {
		int fc = train.getFc_seating();
		int available = fc - fcReserved;
		if (available >= total) {
			fcReserved += total;
			return total;
		}
		else if (available == 0) {
			return 0;
		}
		fcReserved += available;
		return available;
	}
	
	public int delReservedSt(int total) {
		if (stReserved >= total) {
			stReserved -= total;
			return total;
		}
		else if (stReserved == 0) {
			return 0;
		}
		int tmp = stReserved;
		stReserved -= stReserved;
		return tmp;
	}
	
	public int delReservedFc(int total) {
		if (fcReserved >= total) {
			fcReserved -= total;
			return total;
		}
		else if (fcReserved == 0) {
			return 0;
		}
		int tmp = fcReserved;
		fcReserved -= fcReserved;
		return tmp;
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
	
	
	
	
}
