package com.models.assign;

import java.util.Date;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class VisitorQueue {

	private final LinkedList<Person> list = new LinkedList<Person>(); //This is where we hold person objects
	
	private int id = 1;
	
	
	//************Methods****************
	public Person createPerson(String firstname, String lastname, Date arrival, String passport){
		
		Person person = new Person(id, firstname, lastname, arrival, passport);
		id++;
		
		return person;
	}
	
	
	//Adds new person to the tail of the queue
	public Person add(Person person){  
		list.add(person);
		return person;
	}
	
	//Adds new person to the top of the queue
	public Person addTopPriority(Person person){ 
		list.addFirst(person);
		return person;
	}
	//Adds new person to a selected position in the queue
	public Person addPriority(int pos,Person person) throws IndexOutOfBoundsException { 
		list.add(pos, person);
		return person;
	}
	
	//Once someone has been served it needs to be cleared from the queue
	public Person removeFirst() throws NoSuchElementException{ 
		return list.removeFirst();
	}
	
	public Person remove(int index) throws IndexOutOfBoundsException{
		return list.remove(index);
	}
	
	public boolean removeNLast(int n){
		boolean allowed =false;
		if(list.size()>n){
			for(int i=0; i<n; i++){
				list.removeLast();
			}
			allowed = true;
		}
		return allowed;
	}
	
	public int length(){
		return list.size();
	}
	
	public Person getPerson(int index){
		return list.get(index);
	}
	
	public LinkedList<Person> getQueue(){
		return list;
	}
	public int getPosition(Person p){
		return list.indexOf(p);
	}
	public void updatePerson(int pos, String fn, String ln, Date ad, String ps){
		list.get(pos).setFirstname(fn);
		list.get(pos).setLastname(ln);
		list.get(pos).setArrivalDate(ad);
		list.get(pos).setPassport(ps);
	}
}
