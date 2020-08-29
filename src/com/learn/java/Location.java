package com.learn.java;

import java.util.HashMap;
import java.util.Map;

public class Location {

	// Fields
	private final int locationID;
	private final String description;
	/**
	 * String => direction
	 * Integer => location
	 */
	private final Map<String, Integer> exits;
	
	// Constructor
	public Location(int locationID, String description) {
		this.locationID = locationID;
		this.description = description;
		this.exits = new HashMap<>();
		// Every location have an QUIT exit
		this.exits.put("Q", 0);
	}
	
	// Methods
	public void addExist(String direction, int location) {
		this.exits.put(direction, location);
	}
	
	// Getters
	public int getLocationID() {
		return locationID;
	}

	public String getDescription() {
		return description;
	}

	public Map<String, Integer> getExits() {
		return new HashMap<String, Integer>(exits);
	}
}
