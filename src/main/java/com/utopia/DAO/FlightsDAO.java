package com.utopia.DAO;

import java.util.List;

import com.utopia.model.Airport;
import com.utopia.model.Flights;

public interface FlightsDAO {
	
	public List<Flights> viewAllFlights(); 

	public boolean addNewFlight(Flights flight);
	public boolean deleteFlight(Flights flight);
	 
    public Flights getFlightByID(int flightID);

    public List<Flights> getListofFlightsbyEmployeeId(int employeeId);
    
    public boolean updateFlight(Flights flight);
    
    public Airport getAirportByID(int airportID);
    public boolean hasAvailableSeats(int flightID, String seatClass);
   
    public boolean updateFlightSeats(Flights flight);
}
