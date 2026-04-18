/**
 * Implementation of Merge Sort Algorithm
 * 
 * @author Raiyat Haque
*/
public class MergeSort{
    /**
     * Merges the two primitive arrays into a final sorted primitive array, assuming the sides are already sorted
     * @param leftSide,rightSide     The two primitive arrays that need to be merged
    */
    public static <E> E[] merge(E[] leftSide, E[] rightSide){
        E[] result = SortUtils.arrayAs(leftSide, leftSide.length + rightSide.length);
        int index = 0;
        int leftPointer = 0;
        int rightPointer = 0;
        while(leftPointer < leftSide.length && rightPointer < rightSide.length){
            if(SortUtils.compare(leftSide[leftPointer], rightSide[rightPointer]) > 0){
                result[index] = rightSide[rightPointer];
                index++;
                rightPointer++;
            }
            else if(SortUtils.compare(leftSide[leftPointer], rightSide[rightPointer]) < 0){
                result[index] = leftSide[leftPointer];
                index++;
                leftPointer++;
            }
            else if(SortUtils.compare(leftSide[leftPointer], rightSide[rightPointer]) == 0){
                result[index] = leftSide[leftPointer];
                index++;
                leftPointer++;
            }
        }
        if(rightPointer == rightSide.length){
            for(int i = leftPointer; i < leftSide.length; i++){
                result[index] = leftSide[i];
                index++;
            }
        }
        if(leftPointer == leftSide.length){
            for(int i = rightPointer; i < rightSide.length; i++){
                result[index] = rightSide[i];
                index++;
            }
        }
        return result;
    }
    
    /**
     * Returns a sorted array of the given range [i,j] using the Merge Sort algorithm.
     * @param items    The list of items that needs to be sorted
     * @param i,j      The values of the range
    */
    private static <E> E[] sort(E[] items, int i, int j){
        if(i == j){
            E[] array = SortUtils.arrayAs(items,1);
            array[0] = items[i];
            return array;
        }
        int mid = ( i + j)/2;
        E[] left_array = MergeSort.sort(items, i, mid);
        E[] right_array = MergeSort.sort(items, mid+1, j);
        E[] result = MergeSort.merge(left_array,right_array);
        return result;
    }
    
    /**
     * Returns a sorted array of the given items using the Merge Sort algorithm.
     * @param items     The list of items that needs to be sorted
    */
    public static <E> E[] sort(E[] items){
        E[] sortedArray = MergeSort.sort(items, 0, items.length-1);
        return sortedArray;
    }
}
