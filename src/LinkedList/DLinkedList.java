package LinkedList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author ic01
 */
public class DLinkedList {

    /**
     * @param args the command line arguments
     */
    public DoubleNode head;

    public DLinkedList() {
        head = new DoubleNode();
    }

    //add a new node to the head of the list
    public void addFirst(double element1, double element2, int x) {
        // make variable head point to new node
        head = new DoubleNode(element1, element2, x, head);
    }

    public void addLast(double element1,double element2, int x ) {
        DoubleNode tail;
        tail = head;
        while (tail.getNext() != null) {
            tail = tail.getNext();
        }
        //insert new node at end of list
        tail.setNext(new DoubleNode(element1, element2, x, null));
    }

    //add a new node after position of curnode
    public void addMid(double element1, double element2, int x, int entryafter) {
        DoubleNode curnode;
        curnode = head;
        //go to last node and rermember previous node at all times
        while (curnode != null && curnode.getCounter() != entryafter) {
            curnode = curnode.getNext();
        }
        //if first occurrence of element entryafter was located then insert new node
        if (curnode != null) {
            DoubleNode newnode = new DoubleNode(element1,element2,x,curnode.getNext());
            curnode.setNext(newnode);
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void removeFirst() {
        DoubleNode oldhead;
        oldhead = head;
        //remove first node from linked list
        if (head != null) {
            head = head.getNext();
            oldhead.setNext(null);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void removeLast() {
        DoubleNode temp, previous;
        temp = head;
        previous = temp;
        //go to last node and remember previous node at all times
        while (temp != null && temp.getNext() != null) {
            previous = temp;
            temp = temp.getNext();
        }
        if (previous != null) {
            //remove last node
            previous.setNext(null);
        } else {
            throw new NoSuchElementException();
        }
    }

    //very similar to removeLast except we are looking for element i
    public void removeMid(int element) {
        DoubleNode temp, previous;
        temp = head.getNext();
        previous = null;
        //go to last node and remember previous node at all times
        while (temp.getElement1() != element && temp.getNext() != null) {
            previous = temp;
            temp = temp.getNext();
        }
        if (previous != null && temp.getNext() != null) {
            //not first or last node so we can remove node defined by temp. 
            // set the previous node to that after temp
            previous.setNext(temp.getNext());
            temp.setNext(null);
        } else {
            throw new NoSuchElementException();
        }
    }

    public int size() {
        int size = 0;
        for (DoubleNode n = head; n.getNext() != null; n = n.getNext()) {
            size++;
        }
        return size;
    }

    public static String printList(DLinkedList thelist) {
        DoubleNode temp;
        String text = "";
        if (thelist.isEmpty()) {
            System.out.println("List is empty");
            text = "List is empty";
        } else {
            
            temp = thelist.head;
            while (temp != null) {
                text += "Approx for iteration{}" + temp.getCounter() + " is " + temp.getElement1()+ "\n ";
                temp = temp.getNext();
              
            }
            System.out.println();
            }
        return text;
    }
    
}
