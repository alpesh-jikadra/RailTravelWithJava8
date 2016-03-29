package com.railTravel;

import java.util.List;
import java.util.Map;

public class Train {

	private Integer trainNo;
	private String startStation;
	private String endStation;
	private String trainName;
	private List<TrainStop> stops;
	private int totalSeats;
	private double ticketCharges;

	public Train(Integer trainNo, String startStation, String endStation, String trainName,
			List<TrainStop> stops, int totalSeats, double ticketCharges) {
				this.trainNo = trainNo;
				this.startStation = startStation;
				this.endStation = endStation;
				this.trainName = trainName;
				this.stops = stops;
				this.totalSeats = totalSeats;
				this.ticketCharges = ticketCharges;
	}

	public Integer getTrainNo() {
		return trainNo;
	}

	public String getStartStation() {
		return startStation;
	}

	public String getEndStation() {
		return endStation;
	}

	public String getTrainName() {
		return trainName;
	}

	public List<TrainStop> getStops() {
		return stops;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public double getTicketCharges() {
		return ticketCharges;
	}

	public void blockSeats(Integer noOfSeats) {
		totalSeats-=noOfSeats;
	}

	
}
