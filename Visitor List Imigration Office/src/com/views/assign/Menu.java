package com.views.assign;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.models.assign.Person;

public class Menu {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
	//Instance of the SimpleDateFormat class. Used for giving format to the Date data type.

	// *********************************************************************************
	// Prints a welcome message when the program starts.

	public void printHeader() {
		System.out.println("---------------------------------------------");
		System.out.println("|                                           |");
		System.out.println("|              Welcome to                   |");
		System.out.println("|           Waiting list 1.0                |");
		System.out.println("|                                           |");
		System.out.println("---------------------------------------------");

	}

	// *********************************************************************************
	// Prints the menu options to the screen

	public void printMenu() {
		System.out.print("\nPlease select an option:");
		System.out.print("\n1 -Add a new visitor to the queue. | ");
		System.out.print("2 -Add a new visitor with top priority status. | ");
		System.out.print("\n3 -Add a new visitor with priority. | ");
		System.out.print("4 -Remove visitor from the top of the queue. | ");
		System.out.print("\n5 -Remove visitor by id. | ");
		System.out.print("6 -Remove last N visitors. | ");
		System.out.print("7 -Search by id. | ");
		System.out.print("8 -Update visitor. | ");
		System.out.print("\n9 -Print queue. | ");
		System.out.print("0 -Exit.\n");
	}

	// *********************************************************************************
	// Prompts user for menu option

	@SuppressWarnings("resource") // gets rid of the warning "Resource Leak 'kb'
									// is never closed".
	public int getOption() {
		Scanner kb = new Scanner(System.in);
		int option = -1;

		while (option < 0 || option > 9) { // This loop will run until the user
											// inserts a number within that
											// range.
			try {
				printMsg("Enter option: ");
				option = Integer.parseInt(kb.nextLine());
			} catch (NumberFormatException e) {
				printMsg("Invalid option. Please try again.");
			}
		}
		return option;
	}

	// *********************************************************************************
	// Prompts user for information.
	public String getInput(String prompt){
		String input = "";
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		try{
			System.out.print("\n"+prompt);
			input = kb.nextLine();
		}catch(Exception e){
			System.out.println("\nInvalid Input.");
		}
		return input;
	}


	// *********************************************************************************
	// Prompts user for the number of visitors to be removed

	public int onRemoveNLast() {
		int n = 0;
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		System.out.println("\nPlease insert the number of visitors to be deleted.");
		try {
			System.out.print("No:");
			n = Integer.parseInt(kb.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input");
		}
		return n;
	}

	// *********************************************************************************
	// Prints full visitor's information

	public void printPerson(Person p, int pos) {
		System.out.println("\n--------------------------------");
		System.out.println("ID: " + p.getId());
		System.out.println("Pos: " + pos);
		System.out.println("First Name: " + p.getFirstname());
		System.out.println("Last Name: " + p.getLastname());
		System.out.println("Arrival Date: " + dateFormat.format(p.getArrivalDate()));
		System.out.println("Passport No: " + p.getPassport());
		System.out.println("--------------------------------");
	}

	// *********************************************************************************
	// Prints brief visitor's information.
	// This method is used for printing the full queue.

	public void printPersonBrief(int pos, Person p) {
		System.out.println(pos + ") " + p.getFirstname() + " " + p.getLastname() + " id: " + p.getId());
	}

	// *********************************************************************************
	// Prints a message to the screen.
	
	public void printMsg(String msg) {
		System.out.println("\n" + msg);
	}
}
