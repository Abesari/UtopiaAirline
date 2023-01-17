package com.utopia.Menu;
import java.util.List;
import java.util.Scanner;

import com.utopia.DAO.TravelersDAO;
import com.utopia.DAOImpl.AirportsDAOImpl;
import com.utopia.DAOImpl.FlightsDAOImpl;
import com.utopia.DAOImpl.TicketsDAOImpl;
import com.utopia.DAOImpl.TravelersDAOImpl;
import com.utopia.model.Airport;
import com.utopia.model.Flights;
import com.utopia.model.Tickets;
import com.utopia.model.Travelers;

public class MainMenu {

    TravelersDAOImpl travelersDAO = new TravelersDAOImpl();
    FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
    TicketsDAOImpl ticketDAO = new TicketsDAOImpl();
    public void display() {
        System.out.println("Welcome to the Utopia Airlines Management System.");
        System.out.println("Which category of user are you?");
        System.out.println("1) Employee/Agent");
        System.out.println("2) Administrator");
        System.out.println("3) Traveler");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                employeeMenu();
                break;
            case 2:
                administratorMenu();
                break;
            case 3:
                travelerMenu();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                display();
                break;
        }
    }

    public void employeeMenu() {
        System.out.println("EMPLOYEE MENU");
        System.out.println("1) Enter Flights You Manage");
        System.out.println("2) Quit to previous");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
                System.out.println("Enter your employee ID:");
                int employeeId = scanner.nextInt();
                List < Flights > flights = flightsDAO.getListofFlightsbyEmployeeId(employeeId);
                if (flights.size() == 0) {
                    System.out.println("No flights found for this employee ID");
                } else {
                    for (Flights flight: flights) {
                        System.out.println("Flight ID: " + flight.getFlightID() + " | Departure Airport: " + flight.getDepartureAirport().getName() +
                            " | Arrival Airport: " + flight.getArrivalAirport().getName());
                    }
                }
                manageFlight();
                break;
            case 2:
                display();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                employeeMenu();
                break;
        }
    }

    public void manageFlight() {
        System.out.println("MANAGE FLIGHT");
        System.out.println("1) View more details about the flight");
        System.out.println("2) Update the details of the Flight");
        System.out.println("3) Update amount of First, Business, or Economy seats");
        System.out.println("4) Quit to previous");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                viewFlightDetails();
                break;
            case 2:
                UpdateDetails();
                break;
            case 3:
                checkFlightSeats();
                break;
            case 4:
            default:
                System.out.println("Invalid choice. Please try again.");
                employeeMenu();
                break;
        }
    }




    private void checkFlightSeats() {
        FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
        System.out.println("Enter the flight ID:");
        Scanner scanner = new Scanner(System.in);
        int flightID = scanner.nextInt();
        Flights flight = flightsDAO.getFlightByID(flightID);
        if (flight == null) {
            System.out.println("No flight found with this ID");
        } else {
            System.out.println("Pick the Seat Class you want to add seats of, to your flight:");
            System.out.println("1) First");
            System.out.println("2) Business");
            System.out.println("3) Economy");
            System.out.println("4) Quit to cancel operation");
            int seatChoice = scanner.nextInt();
            switch (seatChoice) {
                case 1:
                    int numFirst = flight.getNumOfFirstClass();
                    System.out.println("Existing number of first class seats: " + numFirst);
                    System.out.println("Enter new number of seats:");
                    int newFirst = scanner.nextInt();
                    flight.setNumOfFirstClass(numFirst + newFirst);
                    flightsDAO.updateFlightSeats(flight);
                    System.out.println("Successfully added " + newFirst + " first class seats to flight ID: " + flightID);
                    break;
                case 2:
                    int numBusiness = flight.getNumOfBusiness();
                    System.out.println("Existing number of business class seats: " + numBusiness);
                    System.out.println("Enter new number of seats:");
                    int newBusiness = scanner.nextInt();
                    flight.setNumOfBusiness(numBusiness + newBusiness);
                    flightsDAO.updateFlightSeats(flight);
                    System.out.println("Successfully added " + newBusiness + " business class seats to flight ID: " + flightID);
                    break;
                case 3:
                    int numEconomy = flight.getNumOfEconomy();
                    System.out.println("Existing number of economy class seats: " + numEconomy);
                    System.out.println("Enter new number of seats:");
                    int newEconomy = scanner.nextInt();
                    flight.setNumOfEconomy(numEconomy + newEconomy);
                    flightsDAO.updateFlightSeats(flight);
                    System.out.println("Successfully added " + newEconomy + " economy class seats to flight ID: " + flightID);
                    break;
                case 4:
                    System.out.println("Cancelled operation");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    checkFlightSeats();
                    break;
            }
            manageFlight();
        }
    }



    public void UpdateDetails() {
        System.out.println("Enter the ID of the flight you want to update: ");
        Scanner scanner = new Scanner(System.in);
        int flightID = scanner.nextInt();
        scanner.nextLine();
        FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
        Flights flight = flightsDAO.getFlightByID(flightID);
        if (flight != null) {
            System.out.println("Updating flight details for flight ID: " + flightID);
            System.out.println("Enter new arrival time: ");
            String arrivalTime = scanner.nextLine();
            System.out.println("Enter new departure time: ");
            String departureTime = scanner.nextLine();
            System.out.println("Enter new arrival date: ");
            String arrivalDate = scanner.nextLine();
            System.out.println("Enter new departure date: ");
            String departureDate = scanner.nextLine();
            System.out.println("Enter new departure airport ID: ");
            int departureAirportID = scanner.nextInt();
            System.out.println("Enter new arrival airport ID: ");
            int arrivalAirportID = scanner.nextInt();
            System.out.println("Enter new number of first class seats: ");
            int numOfFirstClass = scanner.nextInt();
            System.out.println("Enter new number of business class seats: ");
            int numOfBusiness = scanner.nextInt();
            System.out.println("Enter new number of economy class seats: ");
            int numOfEconomy = scanner.nextInt();
            flight.setArrivalTime(arrivalTime);
            flight.setDepartureTime(departureTime);
            flight.setArrivalDate(arrivalDate);
            flight.setDepartureDate(departureDate);
            flight.setDepartureAirportID(departureAirportID);
            flight.setArrivalAirportID(arrivalAirportID);
            flight.setNumOfFirstClass(numOfFirstClass);
            flight.setNumOfBusiness(numOfBusiness);
            flight.setNumOfEconomy(numOfEconomy);
            boolean updateSuccessful = flightsDAO.updateFlight(flight);
            if (updateSuccessful) {
                System.out.println("Flight details updated successfully");
            } else {
                System.out.println("Error updating flight details, please try again later.");
            }
        } else {
            System.out.println("No flight found with ID: " + flightID);
        }
        manageFlight();
    }


    public void viewFlightDetails() {
        FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
        System.out.println("Enter the flight ID:");
        Scanner scanner = new Scanner(System.in);
        int flightID = scanner.nextInt();
        Flights flight = flightsDAO.getFlightByID(flightID);
        if (flight == null) {
            System.out.println("No flight found with this ID");
        } else {
            System.out.println("Flight ID: " + flight.getFlightID());
            System.out.println("Departure Time: " + flight.getDepartureTime());
            System.out.println("Arrival Time: " + flight.getArrivalTime());
            System.out.println("Departure Date: " + flight.getDepartureDate());
            System.out.println("Arrival Date: " + flight.getArrivalDate());
            System.out.println("Departure Airport: " + flight.getDepartureAirport().getName());
            System.out.println("Arrival Airport: " + flight.getArrivalAirport().getName());
            System.out.println("Number of First Class seats: " + flight.getNumOfFirstClass());
            System.out.println("Number of Business seats: " + flight.getNumOfBusiness());
            System.out.println("Number of Economy seats: " + flight.getNumOfEconomy());
        }
        manageFlight();

    }

    
    
    public void travelerMenu() {
        TravelersDAOImpl travelersDAO = new TravelersDAOImpl();
        FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
        TicketsDAOImpl ticketDAO = new TicketsDAOImpl();

        int travelerID = 0;
        while (true) {
            System.out.println("Enter your Membership Number:");
            Scanner scanner = new Scanner(System.in);
            travelerID = scanner.nextInt();
            if (travelersDAO.isValidMembershipNumber(travelerID)) {
                break;
            } else {
                System.out.println("Invalid membership number. Please try again.");
            }
        }
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("TRAV1:");
            System.out.println("1) Book a Ticket");
            System.out.println("2) Cancel an Upcoming Trip");
            System.out.println("3) Quit to Previous");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    bookTicket();
                    break;
                case 2:
                    cancelTrip();
                    break;
                case 3:
                    display();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void cancelTrip() {
        System.out.println("Enter your membership number:");
        Scanner scanner = new Scanner(System.in);
        int travelerID = scanner.nextInt();
        if (travelersDAO.isValidMembershipNumber(travelerID)) {
            List < Tickets > tickets = ticketDAO.getUpcomingTripsByTravelerID(travelerID);
            if (tickets.size() > 0) {
                System.out.println("Upcoming Trips:");
                for (int i = 0; i < tickets.size(); i++) {
                    System.out.println((i + 1) + ") " + tickets.get(i).getFlight().getDepartureAirport() + " -> " + tickets.get(i).getFlight().getArrivalAirport());
                }
                System.out.println("Which trip would you like to cancel? (Enter the corresponding number)");
                int choice = scanner.nextInt();
                if (choice > 0 && choice <= tickets.size()) {
                    Tickets ticket = tickets.get(choice - 1);
                    boolean isCancelled = ticketDAO.cancelTicket(ticket.getTicketID());
                    if (isCancelled) {
                        System.out.println("Trip successfully cancelled.");
                    } else {
                        System.out.println("Error cancelling trip. Please try again later.");
                    }
                } else {
                    System.out.println("Invalid choice. Please try again.");
                    cancelTrip();
                }
            } else {
                System.out.println("You have no upcoming trips.");
            }
        } else {
            System.out.println("Invalid membership number. Please try again.");
            cancelTrip();
        }
    }





    private void bookTicket() {
        TravelersDAOImpl travelersDAO = new TravelersDAOImpl();
        FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
        TicketsDAOImpl ticketsDAO = new TicketsDAOImpl();
        int travelerID;
        while (true) {

            System.out.println("Enter your Membership Number:");
            Scanner scanner = new Scanner(System.in);
            travelerID = scanner.nextInt();
            if (travelersDAO.isValidMembershipNumber(travelerID)) {
                break;
            } else {
                System.out.println("Invalid membership number. Please try again.");
            }
        }
        List < Flights > flights = flightsDAO.viewAllFlights();
        int flightID = 0;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Pick the Flight you want to book a ticket for:");
            for (int i = 0; i < flights.size(); i++) {
                System.out.println((i + 1) + ") " + flights.get(i).getDepartureAirport() + " -> " + flights.get(i).getArrivalAirport());
            }
            System.out.println((flights.size() + 1) + ") Quit to previous");
            int choice = scanner.nextInt();
            if (choice > 0 && choice <= flights.size()) {
                flightID = flights.get(choice - 1).getFlightID();
                break;
            } else if (choice == flights.size() + 1) {
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Pick the Seat you want to book a ticket for:");
            System.out.println("1) View Flight Details");
            System.out.println("2) First");
            System.out.println("3) Business");
            System.out.println("4) Economy");
            System.out.println("5) Quit to cancel operation");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Flights flight = flightsDAO.getFlightByID(flightID);
                    System.out.println("Flight Details:");
                    System.out.println("Departure Airport: " + flight.getDepartureAirport());
                    System.out.println("Arrival Airport: " + flight.getArrivalAirport());
                    System.out.println("Departure Time: " + flight.getDepartureTime());
                    System.out.println("Arrival Time: " + flight.getArrivalTime());
                    break;
                case 2:
                    if (flightsDAO.hasAvailableSeats(flightID, "first")) {
                        ticketsDAO.bookTicket(travelerID, flightID, "first");
                        System.out.println("Ticket booked successfully in first class!");
                        return;
                    } else {
                        System.out.println("Sorry, no available seats in first class. Please choose another class.");
                    }
                    break;
                case 3:
                    if (flightsDAO.hasAvailableSeats(flightID, "business")) {
                        ticketsDAO.bookTicket(travelerID, flightID, "business");
                        System.out.println("Ticket booked successfully in business class!");
                        return;
                    } else {
                        System.out.println("Sorry, no available seats in business class. Please choose another class.");
                    }
                    break;
                case 4:
                    if (flightsDAO.hasAvailableSeats(flightID, "economy")) {
                        ticketsDAO.bookTicket(travelerID, flightID, "economy");
                        System.out.println("Ticket booked successfully in economy class!");
                        return;
                    } else {
                        System.out.println("Sorry, no available seats in economy class. Please choose another class.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }

    public void administratorMenu() {
        System.out.println("ADMINISTRATOR MENU");
        System.out.println("1) Manage Flights");
        System.out.println("2) Manage Tickets");
        System.out.println("3) Manage Airports");
        System.out.println("4) Manage Travelers");
        System.out.println("5) Quit to previous");
        
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                manageFlights();
                break;
            case 2:
                manageTickets();
                break;
            case 3:
                manageAirports();
                break;
            case 4:
                manageTravelers();
                break;
            case 5:
                display();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                administratorMenu();
                break;
        }
    }

    public void manageFlights() {
    	System.out.println("MANAGE FLIGHTS");
    	System.out.println("1) Add a new flight");
    	System.out.println("2) Update an existing flight");
    	System.out.println("3) Delete a flight");
    	System.out.println("4) View all flights");
    	System.out.println("5) Quit to previous");
    	Scanner scanner = new Scanner(System.in);
    	int choice = scanner.nextInt();

    	switch (choice) {
    	    case 1:
    	        addNewFlight();
    	        break;
    	    case 2:
    	        updateExistingFlight();
    	        break;
    	    case 3:
    	        deleteFlight();
    	        break;
    	    case 4:
    	        viewAllFlights();
    	        break;
    	    case 5:
    	        administratorMenu();
    	        break;
    	    default:
    	        System.out.println("Invalid choice. Please try again.");
    	        manageFlights();
    	        break;
    	}
    	}

       
	private void addNewFlight() {
		FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
		Flights flight = new Flights();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the employee ID:");
		flight.setEmployeeID(scanner.nextInt());

		System.out.println("Enter the departure time (HH:MM):");
		flight.setDepartureTime(scanner.next());

		System.out.println("Enter the arrival time (HH:MM):");
		flight.setArrivalTime(scanner.next());

		System.out.println("Enter the departure date (YYYY-MM-DD):");
		flight.setDepartureDate(scanner.next());

		System.out.println("Enter the arrival date (YYYY-MM-DD):");
		flight.setArrivalDate(scanner.next());

		System.out.println("Enter the departure airport ID:");
		flight.setDepartureAirportID(scanner.nextInt());

		System.out.println("Enter the arrival airport ID:");
		flight.setArrivalAirportID(scanner.nextInt());

		System.out.println("Enter the number of first class seats:");
		flight.setNumOfFirstClass(scanner.nextInt());

		System.out.println("Enter the number of business class seats:");
		flight.setNumOfBusiness(scanner.nextInt());

		System.out.println("Enter the number of economy class seats:");
		flight.setNumOfEconomy(scanner.nextInt());

		boolean added = flightsDAO.addNewFlight(flight);
		if(added) {
		    System.out.println("Successfully added new flight.");
		} else {
		    System.out.println("Error adding new flight. Please try again.");
		}
		
	}

	private void updateExistingFlight() {
		FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
		Flights flight = new Flights();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the employee ID:");
		flight.setEmployeeID(scanner.nextInt());

		System.out.println("Enter the departure time (HH:MM):");
		flight.setDepartureTime(scanner.next());

		System.out.println("Enter the arrival time (HH:MM):");
		flight.setArrivalTime(scanner.next());

		System.out.println("Enter the departure date (YYYY-MM-DD):");
		flight.setDepartureDate(scanner.next());

		System.out.println("Enter the arrival date (YYYY-MM-DD):");
		flight.setArrivalDate(scanner.next());

		System.out.println("Enter the departure airport ID:");
		flight.setDepartureAirportID(scanner.nextInt());

		System.out.println("Enter the arrival airport ID:");
		flight.setArrivalAirportID(scanner.nextInt());

		System.out.println("Enter the number of first class seats:");
		flight.setNumOfFirstClass(scanner.nextInt());

		System.out.println("Enter the number of business class seats:");
		flight.setNumOfBusiness(scanner.nextInt());

		System.out.println("Enter the number of economy class seats:");
		flight.setNumOfEconomy(scanner.nextInt());

		boolean added = flightsDAO.updateFlight(flight);
		if(added) {
		    System.out.println("Successfully updates new flight.");
		} else {
		    System.out.println("Error updating new flight. Please try again.");
		}
		
	}

	private void deleteFlight() {
		FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
		

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the ID of the flight you want to delete:");
		int flightID = scanner.nextInt();
		Flights flight = flightsDAO.getFlightByID(flightID);
		if(flight == null) {
		System.out.println("No flight found with this ID");
		} else {
		if(flightsDAO.deleteFlight(flight)) {
		System.out.println("Flight with ID: " + flightID + " has been deleted.");
		} else {
		System.out.println("Error deleting flight with ID: " + flightID);
		}
		}
	}
	

		
	private void viewAllFlights() {
		 FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
		    List<Flights> flightList = flightsDAO.viewAllFlights();
		    if (flightList.isEmpty()) {
		        System.out.println("No flights found");
		    } else {
		        for (Flights flight : flightList) {
		            System.out.println("Flight ID: " + flight.getFlightID());
		            System.out.println("Employee ID: " + flight.getEmployeeID());
		            System.out.println("Departure Time: " + flight.getDepartureTime());
		            System.out.println("Arrival Time: " + flight.getArrivalTime());
		            System.out.println("Departure Date: " + flight.getDepartureDate());
		            System.out.println("Arrival Date: " + flight.getArrivalDate());
		            System.out.println("Departure Airport: " + flight.getDepartureAirport().getName());
		            System.out.println("Arrival Airport: " + flight.getArrivalAirport().getName());
		            System.out.println("Number of First Class Seats: " + flight.getNumOfFirstClass());
		            System.out.println("Number of Business Class Seats: " + flight.getNumOfBusiness());
		            System.out.println("Number of Economy Class Seats: " + flight.getNumOfEconomy());
		        }
		    }
		    manageFlights();
		
	}

	
	
	
	
	
	
	
	
	
	private void manageTickets() {
		System.out.println("MANAGE TICKETS");
	    System.out.println("1) Add a new ticket");
	    System.out.println("2) Update an existing ticket");
	    System.out.println("3) Delete a ticket");
	    System.out.println("4) View all tickets");
	    System.out.println("5) Quit to previous");
	    Scanner scanner = new Scanner(System.in);
	    int choice = scanner.nextInt();

	    switch (choice) {
	        case 1:
	            addNewTicket();
	            break;
	        case 2:
	            updateExistingTicket();
	            break;
	        case 3:
	            deleteTicket();
	            break;
	        case 4:
	            viewAllTickets();
	            break;
	        case 5:
	            administratorMenu();
	            break;
	        default:
	            System.out.println("Invalid choice. Please try again.");
	            manageTickets();
	            break;
	    }
		
	}

	private void addNewTicket() {
		 TicketsDAOImpl ticketsDAO = new TicketsDAOImpl();
		    Tickets ticket = new Tickets();

		    Scanner scanner = new Scanner(System.in);

		    System.out.println("Enter the traveler ID:");
		    ticket.setTravelerFK(scanner.nextInt());

		    System.out.println("Enter the flight ID:");
		    ticket.setFlightFK(scanner.nextInt());

		    System.out.println("Enter the seat class:");
		    ticket.setSeatClass(scanner.next());

		    boolean added = ticketsDAO.addNewTicket(ticket);
		    if(added) {
		        System.out.println("Successfully added new ticket.");
		    } else {
		        System.out.println("");
		    }
		
	}

	private void updateExistingTicket() {
		 TicketsDAOImpl ticketsDAO = new TicketsDAOImpl();
		    Tickets ticket = new Tickets();

		    Scanner scanner = new Scanner(System.in);

		    System.out.println("Enter the ticket ID:");
		    ticket.setTicketID(scanner.nextInt());

		    System.out.println("Enter the traveler ID:");
		    ticket.setTravelerFK(scanner.nextInt());
		    System.out.println("Enter the seat class:");
		    ticket.setSeatClass(scanner.next());

		    boolean updated = ticketsDAO.updateExistingTicket(ticket);
		    if(updated) {
		        System.out.println("Successfully updated the ticket.");
		    } else {
		        System.out.println("Error updating the ticket. Please try again.");
		    }
		
	}

	private void deleteTicket() {
		TicketsDAOImpl ticketsDAO = new TicketsDAOImpl();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the ticket ID:");
		int ticketID = scanner.nextInt();

		boolean deleted = ticketsDAO.deleteTicket(ticketID);
		if(deleted) {
		    System.out.println("Successfully deleted the ticket.");
		} else {
		    System.out.println("Error deleting the ticket. Please try again.");
		}
	}

	private void viewAllTickets() {
		TicketsDAOImpl ticketsDAO = new TicketsDAOImpl();
		List<Tickets> tickets = ticketsDAO.viewAllTickets();
		for (Tickets ticket : tickets){
			System.out.println("Ticket ID: " + ticket.getTicketID() + " | Traveler ID: " + ticket.getTravelerFK() + " | Flight ID: " + ticket.getFlightFK() + " | Seat Class: " + ticket.getSeatClass());
		}
		System.out.println("End of ticket list.");
		}
		
	

	

	private void manageAirports() {
		System.out.println("MANAGE AIRPORTS");
	    System.out.println("1) Add a new airport");
	    System.out.println("2) Update an existing airport");
	    System.out.println("3) Delete an airport");
	    System.out.println("4) View all airports");
	    System.out.println("5) Quit to previous");
	    Scanner scanner = new Scanner(System.in);
	    int choice = scanner.nextInt();

	    switch (choice) {
	        case 1:
	            addNewAirport();
	            break;
	        case 2:
	            updateExistingAirport();
	            break;
	        case 3:
	            deleteAirport();
	            break;
	        case 4:
	            viewAllAirports();
	            break;
	        case 5:
	            administratorMenu();
	            break;
	        default:
	            System.out.println("Invalid choice. Please try again.");
	            manageAirports();
	            break;
	    }
	}
		
	private void addNewAirport() {
		AirportsDAOImpl airportsDAO = new AirportsDAOImpl();
		Airport airport = new Airport();
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Enter the city:");
	    airport.setCity(scanner.nextLine());

	    System.out.println("Enter the airport name:");
	    airport.setName(scanner.nextLine());

	    boolean added = airportsDAO.addNewAirport(airport);
	    if(added) {
	        System.out.println("Successfully added new airport.");
	    } else {
	        System.out.println("Error adding new airport. Please try again.");
	    }
		
	}

	private void updateExistingAirport() {
		AirportsDAOImpl airportsDAO = new AirportsDAOImpl();
		Airport airport = new Airport();
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Enter the airport ID:");
	    int airportID = scanner.nextInt();
	    airport = airportsDAO.getAirportByID(airportID);

	    System.out.println("Enter the updated city:");
	    airport.setCity(scanner.nextLine());

	    System.out.println("Enter the updated airport name:");
	    airport.setName(scanner.nextLine());

	    boolean updated = airportsDAO.updateExistingAirport(airport);
	    if(updated) {
	        System.out.println("Successfully updated airport.");
	    } else {
	        System.out.println("Error updating airport. Please try again.");
	    }
		
	}

	private void deleteAirport() {
		AirportsDAOImpl airportsDAO = new AirportsDAOImpl();
		Scanner scanner = new Scanner(System.in);

	    System.out.println("Enter the airport ID:");
	    int airportID = scanner.nextInt();

	    boolean deleted = airportsDAO.deleteAirport(airportID);
	    if(deleted) {
	        System.out.println("Successfully deleted airport.");
	    } else {
	        System.out.println("Error deleting airport. Please try again.");
	    }
		
	}

	private void viewAllAirports() {
		AirportsDAOImpl airportsDAO = new AirportsDAOImpl();
		List<Airport> airports = airportsDAO.viewAllAirports();
	    System.out.println("Airport ID \t City \t Name");
	    for(Airport airport : airports) {
	        System.out.println(airport.getAirportID() + "\t" + airport.getCity() + "\t" + airport.getName());
	    }
		
	}

	

	private void manageTravelers() {
		 System.out.println("MANAGE TRAVELERS");
		    System.out.println("1) Add a new traveler");
		    System.out.println("2) Update an existing traveler");
		    System.out.println("3) Delete a traveler");
		    System.out.println("4) View all travelers");
		    System.out.println("5) Quit to previous");
		    Scanner scanner = new Scanner(System.in);
		    int choice = scanner.nextInt();

		    switch (choice) {
		        case 1:
		            addNewTraveler();
		            break;
		        case 2:
		            updateExistingTraveler();
		            break;
		        case 3:
		            deleteTraveler();
		            break;
		        case 4:
		            viewAllTravelers();
		            break;
		        case 5:
		            administratorMenu();
		            break;
		        default:
		            System.out.println("Invalid choice. Please try again.");
		            manageTravelers();
		            break;
		    }
		
	}

	private void addNewTraveler() {
		TravelersDAOImpl travelersDAO = new TravelersDAOImpl();
	    Travelers traveler = new Travelers();

	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Enter the traveler name:");
	    traveler.setName(scanner.next());

	    System.out.println("Enter the traveler email:");
	    traveler.setEmail(scanner.next());

	    boolean added = travelersDAO.addNewTraveler(traveler);
	    if(added) {
	        System.out.println("Successfully added new traveler.");
	    } else {
	        System.out.println("Error adding new traveler. Please try again.");
	    }
	}

	private void updateExistingTraveler() {
		Travelers traveler = new Travelers();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the traveler ID:");
		traveler.setTravelerID(scanner.nextInt());

		System.out.println("Enter the new name:");
		traveler.setName(scanner.nextLine());

		System.out.println("Enter the new email:");
		traveler.setEmail(scanner.nextLine());

		TravelersDAO dao = new TravelersDAOImpl();
		boolean updated = dao.updateExistingTraveler(traveler);
		if(updated) {
		    System.out.println("Successfully updated traveler.");
		} else {
		    System.out.println("Error updating traveler. Please try again.");
		}
		
	}

	private void deleteTraveler() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the traveler ID:");
		int travelerID = scanner.nextInt();
		TravelersDAO dao = new TravelersDAOImpl();
		boolean deleted = dao.deleteTraveler(travelerID);
		if(deleted) {
		    System.out.println("Successfully deleted traveler.");
		} else {
		    System.out.println("Error deleting traveler. Please try again.");
		}

		
	}

	private void viewAllTravelers() {
		TravelersDAOImpl travelersDAO = new TravelersDAOImpl();
		List<Travelers> travelers = travelersDAO.viewAllTravelers();
		if(travelers.size() > 0) {
		for (Travelers traveler : travelers) {
		System.out.println("Traveler ID: " + traveler.getTravelerID());
		System.out.println("Name: " + traveler.getName());
		System.out.println("Email: " + traveler.getEmail());
		}
		} else {
		System.out.println("No travelers found.");
		}
		
	}

    

}