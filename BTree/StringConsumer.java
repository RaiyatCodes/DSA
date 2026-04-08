import java.util.function.Consumer;

public class StringConsumer<T> implements Consumer<T>{
    private String str;
    
    public StringConsumer(){
        str = "";
    }
    
    @Override
    public void accept(T item){
        str += item + " ";
    }
    
    public String getValue(){
        return "[" + str.trim() + "]";
    }
}
