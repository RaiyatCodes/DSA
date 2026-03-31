import java.util.function.Consumer; 

public class StringConsumer<T> implements Consumer<T> {
    private String str; // the data member to collect the visited items
    
    public StringConsumer(){  //start with a blank String
        str = "";  
    }
    
    @Override
    public void accept(T items){
        str += items + " "; // append current item to overall string
    }
    
    public String getValue(){
        return "[" + str.trim() + "]"; // use after the traversal to get the overall string
    }
}
