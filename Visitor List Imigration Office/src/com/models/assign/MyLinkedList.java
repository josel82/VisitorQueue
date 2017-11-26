package com.models.assign;


/***************************************************************************************
*    Part of this code was inspire by:
*    
*    Title: Java LinkedList Assignment Implemenation
*    Author: Trevor Page
*    Date: 26-11-2017
*    Availability: https://www.youtube.com/watch?v=VR363sIAvr4&t=362s
*    
*    And,
*    
*    Title: BasicLinkedList
*    Author: CCT College Dublin
*    Date: 25-11-2017
*    Availability: CCT Moodle
*    
***************************************************************************************/

public class MyLinkedList<E> {
	
	
	private Link head;
	private Link tail;
	private int length;
	
	// Constructor. It initializes the linked List.
	public MyLinkedList(){
		this.head = null;
		this.tail = null;
		this.length = 0;
	}
	
	
	//*****************************************************************
	//************************* ADDITION ******************************
	//*****************************************************************
	
	
	// Appends an item to the list
	public void add(E data){
		
		if(tail == null){                     // If the list is empty tail and head will be null, 
			Link newLink = new Link(data);    // a link added at this point will be both head an tail
			tail = newLink;
			head = newLink;
			length++;
			return;
		}
		
		Link newLink = new Link(data);        // Case there are links in the list already. a new link is created,
		tail.next = newLink;				  // this is linked to the last link in the list
		tail = newLink;                       // then it is set as the new tail
		length++;
		
	}
		
	// addFirst prepends an item to the Linked List 
	public void addFirst(E data){
		if(tail == null){                     //Case the list is empty
			Link newLink = new Link(data);
			tail = newLink;
			head = newLink;
			length++;
			return;
		}
		
		Link newLink = new Link(data);        //Case there are links in the list already.
		newLink.next = head;
		head = newLink;
		length++;
		
	}
	
	// addAt adds an item to an specified position in the list
	public void addAt(E data, int pos){
		if(pos > length){ 
			throw new IllegalStateException("The index inserted is greater that the length of the list.");
		} 
		
		Link currentLink = head;
		int currentPos = 1;
		pos = pos-1;
		
		while(currentLink.next != null && currentPos < pos ){
			
			currentLink = currentLink.next;
			currentPos++;
		}
		
		Link newLink = new Link(data);
		Link nextLink = currentLink.next;
		currentLink.next = newLink;
		newLink.next = nextLink;
		length++;
			
	}
	
	//*****************************************************************
	//*********************** DELETION ********************************
	//*****************************************************************
	
	public E remove(){ //This method will remove the first item in the link
		
		if(head == null){  // <= Case there is no items in the list
			throw new IllegalStateException("There's no items to delete.");
		}	
		
		Link currentLink = head;
		head = head.next;		
		length--;
		return currentLink.data;
		
	}
	
	public E removeLast(){ //This method removes the last item of the list
		if(head == null){  // <= Case there is no items in the list
			throw new IllegalStateException("There's no items to delete.");
		}	
		
		Link currentLink = head;
		Link previousLink = null;
		while(currentLink.next != null){ // <= Iterates over the list
			previousLink = currentLink;
			currentLink = currentLink.next;
		}
		previousLink.next = null;
		tail = previousLink;
		length--;
		return currentLink.data;
		
	}
	
	public E removeItem(E item){
		
		if(head == null){  // <= Case there is no items in the list
			throw new IllegalStateException("There's no items to delete.");
		}	
		if(head.data == item){ // <= Case the item is at the head of the list
			Link currentLink = head;
			head = head.next;		
			length--;
			return currentLink.data;
		}
		
		Link currentLink = head;
		Link previousLink = null;
		while(currentLink.next != null){ // <= Iterates over the list
			if(currentLink.data == item){ // if the item is found it jumps out of the loop
				break;
			}
			previousLink = currentLink;
			currentLink = currentLink.next;
		}
		
		if(tail.data == item){ // <= Case the item is at the tail of the list
			previousLink.next = null;
			length--;
			return currentLink.data;
		}
		
		previousLink.next = currentLink.next;
		length--;
		return currentLink.data;
		
	}
	
	
	//*****************************************************************
	//************************* SEARCH ********************************
	//*****************************************************************
	
	//Returns item given a specified position
	public E find(int pos){
		if(head == null){  // <= Case there is no items in the list
			throw new IllegalStateException("There's no items to retrieve.");
		}	
		if(pos<1 || pos>length ){ // <= Case the given position is out of bounds
			throw new IllegalStateException("Out of bounds. Illegal position.");
		}
		
		Link currentLink = head;
		int currentPos = 1;
		while(currentLink.next != null && currentPos < pos){ // <= Iterates over the list up to the given position
			currentLink = currentLink.next;
			currentPos++;
		}
		return currentLink.data; // returns the item at the given position
	}
	
	//This method returns the position a given item is
	public int getPos(E item){
		if(head == null){  //Case there is no items in the list
			throw new IllegalStateException("There's no items to retrieve.");
		}
		
		Link currentLink = head;
		int currentPos = 1;
		while(currentLink.next != null){ // <= Iterates ove the list
			if(currentLink.data == item){ // <= compare items with the item provided. if there is a match it jumps out of the 
				break;					  // loop 
			}
			currentLink = currentLink.next;
			currentPos++;
		}
		return currentPos;
	}
	
	
	public int size(){ // Returns the length of the list
		return length;
	}
	
	

	//*******************************************************************************************************************
	//Link class defines a link in in the Linked list
	//*******************************************************************************************************************
	private class Link{
		private Link next;
		private E data;

		
		public Link (E data){
			this.next = null;
			this.data = data;
		}
	}
	
}

