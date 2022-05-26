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
 * Test methods to verify your implementation of the methods for P08.
 */
public class DNATester {

  /**
   * Checks the correctness of the Node class
   *
   * @return true if and only if all methods work correctly
   */
  public static boolean testNode(){
    Node<String> node = new Node<>("String");
    if(!node.getData().equals("String")){
      return false;
    }
    Node<Character> node2 = new Node<>('c');
    if(!node2.getData().equals('c')){
      return false;
    }
    Node<String> node3 = new Node<>("String2", node);
    if(node3.getNext() != node){
      return false;
    }
    return true;
  }

  /**
   * Checks the correctness of the enqueue and dequeue methods
   *
   * @return true if and only if all methods work correctly
   */
  public static boolean testEnqueueDequeue(){

    LinkedQueue<Character> queue = new LinkedQueue<>();

    try {
      // 1. Check enqueue with empty list
      queue.enqueue('A');
      if(!queue.toString().trim().equalsIgnoreCase("A")){
        return false;
      }
      // 2. Checks enqueue with non-empty list
      queue.enqueue('B');
      queue.enqueue('C');
      if(!queue.toString().trim().equalsIgnoreCase("A B C")){
        return false;
      }
      // 3. Checks dequeue with non-empty list
      queue.dequeue();
      queue.dequeue();
      if(!queue.toString().trim().equalsIgnoreCase("C")){
        return false;
      }
      // 4. Checks dequeue with one element list
      queue.dequeue();
      if(!queue.toString().trim().equalsIgnoreCase("")){
        return false;
      }
      // 5. Checks dequeue with empty element
      try{
        queue.dequeue();
        return false;
      } catch (NoSuchElementException e){
        // Passed
      } catch (Exception e){
        return false;
      }
    } catch (Exception e){
      return false;
    }
    return true;
  }

  /**
   * Checks the correctness of the isEmpty and size methods
   *
   * @return true if and only if all methods work correctly
   */
  public static boolean testQueueSize(){

    LinkedQueue<String> queue = new LinkedQueue<>();

    // 1. test is empty and size with empty list
    if(!queue.isEmpty()){
      return false;
    }
    if(queue.size() != 0){
      return false;
    }

    // 2. test is empty and size with non-empty list
    queue.enqueue("test");
    if(queue.isEmpty()){
      return false;
    }
    if(queue.size() != 1){
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the toString method
   *
   * @return true if and only if all methods pass
   */
  public static boolean testToString(){
    LinkedQueue<String> test = new LinkedQueue<>();
    test.enqueue("A");
    test.enqueue("B");

    if(!test.toString().equals("A B")){
      return false;
    }
    return true;
  }

  /**
   * Tests the transcribeDNA() method
   *
   * @return true if and only if the method works correctly
   */
  public static boolean testTranscribeDNA() {
    DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
    String mRNA = "CCUCAGUCAAUUCGCUGGCCCUGUAUGACAGAACCAUUAGAGGCUCGAUCUUUCAGAGAC";
    System.out.println(testDNA.transcribeDNA().toString());
    if (testDNA.transcribeDNA().toString().replaceAll(" ", "").equals(mRNA)) {
      return true;
    }
    return false;
  }

  /**
   * Checks the correctness of mRNATranslate method
   *
   * @return true if and only if all methods correctly
   */
  public static boolean testMRNATranslate(){

    DNA dna = new DNA("GGA");

    LinkedQueue<String> transcribed = dna.mRNATranslate(dna.transcribeDNA());

    if(!transcribed.toString().equals("P")){
      return false;
    }

    DNA dna1 = new DNA("GGAGGA");

    LinkedQueue<String> test2 = dna1.mRNATranslate(dna1.transcribeDNA());
    if(!test2.toString().replaceAll(" ", "").equals("PP")){
      return false;
    }

    return true;
  }

  /**
   * Tests the translateDNA() method
   * @return true if and only if the method works correctly
   */
  public static boolean testTranslateDNA() {
    DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
    System.out.println(testDNA.translateDNA().toString());
    if (!testDNA.translateDNA().toString().replaceAll(" ", "")
            .equals("PQSIRWPCMTEPLEARSFRD")) {
      return false;
    }
    // 2. Tests null DNA sequence
    DNA test = new DNA(null);
    if(!test.translateDNA().toString().replaceAll(" ", "").equals("")){
      return false;
    }
    // 3. Test to make sure if 3 char sequence corresponds to "STOP" that the method returns
    DNA test2 = new DNA("AAAATC");
    if(!test2.translateDNA().toString().replaceAll(" ", "").equals("F")){
      return false;
    }
    return true;
  }

  /**
   * Main method - use this to run your test methods and output the results (ungraded)
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("Node Class: " + testNode());
    System.out.println("EnqueueDequeue: " + testEnqueueDequeue());
    System.out.println("testQueueSize: " + testQueueSize());
    System.out.println("transcribeDNA: " + testTranscribeDNA());
    System.out.println("testTranscribeMRNA: " + testMRNATranslate());
    System.out.println("translateDNA: " + testTranslateDNA());
    System.out.println("testToString: " + testToString());

  }

}
