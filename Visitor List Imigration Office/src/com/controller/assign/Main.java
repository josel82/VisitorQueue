package com.controller.assign;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import com.models.assign.Person;
import com.models.assign.VisitorQueue;
import com.views.assign.Menu;

public class Main {
	
	private final VisitorQueue queue = new VisitorQueue();
	private final Menu menu = new Menu();
	
	private boolean exit = false;
	
	public Main(){
	
		runMenu();
	}
	
	
	
	public void runMenu(){

		menu.printHeader();
		
		while(!exit){
			
			String[] details;
			Person np;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
			Date date = null;
			
			menu.printMenu();
			int option = menu.getOption();
			
			
			switch(option){
			case 0:
				exit = true;
				menu.printMsg("Good Bye!.");
				break;
			case 1:{//Add a new person to the tail of the queue
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
				
				np = queue.add(queue.createPerson(fn, ln, date, ps));
				menu.printMsg("New person added with id: "+np.getId());

				break;
			}
			case 2: {//Add a new person to the head of the queue
				
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
				np = queue.addTopPriority(queue.createPerson(fn, ln, date, ps));
				menu.printMsg("New person added to the top of the queue with id: "+np.getId());
				
				break;
			}
			case 3:{ //Add a new person with priority
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
					np = queue.addPriority(Integer.parseInt(pos)-1,queue.createPerson(fn, ln, date, ps));
					menu.printMsg("New person added with id: "+np.getId()+" has bee added to position: "+pos);
				}catch(IndexOutOfBoundsException e){
					menu.printMsg("Invalid position. Please try again.");
				}		
			
				break;
			}
			case 4:
				try{
					Person rp = queue.removeFirst();
					menu.printMsg("Visitor with id: "+rp.getId()+" has been cleared from que queue");
				}catch(NoSuchElementException e){
					menu.printMsg("The queue is empty. Nothing to remove.");
				}
				
				break;
			case 5:{
				int id = -1;
				menu.printMsg("Please insert visitor's id.");
				try{
					id = Integer.parseInt(menu.getInput("ID:"));
				}catch(NumberFormatException e){
					menu.printMsg("Invalid input");
				}
				if(id<1){
					menu.printMsg("Invalid id");
				}else{
					boolean found = false;
					for(Person p: queue.getQueue()){
						if(p.getId() == id){
							try{
								queue.remove(queue.getPosition(p));
								menu.printMsg("Visitor with id: "+p.getId()+" has been removed from que queue");
								found = true;
							}catch(IndexOutOfBoundsException e){
								menu.printMsg("Invalid id. Please try again.");
							}
							break;
						}
					}
					if(!found){
						menu.printMsg("No visitor with id: "+id+" has been found.");
					}
				}
				break;
			}
			case 6:
				int n = menu.onRemoveNLast();
				if(n<0){
					menu.printMsg("Invalid input");
				}else{
					boolean r = queue.removeNLast(n);
					if(r){
						menu.printMsg("The last "+n+" visitors have been successfully removed.");
					}else{
						menu.printMsg("The number inserted is larger than the number of visitor in the queue.");
					}
				}
				break;
			case 7:	{
				int id = -1;
				menu.printMsg("Please insert visitor's id.");
				try{
					id = Integer.parseInt(menu.getInput("ID:"));
				}catch(NumberFormatException e){
					menu.printMsg("Invalid input");
				}
				if(id<1){
					menu.printMsg("Invalid id");
				}else{
					boolean found = false;
					for(Person p: queue.getQueue()){
						if(p.getId() == id){
							menu.printPerson(p, queue.getPosition(p)+1);
							found = true;
						}
					}
					if(!found){
						menu.printMsg("No visitor with id: "+id+" was found.");
					}
				}
				break;
			}
			case 8:{
				int id = -1;
				menu.printMsg("Please insert visitor's id.");
				try{
					id = Integer.parseInt(menu.getInput("ID:"));
				}catch(NumberFormatException e){
					menu.printMsg("Invalid input");
				}
				if(id<1){
					menu.printMsg("Invalid id");
				}else{
					boolean found = false;
					for(Person p: queue.getQueue()){
						if(p.getId() == id){
							int pos = queue.getPosition(p);
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
							queue.updatePerson(pos, fn, ln, date, ps);
							menu.printMsg("This visitor has been successfully updated:");
							menu.printPerson(p, pos+1);
							found = true;
						}
					}
					if(!found){
						menu.printMsg("No visitor with id: "+id+" has been found.");
					}
				}
				break;
			}
			case 9:
				
				if(queue.length()==0){
					menu.printMsg("The queue is empty.");
				}else{
					menu.printMsg("------------Queue-------------");
					for(int i=0; i<queue.length(); i++){
						menu.printPersonBrief(i+1, queue.getPerson(i));
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
