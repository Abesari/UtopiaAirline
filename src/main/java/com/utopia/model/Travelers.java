package com.utopia.model;

public class Travelers {

	public int travelerID;
	public String name;
	public String email;
	public int getTravelerID() {
		return travelerID;
	}
	public void setTravelerID(int travelerID) {
		this.travelerID = travelerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Travelers [travelerID=" + travelerID + ", name=" + name + ", email=" + email + "]";
	}
	
	
}
