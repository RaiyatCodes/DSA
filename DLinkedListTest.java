/**
 * Unit test cases for the implementation of a Doubly-Linked list.
 * 
 * @author Raiyat Haque
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

import java.util.NoSuchElementException;

public class DLinkedListTest{
    /**
     * the list to use for testing 
    */
    private DLinkedList<Integer> list;
    
    /**
     * Returns a linked list with the given items
     * (the items in the list are in the same order as in the given array)
     */
    private static <E> DLinkedList<E> load(E... items){
        DLinkedList<E> list = new DLinkedList<E>();
        for (E data: items){
            list.addLast(data);
        }
        return list;
    }
    
    @Test
    public void test_addFirst(){
        // testing empty list
        list = load();
        list.addFirst( 4 );
        assertEquals( list.toStringNext(), "[ 4 ]" );
        assertEquals( list.toStringPrev(), "[ 4 ]" );
        
        // testing a single list
        list = load( 8 );
        list.addFirst( 9 );
        assertEquals( list.toStringNext(), "[ 9 8 ]" );
        assertEquals( list.toStringPrev(), "[ 9 8 ]" );
        
        // testing multi list
        list = load( 6, 1, 7, 5, 3, 4 );
        list.addFirst( 2 );
        assertEquals( list.toStringNext(), "[ 2 6 1 7 5 3 4 ]" );
        assertEquals( list.toStringPrev(), "[ 2 6 1 7 5 3 4 ]");
    }
    
    @Test
    public void test_addLast(){
        //testing empty list
        list = load();
        list.addLast(4);
        assertEquals( list.toStringNext(), "[ 4 ]" );
        assertEquals( list.toStringPrev(), "[ 4 ]" );
        
        //testing single list
        list = load( 8 );
        list.addLast( 7 );
        assertEquals( list.toStringNext(), "[ 8 7 ]" );
        assertEquals( list.toStringPrev(), "[ 8 7 ]" );
        
        //testing multi list
        list = load( 6, 1, 7, 5, 3, 4 );
        list.addLast( 9 );
        assertEquals( list.toStringNext(), "[ 6 1 7 5 3 4 9 ]" );
        assertEquals( list.toStringPrev(), "[ 6 1 7 5 3 4 9 ]" );
    }
    
    @Test
    public void test_isEmpty(){
        // testing empty list 
        list = load();
        assertTrue( list.isEmpty() );
        assertEquals( list.toStringNext(), "[ ]" );
        assertEquals( list.toStringPrev(), "[ ]" );
        
        // testing single list
        list = load( 8 );
        assertFalse( list.isEmpty() );
        assertEquals( list.toStringNext(), "[ 8 ]");
        assertEquals( list.toStringPrev(), "[ 8 ]");
        
        //testing multi list
        list = load(8, 9, 0, 3, 5, 6);
        assertFalse( list.isEmpty() );
        assertEquals( list.toStringNext(), "[ 8 9 0 3 5 6 ]");
        assertEquals( list.toStringPrev(), "[ 8 9 0 3 5 6 ]");
    }
    
    @Test
    public void test_getFirst(){
        //testing empty list
        list = load();
        assertThrowsExactly( NoSuchElementException.class, () -> list.getFirst() );
        assertEquals( list.toStringNext(), "[ ]" );
        assertEquals( list.toStringPrev(), "[ ]" );
        
        //testing single list
        list = load( 8 );
        assertTrue ( list.getFirst().equals(8));
        assertEquals( list.toStringNext(), "[ 8 ]" );
        assertEquals( list.toStringPrev(), "[ 8 ]" );
        
        //testing multi list
        list = load( 6, 1, 5, 6, 9 );
        assertTrue( list.getFirst().equals(6));
        assertEquals( list.toStringNext(), "[ 6 1 5 6 9 ]" );
        assertEquals( list.toStringPrev(), "[ 6 1 5 6 9 ]" );
    }
    
    @Test 
    public void test_getLast(){
        //testing empty list
        list = load();
        assertThrowsExactly( NoSuchElementException.class, () -> list.getLast() );
        assertEquals( list.toStringNext(), "[ ]" );
        assertEquals( list.toStringPrev(), "[ ]" );
        
        //testing single list
        list = load( 9 );
        assertTrue ( list.getLast().equals(9));
        assertEquals( list.toStringNext(), "[ 9 ]" );
        assertEquals( list.toStringPrev(), "[ 9 ]" );
        
        //testing multi list
        list = load( 8, 9, 4, 7, 2, 5);
        assertTrue( list.getLast().equals(5));
        assertEquals( list.toStringNext(), "[ 8 9 4 7 2 5 ]" );
        assertEquals( list.toStringPrev(), "[ 8 9 4 7 2 5 ]" );
    }
    
    @Test
    public void test_contains(){
        //testing empty list
        list = load();
        assertFalse( list.contains(5) );
        assertEquals( list.toStringNext(), "[ ]" );
        assertEquals( list.toStringPrev(), "[ ]" );
        
        //testing single list
        list = load( 10 );
        assertTrue( list.contains(10) );
        assertEquals( list.toStringNext(), "[ 10 ]");
        assertEquals( list.toStringPrev(), "[ 10 ]");
        
        //testing multi test
        list = load( 5, 6, 8, 2, 4 );
        assertTrue( list.contains(8) );
        assertFalse( list.contains(0) );
        assertEquals( list.toStringNext(), "[ 5 6 8 2 4 ]" );
        assertEquals( list.toStringPrev(), "[ 5 6 8 2 4 ]" );
    }
    
    @Test
    public void test_get(){
        //testing empty list
        list = load();
        assertThrowsExactly( IndexOutOfBoundsException.class, () -> list.get(1));
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
        
        //testing single list
        list = load(11);
        assertTrue( list.get(1).equals(11)) ;
        assertEquals( list.toStringNext(), "[ 11 ]");
        assertEquals( list.toStringPrev(), "[ 11 ]");
        
        //testing multi test
        list = load(10, 12, 13, 4, 5);
        assertThrowsExactly( IndexOutOfBoundsException.class, () -> list.get(10) );
        assertTrue( list.get(3).equals(13));
        assertEquals( list.toStringNext(), "[ 10 12 13 4 5 ]");
        assertEquals( list.toStringPrev(), "[ 10 12 13 4 5 ]"); 
    }
    
    @Test
    public void test_set(){
        //testing empty list
        list = load();
        assertThrowsExactly( IndexOutOfBoundsException.class, () -> list.set( 3, 10 ));
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
        
        //testing single list
        list = load(4);
        list.set(1,5);
        assertEquals( list.toStringNext(), "[ 5 ]");
        assertEquals( list.toStringPrev(), "[ 5 ]");
        
       //testing multi list
        list = load(5,6,3,2,1);
        list.set(1,7);
        assertEquals( list.toStringNext(), "[ 7 6 3 2 1 ]");
        assertEquals( list.toStringPrev(), "[ 7 6 3 2 1 ]");
        
        list = load(4,6,7);
        assertThrowsExactly( IndexOutOfBoundsException.class, () -> list.set(5,3));
        assertEquals( list.toStringNext(), "[ 4 6 7 ]");
        assertEquals( list.toStringPrev(), "[ 4 6 7 ]");
    }
    
    @Test
    public void test_add(){
        //testing empty list
        list = load();
        list.add(1,2);
        assertEquals( list.toStringNext(), "[ 2 ]");
        assertEquals( list.toStringPrev(), "[ 2 ]");
        
        //testing single list
        list = load(4);
        list.add(1,5);
        assertEquals( list.toStringNext(), "[ 5 4 ]");
        assertEquals( list.toStringPrev(), "[ 5 4 ]");
        
        //testing multi list
        list = load(6, 7, 9, 3);
        list.add(3,2);
        assertEquals( list.toStringNext(), "[ 6 7 2 9 3 ]");
        assertEquals( list.toStringPrev(), "[ 6 7 2 9 3 ]");
        
        list  = load(4,6,3,1);
        assertThrowsExactly( IndexOutOfBoundsException.class, () -> list.add(10,5));
        assertEquals( list.toStringNext(), "[ 4 6 3 1 ]");
        assertEquals( list.toStringPrev(), "[ 4 6 3 1 ]");
    }
    
    @Test
    public void test_clear(){
        //testing empty list
        list = load();
        list.clear();
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
        
        //testing single list
        list = load(5);
        list.clear();
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
        
        //testing multi test
        list = load(6,8,9,10,44);
        list.clear();
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
    }
    
    @Test
    public void test_toStringNext(){
        //testing empty list
        list = load(); 
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
        
        //testing single list
        list = load(24);
        assertEquals( list.toStringNext(), "[ 24 ]");
        assertEquals( list.toStringPrev(), "[ 24 ]");
        
        //testing multi list
        list = load(2,4,6,3);
        assertEquals( list.toStringNext(), "[ 2 4 6 3 ]");
        assertEquals( list.toStringPrev(), "[ 2 4 6 3 ]");
    }
    
    @Test
    public void test_toStringPrev(){
        //testing empty list
        list = load();
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
        
        //testing single list
        list = load(34);
        //ist.clear();
        assertEquals( list.toStringNext(), "[ 34 ]");
        assertEquals( list.toStringPrev(), "[ 34 ]");
        
        //testing multi list
        list = load(3,5,6,7,12,10);
        //list.clear();
        assertEquals( list.toStringNext(), "[ 3 5 6 7 12 10 ]");
        assertEquals( list.toStringPrev(), "[ 3 5 6 7 12 10 ]");
    }
    
    @Test
    public void test_removeFirst(){
        //testing an empty list;
        list = load();
        assertThrowsExactly( IndexOutOfBoundsException.class, () -> list.removeFirst());
        assertEquals( list.toStringNext(), "[ ]" );
        assertEquals( list.toStringPrev(), "[ ]" );
        
        //testing a single list
        list = load(5);
        assertTrue( list.removeFirst().equals(5));
        assertEquals( list.toStringNext(), "[ ]" );
        assertEquals( list.toStringPrev(), "[ ]" );
        
        //testing a multi list
        list = load(5,6,3,1,8);
        assertTrue( list.removeFirst().equals(5));
        assertEquals( list.toStringNext(), "[ 6 3 1 8 ]");
        assertEquals( list.toStringPrev(), "[ 6 3 1 8 ]");
    }
    
    @Test 
    public void test_removeLast(){
        //testing an empty list
        list = load();
        assertThrowsExactly( IndexOutOfBoundsException.class, () -> list.removeFirst());
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
        
        //testing a single list
        list = load(6);
        assertTrue( list.removeLast().equals(6));
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
        
        //testign a multi list
        list = load(8,9,3,1,6,10);
        assertTrue( list.removeLast().equals(10));
        assertEquals( list.toStringNext(), "[ 8 9 3 1 6 ]");
        assertEquals( list.toStringPrev(), "[ 8 9 3 1 6 ]");
    }
    
    @Test
    public void test_remove(){
        //testing empty list
        list = load();
        assertThrowsExactly( IndexOutOfBoundsException.class, () -> list.remove(3));
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
        
        //testing single list
        list = load(9);
        assertTrue( list.remove(1).equals(9));
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
        
        //testing multi list
        list = load(10,4,6,2,8,5);
        assertTrue( list.remove(3).equals(6));
        assertEquals( list.toStringNext(), "[ 10 4 2 8 5 ]");
        assertEquals( list.toStringPrev(), "[ 10 4 2 8 5 ]");
    }
    
    @Test
    public void test_removeAll(){
        //testing empty list
        list = load();
        assertThrowsExactly( NoSuchElementException.class, () -> list.removeAll(1));
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
        
        //testing single list
        list = load(7);
        assertTrue(list.removeAll(7));
        assertEquals( list.toStringNext(), "[ ]");
        
        //testing multi list
        list = load(9,2,5,6,3,3);
        assertTrue( list.removeAll(3));
        assertEquals( list.toStringNext(), "[ 9 2 5 6 ]");
        assertEquals( list.toStringPrev(), "[ 9 2 5 6 ]");
        
        list = load(3,4,6,23,6,2);
        assertTrue( list.removeAll(6));
        assertEquals( list.toStringNext(), "[ 3 4 23 2 ]");
        assertEquals( list.toStringPrev(), "[ 3 4 23 2 ]");
        
        list = load(3,1,5,6,7,8,1);
        assertFalse( list.removeAll(2));
        assertEquals( list.toStringNext(), "[ 3 1 5 6 7 8 1 ]");
        assertEquals( list.toStringPrev(), "[ 3 1 5 6 7 8 1 ]");
    }
    
    @Test
    public void test_equals(){ 
        //testing empty list
        DLinkedList<Integer> list_1 = load();
        DLinkedList<Integer> list_2 = load(1,2,3,4,5);
        
        assertFalse( list_1.equals(list_2));
        assertEquals( list_1.toStringNext(), "[ ]");
        assertEquals( list_2.toStringNext(), "[ 1 2 3 4 5 ]");
        assertEquals( list_1.toStringPrev(), "[ ]");
        assertEquals( list_2.toStringPrev(), "[ 1 2 3 4 5 ]");
        
        //testing single list
        list_1 = load(5);
        list_2 = load(4);
        
        assertFalse( list_1.equals(list_2));
        assertTrue( list_1.equals(list_1));
        assertEquals( list_1.toStringNext(), "[ 5 ]");
        assertEquals( list_2.toStringPrev(), "[ 4 ]");
        
        //testing multi list
        list_1 = load(4,5,7,8);
        list_2 = load(4,5,7,8);
        DLinkedList<Integer> list_3 = load(5,4,8,7);
        DLinkedList<Integer> list_4 = load(4,5,7);
        
        assertTrue( list_1.equals(list_1));
        assertTrue( list_1.equals(list_2));
        assertFalse( list_1.equals(list_3));
        assertFalse( list_1.equals(list_4));
        assertEquals( list_1.toStringNext(), "[ 4 5 7 8 ]");
        assertEquals( list_2.toStringPrev(), "[ 4 5 7 8 ]");
        
        //testing a list not DLinkedList
        String[] list_string = {"apples", "bananas", "oranges"};
        list_1 = load(4,5,7,8,0);
        assertFalse( list_1.equals(list_string));
        assertEquals( list_1.toStringNext(), "[ 4 5 7 8 0 ]");
    }
    
    @Test 
    public void test_iterFails(){
        //testing multi list
        list = load(1,2,3);
        Iterator<Integer> iter = list.iterator();
        assertTrue(iter.next().equals(1));
        assertTrue(iter.next().equals(2));
        assertTrue(iter.next().equals(3));
        assertEquals( list.toStringNext(), "[ 1 2 3 ]");
        assertEquals( list.toStringPrev(), "[ 1 2 3 ]");
        
        list = load(1,2,3,7,8,4);
        iter = list.iterator();
        assertTrue(iter.next().equals(1));
        assertTrue(iter.next().equals(2));
        assertTrue(iter.next().equals(3));
        iter.remove();
        assertTrue(iter.next().equals(7));
        assertEquals( list.toStringNext(), "[ 1 2 7 8 4 ]");
        assertEquals( list.toStringPrev(), "[ 1 2 7 8 4 ]");
        
        //testing single list
        list = load(2);
        iter = list.iterator();
        assertTrue(iter.next().equals(2));
        assertEquals( list.toStringNext(), "[ 2 ]");
        assertEquals( list.toStringPrev(), "[ 2 ]");
        
        //testing empty list
        list = load();
        Iterator<Integer> itr = list.iterator();
        assertThrowsExactly( NoSuchElementException.class, () -> itr.next());
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
    }
    
    @Test 
    public void test_removeAllIter(){
        //testing empty list
        list = load();
        assertThrowsExactly( IllegalStateException.class, () -> list.removeAllIter(3));
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
        
        //testing single list
        list = load(4);
        assertFalse( list.removeAllIter(2));
        assertTrue( list.removeAllIter(4));
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
        
        //testing multi list  (need to talk with professor about this)
        list = load(1,2,3,4,5,4);
        assertTrue( list.removeAllIter(4));  
        assertEquals( list.toStringNext(), "[ 1 2 3 5 ]");
        assertEquals( list.toStringPrev(), "[ 1 2 3 5 ]");
        
        list = load(1,2,2,3,5);
        assertTrue( list.removeAllIter(2));
        assertEquals( list.toStringNext(), "[ 1 3 5 ]");
        assertEquals( list.toStringPrev(), "[ 1 3 5 ]");
    }
    
    @Test
    public void test_containsIter(){
        //testing empty list
        list = load();
        assertFalse( list.containsIter(3));
        assertEquals( list.toStringNext(), "[ ]");
        assertEquals( list.toStringPrev(), "[ ]");
        
        //testing single list                                                 
        list = load(4);
        assertTrue( list.containsIter(4));
        assertEquals( list.toStringNext(), "[ 4 ]");
        assertEquals( list.toStringPrev(), "[ 4 ]");
        
        //testing multi list
        list = load(1,3,5,6,7);
        assertTrue( list.contains(5));
        assertFalse( list.contains(2));
        assertEquals( list.toStringNext(), "[ 1 3 5 6 7 ]");
        assertEquals( list.toStringPrev(), "[ 1 3 5 6 7 ]");
    }
}