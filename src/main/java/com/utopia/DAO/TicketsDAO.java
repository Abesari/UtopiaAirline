package com.utopia.DAO;

import java.util.List;

import com.utopia.model.Tickets;

public interface TicketsDAO {

	public void bookTicket(int travelerID, int flightID, String seatClass);
	public List<Tickets> getUpcomingTripsByTravelerID(int travelerID);
	public boolean cancelTicket(int ticketID);
	public boolean addNewTicket(Tickets ticket);
    public boolean updateExistingTicket(Tickets ticket);
    public boolean deleteTicket(int ticketID);
    public List<Tickets> viewAllTickets();
}
