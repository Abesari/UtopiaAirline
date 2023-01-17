package com.utopia.DAOImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utopia.Connection.ConnectionUtility;
import com.utopia.DAO.TravelersDAO;
import com.utopia.model.Travelers;

public class TravelersDAOImpl implements TravelersDAO {

	String file = "\"\\\"C:\\\\\\\\Users\\\\\\\\Abesari\\\\\\\\smoothstack\\\\\\\\UtopiaAirline\\\\\\\\src\\\\\\\\main\\\\\\\\java\\\\\\\\Resources\\\\\\\\connection.properties\\\"\"";

	@Override
	public boolean isValidMembershipNumber(int TravelerID) {
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
			String travelerQuery = "SELECT * FROM travelers WHERE travelerID = ?";
			PreparedStatement travelerStmt = con.prepareStatement(travelerQuery);
			travelerStmt.setInt(1, TravelerID);
			ResultSet travelerRs = travelerStmt.executeQuery();
			if(travelerRs.next()) {
			return true;
			} else {
			return false;
			}
			} catch (SQLException | IOException e) {
			e.printStackTrace();
			return false;
			}
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
	public boolean addNewTraveler(Travelers traveler) {
		 try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
	            String query = "INSERT INTO travelers (name, email) VALUES (?,?)";
	            PreparedStatement stmt = con.prepareStatement(query);
	            stmt.setString(1, traveler.getName());
	            stmt.setString(2, traveler.getEmail());
	            int result = stmt.executeUpdate();
	            if(result > 0) {
	                return true;
	            } else {
	                return false;
	            }
	        } catch (SQLException | IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	}

	@Override
	public boolean updateExistingTraveler(Travelers traveler) {
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
	        String query = "UPDATE travelers SET name = ?, email = ? WHERE travelerID = ?";
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setString(1, traveler.getName());
	        stmt.setString(2, traveler.getEmail());
	        stmt.setInt(3, traveler.getTravelerID());
	        int result = stmt.executeUpdate();
	        if(result > 0) {
	            return true;
	        } else {
	            return false;
	        }
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean deleteTraveler(int travelerID) {
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
	        String query = "DELETE FROM travelers WHERE travelerID = ?";
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setInt(1, travelerID);
	        int result = stmt.executeUpdate();
	        if(result > 0) {
	            return true;
	        } else {
	            return false;
	        }
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public List<Travelers> viewAllTravelers() {
		
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
	        String query = "SELECT * FROM travelers";
	        PreparedStatement stmt = con.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();
	        List<Travelers> travelers = new ArrayList<>();
	        while(rs.next()) {
	            Travelers traveler = new Travelers();
	            traveler.setTravelerID(rs.getInt("travelerID"));
	            traveler.setName(rs.getString("name"));
	            traveler.setEmail(rs.getString("email"));
	            travelers.add(traveler);
	        }
	        return travelers;
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public Travelers getTravelerByID(int travelerID) {
		 try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
		        String query = "SELECT * FROM travelers WHERE travelerID = ?";
		        PreparedStatement stmt = con.prepareStatement(query);
		        stmt.setInt(1, travelerID);
		        ResultSet rs = stmt.executeQuery();
		        if(rs.next()) {
		            Travelers traveler = new Travelers();
		            traveler.setTravelerID(rs.getInt("travelerID"));
		            traveler.setName(rs.getString("name"));
		            traveler.setEmail(rs.getString("email"));
		            return traveler;
		        } else {
		            return null;
		        }
		    } catch (SQLException | IOException e) {
		        e.printStackTrace();
		        return null;
		    }
	}
	}
	


