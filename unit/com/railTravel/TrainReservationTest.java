package com.railTravel;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.railTravel.exception.InvalidBoardingPoint;
import com.railTravel.exception.NoSeatAvailable;
import com.railTravel.exception.NoTrainAvailable;

public class TrainReservationTest {
	private BookingSystem bookingSystem;

	
	/**
	 * Assumtions
	 * 
	 * 1. No Date rule
	 * 2. Only from source to destination booking allowed
	 * 3. Charges are from source to destination
	 * 
	 * */
	@Before
	public void setup(){
		List<Train> trains = new ArrayList<Train>();
		
		TrainStop puneToLonaval = new TrainStop("Pune","Lonavala",10d,"00:50");
		TrainStop LonavalToKarjat = new TrainStop("Lonavala","Karjat",10d,"01:50");
		TrainStop karjatToDadar  = new TrainStop("Karjat","Dadar",10d,"03:00");
		TrainStop dadarToCst  = new TrainStop("Dadar","CST",10d,"04:00");
		
		List<TrainStop> stops = new ArrayList<>();
		stops.add(puneToLonaval);
		stops.add(LonavalToKarjat);
		stops.add(karjatToDadar);
		stops.add(dadarToCst);
		
		Train t16382 = new Train(16382,"Pune","CST","CAPE  Mumbai EXP",stops,10,100d);
		trains.add(t16382);
		
		
		
		
		TrainStop kolhapurToPune  = new TrainStop("Kolhapur","Pune",10d,"00:10");
		TrainStop puneTokarjat = new TrainStop("Pune","Karjat",10d,"00:20");
		
		stops = new ArrayList<>();
		stops.add(kolhapurToPune);
		stops.add(puneTokarjat);
		stops.add(karjatToDadar);
		stops.add(dadarToCst);
		Train t16383 = new Train(16383,"Kolhapur","CST","CAPE  Mumbai EXP New",stops,10,500d);
		trains.add(t16383);
		
		bookingSystem = new BookingSystem(trains);
	}
	
	@Test
	public void shouldGetTrainBetweenStations(){
		List<Train> findTrain = bookingSystem.findTrain("Pune","CST");
		Assert.assertNotNull(findTrain);
		Assert.assertEquals(2, findTrain.size());
		Assert.assertEquals(Integer.valueOf(16382),findTrain.get(0).getTrainNo());
		
		findTrain = bookingSystem.findTrain("Lonavala","Karjat");
		Assert.assertNotNull(findTrain);
		Assert.assertEquals(1, findTrain.size());
		Assert.assertEquals(Integer.valueOf(16382),findTrain.get(0).getTrainNo());
		
	}
	
	@Test
	public void shouldBookSeat() throws NoTrainAvailable, NoSeatAvailable, InvalidBoardingPoint{
		BookingRequest bookingRequest=new BookingRequest(16382,3);
		Ticket bookSeat = bookingSystem.bookSeat(bookingRequest);
		Assert.assertEquals(Integer.valueOf(16382), bookSeat.getTrainNo());
		Assert.assertEquals(Integer.valueOf(3), bookSeat.getNoOfSeats());
		Assert.assertEquals("Pune", bookSeat.getBoardingPoint());
		Assert.assertEquals(Double.valueOf(300), bookSeat.getTotalAmount());
	}
	
	@Test(expected=NoTrainAvailable.class)
	public void shouldThrowNoTrainException() throws NoTrainAvailable, NoSeatAvailable, InvalidBoardingPoint{
		BookingRequest bookingRequest=new BookingRequest(9999999,3);
		bookingSystem.bookSeat(bookingRequest);
		
	}
	
	@Test(expected=NoSeatAvailable.class)
	public void shouldThrowNoSeatAvailableException() throws NoTrainAvailable, NoSeatAvailable, InvalidBoardingPoint{
		BookingRequest bookingRequest=new BookingRequest(16382,100);
		bookingSystem.bookSeat(bookingRequest);
		
	}
	
	@Test
	public void shouldBookSeatAsPerBoarding() throws NoTrainAvailable, NoSeatAvailable, InvalidBoardingPoint{
		BookingRequest bookingRequest=new BookingRequest(16382,2,"Lonavala");
		Ticket bookSeat = bookingSystem.bookSeat(bookingRequest);
		Assert.assertEquals(Integer.valueOf(16382), bookSeat.getTrainNo());
		Assert.assertEquals(Integer.valueOf(2), bookSeat.getNoOfSeats());
		Assert.assertEquals("Lonavala", bookSeat.getBoardingPoint());
	}
	
	@Test(expected=InvalidBoardingPoint.class)
	public void shouldNotBookSeatIfInvalidBordingPoint() throws NoTrainAvailable, NoSeatAvailable, InvalidBoardingPoint{
		BookingRequest bookingRequest=new BookingRequest(16383,2,"Lonavala");
		bookingSystem.bookSeat(bookingRequest);
	}
	
	@Test
	public void shouldNotBookTicketAfterBookingEnoughTicket() throws NoTrainAvailable, NoSeatAvailable, InvalidBoardingPoint{

		BookingRequest bookingRequest=new BookingRequest(16382,9);
		Ticket bookSeat = bookingSystem.bookSeat(bookingRequest);
		Assert.assertEquals(Integer.valueOf(16382), bookSeat.getTrainNo());
		Assert.assertEquals(Integer.valueOf(9), bookSeat.getNoOfSeats());
		Assert.assertEquals("Pune", bookSeat.getBoardingPoint());
		Assert.assertEquals(Double.valueOf(900), bookSeat.getTotalAmount());

		try{
			bookingRequest=new BookingRequest(16382,9);
			bookingSystem.bookSeat(bookingRequest);
		}catch(NoSeatAvailable ne){
			Assert.assertEquals("No Seat Available in 16382", ne.getMessage());
		}
		
	}
	
	@Test
	public void shouldBookTicketForGivenSourceAndDestination() throws NoTrainAvailable, NoSeatAvailable, InvalidBoardingPoint{
		BookingRequest bookingRequest = new BookingRequest(16382,5, "Pune","Karjat");
		Ticket bookSeat = bookingSystem.bookSeat(bookingRequest);
		Assert.assertNotNull(bookSeat);
		
		bookingRequest = new BookingRequest(16382,2, "Lonavala","Karjat");
		bookSeat = bookingSystem.bookSeat(bookingRequest);
		Assert.assertNotNull(bookSeat);
		
		bookingRequest = new BookingRequest(16382,3, "Pune","Lonavala");
		bookSeat = bookingSystem.bookSeat(bookingRequest);
		Assert.assertNotNull(bookSeat);
		
		bookingRequest = new BookingRequest(16382,5,"Lonavala","Karjat");
		bookSeat = bookingSystem.bookSeat(bookingRequest);
		Assert.assertNotNull(bookSeat);
		
		bookingRequest = new BookingRequest(16382,8,"Karjat","CST");
//		try{
		bookSeat = bookingSystem.bookSeat(bookingRequest);
		Assert.assertNotNull(bookSeat);
//		}catch(NoSeatAvailable e){
//			Assert.assertEquals("No Seat Available in 16382", e.getMessage());
//		}
		bookingRequest = new BookingRequest(16382,2,"Karjat","CST");
		bookSeat = bookingSystem.bookSeat(bookingRequest);
		Assert.assertNotNull(bookSeat);
		
	}
	
	
	@Test
	public void shouldBookTicketIfIntermediatStationPassengerIsPresent(){
		BookingRequest request = new BookingRequest(16382, 9);
	}
}
