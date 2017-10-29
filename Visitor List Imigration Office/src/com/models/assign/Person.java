package com.models.assign;

import java.time.LocalDate;

public class Person {
	
	private int id;
	private String firstname;
	private String lastname;
	private String arrivalDate;
	private String passport;
	
	public Person(int id, String firstname, String lastname, String arrivalDate, String passport){
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.arrivalDate = arrivalDate;
		this.passport = passport;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
