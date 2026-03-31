/**
 * Implementation of Entry for the Linear-HashMap
 * 
 * @author Raiyat Haque
*/
public class Entry<K,V>{
    K key; // the user perspective index
    V value; // the data given by the user
    
    /**
     * Creates an Entry with the given key and value
    */
    public Entry(K key, V value){
        this.key = key;
        this.value = value;
    }
    
    //default constructor for special bucket deleted
    public Entry(){
        this.key = null;
        this.value = null;
    }
    
    /**
     * String representation of the key
    */
    public String toString(){
        return this.key + ":" + this.value;
    }
}
