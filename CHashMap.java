/**
 * Implementation of Chained-HashMap
 * 
 * @author Raiyat Haque
*/
import java.util.LinkedList;
import java.util.Iterator;

public class CHashMap<K,V>{
    private int size; //data member to keep track of the number of entries in the list
    private LinkedList<Entry<K,V>>[] array; //The storage for this data structure
    private double loadFactor; //load factor of the list
    private int capacity; // the size of the list
    
    /**
     * Creates a map for the given capacity and load factor
     * 
     * @param initialCapacity   the size of the list set by the user
     * @param loadFactor     the threshold for resizing
    */
    public CHashMap(int initialCapacity, double loadFactor){
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        array = (LinkedList<Entry<K,V>>[]) new LinkedList[capacity];
        for(int i = 0; i < capacity; i++){
            array[i] = new LinkedList<Entry<K,V>>();
        }
    }
    
    /**
     * Puts the given value under the given key and returns the old value associated with this key
     * 
     * @param key    the user perspective index
     * @param value  the data that needs to be stored 
     * 
     * @return Null
    */
    public V put(K key, V value){
        Entry<K,V> entry = new Entry(key, value);
        boolean deletedSignal = false;
        Entry<K,V> firstDeletedEntry = null;
        int hashIndex = key.hashCode() % capacity;
        int initialHashIndex = hashIndex;
        LinkedList<Entry<K,V>> list = array[hashIndex];
        if(list.isEmpty()){
            list.addFirst(entry);
            size++;
        }
        for(Entry<K,V> data : list){
            if(entry.key.equals(data.key)){
                V oldValue = data.value;
                data.value = entry.value;
                return oldValue;
            }
        }
        list.addFirst(entry);
        size++;
        return entry.value;
    }
    
    /**
     * Returns the value associated with the given key
     * 
     * @param key  the user perspective index
     * @return the value the user wants
    */
    public V get(K key){
        int hashIndex = key.hashCode() % capacity;
        LinkedList<Entry<K,V>> list = array[hashIndex];
        V value = null;
        if(list.isEmpty()){
            return null;
        }
        for(Entry<K,V> data: list){
            if(data.key.equals(key)){
                value = data.value;
            }
        }
        return value;
    }
    
    /** 
     * Removes/deletes the entry with the given
     * 
     * @param key  the user perspective index
     * @return the value that got removed
    */
    public V remove(K key){
        int hashIndex = key.hashCode() % capacity;
        V oldValue = null;
        LinkedList<Entry<K,V>> list = array[hashIndex];
        if(list.isEmpty()){
            return null;
        }
        Iterator<Entry<K,V>> iter = list.iterator();
        while(iter.hasNext()){
            Entry<K,V> data = iter.next();
            if(data.key.equals(key)){
                oldValue = data.value;
                iter.remove();
                size--;
            }
        }
        return oldValue;
    }
    
    /**
     * Determines if the map contains the given value
     * 
     * @param value  the data that needs to be checked
     * @return True if the list contains the user-specified value
    */
    public boolean containsValue(V value){
        for(LinkedList<Entry<K,V>> list: array){
            if(list.isEmpty()){
                continue;
            }
            for(Entry<K,V> data: list){
                if(data.value.equals(value)){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Returns a String representation of the map
     * @return A String representaion of the list
    */
    public String toString(){
        String str = size +":[ ";
        for (int i = 0; i < capacity; i++){
            str += array[i].toString() + " ";
        }
        str += "]";
        str = str.replaceAll(",","");
        return str;
    }
}
