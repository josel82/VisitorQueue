package com.controller.assign;

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
			int id;
			menu.printMenu();
			int option = menu.getInput();
			switch(option){
			case 0:
				exit = true;
				System.out.println("\nGood Bye!.");
				break;
			case 1://Add a new person to the tail of the queue
				details = menu.onAdd();
				if(details[4]=="error"){//Handles user input errors
					System.out.println("Invalid Input.");
				}else{
					np = queue.add(queue.createPerson(details[0], details[1], details[2], details[3]));
					System.out.println("\nNew person added with id: "+np.getId());
				}
				break;
			case 2: //Add a new person to the head of the queue
				details = menu.onAdd();
				if(details[4]=="error"){//Handles user input errors
					System.out.println("Invalid Input.");
				}else{
					np = queue.addTopPriority(queue.createPerson(details[0], details[1], details[2], details[3]));
					System.out.println("\nNew person added to the top of the queue with id: "+np.getId());
				}
				break;
			case 3: //Add a new person with priority
				details = menu.onAddWithPriority();
				if(details[5] == "error"){ //Handles user input errors
					System.out.println("Invalid Input.");
				}else{
					
					try{// In case user inputs a position that is out of bounds
						np = queue.addPriority(Integer.parseInt(details[4])-1,queue.createPerson(details[0], details[1], details[2], details[3]));
						System.out.println("\nNew person added with id: "+np.getId()+" has bee added to position: "+details[4]);
					}catch(IndexOutOfBoundsException e){
						System.out.println("\nInvalid position. Please try again.");
					}		
				}			
				break;
			case 4:
				try{
					Person rp = queue.removeFirst();
					System.out.println("Visitor with id: "+rp.getId()+" has been cleared from que queue");
				}catch(NoSuchElementException e){
					System.out.println("\nThe queue is empty. Nothing to remove.");
				}
				
				break;
			case 5:	
				id = menu.insertId();
				if(id<1){
					System.out.println("Invalid id");
				}else{
					boolean found = false;
					for(Person p: queue.getQueue()){
						if(p.getId() == id){
							try{
								queue.remove(queue.getPosition(p));
								System.out.println("\nVisitor with id: "+p.getId()+" has been cleared from que queue");
								found = true;
							}catch(IndexOutOfBoundsException e){
								System.out.println("\nInvalid id. Please try again.");
							}
							break;
						}
					}
					if(!found){
						System.out.println("\nNo visitor with id: "+id+" has been found.");
					}
				}
				break;
			case 6:
				int n = menu.onRemoveNLast();
				if(n<0){
					System.out.println("\nInvalid input");
				}else{
					boolean r = queue.removeNLast(n);
					if(r){
						System.out.println("\nThe last "+n+" visitors have been successfully removed.");
					}else{
						System.out.println("\nUnable to remove the last "+n+" visitors.");
					}
				}
				break;
			case 7:	
				id = menu.insertId();
				if(id<1){
					System.out.println("Invalid id");
				}else{
					for(Person p: queue.getQueue()){
						if(p.getId() == id){
							menu.printPerson(p, queue.getPosition(p)+1);
						}
					}
				}
				break;
			case 8:
				id = menu.insertId();
				if(id<1){
					System.out.println("Invalid id");
				}else{
					boolean found = false;
					for(Person p: queue.getQueue()){
						if(p.getId() == id){
							int pos = queue.getPosition(p);
							details = menu.onAdd();
							queue.updatePerson(pos, details[0], details[1], details[2], details[3]);
							System.out.println("\nThis visitor has been successfully updated:");
							menu.printPerson(p, pos+1);
							found = true;
						}
					}
					if(!found){
						System.out.println("\nNo visitor with id: "+id+" has been found.");
					}
				}
				break;
			case 9:
				
				if(queue.length()==0){
					System.out.println("\nThe queue is empty.");
				}else{
					System.out.println("\n------------Queue-------------");
					for(int i=0; i<queue.length(); i++){
						menu.printPersonBrief(i+1, queue.getPerson(i));
					}
					System.out.println("------------------------------");
				}
				break;
			}
		}
		
	}
	

	public static void main(String[] args) {
		
		
		new Main(); //calls the main class constructor
		
	}

}
