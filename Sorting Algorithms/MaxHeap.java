/**
 * Implementation of the Max Heap
 * 
 * @author Raiyat Haque
*/
public class MaxHeap<E>{
    E[] items; //the primitive array of items managed by this heap
    int size; //The size of the heap
    
    /**
     * Creates a max heap from the given items. The size is initially the same as the number of items in the array.
     * @param theItems    The list of items given by user
    */
    public MaxHeap(E[] theItems){
        int i = 0;
        items = SortUtils.arrayAs(theItems, theItems.length);
        size = items.length;
        for(E data: theItems){
            items[i] = data;
            i++;
        }
        makeHeap();
    }
    
    /**
     * Pushes the item at the given index down the items array (down the heap) until it ends up in a place where it has no max-heap
     * property violations with its children.
     * @param j   The index of the item that needs to be pushed
    */
    private void pushDown(int j){
        if(isLeaf(j)){
            return;
        }
        E parent = items[j];
        E leftChild = items[left(j)];
        if( right(j) >= size){ //has no right child
            if(SortUtils.compare(parent, leftChild) < 0){
                SortUtils.swap(items, left(j), j);
                pushDown(left(j));
            }
            return;
        }
        E rightChild = items[right(j)]; //there is a right child
        if(SortUtils.compare(leftChild,rightChild) > 0){
            if(SortUtils.compare(parent,leftChild) < 0){
                SortUtils.swap(items,left(j), j);
                pushDown(left(j));
            }
        }
        else{
            if(SortUtils.compare(parent,rightChild) < 0){
                SortUtils.swap(items, right(j), j);
                pushDown(right(j));
            }
        }
    }
    
    /**
     * Rearranges the data member items so that it represents a max heap.
    */
    private void makeHeap(){
        for(int i = items.length -1 ; i >= 0; i--){
            pushDown(i);
        }
    }
    
    /**
     * Returns (and removes) the max value in the heap
    */
    public E pop(){
        E max = items[0];
        items[0] = items[size - 1];
        size--;
        pushDown(0);
        return max;
    }
    
    /**
     * Determines if the item at the given index is a leaf
     * @param i   the index of the item that needs to be checked
    */
    private boolean isLeaf(int i){
        return (left(i) >= size);
    }
    
    /**
     * Returns a string representation of this heap(up to its size).
    */
    public String toString(){
        return SortUtils.toString(items, size);
    }
    
    /**
     * Returns the child index
     * @param i    The specfied index
    */
    private int left(int i){
        return (2*i) +  1;
    }
    
    /**
     * Returms the child index
     * @param i   The specified index
    */
    private int right(int i){
        return (2*i) + 2;
    }
}