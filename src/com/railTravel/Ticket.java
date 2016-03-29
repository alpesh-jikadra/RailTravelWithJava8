package com.railTravel;

public class Ticket {

	private Integer trainNo;
	private Integer noOfSeats;
	private String boardingPoint;
	private Double totalAmount;

	public Ticket(Integer trainNo, Integer noOfSeats, String boardingPoint, Double totalAmount) {
		this.trainNo = trainNo;
		this.noOfSeats = noOfSeats;
		this.boardingPoint = boardingPoint;
		this.totalAmount = totalAmount;
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

	public Double getTotalAmount() {
		return totalAmount;
	}
	
	
	
	
}
