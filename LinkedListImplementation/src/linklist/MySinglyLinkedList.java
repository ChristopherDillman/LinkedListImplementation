package linklist;

//MySinglyLinkedList.java by Christopher Dillman on 9/10/2016, last updated 10/14/2016

import java.util.HashMap;
import java.util.Map;

//A Linked List is made of nodes
//A node contains the data + the next
//A next is the pointer to the subsequent node in the list
//The head is the first pointer to first node
//Sample list: head(start) -> [Data + Next] -> [Data + Next] -> Null(end)

public class MySinglyLinkedList {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.addToEndOfList("01"); //Index 0
		ll.addToEndOfList("5A");
		ll.addToEndOfList("12");
		ll.addToEndOfList("3Q"); //Index 3
		ll.addToEndOfList("J8");
		ll.addToEndOfList("RR"); //Index 5

		System.out.println(ll.toString());

		ll.deleteFromList(3);

		System.out.println(ll.toString());

		System.out.println(ll.getFromList(2));

		ll.findMiddleNodeTwoLoopsThroughList();
		ll.findMiddleNodeOneLoopThroughList();
		
		int length = ll.getLength();
		System.out.println("The length of the list is " + length + 
				" nodes with indexes 0 through " + (length - 1) + ".");
	}
}

class LinkedList {

	private Node head;

	//Constructor
	public LinkedList() {

		//Its a new list, so it is empty.  It has only a head, which points to null
		//In other words, the start points directly to the end
		head = new Node(null);
	}

	/*
	 * Adds a node to the end of the list
	 */
	public void addToEndOfList(Object data) {

		//Sets the data to the first position if the list is empty
		if(head == null) {
			head = new Node(data);
		}

		Node nodeToAdd = new Node(data); //Places the data in a new node that has yet to be added to the list
		Node currentNode = head; //Starts at the head, which will point to the first node


		if(currentNode != null) {
			//Proceed through the list until you find the end
			while(currentNode.getNext() != null) {
				currentNode = currentNode.getNext();
			}

	        //nodeToAdd already has the data in it, it just needed the node at the end of the list to point to it
			//The new node's next will point to null
			currentNode.setNext(nodeToAdd);
		}
	}

	/*
	 * Given an index, deletes the node at the index
	 */
	public void deleteFromList(int index) {
		int indexCount = 0;

		//If head == null, the list must be empty
		if(head == null) {
			System.out.println("Empty list -- Nothing to delete.");
		}

		Node currentNode = head;

		//loops through until it finds the node before the index
		while(indexCount < index) {
			currentNode = currentNode.getNext();
			indexCount++;
		}
		currentNode.setNext(currentNode.getNext().getNext()); //sets the node before the index to point to the node after the index

	}

	/*
	 * Given an index, retrieves and object from the list at that index
	 */
	public String getFromList(int index) {
		int indexCount = 0;

		//If head == null, the list must be empty
		if(head == null) {
			System.out.println("Empty list -- Nothing to get.");
		}

		Node currentNode = head;
		while(indexCount <= index) {
			currentNode = currentNode.getNext();
			indexCount++;
		}
		return currentNode.getData().toString();

	}

	/*
	 * Converts the object in the list to a printable string
	 */
	public String toString() {
		String output = "";

		if (head != null) {
			Node currentNode = head.getNext();
			while (currentNode != null) {
				output += "[" + currentNode.getData().toString() + "]";
				currentNode = currentNode.getNext();
			}

		}
		return output;
	}

	/*
	 * Runs through the list twice to find the middle value
	 */
	public void findMiddleNodeTwoLoopsThroughList() {
		int listLength = this.getLength();

		//Looks through the list to find the middle node
		System.out.println("The value of the middle node is: "
				+ getFromList(listLength/2));
	}

	/*
	 * Runs through the list once to find the middle value
	 * Stores the values of the list in a temporary hash map to quickly find the middle value
	 */
	public void findMiddleNodeOneLoopThroughList() {
		Map<Integer, String> nodeMap = new HashMap<Integer, String>();

		//If head == null, the list must be empty
		if(head == null) {
			System.out.println("Empty list -- No middle node.");
		}
		
		Node currentNode = head;
		int index = 0;
		
		//Finds the length of the Linked List and puts each value into the map
		while(currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
			nodeMap.put(index, currentNode.getData().toString());
			index++;
		}
		
		System.out.println("The Hash Map is: " + nodeMap.toString());
		//Returns the value of the middle item in the map
		System.out.println("The value of the middle node is: " 
				+ nodeMap.get(nodeMap.size()/2));
	}
	
	/*
	 * Retrieves the length of the list and returns it as an integer
	 */
	public int getLength() {
		int size = -1;
		
		//If head points to null, the list must have no objects stored
		if(head == null) {
			size++;
			return size;
		}
		
		Node currentNode = head;
		int index = 0;
		
		
		//Continues through the list until it finds a pointer that points to null
		//This must be the end of the list
		while(currentNode != null) {
			currentNode = currentNode.getNext();
			size++;
		}
		return size;		
	}
}

class Node {

	Node next;
	Object data;

	//Constructor for a new node
	public Node(Object dataValue) {
		next = null;
		data = dataValue;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object dataValue) {
		data = dataValue;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node nextValue) {
		next = nextValue;
	}
}
