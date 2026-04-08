/**
 * Unit test cases for the implementation of a B-Tree.
 * 
 * @author Raiyat Haque
*/
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BTreeTest{
    /**
     * the tree to use for testing
    */
    private BTree<Integer> tree;
    
    //returns a tree loaded with the given items
    static BTree<Integer> load(int order, Integer... items){
        IntComparator compare = new IntComparator();
        BTree<Integer> tree = new BTree<Integer>(order,compare);
        for(Integer value: items){
            tree.add(value);
        }
        return tree;
    }
    
    /**
     * Test the add method with tree of order 2
    */
    @Test
    public void test_add_2(){
        //testing empty of order 2
        tree = load( 2 );
        tree.add(5);
        assertEquals( tree.toString(), "[[5]]" );
        assertEquals( tree.toStringSorted(), "[5]");
        
        //tesing single tree of order 2
        tree = load(2, 1);
        tree.add(2);
        assertFalse(tree.contains(4));
        assertEquals( tree.toString(), "[[1 2]]");
        assertEquals( tree.toStringSorted(), "[1 2]");
        
        tree = load(2, 1,2,3,4,5,6,7,8,9);
        tree.add(10);
        assertEquals( tree.toString(), "[[4] [2] [6 8] [1] [3] [5] [7] [9 10]]");
        assertEquals( tree.toStringSorted(), "[1 2 3 4 5 6 7 8 9 10]");
    }
    
    /**
     * Test the add method with tree of order 4
    */
    @Test
    public void test_add_4(){
        tree = load(4, 10,20,5,8,12,18,23,36);
        tree.add(40);
        assertTrue(tree.contains(5));
        assertEquals( tree.toString(), "[[10 20] [5 8] [12 18] [23 36 40]]");
        assertEquals( tree.toStringSorted(), "[5 8 10 12 18 20 23 36 40]");
        
        tree = load(4, 50,30,70,10,40,60,80,20,35,45,65);
        tree.add(75);
        assertFalse(tree.contains(5));
        assertEquals( tree.toString(), "[[40 60] [10 20 30 35] [45 50] [65 70 75 80]]");
        assertEquals( tree.toStringSorted(), "[10 20 30 35 40 45 50 60 65 70 75 80]");
    }
    
    //testing with order 3
    @Test
    public void test_add_3(){
        tree = load(3, 1,2,3,4,5,6,7,8);
        tree.add(9);
        assertTrue(tree.contains(6));
        assertEquals(tree.toString(), "[[3 6] [1 2] [4 5] [7 8 9]]");
        assertEquals(tree.toStringSorted(), "[1 2 3 4 5 6 7 8 9]");
        
        tree = load(3, 8,3,10,1,6,14,4,7);
        tree.add(13);
        assertFalse(tree.contains(0));
        assertEquals(tree.toString(), "[[4 8] [1 3] [6 7] [10 13 14]]");
        assertEquals(tree.toStringSorted(), "[1 3 4 6 7 8 10 13 14]");
    }
    
    //testing with order 5
    @Test
    public void test_add_5(){
        tree = load(5, 15,5,1,20,25,30,35,40,45,50,55,60);
        tree.add(65);
        assertFalse(tree.contains(-1));
        assertEquals(tree.toString(), "[[20 40] [1 5 15] [25 30 35] [45 50 55 60 65]]");
        assertEquals(tree.toStringSorted(), "[1 5 15 20 25 30 35 40 45 50 55 60 65]");
    }
}