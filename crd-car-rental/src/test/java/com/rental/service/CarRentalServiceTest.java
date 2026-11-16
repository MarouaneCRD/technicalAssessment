package com.rental.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rental.exception.FullyBookedException;
import com.rental.model.*;

public class CarRentalServiceTest {

	private CarRentalService carRentalService;
	private final LocalDateTime pickupDateTime = LocalDateTime.of(2026, Month.NOVEMBER, 15, 8, 0);

	@BeforeEach
	void SetUp() {
		carRentalService = new CarRentalService();
		carRentalService.addCar(new Car("Sedan", CarType.SEDAN));
		carRentalService.addCar(new Car("Suv-Black", CarType.SUV));
		carRentalService.addCar(new Car("Suv-Red", CarType.SUV));
		carRentalService.addCar(new Car("van", CarType.VAN));
	}

	@Test
	void BookingAvailableCar() {
		Reservation reservation = carRentalService.bookACar(CarType.VAN, "Marouane", pickupDateTime, 5);
		assertNotNull(reservation.getReservationId());
		assertEquals("van", reservation.getCar().getId());
		assertEquals(pickupDateTime.plusDays(5), reservation.getReturnDateTime());
	}

	@Test
	void thowFullyBookedException() {
		carRentalService.bookACar(CarType.VAN, "Marouane", pickupDateTime, 5);
		assertThrows(FullyBookedException.class,
				() -> carRentalService.bookACar(CarType.VAN, "Marouane", pickupDateTime, 5));
	}

	@Test
	void throwExceptionForOverlappingDates() {
		carRentalService.bookACar(CarType.VAN, "Marouane", pickupDateTime, 5);
		assertThrows(FullyBookedException.class,
				() -> carRentalService.bookACar(CarType.VAN, "Srujan", pickupDateTime, 3));
	}

	@Test
	void catchIllegalArguments() {
		assertThrows(IllegalArgumentException.class,
				() -> carRentalService.bookACar(CarType.VAN, "", pickupDateTime, 5));
		assertThrows(IllegalArgumentException.class, () -> carRentalService.bookACar(null, "", pickupDateTime, 5));

	}
}
