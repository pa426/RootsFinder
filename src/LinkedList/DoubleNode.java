package LinkedList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Cos Ierotheou
 */
public class DoubleNode {
  // Instance variables:
  private double element1;
  private double element2;
  int counter;
  private DoubleNode next;
  /** Creates a node with null references to its element and next node. */
  public DoubleNode() {
    this(0.0,0.0,0, null);
  }
  /** Creates a node with the given element and next node. */
  public DoubleNode(double e,double c, int x, DoubleNode n) {
    element1 = e;
    element2 = c;
    counter = x;
    next = n;
  }
  // Accessor methods:
  public double getElement1() {
    return element1; 
  }
  
  public double getElement2() {
    return element2; 
  }
  
  public int getCounter() {
    return counter; 
  }
  
  public DoubleNode getNext() { 
    return next;
  }
  // Modifier methods:
  public void setElement1(double newElem1 ) { 
    element1 = newElem1; 
  }
  public void setElement2(double newElem2 ) { 
    element1 = newElem2; 
  }
  public void setNext(DoubleNode newNext) {
    next = newNext; 
  }
}

