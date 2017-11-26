package com.models.assign;

import java.util.Date;
import java.util.NoSuchElementException;

public class VisitorQueue {

//	private final LinkedList<Person> list = new LinkedList<Person>(); //This is where we hold person objects
	private final MyLinkedList<Person> list = new MyLinkedList<Person>();
	
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
	public Person addPriority(int pos,Person person) throws IllegalStateException { 
		list.addAt(person, pos);
		return person;
	}
	
	// *********************************************************************************
	//Once someone has been served it needs to be cleared from the queue
	public Person removeFirst() throws NoSuchElementException{ 
		return list.remove();
	}
	// *********************************************************************************
	// Removes a person by index
	public Person removeByID(int id) throws IllegalStateException{
		
		for(int i = 1; i <= list.size(); i++){
			Person p = list.find(i);
			if(p.getId() == id){
				p = list.removeItem(p);
				return p;
			}
		}
		throw new IllegalStateException("Unable to find person with id: "+id);
		
	}
	
	// *********************************************************************************
	// Removes the last n of persons in the list
	public void removeNLast(int n) throws IllegalStateException{
		if(list.size()< n ) throw new IllegalStateException("Invalid! The number inserted is higher than the length of the queue.");
		
			for(int i=0; i<n; i++){//this loop will delete the last person of the queue "n" times
				list.removeLast();
			}
	}
	
	// *********************************************************************************
	// Returns the length of the list
	public int length(){
		return list.size();
	}
	
	// *********************************************************************************
	// Returns a person object stored in the list. it asks for that person index.
	public Person getPersonByID(int id)throws IllegalStateException{
		
		for(int i = 1; i <= list.size(); i++){
			Person p = list.find(i);
			if(p.getId() == id){
				return p;
			}
		}
		throw new IllegalStateException("Unable to find person with id: "+id);
	}
	
	// *********************************************************************************
	// Returns the entire list
	public Person getPersonByPos(int pos) throws IllegalStateException{
		return list.find(pos);
	}
	
	// *********************************************************************************
	// Returns the index of a specific person
	public int getPosition(Person p){
		return list.getPos(p);
	}
	
	// *********************************************************************************
	// Updates a person object that is in the list.
	public void updatePerson(int pos, String fn, String ln, Date ad, String ps){
		list.find(pos).setFirstname(fn);
		list.find(pos).setLastname(ln);
		list.find(pos).setArrivalDate(ad);
		list.find(pos).setPassport(ps);
	}
}
