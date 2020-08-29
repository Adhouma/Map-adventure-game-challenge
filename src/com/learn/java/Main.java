package com.learn.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	/**
	 * Create a Location class with locationID and description as fields Every
	 * location should have at least 1 exit + "QUIT" exit
	 * 
	 * The main locations are: - You are sitting in front of a computer learning
	 * Java - You are standing at the end of a road before a small brick building -
	 * You are at the top of a hill - You are inside a building, a well house for a
	 * small spring - You are in a valley beside a stream - You are in the forest
	 * 
	 * You start the game at the location 1 => "You are standing at the end of a
	 * road before a small brick building" The player can exit the game if he reach
	 * the location 0 => "You are sitting in front of a computer learning Java"
	 * 
	 * See the image Adventure-game-exits.PNG located in the Exits folder to know
	 * every location exits
	 * 
	 * The player should only move to the available exits for every location
	 * 
	 * Change the program to allow players to type full words, or phrases, then move
	 * to the correct location based upon their input
	 * 
	 * The player should be able to type commands such as - "Go West", - "run
	 * South", - even "Can i go to the south please ?" - or just "East" and the
	 * program will move to the appropriate location if there is one.
	 * 
	 * As at present, an attempt to move in an invalid direction should print a
	 * message and remain in the same place. Single letter commands (N, W, S, E, Q)
	 * should still be available.
	 */
	private static Map<Integer, Location> locations = new HashMap<>();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {

		// Locations
		locations.put(0, new Location(0, "You are sitting in front of a computer learning Java"));
		locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building"));
		locations.put(2, new Location(2, "You are at the top of a hill"));
		locations.put(3, new Location(3, "You are inside a building, a well house for a small spring"));
		locations.put(4, new Location(4, "You are in a valley beside a stream"));
		locations.put(5, new Location(5, "You are in the forest"));

		/**
		 * Exists N => NORTH S => SOUTH W => WEST E => EAST Q => QUIT
		 */
		// Location 1 exits
		locations.get(1).addExist("N", 5);
		locations.get(1).addExist("S", 4);
		locations.get(1).addExist("W", 2);
		locations.get(1).addExist("E", 3);

		// Location 2 exits
		locations.get(2).addExist("N", 5);

		// Location 3 exits
		locations.get(3).addExist("W", 1);

		// Location 4 exits
		locations.get(4).addExist("N", 1);
		locations.get(4).addExist("W", 2);

		// Location 5 exits
		locations.get(5).addExist("S", 1);
		locations.get(5).addExist("W", 2);

		Map<String, String> vocabulary = new HashMap<>();
		vocabulary.put("NORTH", "N");
		vocabulary.put("SOUTH", "S");
		vocabulary.put("WEST", "W");
		vocabulary.put("EAST", "E");
		vocabulary.put("QUIT", "Q");

		// Game
		System.out.println("/*--- Map Adventure game ---*/");
		int currentLocation = 1;
		boolean quit = false;
		while (!quit) {
			// Current location
			System.out.println("Current location: " + locations.get(currentLocation).getDescription());

			// Available exits for currentLocation
			Map<String, Integer> availableExits = locations.get(currentLocation).getExits();
			for (String exit : availableExits.keySet()) {
				System.out.print(exit + ", ");
			}

			// New line
			System.out.println("");

			// Enter the new direction
			System.out.println("Where to go ?");
			String direction = scanner.nextLine().toUpperCase();
			if (direction.length() > 1) {
				String[] words = direction.split(" ");
				for (String word : words) {
					if (vocabulary.containsKey(word)) {
						direction = vocabulary.get(word);
					}
				}
			}

			if (availableExits.containsKey(direction)) {
				currentLocation = availableExits.get(direction);
			} else {
				System.out.println("(Wrong / unavailable) exit, try again ?");
			}

			// Quit the game if currentLocation "in front of a computer learning Java"
			if (currentLocation == 0) {
				System.out.println("You are out of the game, Goodbye.");
				quit = true;
			}
		}
	}
}
