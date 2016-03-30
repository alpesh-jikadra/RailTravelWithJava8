package com.railTravel;

public class BookingRequest {

	private Integer trainNo;
	private Integer noOfSeats;
	private String boardingPoint;
	private String source;
	private String destination;

	public BookingRequest(Integer trainNo, Integer noOfSeats, String boardingPoint) {
		this.trainNo = trainNo;
		this.noOfSeats = noOfSeats;
		this.boardingPoint = boardingPoint;
	}

	public BookingRequest(Integer trainNo, Integer noOfSeats) {
		this.trainNo = trainNo;
		this.noOfSeats = noOfSeats;
	}

	public BookingRequest(Integer trainNo, Integer noOfSeat, String source, String destination) {
		this.trainNo = trainNo;
		noOfSeats = noOfSeat;
		this.source = source;
		this.destination = destination;
	}

	public Integer getTrainNo() {
		return trainNo;
	}

	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	public String getBoardingPoint() {
		return boardingPoint;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}

	
}
