/**
 * Consumer for counting items in a given range
*/
import java.util.Comparator;
import java.util.function.Consumer;

public class CountRangeConsumer<T> implements Consumer<T>{
    // any data members that allow the consumer to remember
    // and keep track of the relevant information to do its work
    private T lowerBound;
    private T upperBound;
    private Comparator cmp;
    private String str;
    public CountRangeConsumer(T a, T b, Comparator comp){
        // constructs a consumer with the given range values [a,b]
        // and the constructor that was used to build the tree
        this.lowerBound = a;
        this.upperBound = b;
        this.cmp = comp;
        str = "";
    }
    
    @Override
    public void accept(T item){
        if(cmp.compare(item,lowerBound) == 0){
            str += item + " ";
        }
        else if(cmp.compare(item,upperBound) == 0){
            str += item + " ";
        }
        else if(cmp.compare(item,lowerBound) == 1 && cmp.compare(item,upperBound) == -1){
            str += item + " ";
        }
    }
    
    public String getValue(){
        return "[" + str.trim() + "]";
    }
}
