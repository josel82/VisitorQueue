package com.views.assign;

import java.util.Scanner;

import com.models.assign.Person;

public class Menu {

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
		System.out.print("\n1 -Add a new person to the queue. | ");
		System.out.print("2 -Add a new person with top priority status. | ");
		System.out.print("\n3 -Add a new person with priority. | ");
		System.out.print("4 -Remove person from the top of the queue. | ");
		System.out.print("\n5 -Remove person by id. | ");
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
	public int getInput() {
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
	// Prompts the user for the visitor's information

	public String[] onAdd() {
		String[] str = { "", "", "", "", "" };
		try {
			Scanner kb = new Scanner(System.in);
			System.out.println("Please insert details.");
			System.out.print("\nFistName:");
			String fn = kb.nextLine();
			System.out.print("\nLastName:");
			String ln = kb.nextLine();
			System.out.print("\nArrival Date:");
			String ad = kb.nextLine();
			System.out.print("\nPassport No:");
			String ps = kb.nextLine();

			str[0] = fn;
			str[1] = ln;
			str[2] = ad;
			str[3] = ps;
			str[4] = "success";

		} catch (Exception e) {
			str[4] = "error";
		}
		return str;
	}

	// *********************************************************************************
	// Prompts user for visitor info and position, so this can be added to a
	// specific place in the queue.

	public String[] onAddWithPriority() {
		String[] str = { "", "", "", "", "", "" }; // initialize variable

		try { // takes input from user
			Scanner kb = new Scanner(System.in);
			System.out.println("Please insert details.");
			System.out.print("\nFistName:");
			String fn = kb.nextLine();
			System.out.print("\nLastName:");
			String ln = kb.nextLine();
			System.out.print("\nArrival Date:");
			String ad = kb.nextLine();
			System.out.print("\nPassport No:");
			String ps = kb.nextLine();
			System.out.print("\nPosition in the queue:");
			String pos = kb.nextLine();
			str[0] = fn;
			str[1] = ln;
			str[2] = ad;
			str[3] = ps;
			str[4] = pos;
			str[5] = "success";
		} catch (Exception e) {
			str[5] = "error";
		}
		return str;
	}

	// *********************************************************************************
	// Prompts user for the visitor's id

	public int insertId() {
		int id = -1;
		Scanner kb = new Scanner(System.in);
		System.out.println("\nPlease insert visitor's id.");
		try {
			System.out.print("ID:");
			id = Integer.parseInt(kb.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input");
		}
		return id;
	}

	// *********************************************************************************
	// Prompts user for the number of visitors to be removed

	public int onRemoveNLast() {
		int n = 0;
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
		System.out.println("Arrival Date: " + p.getArrivalDate());
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
