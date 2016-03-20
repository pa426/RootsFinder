package ArrayStack;


import org.apache.commons.lang.ArrayUtils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ic01
 */
/**
 * Implementation of the stack ADT using a fixed-length array. An exception is
 * thrown if a push operation is attempted when the size of the stack is equal
 * to the length of the array. This class includes the main methods of the
 * built-in class java.util.Stack.
 */
public class BidimensionalArrayStack implements Stack {

    protected int capacity; 	// The actual capacity of the stack array
    public static final int CAPACITY = 2;	// default array capacity
    public Object S[][];		// Generic array used to implement the stack 
    protected int top = -1;	// index for the top of the stack (-1 = empty stack)

    public BidimensionalArrayStack() {
        this(CAPACITY); // default capacity 
    }

    public BidimensionalArrayStack(int cap) {
        capacity = cap;
        S = new Object[2][capacity];
    }

    public int size() {
        return (top + 1);
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public void push(Object element1, Object element2) throws FullStackException {
        if (size() == capacity) {
            //throw new FullStackException("Stack is full. Stack size max is "+ capacity);
            // can replace previous line with code to double stack size
            doubleArray();
        }
        S[0][++top] = element1;
        S[1][top] = element2;
    }

    public Object top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        }
        return S[0][top];
    }

    public Object popElement1() throws EmptyStackException {
        Object element1;
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        }
        element1 = S[0][top];
        S[0][top] = null; // dereference S[top] for garbage collection.
        return element1;
    }

    public Object popElement2() throws EmptyStackException {
        Object element2;
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        }
        element2 = S[1][top];
        S[1][top--] = null; // dereference S[top] for garbage collection.
        return element2;
    }

    private void doubleArray() {

        capacity = 2 * capacity;
        Object[][] newArray = new Object[2][capacity];
        for (int i = 0; i < S[0].length; i++) {
            newArray[0][i] = S[0][i];
            newArray[1][i] = S[1][i];
        }
        S = newArray;
    }

//    public double[][] getArray() {
//        
//        try {
//            for (int i = (capacity-1); i > 0; i--) {
//                while (Double.parseDouble(S[0][i].toString()) == 0.0) {
//                    popElement1();
//                    popElement2();
//
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("XOXXOOXO" + e);
//
//        }
//        double[][] data = new double[2][capacity];
//
//        try {
//
//            for (int i = 0; i < (S[0].length); i++) {
//                data[0][i] = ((Number) S[0][i]).doubleValue();
//                data[1][i] = ((Number) S[1][i]).doubleValue();
//            }
//            return data;
//
//        } catch (Exception e) {
//            System.out.println("GAGAGAGAGGAGAAGA" + e);
//
//        }
//
//        return data;
//    }

//
//    public static void main(String[] args) {
//        Stack S = new SimpleArrayStackofchars();
//        S.push("1");
//        S.push("2");
//        S.push("3");
//        S.push("4");
//        S.push("5");
//        S.push("6");
//      while (!S.isEmpty()) {
//        System.out.println(S.pop());
//        }        
//    }
}
