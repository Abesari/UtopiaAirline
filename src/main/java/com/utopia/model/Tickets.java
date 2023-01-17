package com.utopia.model;

public class Tickets {

	public int ticketID;
	public int flightFK;
	public int travelerFK;
	public String seatClass;
	public boolean canceled;
	public Flights flight;
	public int getTicketID() {
		return ticketID;
	}
	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}
	public int getFlightFK() {
		return flightFK;
	}
	public void setFlightFK(int flightFK) {
		this.flightFK = flightFK;
	}
	public int getTravelerFK() {
		return travelerFK;
	}
	public void setTravelerFK(int travelerFK) {
		this.travelerFK = travelerFK;
	}
	public String getSeatClass() {
		return seatClass;
	}
	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}
	public boolean isCanceled() {
		return canceled;
	}
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	public Flights getFlight() {
		return flight;
	}
	public void setFlight(Flights flight) {
		this.flight = flight;
	}
	@Override
	public String toString() {
		return "Tickets [ticketID=" + ticketID + ", flightFK=" + flightFK + ", travelerFK=" + travelerFK
				+ ", seatClass=" + seatClass + ", canceled=" + canceled + ", flight=" + flight + "]";
	}
	
	
	
}
