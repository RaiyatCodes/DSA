/**
Analyzing the performance of Merge Sort, Quick Sort & Heap Sort for array input sizes ranging from 100,000-1 million
*/
public class SortAlgos{
    public static void main (String[] args){
        compareSortAlgos(100000, 1000000, 100000, "sorted");
        compareSortAlgos(100000, 1000000, 100000, "reverse");
        compareSortAlgos(100000, 1000000, 100000, "random");
    }
    
    public static void compareSortAlgos(int initSize, int finalSize, int sizeStep, String order){
        System.out.println("\nOrder: " + order);
        System.out.println("Size\tQSms\tHSms\tMSms");
        
        for(int i = initSize; i <= finalSize; i += sizeStep){
            Double[] test_array = SortUtils.generateArray(i, order);
            Double[] copy1 = new Double[i];
            Double[] copy2 = new Double[i];
            Double[] copy3 = new Double[i];
            int index = 0;
            for(Double data: test_array){
                copy1[index] = test_array[index];
                copy2[index] = test_array[index];
                copy3[index] = test_array[index];
                index++;
            }
            
            //QuickSort
            long start = System.currentTimeMillis();
            QuickSort.sort(copy1);
            long end = System.currentTimeMillis();
            long qsTime = end-start;
            
            //MergeSort
            start = System.currentTimeMillis();
            copy2 = MergeSort.sort(copy2);
            end = System.currentTimeMillis();
            long msTime = end-start;
            
            //HeapSort
            start = System.currentTimeMillis();
            HeapSort.sort(copy3);
            end = System.currentTimeMillis();
            long hsTime = end - start;
            
            System.out.println(i + "\t" + qsTime + "\t" + hsTime + "\t" + msTime);
        }
    }
}
