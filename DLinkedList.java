/**
 * Implementation of Header Doubly-Linked List
 * 
 * @author Raiyat Haque
*/
import java.util.NoSuchElementException;
import java.util.Iterator;

public class DLinkedList<E> implements Iterable<E> {
    // Representation of the list nodes 
    private class Node {
        Node next; // the successor of the node
        Node prev; // the predecessor of the node
        E data; // the data value stored in the node
        
        // creates a node with the given data item and no successor and predecessor
        public Node(E data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    private Node head; // the head of the list
    private Node tail; // the tail of the list
    private int count; // to keep track of number of nodes in the list
    
    // The class that creates iterable objects 
    private class DListIterator implements Iterator<E>{
        private Node currNode;
        private boolean turnedON;
        public DListIterator(){
            currNode = head.next;
            turnedON = false;
        }
        
        @Override
        public boolean hasNext(){
            return (currNode != null);
        }
        
        @Override
        public E next(){
            if(!(hasNext())){ 
                throw new NoSuchElementException();
            }
            E data = null;
            data = currNode.data;
            currNode = currNode.next;
            turnedON = true;
            return data;
        }
        //This removes the last element given by next
        @Override
        public void remove(){
            if(turnedON){
                if(currNode == null){
                    tail = tail.prev;
                    tail.next = null;
                    count--;
                    turnedON = false;
                }
                else{
                    Node temp = currNode;
                    currNode = currNode.prev;
                    currNode.prev.next = temp;
                    temp.prev = currNode.prev;
                    currNode = temp;
                    count--;
                    turnedON = false;
                }
            }
            else{
                throw new IllegalStateException();
            }  
        }
    }
    
    // method of the stack -- required for Iterable
    @Override
    public Iterator<E> iterator() {
        return new DListIterator();
    }
    
    /**
     * Constructor that initializes the list
     * 
    */
    public DLinkedList(){
        Node dummy = new Node(null);
        head = dummy;
        tail = head;
        count = -1;
    }
    
    /**
     * Checks if the list is empty or not.
     * 
    */
    public boolean isEmpty(){
        return (count == -1);
    }
    
    /**
     * Adds the given item to the front of the list
     * 
     * @param item  the data to add
    */
    public void addFirst(E item){
        Node newNode = new Node(item);
        
        if(isEmpty()){
            head.next = newNode;
            newNode.prev = head;
            newNode.next = null;
            tail = newNode;
            count++;
        }
        
        else {
            Node temp = head.next;
            head.next = newNode;
            newNode.prev = head;
            newNode.next = temp;
            temp.prev = newNode;
            count++;
        }
    }
    
    /**
     * Adds the given item to the end of the list
     * 
     */
    public void addLast(E item){
        Node newNode = new Node(item);
        
        if(isEmpty()){
            addFirst(item);
        }
        
        else {
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = null;
            tail = newNode;
            count++;
        }
    }
    
    /**
     * Returns the first item of the list
     */
    public E getFirst(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        
        return head.next.data; 
    }
    
    /**
     * Returns the last element of the list
     * 
    */
    public E getLast(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        
        return tail.data;
    }
    
    /**
     * Determines if the list contains the given item
     * 
     * @param item   the data that needs to be checked whether it is in the list or not
    */
    public boolean contains(E item){
        if(isEmpty()){
            return false;
            //throw new NoSuchElementException();
        }
        
        Node current = head;
        for(int i = 0; i <= count; i++){
            current = current.next;
            if(current.data.equals(item)){
                return true;
            }
        }
        return false;
    }
    
    /** 
     * Returns the item at the given index in the list
     * 
     * @param index  the place where the data is in the list
     * 
     * @throws IndexOutOfBoundsException if the index is invalid
    */
    public E get(int index){
        int actual_index = index - 1;
        E item = null;
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        if(actual_index > count || actual_index < 0){
            throw new IndexOutOfBoundsException();
        }
        else{
            Node current = head;
            for(int i = 0; i <= actual_index; i++){
                current = current.next;
            }
        
            item = current.data;
        }
        return item;
    }
    
    /**
     * Replaces the item in the node at the given index with the given item and returns the previous item at that index
     * 
     * @param index  the place where the data will be set in the list
     * @param item   the given data by the user
     * 
     * @throws IndexOutOfBoundsException if the index is invalid
    */
    public E set(int index, E item){
        int actual_index = index - 1;
        if(actual_index > count || actual_index < 0){
            throw new IndexOutOfBoundsException();
        }
        
        Node current = head;
        
        for(int i = 0; i <= actual_index; i++){
            current = current.next;
        }
        
        E oldItem = current.data;
        current.data = item;
        
        return oldItem;
    }
    
    /** 
     * Adds the given item at the given index
     * 
     * @param item  the given data by the user
     * @param index  the given position to where the item will be placed in the list
     * 
     * @throws IndexOutOfBoundsException if the index is invalid
    */
    public void add(int index, E item){
        Node newNode = new Node(item);
        int actual_index = index - 1;
        
        if(isEmpty()){
            head.next = newNode;
            newNode.prev = head;
            newNode.next = null;
            tail = newNode;
            count++;
        }
        else if((isEmpty() == false) && (actual_index > count || actual_index < 0)){
            throw new IndexOutOfBoundsException();
        }
        else{
            Node current = head;
            for(int i = 0; i <= actual_index; i++){
                current = current.next;
            }
        
            newNode.prev = current.prev;
            current.prev.next = newNode;
            newNode.next = current;
            current.prev = newNode;
            count++;
        }
    }
    
    /** 
     * Clears all the elements in the list
    */ 
    public void clear(){
        Node current = head;
        Node temp = head;
        
        for(int i = 0; i <= count; i++){
            current = current.next;
            temp.next = null;
            temp.prev = null;
            temp = current;
        }
        
        count = -1;
    }
    
    /** 
     * Returns a string representation of the list
    */ 
    public String toStringNext(){
        if(isEmpty()){
            return "[ ]";
        }
        
        StringBuilder sb = new StringBuilder();
        Node current = head;
        sb.append("[ ");
        for(int i = 0; i <= count; i ++){
            current = current.next;
            sb.append(current.data + " ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    /** 
     * Returns a string representation of the list, but in reverse
     */
    public String toStringPrev(){
        if(isEmpty()){
            return "[ ]";
        }
        Object[] arr = new Object[count + 1];
        StringBuilder sb = new StringBuilder();
        Node current = tail;
        
        for( int i = count; i >= 0; i--){
            arr[count-i] = current.data;
            current = current.prev;
        }
        sb.append("[ ");
        for(int i = count; i >= 0; i--){
            sb.append(arr[i] + " ");
        }
        sb.append("]");
        
        return sb.toString();
    }
    
    /**
     * Removes the first element in the list
     */
    public E removeFirst(){
        E data = null;
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        Node temp = head.next;
        Node temp2 = temp.next;
        if( temp2 != null){
            data = temp.data;
            head.next = temp2;
            temp2.prev = head;
            count--;
        }
        else{
            data = temp.data;
            head.next = null;
            tail = head;
            count--;
        }
        return data;
    }
    
    /**
     * Removes the last element in the list
     */
    public E removeLast(){
        E data = null;
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        if(tail.prev == head){
            data = tail.data;
            tail = head;
            count--;
        }
        else{
            data = tail.data;
            tail = tail.prev;
            count--;
        }
        return data;
    }
    
    /**
     * Removes the element at the given index
     * 
     * @param index  the location in the list
     */
    public E remove(int index){
        E data = null;
        int actual_index = index - 1;
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        if(actual_index < 0 || actual_index > count){
            throw new IndexOutOfBoundsException();
        }
        else{
            Node curr = head;
            for(int i = 0; i <= actual_index; i++){
                curr = curr.next;
            }
            data = curr.data;
            if(curr.prev == head){
                tail = head;
                head.next = null;
                count--;
            }
            else if(curr == tail){
                tail = curr.prev;
                curr.prev.next= null;
                count--;
            }
            else{
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                count--;                   
            }
        }
        return data;
    }
    
    /**
     * Removes all occurrences of the given item in the list
     * 
     * @param item  the data that needs to be removed
     */ 
    public boolean removeAll(E item){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        if(contains(item)){
            Node current = head.next;
            if(current == tail){
                tail = head;
                head.next = null;
                count--;
            }
            while(current.next != null){
                if(current.data.equals(item)){
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    count--;
                }
                current = current.next;
            }
            if(current.data.equals(item)){
                tail = current.prev;
                current.prev.next = null;
                count--;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Same as contains but uses an iterator over this list to visit nodes as it searches for the item
     * 
     * @param item  the data that needs to be checked
     */
    public boolean containsIter( E item ){
        Iterator<E> itr = this.iterator();
        while(itr.hasNext()){
            E data = itr.next();
            if(data.equals(item)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Determines if this list is equal to the given list
     * 
     * @param other   the list to be compared with
     */
    public boolean equals(Object other){ 
        if(!(other instanceof DLinkedList)){ 
            return false; 
        }
        else if(this == other){ 
            return true;
        }
        DLinkedList<E> list = (DLinkedList<E>) other;
        Iterator<E> iterator = this.iterator();
        Iterator<E> itr = list.iterator();
        while(iterator.hasNext() && itr.hasNext()){
            E data1 = iterator.next();
            E data2 = itr.next();
            if(!(data1.equals(data2))){
                return false;
            }
            if(this.getLast().equals(data1) && list.getLast().equals(data2)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Same as removeAll but uses an iterator over this list to visit the nodes and remove item
     * 
     * @param item  the data that needs to be removed from the list
    */
    public boolean removeAllIter(E item){
        Iterator<E> iter = this.iterator();
        if(isEmpty()){
            throw new IllegalStateException();
        }
        if(contains(item)){
            while(iter.hasNext()){
                E data = iter.next();
                if(data.equals(item)){
                    iter.remove();
                }
            }
            return true;
        }
        return false;
    }
}
