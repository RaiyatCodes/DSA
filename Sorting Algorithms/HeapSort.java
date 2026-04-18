/**
 * Implementation of a class that implements the Heap Sort Algorithm
*/
public class HeapSort{
    /**
     * Sorts the given items using the HeapSort algorithm
     * @param items    The list of items that needs to be sorted
    */
    public static <E> void sort(E[] items){
        MaxHeap<E> heap = new MaxHeap<E>(items);
        for(int i = items.length -1; i >= 0; i--){
            E data = heap.pop();
            items[i] = data;
        }
    }
}
