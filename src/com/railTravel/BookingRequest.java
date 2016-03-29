package com.railTravel;

public class BookingRequest {

	private Integer trainNo;
	private Integer noOfSeats;
	private String boardingPoint;

	public BookingRequest(Integer trainNo, Integer noOfSeats, String boardingPoint) {
		this.trainNo = trainNo;
		this.noOfSeats = noOfSeats;
		this.boardingPoint = boardingPoint;
	}

	public BookingRequest(Integer trainNo, Integer noOfSeats) {
		this.trainNo = trainNo;
		this.noOfSeats = noOfSeats;
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

	
}
