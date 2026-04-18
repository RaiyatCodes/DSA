/**
 * Implementation of the Quick Sort algorithm
 * 
 * @author Raiyat Haque
*/
public class QuickSort{
    /**
     * Partitions the items in the given range [i,j] (inclusive) around a pivot. The method returns the index where the pivot element 
     * ends up after the partition.
     * @param items   The list of items provided
     * @param i,j     The values of the range
    */
    public static <E> int partition(E[] items, int i, int j){
        int mid = (i+j)/2;
        SortUtils.median3(items, i,j);
        int pivot = mid;
        SortUtils.swap(items, pivot,j);
        int leftPointer = i;
        int rightPointer = j - 1;
        while(leftPointer <= rightPointer){
            while(leftPointer <= rightPointer && SortUtils.compare(items[leftPointer], items[j]) < 0){
                leftPointer++;
            }
            while(leftPointer <= rightPointer && SortUtils.compare(items[rightPointer], items[j]) > 0){
                rightPointer--;
            }
            if( leftPointer <= rightPointer){
                SortUtils.swap(items, leftPointer, rightPointer);
                leftPointer++;
                rightPointer--;
            }
        }
        SortUtils.swap(items, leftPointer, j);
        return leftPointer;
    }
    
    /**
     * Sort the items in the given range [i, j] (inclusive) using the QuickSort algorithm.
     * @param items     The list of items that needs to be sorted
     * @param i,j       The values of the range
    */
    private static <E> void sort(E[] items, int i, int j){
        if( i >= j){
            return;
        }
        int pivot = QuickSort.partition(items, i, j);
        QuickSort.sort(items, i, pivot-1);
        QuickSort.sort(items, pivot+1, j);
    }
    
    /**
     * Sorts the given items using the QuickSort algorithm
     * @param items    The list of items that needs to be sorted
    */
    public static <E> void sort(E[] items){
        QuickSort.sort(items, 0, items.length-1);
    }
}
