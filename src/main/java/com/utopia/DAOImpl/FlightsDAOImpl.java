package com.utopia.DAOImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utopia.Connection.ConnectionUtility;
import com.utopia.DAO.FlightsDAO;
import com.utopia.model.Airport;
import com.utopia.model.Flights;

public class FlightsDAOImpl implements FlightsDAO {

	String file = "\"\\\"C:\\\\\\\\Users\\\\\\\\Abesari\\\\\\\\smoothstack\\\\\\\\UtopiaAirline\\\\\\\\src\\\\\\\\main\\\\\\\\java\\\\\\\\Resources\\\\\\\\connection.properties\\\"\"";

	@Override
	public List<Flights> viewAllFlights() {
		List<Flights> flightList = new ArrayList<>();
		try(Connection con = ConnectionUtility.getConnectionFromFile(file) ){
			String query = "SELECT * FROM FLIGHTS";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int flightID = rs.getInt("flightID");
				int employeeID = rs.getInt("employeeID");
				String arrivalTime = rs.getString("arrivalTime");
				String departureTime = rs.getString("departureTime");
				String arrivalDate = rs.getString("arrivalDate");
				String departureDate = rs.getString("departureDate");
				int departureAirportID = rs.getInt("departureAirport");
				int arrivalAirportID = rs.getInt("arrivalAirport");
				int numOfFirstClass = rs.getInt("numOfFirstClass");
				int numOfBusiness = rs.getInt("numOfBusiness");
				int numOfEconomy = rs.getInt("numOfEconomy");
				Airport arrivalAirport = getAirPortInfoById(arrivalAirportID);
				Airport departureAirport = getAirPortInfoById(departureAirportID);
				
				//Foreign Key Retrieval from Airport table

				Flights flight = new Flights(flightID, employeeID, arrivalTime, departureTime, arrivalDate, 
						 departureDate,  departureAirportID,  arrivalAirportID,
						 numOfFirstClass,  numOfBusiness,  numOfEconomy, departureAirport,  arrivalAirport);
		     
				flightList.add(flight);				
			}
			
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return flightList;
	}
	
	public Airport getAirPortInfoById(int airportID) {
		Airport airport = new Airport();
		try(Connection con = ConnectionUtility.getConnectionFromFile(file) ){

		 String airportQuery = "SELECT * FROM AIRPORT WHERE airportID = ?";
         PreparedStatement airportStmt = con.prepareStatement(airportQuery);
         airportStmt.setInt(1, airportID);
         ResultSet airportRs = airportStmt.executeQuery();
         while(airportRs.next()) {
        	 
         String city = airportRs.getString("city");
         String name = airportRs.getString("name");
         airport.setCity(city);
         airport.setName(name);
         airport.setAirportID(airportID);
         } 
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return airport;
	}

	@Override
	public Flights getFlightByID(int flightID) {
		Flights flight = new Flights(flightID);
        try(Connection con = ConnectionUtility.getConnectionFromFile(file)){
            String query = "SELECT * FROM flights WHERE flightID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, flightID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            		String arrivalTime = rs.getString("arrivalTime");
            		String departureTime = rs.getString("departureTime");
            		String arrivalDate = rs.getString("arrivalDate");
            		String departureDate = rs.getString("departureDate");
                	int departureAirportID = rs.getInt("departureAirport");
                	int arrivalAirportID = rs.getInt("arrivalAirport");
	                int numOfFirstClass = rs.getInt("numOfFirstClass");
	                int numOfBusiness = rs.getInt("numOfBusiness");
	                int numOfEconomy = rs.getInt("numOfEconomy");
	                Airport departureAirport = getAirportByID(departureAirportID);
	                Airport arrivalAirport = getAirportByID(arrivalAirportID);

	                flight.setArrivalTime(arrivalTime);
	                flight.setDepartureTime(departureTime);
	                flight.setArrivalDate(arrivalDate);
	                flight.setDepartureDate(departureDate);
	                flight.setDepartureAirportID(departureAirportID);
	                flight.setArrivalAirportID(arrivalAirportID);
	                flight.setNumOfFirstClass(numOfFirstClass);
	                flight.setNumOfBusiness(numOfBusiness);
	                flight.setNumOfEconomy(numOfEconomy);
	                flight.setDepartureAirport(departureAirport);
	                flight.setArrivalAirport(arrivalAirport);
                }
                return flight;
                } catch (SQLException | IOException e) {
                e.printStackTrace();
                }
                System.out.println("Something went wrong");
                return flight;
		
	}

