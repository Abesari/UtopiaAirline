package com.utopia.model;

public class Airport {

	
	public int airportID;
	public String city;
	public String name;
	public int getAirportID() {
		return airportID;
	}
	public void setAirportID(int airportID) {
		this.airportID = airportID;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Airport [airportID=" + airportID + ", city=" + city + ", name=" + name + "]";
	}
	
	public Airport() {
	}
	
	public Airport(int airportID, String city, String name) {
		super();
		this.airportID = airportID;
		this.city = city;
		this.name = name;
	}
	public Airport(int airportID) {
		this.setAirportID(airportID);	}
	
}
