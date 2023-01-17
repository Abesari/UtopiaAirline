package com.utopia.model;

public class Flights {

	public int flightID;
	public int employeeID;
	public String arrivalTime;
	public String departureTime;
	public String arrivalDate;
	public String departureDate;
	public int departureAirportID;
	public int arrivalAirportID;
	public int numOfFirstClass;
	public int numOfBusiness;
	public int numOfEconomy;
	public Airport departureAirport;
	public Airport arrivalAirport;
	public int getFlightID() {
		return flightID;
	}
	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public int getDepartureAirportID() {
		return departureAirportID;
	}
	public void setDepartureAirportID(int departureAirportID) {
		this.departureAirportID = departureAirportID;
	}
	public int getArrivalAirportID() {
		return arrivalAirportID;
	}
	public void setArrivalAirportID(int arrivalAirportID) {
		this.arrivalAirportID = arrivalAirportID;
	}
	public int getNumOfFirstClass() {
		return numOfFirstClass;
	}
	public void setNumOfFirstClass(int numOfFirstClass) {
		this.numOfFirstClass = numOfFirstClass;
	}
	public int getNumOfBusiness() {
		return numOfBusiness;
	}
	public void setNumOfBusiness(int numOfBusiness) {
		this.numOfBusiness = numOfBusiness;
	}
	public int getNumOfEconomy() {
		return numOfEconomy;
	}
	public void setNumOfEconomy(int numOfEconomy) {
		this.numOfEconomy = numOfEconomy;
	}
	public Airport getDepartureAirport() {
		return departureAirport;
	}
	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}
	public Airport getArrivalAirport() {
		return arrivalAirport;
	}
	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	@Override
	public String toString() {
		return "Flights [flightID=" + flightID + ", employeeID=" + employeeID + ", arrivalTime=" + arrivalTime
				+ ", departureTime=" + departureTime + ", arrivalDate=" + arrivalDate + ", departureDate="
				+ departureDate + ", departureAirportID=" + departureAirportID + ", arrivalAirportID="
				+ arrivalAirportID + ", numOfFirstClass=" + numOfFirstClass + ", numOfBusiness=" + numOfBusiness
				+ ", numOfEconomy=" + numOfEconomy + ", departureAirport=" + departureAirport + ", arrivalAirport="
				+ arrivalAirport + "]";
	}
	public Flights(int flightID, int employeeID, String arrivalTime, String departureTime, String arrivalDate,
			String departureDate, int departureAirportID, int arrivalAirportID, int numOfFirstClass, int numOfBusiness,
			int numOfEconomy, Airport departureAirport, Airport arrivalAirport) {
		super();
		this.flightID = flightID;
		this.employeeID = employeeID;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.departureAirportID = departureAirportID;
		this.arrivalAirportID = arrivalAirportID;
		this.numOfFirstClass = numOfFirstClass;
		this.numOfBusiness = numOfBusiness;
		this.numOfEconomy = numOfEconomy;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
	}
	
	

	public Flights() {}
	public Flights(int flightID2) {
		
	}
	
	
}
