package com.utopia.DAOImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utopia.Connection.ConnectionUtility;
import com.utopia.DAO.TicketsDAO;
import com.utopia.model.Flights;
import com.utopia.model.Tickets;

public class TicketsDAOImpl implements TicketsDAO {

	FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
	String file = "\"\\\"C:\\\\\\\\Users\\\\\\\\Abesari\\\\\\\\smoothstack\\\\\\\\UtopiaAirline\\\\\\\\src\\\\\\\\main\\\\\\\\java\\\\\\\\Resources\\\\\\\\connection.properties\\\"\"";

	
	@Override
	public void bookTicket(int travelerID, int flightID, String seatClass) {
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
			String query = "INSERT INTO tickets (flightFK, travelerFK, seatClass) VALUES (?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, flightID);
			stmt.setInt(2, travelerID);
			stmt.setString(3, seatClass);
			int result = stmt.executeUpdate();
			if(result > 0) {
			System.out.println("Ticket booked successfully!");
			} else {
			System.out.println("Error booking ticket, please try again.");
			}
			} catch (SQLException | IOException e) {
			e.printStackTrace();
			}
		
	}

	@Override
	public List<Tickets> getUpcomingTripsByTravelerID(int travelerID) {
		List<Tickets> tickets = new ArrayList<>();
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
		String query = "SELECT * FROM tickets WHERE travelerFK = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, travelerID);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
		Tickets ticket = new Tickets();
		int flightID = rs.getInt("flightFK");
		Flights flight = flightsDAO.getFlightByID(flightID);
		ticket.setFlight(flight);
		ticket.setTicketID(rs.getInt("ticketID"));
		ticket.setSeatClass(rs.getString("seatClass"));
		ticket.setCanceled(rs.getBoolean("canceled"));
		tickets.add(ticket);
		}
		} catch (SQLException | IOException e) {
		e.printStackTrace();
		}
		return tickets;
		}

	@Override
	public boolean cancelTicket(int ticketID) {
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
	        String query = "UPDATE tickets SET canceled = true WHERE ticketID = ?";
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setInt(1, ticketID);
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
	public boolean updateExistingTicket(Tickets ticket) {
		 try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
		        String query = "UPDATE tickets SET flightFK = ?, travelerFK = ?, seatClass = ? WHERE ticketID = ?";
		        PreparedStatement stmt = con.prepareStatement(query);
		        stmt.setInt(1, ticket.getFlightFK());
		        stmt.setInt(2, ticket.getTravelerFK());
		        stmt.setString(3, ticket.getSeatClass());
		        stmt.setInt(4, ticket.getTicketID());
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
	public boolean deleteTicket(int ticketID) {
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
	        String query = "DELETE FROM tickets WHERE ticketID = ?";
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setInt(1, ticketID);
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
	public List<Tickets> viewAllTickets() {
		List<Tickets> tickets = new ArrayList<>();
	    try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
	        String query = "SELECT * FROM tickets";
	        PreparedStatement stmt = con.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();
	        while(rs.next()) {
	            Tickets ticket = new Tickets();
	            ticket.setTicketID(rs.getInt("ticketID"));
	            ticket.setFlightFK(rs.getInt("flightFK"));
	            ticket.setTravelerFK(rs.getInt("travelerFK"));
	            ticket.setSeatClass(rs.getString("seatClass"));
	            tickets.add(ticket);
	        }
	        return tickets;
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	        return tickets;
	    }

}

	@Override
	public boolean addNewTicket(Tickets ticket) {
		try(Connection con = ConnectionUtility.getConnectionFromFile(file)) {
	        String query = "INSERT INTO tickets (travelerFK, flightFK, seatClass) VALUES (?,?,?)";
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setInt(1, ticket.getTravelerFK());
	        stmt.setInt(2, ticket.getFlightFK());
	        stmt.setString(3, ticket.getSeatClass());
	        int result = stmt.executeUpdate();
	        if(result > 0) {
	            System.out.println("Successfully added new ticket.");
	        } else {
	            System.out.println("Error adding new ticket.");
	        }
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    }
		return false;
		
	}
}