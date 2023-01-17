package com.utopia.DAO;

import java.util.List;

import com.utopia.model.Travelers;

public interface TravelersDAO {
	public boolean isValidMembershipNumber(int TravelerID);
	public boolean hasAvailableSeats(int flightID, String seatClass);
	public boolean addNewTraveler(Travelers traveler);
    public boolean updateExistingTraveler(Travelers traveler);
    public boolean deleteTraveler(int travelerID);
    public List<Travelers> viewAllTravelers();
    public Travelers getTravelerByID(int travelerID);
}
