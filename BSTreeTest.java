/**
 * JUnit test cases for the Binary Search Tree Implementation
 * 
 * @author Raiyat Haque
*/

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class BSTreeTest{
    /**
     * the tree used for testing
    */
    private BSTree<Integer> tree;
    
    // returns a tree loaded with the given items
    private static BSTree<Integer> load(Integer... items){
        IntComparator compare  = new IntComparator();
        BSTree<Integer> tree = new BSTree<Integer>(compare);
        for(Integer value: items){
            tree.addLoop(value);
        }
        return tree;
    }
    
    @Test
    public void test_addLoop(){
        // testing empty
        tree=load();
        tree.addLoop( 1 );
        assertEquals( tree.toString(), "[1]" );
        
        //testing single
        tree = load( 5 );
        tree.addLoop( 6 );
        assertEquals( tree.toString(), "[5 6]");
        
        tree = load(10);
        tree.addLoop(10);
        assertEquals( tree.toString(), "[10]");
        
        //testing multi
        tree = load(1,2,3,4,5,6);
        tree.addLoop(7);
        assertEquals( tree.toString(), "[1 2 3 4 5 6 7]");
        
        tree = load(10,2,5,11,25,3,4,50);
        tree.addLoop(20);
        assertEquals( tree.toString(), "[10 2 11 5 25 3 20 50 4]");
        
        tree = load(10,2,5,11,25,3,4,50);
        tree.addLoop(50);
        assertEquals( tree.toString(), "[10 2 11 5 25 3 50 4]");
    }

    @Test
    public void test_maxValueLoop(){
        // testing empty
        tree = load();
        assertThrowsExactly( NoSuchElementException.class, () -> tree.maxValueLoop());
        assertEquals( tree.toString(), "[]");
        
        //testing single
        tree = load(5);
        assertTrue( tree.maxValueLoop().equals(5));
        assertEquals( tree.toString(), "[5]" );
        
        //testing multi
        tree = load(1,2,3,4,5,6);
        assertTrue( tree.maxValueLoop().equals(6));
        assertEquals( tree.toString(), "[1 2 3 4 5 6]" );
        
        tree = load(10,2,5,6,99,1,55,20,31,15,1000);
        assertFalse( tree.maxValueLoop().equals(99));
        assertEquals( tree.toString(), "[10 2 99 1 5 55 1000 6 20 15 31]");
        
        tree = load(100,1,20,40,40,5,29);
        assertTrue( tree.maxValueLoop().equals(100));
        assertEquals( tree.toString(), "[100 1 20 5 40 29]");
    }
    
    @Test
    public void test_isEmpty(){
        //testing empty
        tree = load();
        assertTrue( tree.isEmpty());
        assertEquals( tree.toString(), "[]");
        
        //testing single 
        tree = load(10);
        assertFalse( tree.isEmpty());
        assertEquals( tree.toString(), "[10]");
        
        //testing multi
        tree = load(1,2,3,4,5,6);
        assertFalse( tree.isEmpty());
        assertEquals( tree.toString(), "[1 2 3 4 5 6]" );
    }
    
    @Test
    public void test_containsLoop(){
        //testing empty
        tree = load();
        assertFalse( tree.containsLoop(5));
        assertEquals( tree.toString(), "[]");
        
        //testing single
        tree = load(5);
        assertTrue( tree.containsLoop(5));
        assertEquals( tree.toString(), "[5]");
        
        //testing multi
        tree = load( 1,2,5,7,19);
        assertTrue( tree.containsLoop(7));
        assertEquals( tree.toString(), "[1 2 5 7 19]");
        
        tree = load(10,2,5,6,99,1,55,20,31,15,1000);
        assertTrue( tree.containsLoop(20));
        assertEquals( tree.toString(), "[10 2 99 1 5 55 1000 6 20 15 31]");
        
        tree = load(100,1,20,40,40,5,29);
        assertFalse( tree.containsLoop(1000));
        assertEquals( tree.toString(), "[100 1 20 5 40 29]");
    }
    
    @Test
    public void test_add(){
        //testing empty
        tree = load();
        tree.add(4);
        assertEquals( tree.toString(), "[4]" );
        
        //testing single
        tree = load( 5 );
        tree.add( 6 );
        assertEquals( tree.toString(), "[5 6]");
        
        tree = load(10);
        tree.add(10);
        assertEquals( tree.toString(), "[10]");
        
        //testing multi
        tree = load(1,2,3,4,5,6);
        tree.add(7);
        assertEquals( tree.toString(), "[1 2 3 4 5 6 7]");
        
        tree = load(10,2,5,11,25,3,4,50);
        tree.add(20);
        assertEquals( tree.toString(), "[10 2 11 5 25 3 20 50 4]");
        
        tree = load(10,2,5,11,25,3,4,50);
        tree.add(50);
        assertEquals( tree.toString(), "[10 2 11 5 25 3 50 4]");
        
        tree = load(10,2,5,6,99,1,55,31,15,1000);
        tree.add(20);
        assertEquals( tree.toString(), "[10 2 99 1 5 55 1000 6 31 15 20]");
    }
    
    @Test
    public void test_maxValue(){
        //testing empty
        tree = load();
        assertThrowsExactly( NoSuchElementException.class, () -> tree.maxValue());
        assertEquals( tree.toString(), "[]");
        
        //testing single
        tree = load(5);
        assertTrue( tree.maxValue().equals(5));
        assertEquals( tree.toString(), "[5]");
        
        //testing multi
        tree = load(1,2,3,4,5,9,6);
        assertFalse( tree.maxValue().equals(10));
        assertEquals( tree.toString(), "[1 2 3 4 5 9 6]");
        
        tree = load(1,2,9,4,5,10,3);
        assertTrue( tree.maxValue().equals(10));
        assertEquals( tree.toString(), "[1 2 9 4 10 3 5]");
    }
    
    @Test
    public void test_contains(){
        //testing empty
        tree = load();
        assertFalse( tree.contains(5));
        assertEquals( tree.toString(), "[]");
        
        //testing single
        tree = load(10);
        assertTrue( tree.contains(10));
        assertEquals( tree.toString(), "[10]");
        
        //testing multi
        tree = load(1,2,4,7,3,10);
        assertTrue( tree.contains(3));
        assertEquals( tree.toString(), "[1 2 4 3 7 10]");
        
        tree = load(10,2,5,6,99,1,55,20,31,15,1000);
        assertTrue( tree.contains(20));
        assertEquals( tree.toString(), "[10 2 99 1 5 55 1000 6 20 15 31]");
        
        tree = load(100,1,20,40,40,5,29);
        assertFalse( tree.contains(1000));
        assertEquals( tree.toString(), "[100 1 20 5 40 29]");
    }
    
    @Test
    public void test_remove(){
        //testing empty
        tree = load();
        assertFalse( tree.remove(5));
        assertEquals( tree.toString(), "[]");
        
        //testing single
        tree = load(8);
        assertTrue( tree.remove(8));
        assertEquals( tree.toString(), "[]" );
                     
        //testing multiple
        tree = load(1,2,4,5,7,8);
        assertFalse( tree.remove(11));
        assertTrue( tree.remove(5));
        assertEquals( tree.toString(), "[1 2 4 7 8]");
        
        tree = load(100,1,20,40,40,5,29);
        assertTrue(tree.remove(100));
        assertEquals(tree.toString(), "[1 20 5 40 29]");
        
        tree = load(10,2,5,6,99,1,55,20,31,15,1000);
        assertTrue( tree.remove(5));
        assertEquals( tree.toString(), "[10 2 99 1 6 55 1000 20 15 31]");
        
        tree = load(10,2,5,1,16,15,21,30,20);
        assertTrue( tree.remove(21));
        assertEquals( tree.toString(), "[10 2 16 1 5 15 20 30]");
        
        tree = load(10,2,5,1,16,15,21,30,20);
        assertTrue( tree.remove(16));
        assertEquals( tree.toString(), "[10 2 15 1 5 21 20 30]");
        
        tree = load(10,2,5,6,99,1,55,20,31,15,1000);///
        assertTrue( tree.remove(10));
        assertEquals( tree.toString(), "[6 2 99 1 5 55 1000 20 15 31]");
        
        tree = load(10,20,30,50,100,1000);
        assertTrue( tree.remove(10));
        assertEquals( tree.toString(), "[20 30 50 100 1000]");
        
        tree = load(10,2,5,6,99,1,55,20,31,15,1000);
        assertTrue( tree.remove(55));
        assertEquals( tree.toString(), "[10 2 99 1 5 20 1000 6 15 31]");
        
        tree = load(10,2,5,6,50,1,55,20,31,15,45);
        assertTrue( tree.remove(55));
        assertEquals( tree.toString(), "[10 2 50 1 5 20 6 15 31 45]");
        
        tree = load(5,4,10,6,20,11);
        assertTrue( tree.remove(20));
        assertEquals( tree.toString(), "[5 4 10 6 11]");
        
        tree = load(5,4,10,6,20,11,15);
        assertTrue( tree.remove(11));
        assertEquals( tree.toString(), "[5 4 10 6 20 15]");
    }
}