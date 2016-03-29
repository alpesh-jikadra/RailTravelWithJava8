package com.railTravel;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.railTravel.exception.InvalidBoardingPoint;
import com.railTravel.exception.NoSeatAvailable;
import com.railTravel.exception.NoTrainAvailable;

public class BookingSystem {

	private List<Train> trains;

	public BookingSystem(List<Train> trains) {
		this.trains = trains;
	}

	
	public List<Train> findTrain(String fromStation, String toStation) {
		List<Train> collect = trains.stream().filter(t -> (t.getStops().stream().map(TrainStop::getFromStation).collect(Collectors.toSet())).contains(fromStation) ).collect(Collectors.toList());
		List<Train> collect2 = collect.stream().filter(t -> (t.getStops().stream().map(TrainStop::getFromStation).collect(Collectors.toSet())).contains(fromStation) ).collect(Collectors.toList());
		return collect2;
	}


	public Ticket bookSeat(BookingRequest bookingRequest) throws NoTrainAvailable, NoSeatAvailable, InvalidBoardingPoint {
		String boardingPoint="";
		Optional<Train> findFirst = trains.stream().filter(t -> t.getTrainNo().equals(bookingRequest.getTrainNo())).findFirst();
		
		if(!findFirst.isPresent()){
			throw new NoTrainAvailable("No Train Available");
		}
		
		Train train = findFirst.get();
		boardingPoint=train.getStops().get(0).getFromStation();
		if(train.getTotalSeats() < bookingRequest.getNoOfSeats()){
			throw new NoSeatAvailable("No Seat Available in "+bookingRequest.getTrainNo());
		}
		
		if(!"".equals(bookingRequest.getBoardingPoint()) && bookingRequest.getBoardingPoint()!=null){
			
			boardingPoint=bookingRequest.getBoardingPoint();
			
			Optional<TrainStop> findFirst2 = train.getStops().stream().filter(t -> t.getFromStation().equalsIgnoreCase(bookingRequest.getBoardingPoint())).findFirst();
			if(!findFirst2.isPresent()){
				throw new InvalidBoardingPoint("Invalid Boarding Point");
			}
		}
		train.blockSeats(bookingRequest.getNoOfSeats());
		return new Ticket(bookingRequest.getTrainNo(),bookingRequest.getNoOfSeats(),boardingPoint,train.getTicketCharges()*bookingRequest.getNoOfSeats());
			

	}



}

//t.getStops().stream().filter(stop -> stop.getFromStation().equals(fromStation))