package com.rental.model;

public class Car {
	private final String id;
	private final CarType type;

	public Car(String id, CarType type) {
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("Car Id should be assigned");
		this.id = id;
		if (type == null)
			throw new IllegalArgumentException("Car type should be assigned");
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public CarType getType() {
		return type;
	}

}
