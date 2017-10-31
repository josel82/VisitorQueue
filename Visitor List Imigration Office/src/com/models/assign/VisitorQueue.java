package com.models.assign;

import java.util.Date;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class VisitorQueue {

	private final LinkedList<Person> list = new LinkedList<Person>(); //This is where we hold person objects
	
	private int id = 1;
	
	
	//************Methods****************
	
	// *********************************************************************************
	// Returns a new Person object
	public Person createPerson(String firstname, String lastname, Date arrival, String passport){
		
		Person person = new Person(id, firstname, lastname, arrival, passport);
		id++;
		
		return person;
	}
	
	// *********************************************************************************
	//Adds new person to the tail of the queue
	public Person add(Person person){  
		list.add(person);
		return person;
	}
	
	// *********************************************************************************
	//Adds new person to the top of the queue
	public Person addTopPriority(Person person){ 
		list.addFirst(person);
		return person;
	}
	
	// *********************************************************************************
	//Adds new person to a selected position in the queue
	public Person addPriority(int pos,Person person) throws IndexOutOfBoundsException { 
		list.add(pos, person);
		return person;
	}
	
	// *********************************************************************************
	//Once someone has been served it needs to be cleared from the queue
	public Person removeFirst() throws NoSuchElementException{ 
		return list.removeFirst();
	}
	// *********************************************************************************
	// Removes a person by index
	public Person remove(int index) throws IndexOutOfBoundsException{
		return list.remove(index);
	}
	
	// *********************************************************************************
	// Removes the last n of persons in the list
	public boolean removeNLast(int n){
		boolean allowed =false;
		if(list.size()>n){ //checks if the number inserted is higher than the length of the list
			for(int i=0; i<n; i++){//this loop will delete the last person of the queue "n" times
				list.removeLast();
			}
			allowed = true; 
		}
		return allowed; //tells the caller of this method whether this operation was allowed
	}
	
	// *********************************************************************************
	// Returns the length of the list
	public int length(){
		return list.size();
	}
	
	// *********************************************************************************
	// Returns a person object stored in the list. it asks for that person index.
	public Person getPerson(int index){
		return list.get(index);
	}
	
	// *********************************************************************************
	// Returns the entire list
	public LinkedList<Person> getQueue(){
		return list;
	}
	
	// *********************************************************************************
	// Returns the index of a specific person
	public int getPosition(Person p){
		return list.indexOf(p);
	}
	
	// *********************************************************************************
	// Updates a person object that is in the list.
	public void updatePerson(int pos, String fn, String ln, Date ad, String ps){
		list.get(pos).setFirstname(fn);
		list.get(pos).setLastname(ln);
		list.get(pos).setArrivalDate(ad);
		list.get(pos).setPassport(ps);
	}
}
