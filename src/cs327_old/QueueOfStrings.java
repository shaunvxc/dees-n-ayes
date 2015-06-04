package cs327_old;

import java.util.EmptyStackException;

/**
 * Class for creating a QueueOfStrings object
 * This object supports all traditional Queue operations
 * and is implemented for values of type String. 
 * @requiredfiles: Node.java
 * @author shaunviguerie
 * @date January 24, 2012
 */
public class QueueOfStrings {
	private Node head;
	private Node tail;
	
	/**
	 * Constructor for a QueueOfStrings object
	 */
	public QueueOfStrings(){
		head = null;
		tail = null;
	}
	
	/**
	 * Instance method to check whether or not the queue is empty
	 */
	public boolean isEmpty(){
		if(head == null){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Method that returns the value at the front of the queue without 
	 * removing it.
	 * @throws EmptyStackException if stack is empty
	 */
	public String front() throws EmptyStackException{
			if(head == null){
				throw new EmptyStackException();
			}
			return head.getValue();
	}
	
	/**
	 * Method for the traditional queue dequeue operation.
	 * @throws EmptyStackException if queue is empty
	 * @return String value of the node being dequeued
	 */
	public String dq() throws EmptyStackException{
		if(head == null){
			throw new EmptyStackException();
		}
		Node temp = head;
		head = temp.getNext();
//		if(head == null){
//			tail = null;
//		}
		return temp.getValue();
	}
	
	/** 
	 * Method for traditional enqueue operation.
	 * Enqueues a string value to the back of the queue
	 * @param String s value added to the back of the queue
	 */
	public void nq(String s){
		Node temp = new Node(s);
		if(head == null){
			head = temp;
			tail = temp;
		}
		else{
			tail.setNext(temp);
			tail = temp;
		}
	}
	
	
}