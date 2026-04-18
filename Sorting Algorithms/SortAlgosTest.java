/**
 * Unit test cases for the implementation of Sorting Algorithms
*/
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SortAlgosTest{
   /**
    * The primitive array to use for testing
   */
    private Integer[] numbers;
    
    //returns a primitive array loaded with the given items
    private static Integer[] load(Integer... items){
        return items;
    }
    
    @Test
    public void test_MergeSort(){
        numbers = load(1,6,8,3,9);
        numbers = MergeSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[1 3 6 8 9]");
        
        numbers = load(2,2,8,3,9,11,20);
        numbers = MergeSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[2 2 3 8 9 11 20]");
        
        numbers = load(2,2,2,2,2);
        numbers = MergeSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[2 2 2 2 2]");
        
        numbers = load(2);
        numbers = MergeSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[2]");
        
        Integer[] array_left = load(2,3);
        Integer[] array_right = load(6,7,8,9);
        Integer[] array_merged = MergeSort.merge(array_left, array_right);
        assertEquals( SortUtils.toString(array_merged), "[2 3 6 7 8 9]");
        
        Integer[] array_left2 = load(2,2,2,2);
        Integer[] array_right2 = load(2,2,2,2);
        Integer[] array_merged2 = MergeSort.merge(array_left2, array_right2);
        assertEquals( SortUtils.toString(array_merged2), "[2 2 2 2 2 2 2 2]");
        
        Integer[] array_left3 = load(1,2,3,4);
        Integer[] array_right3 = load(1,2,3,4);
        Integer[] array_merged3 = MergeSort.merge(array_left3, array_right3);
        assertEquals( SortUtils.toString(array_merged3), "[1 1 2 2 3 3 4 4]");
        
        String[] s1 = {"A", "B", "C"};
        s1 = MergeSort.sort(s1);
        assertEquals(SortUtils.toString(s1), "[A B C]");
        
        String[] s2 = {"D", "B", "F"};
        s2 = MergeSort.sort(s2);
        assertEquals(SortUtils.toString(s2), "[B D F]");
        
        String[] s3 = {"D", "C", "B"};
        s3 = MergeSort.sort(s3);
        assertEquals(SortUtils.toString(s3), "[B C D]");
    }
    
    @Test
    public void test_QuickSort(){
        numbers = load(1,6,8,3,9);
        QuickSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[1 3 6 8 9]");
        
        numbers = load(2,2,8,3,9,11,20);
        QuickSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[2 2 3 8 9 11 20]");
        
        numbers = load(2,2,2,2,2);
        QuickSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[2 2 2 2 2]");
        
        numbers = load(9,8,7,6,5);
        QuickSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[5 6 7 8 9]");
        
        numbers = load(1,2,3,6,7);
        QuickSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[1 2 3 6 7]");
        
        numbers = load(2);
        QuickSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[2]");
        
        numbers = load();
        QuickSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[]");
        
        numbers = load(6,4,8,2,1,9);
        assertTrue(QuickSort.partition(numbers, 1,4) == 3);
        numbers = load(6,4,8,2,1,9);
        assertTrue(QuickSort.partition(numbers, 0,3) == 1);
        numbers = load(6,4,8,2,1,9);
        assertTrue(QuickSort.partition(numbers, 0,0) == 0);
        
        String[] s1 = {"A", "B", "C"};
        QuickSort.sort(s1);
        assertEquals(SortUtils.toString(s1), "[A B C]");
        
        String[] s2 = {"D", "B", "F"};
        QuickSort.sort(s2);
        assertEquals(SortUtils.toString(s2), "[B D F]");
        
        String[] s3 = {"D", "C", "B"};
        QuickSort.sort(s3);
        assertEquals(SortUtils.toString(s3), "[B C D]");
    }
    
    @Test
    public void test_Heap(){
        numbers = load(7,8,3,6,2,9,1);
        MaxHeap<Integer> heap = new MaxHeap<Integer>(numbers);
        assertEquals(heap.toString(), "[9 8 7 6 2 3 1]");
        assertTrue( heap.pop().equals(9));
        assertEquals(heap.toString(), "[8 6 7 1 2 3]");
        
        numbers = load(5);
        MaxHeap<Integer> heap2 = new MaxHeap<Integer>(numbers);
        assertEquals(heap2.toString(), "[5]");
        assertTrue( heap2.pop().equals(5));
        assertEquals(heap2.toString(), "[]");
        
        numbers = load();
        MaxHeap<Integer> heap6 = new MaxHeap<Integer>(numbers);
        assertEquals(heap6.toString(), "[]");
        
        numbers = load(9,7,8,3,2,5,6);
        MaxHeap<Integer> heap3 = new MaxHeap<Integer>(numbers);
        assertEquals(heap3.toString(), "[9 7 8 3 2 5 6]");
        assertTrue( heap3.pop().equals(9));
        assertEquals(heap3.toString(), "[8 7 6 3 2 5]");
        
        numbers = load(1,2,3,4,5,6,7);
        MaxHeap<Integer> heap4 = new MaxHeap<Integer>(numbers);
        assertEquals(heap4.toString(), "[7 5 6 4 2 1 3]");
        assertTrue( heap4.pop().equals(7));
        assertEquals(heap4.toString(), "[6 5 3 4 2 1]");
        
        numbers = load(10,1,8,5);
        MaxHeap<Integer> heap5 = new MaxHeap<Integer>(numbers);
        assertEquals(heap5.toString(), "[10 5 8 1]");
        assertTrue(heap5.pop().equals(10));
        assertEquals(heap5.toString(), "[8 5 1]");
    }
    
    @Test
    public void test_HeapSort(){
        numbers = load(4,6,7,2,1);
        HeapSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[1 2 4 6 7]");
        
        numbers = load(0,1,2,3,4,5);
        HeapSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[0 1 2 3 4 5]");
        
        numbers = load(4);
        HeapSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[4]");
        
        numbers = load(9,8,7,6,5,4,3);
        HeapSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[3 4 5 6 7 8 9]");
        
        numbers = load();
        HeapSort.sort(numbers);
        assertEquals( SortUtils.toString(numbers), "[]");
        
        String[] s1 = {"A", "B", "C"};
        HeapSort.sort(s1);
        assertEquals(SortUtils.toString(s1), "[A B C]");
        
        String[] s2 = {"D", "B", "F"};
        HeapSort.sort(s2);
        assertEquals(SortUtils.toString(s2), "[B D F]");
        
        String[] s3 = {"D", "C", "B"};
        HeapSort.sort(s3);
        assertEquals(SortUtils.toString(s3), "[B C D]");
    }
}
