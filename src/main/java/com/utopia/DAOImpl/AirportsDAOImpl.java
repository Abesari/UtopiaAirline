package com.utopia.DAOImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utopia.Connection.ConnectionUtility;
import com.utopia.DAO.AirportDAO;
import com.utopia.model.Airport;

public class AirportsDAOImpl implements AirportDAO {

	String file = "\"\\\"C:\\\\\\\\Users\\\\\\\\Abesari\\\\\\\\smoothstack\\\\\\\\UtopiaAirline\\\\\\\\src\\\\\\\\main\\\\\\\\java\\\\\\\\Resources\\\\\\\\connection.properties\\\"\"";

	@Override
	public boolean addNewAirport(Airport airport) {
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
	        String query = "INSERT INTO airport (airportID, city, name) VALUES (?,?,?)";
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setInt(1, airport.getAirportID());
	        stmt.setString(2, airport.getCity());
	        stmt.setString(3, airport.getName());
	        int result = stmt.executeUpdate();
	        if(result > 0) {
	            System.out.println("Successfully added new airport.");
	        } else {
	            System.out.println("Error adding new airport.");
	        }
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    }
		return false;
		
	}

	@Override
	public boolean updateExistingAirport(Airport airport) {
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
	        String query = "UPDATE airport SET city = ?, name = ? WHERE airportID = ?";
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setString(1, airport.getCity());
	        stmt.setString(2, airport.getName());
	        stmt.setInt(3, airport.getAirportID());
	        int result = stmt.executeUpdate();
	        if(result > 0) {
	            System.out.println("Successfully updated airport.");
	            return true;
	        } else {
	            System.out.println("Error updating airport.");
	            return false;
	        }
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean deleteAirport(int airportID) {
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
	        String query = "DELETE FROM airport WHERE airportID = ?";
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setInt(1, airportID);
	        int result = stmt.executeUpdate();
	        if(result > 0) {
	            System.out.println("Successfully deleted airport.");
	            return true;
	        } else {
	            System.out.println("Error deleting airport.");
	            return false;
	        }
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public List<Airport> viewAllAirports() {
		List<Airport> airports = new ArrayList<>();
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
	        String query = "SELECT * FROM airport";
	        PreparedStatement stmt = con.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();
	        while(rs.next()) {
	            Airport airport = new Airport();
	            airport.setAirportID(rs.getInt("airportID"));
	            airport.setCity(rs.getString("city"));
	            airport.setName(rs.getString("name"));
	            airports.add(airport);
	            
	        }
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    }
	    return airports;
	}
	

	@Override
	public Airport getAirportByID(int airportID) {
		Airport airport = new Airport();
	    try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
	        String query = "SELECT * FROM airport WHERE airportID = ?";
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setInt(1, airportID);
	        ResultSet rs = stmt.executeQuery();
	        if(rs.next()) {
	            airport.setAirportID(rs.getInt("airportID"));
	            airport.setCity(rs.getString("city"));
	            airport.setName(rs.getString("name"));
	        }
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    }
	    return airport;
	}
	}


