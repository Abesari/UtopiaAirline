package com.utopia.DAO;

import java.util.List;

import com.utopia.model.Airport;

public interface AirportDAO {

	 public boolean addNewAirport(Airport airport);
	    public boolean updateExistingAirport(Airport airport);
	    public boolean deleteAirport(int airportID);
	    public List<Airport> viewAllAirports();
	    public Airport getAirportByID(int airportID);
}
