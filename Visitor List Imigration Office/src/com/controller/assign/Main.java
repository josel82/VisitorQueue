package com.controller.assign;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import com.models.assign.Person;
import com.models.assign.VisitorQueue;
import com.views.assign.Menu;

public class Main {
	
	private final VisitorQueue queue = new VisitorQueue(); //Instance of the VisitorQueue Class. Here is where all visitors are stored.
	private final Menu menu = new Menu(); //Instance of the Menu Class. Here is where all the I/O methods live. 
	
	private boolean exit = false; //The program finishes when this variable is set to true. 
	
	public Main(){ //This constructor is called in by the main method.
	
		runMenu(); 
	}
	
	// *********************************************************************************
	// This method controls the program. it calls method from all the other classes.
	public void runMenu(){

		menu.printHeader();
		
		while(!exit){ 
			
			Person np;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
			Date date = null;
			
			menu.printMenu();
			int option = menu.getOption(); //gets option from the user
			
			//Performs a task depending of the option selected by the user
			switch(option){
			case 0: //Exit the program
				exit = true;
				menu.printMsg("Good Bye!.");
				break;
			case 1:{//Add a new person to the tail of the queue
				//first the information is gathered
				String fn = menu.getInput("First Name:");
				String ln = menu.getInput("Last Name:");
				while(date == null){
					try {// handles error
						date = dateFormat.parse(menu.getInput("Arrival Date:"));
					} catch (ParseException e1) {
						menu.printMsg("Invalid Date.");
					}
				}
				String ps = menu.getInput("Passport:");
				
				np = queue.add(queue.createPerson(fn, ln, date, ps)); //creates and add a person to the queue
				menu.printMsg("New person added with id: "+np.getId());

				break;
			}
			case 2: {//Add a new person to the head of the queue
				//first the information is gathered
				String fn = menu.getInput("First Name:");
				String ln = menu.getInput("Last Name:");
				while(date == null){
					try {//handles error
						date = dateFormat.parse(menu.getInput("Arrival Date:"));
					} catch (ParseException e1) {
						menu.printMsg("Invalid Date.");
					}
				}
				String ps = menu.getInput("Passport:");
				np = queue.addTopPriority(queue.createPerson(fn, ln, date, ps)); //creates a person and adds it to the head of the queue
				menu.printMsg("New person added to the top of the queue with id: "+np.getId());
				
				break;
			}
			case 3:{ //Add a new person with priority
				//first the information is gathered
				String fn = menu.getInput("First Name:");
				String ln = menu.getInput("Last Name:");
				while(date == null){
					try {
						date = dateFormat.parse(menu.getInput("Arrival Date:"));
					} catch (ParseException e1) {
						menu.printMsg("Invalid Date.");
					}
				}
				String ps = menu.getInput("Passport:");
				String pos = menu.getInput("Position:");
					
				try{// In case user inputs a position that is out of bounds
					np = queue.addPriority(Integer.parseInt(pos)-1,queue.createPerson(fn, ln, date, ps)); //creates a person and adds it to a specified position in the queue
					menu.printMsg("New person added with id: "+np.getId()+" has bee added to position: "+pos);
				}catch(IndexOutOfBoundsException e){
					menu.printMsg("Invalid position. Please try again.");
				}		
			
				break;
			}
			case 4://Removes a visitor from the head of the queue. This is the case when the visitor has been served.
				try{
					Person rp = queue.removeFirst();//removes visitor from the head
					menu.printMsg("Visitor with id: "+rp.getId()+" has been cleared from que queue");
				}catch(NoSuchElementException e){//in case the queue is empty
					menu.printMsg("The queue is empty. Nothing to remove.");
				}
				
				break;
				
			case 5:{//Removes a visitor by id
				int id = -1;
				menu.printMsg("Please insert visitor's id.");
				try{
					id = Integer.parseInt(menu.getInput("ID:")); //get visitor's id from the user 
				}catch(NumberFormatException e){
					menu.printMsg("Invalid input");
				}
				if(id<1){
					menu.printMsg("Invalid id");
				}else{
					boolean found = false;
					for(Person p: queue.getQueue()){ //Iterates over the queue
						if(p.getId() == id){ //finds the user
							try{
								queue.remove(queue.getPosition(p));
								menu.printMsg("Visitor with id: "+p.getId()+" has been removed from que queue");
								found = true; // tells the program that teh visitor has been found
							}catch(IndexOutOfBoundsException e){
								menu.printMsg("Invalid id. Please try again.");
							}
							break;
						}
					}
					if(!found){
						menu.printMsg("No visitor with id: "+id+" has been found."); //in case it couldn't find a visitor with that id
					}
				}
				break;
			}
			case 6:// Remove last N visitors
				int n = menu.onRemoveNLast(); //gets number of visitors to be deleted
				if(n<1){ // deleting zero or less visitors doesn't make sense.
					menu.printMsg("Invalid input");
				}else{
					boolean r = queue.removeNLast(n); //removes visitors
					if(r){
						menu.printMsg("The last "+n+" visitors have been successfully removed.");
					}else{
						menu.printMsg("The number inserted is larger than the number of visitor in the queue.");
					}
				}
				break;
			case 7:	{// Search by id
				int id = -1;
				menu.printMsg("Please insert visitor's id.");
				try{
					id = Integer.parseInt(menu.getInput("ID:")); //gets visitor's id from the user.
				}catch(NumberFormatException e){
					menu.printMsg("Invalid input");
				}
				if(id<1){
					menu.printMsg("Invalid id");
				}else{
					boolean found = false;
					for(Person p: queue.getQueue()){ //Iterates over the queue
						if(p.getId() == id){ //finds visitor's id
							menu.printPerson(p, queue.getPosition(p)+1);
							found = true;
						}
					}
					if(!found){
						menu.printMsg("No visitor with id: "+id+" was found."); //in case it couldn't find a visitor with that id
					}
				}
				break;
			}
			case 8:{// Update visitor
				int id = -1;
				menu.printMsg("Please insert visitor's id.");
				try{
					id = Integer.parseInt(menu.getInput("ID:")); // gets visitor's id from the user
				}catch(NumberFormatException e){
					menu.printMsg("Invalid input");
				}
				if(id<1){
					menu.printMsg("Invalid id");
				}else{
					boolean found = false;
					for(Person p: queue.getQueue()){ //Iterates over the queue
						if(p.getId() == id){ //finds visitor with that id
							int pos = queue.getPosition(p); //gets that visitor's position in the queue
							//gets new info from user
							String fn = menu.getInput("First Name:");
							String ln = menu.getInput("Last Name:");
							while(date == null){
								try {
									date = dateFormat.parse(menu.getInput("Arrival Date:"));
								} catch (ParseException e1) {
									menu.printMsg("Invalid Date.");
								}
							}
							String ps = menu.getInput("Passport:");
							queue.updatePerson(pos, fn, ln, date, ps); // updates visitor object with the new info
							menu.printMsg("This visitor has been successfully updated:");
							menu.printPerson(p, pos+1);
							found = true;
						}
					}
					if(!found){
						menu.printMsg("No visitor with id: "+id+" has been found.");//case it couldn't find a visitor with that id
					}
				}
				break;
			}
			case 9: // Prints all visitors currently in the queue
				
				if(queue.length()==0){ //in case there is no visitors in the queue
					menu.printMsg("The queue is empty.");
				}else{
					menu.printMsg("------------Queue-------------");
					for(int i=0; i<queue.length(); i++){ //Iterates over the queue
						menu.printPersonBrief(i+1, queue.getPerson(i)); //retrieves and print visitors information
					}
					menu.printMsg("------------------------------");
				}
				break;
			}
		}
		
	}
	

	public static void main(String[] args) {
		// The programs starts here
		new Main(); //calls the main class constructor
		
	}

}
