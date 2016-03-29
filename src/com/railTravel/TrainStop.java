package com.railTravel;

public class TrainStop {

	private String fromStation;
	private String toStation;
	private double ticketCharge;
	private String departureTime;

	public TrainStop(String fromStation, String toStation, double ticketCharge, String departureTime) {
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.ticketCharge = ticketCharge;
		this.departureTime = departureTime;
		
	}

	public String getFromStation() {
		return fromStation;
	}

	public String getToStation() {
		return toStation;
	}

	public double getTicketCharge() {
		return ticketCharge;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	@Override
	public String toString() {
		return "TrainStop [fromStation=" + fromStation + ", toStation="
				+ toStation + ", ticketCharge=" + ticketCharge
				+ ", departureTime=" + departureTime + "]";
	}

	
	
}
