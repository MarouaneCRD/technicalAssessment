package com.rental.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {
	private final String reservationId;
	private final String clientId;
	private final Car car;
	private final LocalDateTime pickupdateTime;
	private final LocalDateTime returnDateTime;

	public Reservation(String clientId, Car car, LocalDateTime pickupdateTime, int desiredRentaldays) {

		if (desiredRentaldays <= 0)
			throw new IllegalArgumentException("Book At Leat 1 day !");
		if (clientId == null || clientId.isEmpty())
			throw new IllegalArgumentException("Customer is mandatory!");
		if (car == null)
			throw new IllegalArgumentException("car can not be null!");
		if (pickupdateTime == null)
			throw new IllegalArgumentException("pickupdateTime can not be null!");

		this.reservationId = UUID.randomUUID().toString();
		this.clientId = clientId;
		this.car = car;
		this.pickupdateTime = pickupdateTime;
		this.returnDateTime = pickupdateTime.plusDays(desiredRentaldays);

	}

	public String getReservationId() {
		return reservationId;
	}

	public String getClientId() {
		return clientId;
	}

	public Car getCar() {
		return car;
	}

	public LocalDateTime getPickupdateTime() {
		return pickupdateTime;
	}

	public LocalDateTime getReturnDateTime() {
		return returnDateTime;
	}

	public boolean IsReservationOverLapping(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		return this.pickupdateTime.isBefore(endDateTime) && this.returnDateTime.isAfter(startDateTime);

	}

}
