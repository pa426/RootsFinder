package ArrayStack;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ic01
 */
/**
 * Interface for a stack: a collection of objects that are inserted and removed
 * according to the last-in first-out principle. This interface includes the
 * main methods of java.util.Stack.
 *
 * @author Roberto Tamassia
 * @author Michael Goodrich
 * @see EmptyStackException
 */
public interface Stack {

    /**
     * Return the number of elements in the stack.
     *
     * @return number of elements in the stack.
     */
    public int size();

    /**
     * Return whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * Inspect the element at the top of the stack.
     *
     * @return top element in the stack.
     * @exception EmptyStackException if the stack is empty.
     */
    public Object top()
            throws EmptyStackException;

    public void push(Object element1, Object element2);

    /**
     * Remove the top element from the stack.
     *
     * @return element removed.
     * @exception EmptyStackException if the stack is empty.
     */
    public Object popElement1()
            throws EmptyStackException;

    /**
     *
     * @return @throws EmptyStackException
     */
    public Object popElement2()
            throws EmptyStackException;

//    public double[][] getArray();

}