	@Override
	public List<Flights> getListofFlightsbyEmployeeId(int employeeId) {
		List<Flights> flights = new ArrayList<>();
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)){
		String query = "SELECT * FROM flights WHERE employeeId = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, employeeId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			int flightID = rs.getInt("flightID");
			int employeeID = rs.getInt("employeeID");
			String arrivalTime = rs.getString("arrivalTime");
			String departureTime = rs.getString("departureTime");
			String arrivalDate = rs.getString("arrivalDate");
			String departureDate = rs.getString("departureDate");
			int departureAirportID = rs.getInt("departureAirport");
			int arrivalAirportID = rs.getInt("arrivalAirport");
			int numOfFirstClass = rs.getInt("numOfFirstClass");
			int numOfBusiness = rs.getInt("numOfBusiness");
			int numOfEconomy = rs.getInt("numOfEconomy");
			Airport arrivalAirport = getAirPortInfoById(arrivalAirportID);
			Airport departureAirport = getAirPortInfoById(departureAirportID);
			
			//Foreign Key Retrieval from Airport table

			Flights flight = new Flights(flightID, employeeID, arrivalTime, departureTime, arrivalDate, 
					 departureDate,  departureAirportID,  arrivalAirportID,
					 numOfFirstClass,  numOfBusiness,  numOfEconomy, departureAirport,  arrivalAirport);
	     
			flights.add(flight);				
		}
		
		
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return flights;
	}

	@Override
	public Airport getAirportByID(int airportID) {
		Airport airport = new Airport(airportID);
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)){
			String query = "SELECT * FROM airport WHERE airportID = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, airportID);
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				String city = rs.getString("city");
				String name = rs.getString("name");
				Airport airportRS = new Airport();
				airportRS.setAirportID(airportID);
				airportRS.setCity(city);
				airportRS.setName(name);
				return airportRS;
		} 
	} catch (SQLException | IOException e) {
		e.printStackTrace();
	}
		System.out.println("Something went wrong");
		return airport; 
	}

	@Override
	public boolean updateFlight(Flights flight) {
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)){
			String query = "UPDATE flights SET departureTime = ?, arrivalTime = ?, departureDate = ?, arrivalDate = ?, departureAirport = ?, arrivalAirport = ?, numOfFirstClass = ?, numOfBusiness = ?, numOfEconomy = ? WHERE flightID = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, flight.getDepartureTime());
			stmt.setString(2, flight.getArrivalTime());
			stmt.setString(3, flight.getDepartureDate());
			stmt.setString(4, flight.getArrivalDate());
			stmt.setInt(5, flight.getDepartureAirportID());
			stmt.setInt(6, flight.getArrivalAirportID());
			stmt.setInt(7, flight.getNumOfFirstClass());
			stmt.setInt(8, flight.getNumOfBusiness());
			stmt.setInt(9, flight.getNumOfEconomy());
			stmt.setInt(10, flight.getFlightID());
			int result = stmt.executeUpdate();
			if(result > 0) {
			return true;
			}
			} catch (SQLException | IOException e) {
			e.printStackTrace();
			}
			return false;
			}

	@Override
	public boolean hasAvailableSeats(int flightID, String seatClass) {
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
		String query = "SELECT numOfFirstClass, numOfBusiness, numOfEconomy FROM flights WHERE flightID = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, flightID);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
		int numOfFirstClass = rs.getInt("numOfFirstClass");
		int numOfBusiness = rs.getInt("numOfBusiness");
		int numOfEconomy = rs.getInt("numOfEconomy");
		if (seatClass.equalsIgnoreCase("first") && numOfFirstClass > 0) {
		return true;
		} else if (seatClass.equalsIgnoreCase("business") && numOfBusiness > 0) {
		return true;
		} else if (seatClass.equalsIgnoreCase("economy") && numOfEconomy > 0) {
		return true;
		}
		}
		return false; // return false outside of the if statement to make sure the method always returns a boolean value
		} catch (SQLException | IOException e) {
		e.printStackTrace();
		return false;
		}
		}

	@Override
	public boolean updateFlightSeats(Flights flight) {
		 try(Connection con = ConnectionUtility.getConnectionFromFile(file)){
		        String query = "UPDATE flights SET numOfFirstClass = ?, numOfBusiness = ?, numOfEconomy = ? WHERE flightID = ?";
		        PreparedStatement stmt = con.prepareStatement(query);
		        stmt.setInt(1, flight.getNumOfFirstClass());
		        stmt.setInt(2, flight.getNumOfBusiness());
		        stmt.setInt(3, flight.getNumOfEconomy());
		        stmt.setInt(4, flight.getFlightID());
		        int result = stmt.executeUpdate();
		        if(result > 0) {
		            return true;
		        }
		    } catch (SQLException | IOException e) {
		        e.printStackTrace();
		    }
		    return false;
	}

	@Override
	public boolean addNewFlight(Flights flight) {
		 try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
	            String query = "INSERT INTO flights (employeeID, departureTime, arrivalTime, departureDate, arrivalDate, departureAirport, arrivalAirport, numOfFirstClass, numOfBusiness, numOfEconomy) VALUES (?,?,?,?,?,?,?,?,?,?)";
	            PreparedStatement stmt = con.prepareStatement(query);
	            stmt.setInt(1, flight.getEmployeeID());
	            stmt.setString(2, flight.getDepartureTime());
	            stmt.setString(3, flight.getArrivalTime());
	            stmt.setString(4, flight.getDepartureDate());
	            stmt.setString(5, flight.getArrivalDate());
	            stmt.setInt(6, flight.getDepartureAirportID());
	            stmt.setInt(7, flight.getArrivalAirportID());
	            stmt.setInt(8, flight.getNumOfFirstClass());
	            stmt.setInt(9, flight.getNumOfBusiness());
	            stmt.setInt(10, flight.getNumOfEconomy());
	            int result = stmt.executeUpdate();
	            if(result > 0) {
	            return true;
	            }
	            } catch (SQLException | IOException e) {
	            e.printStackTrace();
	            }
	            return false;
	            }

	@Override
	public boolean deleteFlight(Flights flight) {
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)){
			String query = "DELETE FROM flights WHERE flightID = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, flight.getFlightID());
			int result = stmt.executeUpdate();
			if(result > 0) {
			return true;
			}
			} catch (SQLException | IOException e) {
			e.printStackTrace();
			}
			return false;
			}
	


}

	
	


