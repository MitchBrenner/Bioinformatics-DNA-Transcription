//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 DNA
// Course:   CS 300 Spring 2022
//
// Author:   Mitchell Brenner
// Email:    mkbrenner3@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

/**
 * A generic implementation of a linked queue
 *
 * @param <T> The type of data to be contained in the queue
 */
public class LinkedQueue<T> implements QueueADT<T>{

  protected Node<T> back;    //The node at the back of the queue, added MOST recently
  protected Node<T> front;   //The node at the front of the queue, added LEAST recently
  private int n;             //The number of elements in the queue

  /**
   * Adds the given data to this queue; every addition to a queue is made at the back
   *
   * @param data the data to add
   */
  @Override
  public void enqueue(T data) {
    Node<T> newNode = new Node<>(data);

    // if LinkedQueue is empty
    if(n == 0){
      front = newNode;
      back = newNode;
    }
    // if LinkedQue is not empty
    else {
      back.setNext(newNode);
      back = newNode;
    }
    // Increment number of elements in queue
    n++;

  }

  /**
   * Removes and returns the item from this queue that was least recently added
   *
   * @return the item from this queue that was least recently added
   * @throws NoSuchElementException if this queue is empty
   */
  @Override
  public T dequeue() throws NoSuchElementException{

    // if LinkedQueue is empty
    if(n == 0){
      throw new NoSuchElementException("LinkedQueue is empty");
    }

    // Node to be returned
    Node<T> tempNode = front;

    // if LinkedQueue has 1 element
    if(n == 1) {
      front = null;
      back = null;
    }
    // if LinkedQueue has more than 1 element
    else {
      front = front.getNext();
    }

    // Decrement number of elements in LinkedQueue
    n--;

    return tempNode.getData();
  }

  /**
   * Returns the item least recently added to this queue without removing it
   *
   * @return the item least recently added to this queue
   * @throws NoSuchElementException if this queue is empty
   */
  @Override
  public T peek() throws NoSuchElementException{

    // if LinkedQueue is empty
    if(n == 0){
      throw new NoSuchElementException("LinkedQueue is empty");
    }

    return front.getData();
  }

  /**
   * Checks whether the queue contains any elements
   *
   * @return true if this queue is empty; false otherwise
   */
  @Override
  public boolean isEmpty() {
    return n == 0;
  }

  /**
   * Returns the number of items in this queue
   *
   * @return the number of items in this queue
   */
  @Override
  public int size() {
    return n;
  }

  /**
   * Returns a string representation of this queue, beginning at the front (least recently
   * added) of the queue and ending at the back (most recently added). An empty queue is
   * represented as an empty string; otherwise items in the queue are represented using their
   * data separated by spaces
   *
   * @return the sequence of items in FIFO order, separated by spaces
   */
  @Override
  public String toString(){

    String returnMe = "";
    Node<T> curr = front;

    while(curr != null){
      returnMe += curr.getData() + " ";
      curr = curr.getNext();
    }
    return returnMe.trim();
  }

}
