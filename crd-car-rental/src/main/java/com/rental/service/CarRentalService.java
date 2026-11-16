package com.rental.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.rental.exception.FullyBookedException;
import com.rental.model.*;

public class CarRentalService {

	private final List<Reservation> reservations = new ArrayList<>();
	private final Map<CarType, List<Car>> rentalInventory =  new HashMap<CarType, List<Car>>();
	
	
	public CarRentalService() {
		for(CarType type : CarType.values()) {
			rentalInventory.put(type,new ArrayList<>());
		}
	}
	
	public synchronized void addCar(Car car) {
		if(car== null)
			throw new IllegalArgumentException("Add a valid Car !");
		rentalInventory.get(car.getType()).add(car);
	}
	
	public synchronized Reservation bookACar(CarType type, String clientId, LocalDateTime pickupDateTime, int desiredRentalDays) {
		if (desiredRentalDays <= 0)
			throw new IllegalArgumentException("Book At Leat 1 day !");
		if (type == null)
			throw new IllegalArgumentException("choose a valid car type!");
		if (pickupDateTime == null)
			throw new IllegalArgumentException("pickupdateTime can not be null!");
		
		LocalDateTime retunDateTime = pickupDateTime.plusDays(desiredRentalDays);
		
		Car availableCar = findAvailableCar(type, pickupDateTime, retunDateTime)
				.orElseThrow(() ->new  FullyBookedException("No car available for specified Criteria"));
		
		Reservation reservation = new Reservation(clientId,availableCar,pickupDateTime,desiredRentalDays);
		reservations.add(reservation);
		return reservation;
		
	}
	
	
	private Optional<Car> findAvailableCar(CarType type,LocalDateTime from, LocalDateTime to) {
		return rentalInventory.get(type).stream()
				.filter(c->isCarAvailableforBooking(c,from,to))
				.findFirst();
	}
	
	//car shouldn't be already reserved, and  not overlapping with an other reservation and not 
	private Boolean isCarAvailableforBooking(Car car, LocalDateTime from, LocalDateTime to) {
		return reservations.stream()
				.filter(r ->r.getCar().getId().equals(car.getId()))
				.noneMatch(r->r.IsReservationOverLapping(from, to));
	}
	
}
