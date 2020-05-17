package com.example.newcarbontruth;

import java.util.ArrayList;
import java.util.HashMap;

public class Calculations
{
	private HashMap<String, Double> foods;
	private HashMap<String, Double> vehicles;
	//footprint==tons of carbon per year
	private double footprint;

	/* upon initialization, the footprint is calculated with various methods within the class.
	 * The final value of the footprint can be accessed using the footprint's getter method.
	 */
	public Calculations(ArrayList<String> givenFood, ArrayList<String> givenVehicles)
	{
		foods = initializeFoodHashmap();
		vehicles = initializeVehiclesHashmap();
		double foodFootprint=0.0;
		double transportationFootprint=0.0;

		//parses through all of the food values in givenFood, calculates the footprint using foodCalculations(), and adds the totals together.
		for (String string : givenFood)
		{
			int space = string.indexOf(' ');
			String first = string.substring(0, space);
			double second = Double.parseDouble(string.substring(space+1));

			foodFootprint += foodCalculations(first, second);
		}

		//parses through all of the vehicle values in givenVehicles, calculates the footprint using transportationCalculations(), and adds the totals together.
		for (String string : givenVehicles)
		{
			int space = string.indexOf(' ');
			String first = string.substring(0, space);
			double second = Double.parseDouble(string.substring(space+1));

			transportationFootprint+=transportationCalculations(first, second);
		}

		//the total footprint is just the sum of the total food footprint and transportation footprint.
		footprint=foodFootprint+transportationFootprint;
	}

	public double transportationCalculations(String vehicleType, double givenMiles)
	{
		//if the mpg wasn't found in the controller database, mpg is set to 25.
		double mpg=25.0;
		//get mpg based on cartype.
		try {
			mpg= vehicles.get(vehicleType);
		}
		catch (NullPointerException e) {}

		//lbs of CO2 per gallon
		int lbpg=20;

		double totalPounds=givenMiles*(1/mpg)*lbpg;

		//pounds to ton
		int lbpt=2000;

		double totalTonsperYear=totalPounds*365/lbpt;
		return totalTonsperYear;
	}

	public double foodCalculations(String foodType, double givenGrams)
	{
		//if the number of kilos of co2 wasn't found in the controller database, number is set to 3.
		double kiloOfCO2perKiloFood=3.0;
		try {
			//kilos of co2 released from one kilo of given food, with data from (https://www.greeneatz.com/foods-carbon-footprint.html)
			kiloOfCO2perKiloFood = foods.get(foodType);
		}
		catch (NullPointerException e) { }

		double totalKilos = givenGrams/1000.0;

		//kilos to ton
		int kpt=907;

		double totalTonsperYear=totalKilos*365/kpt;
		return totalTonsperYear;
	}

	public double getFootprint()
	{
		return footprint;
	}

	//initializes the food hashmap
	private HashMap<String, Double> initializeFoodHashmap()
	{
		HashMap<String, Double> newFoods = new HashMap<String, Double>();

		newFoods.put("meat", 20.0);
		newFoods.put("dairy", 1.9);
		newFoods.put("fat", 2.3);
		newFoods.put("grain", 2.7);
		newFoods.put("vegetables", 2.0);

		return newFoods;
	}

	//initializes the vehicle hashmap
	private HashMap<String, Double> initializeVehiclesHashmap()
	{
		HashMap<String, Double> newVehicles = new HashMap<String, Double>();
		newVehicles.put("Motorcycle", 44.0);
		newVehicles.put("Car", 24.0);
		newVehicles.put("Van/Light Truck", 17.5);
		newVehicles.put("Transit", 3.26);
		newVehicles.put("Bicycle", 0.0);

		return newVehicles;
	}
}
