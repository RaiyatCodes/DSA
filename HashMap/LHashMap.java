/**
 * Implementation of Linear-HashMap
 * 
 * @author Raiyat Haque
*/

import java.util.Iterator;

public class LHashMap<K,V> implements Iterable<V>{
    private int size; // the number of entries in the  list;
    private double loadFactor; //load factor of the list
    private Entry<K,V>[] array; // the storage for the data structure
    private int capacity;
    private final Entry<K,V> deleted; // entry that represents deleted entries
    private final Entry<K,V> empty; // entry that represents empty cells
    /**
     * Implementation of the Iterator
    */
    private class LHashMapIterator implements Iterator<V>{
        int currentIndex;
        public LHashMapIterator(){
            currentIndex = 0;
        }
        public boolean hasNext(){
            while(currentIndex < capacity){
                if(array[currentIndex].equals(empty) || array[currentIndex].equals(deleted)){
                    currentIndex++;
                    continue;
                }
                else{
                    return true;
                }
            }
            return false;
        }
        public V next(){
            V value = null;
            if(hasNext()){
                value = array[currentIndex].value;
                currentIndex++;
            }
            return value;
        }
    }
    
    /**
     * Creates a map with the given capacity and load factor
     * 
     * @param initialCapacity  the size of the list
     * @param loadFactor  the load factor of the list
    */
    public LHashMap(int initialCapacity, double loadFactor){
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        deleted = new Entry<K,V>();
        empty = new Entry<K,V>();
        array = (Entry<K,V>[]) new Entry[capacity];
        for(int i = 0; i < capacity; i++){
            array[i] = empty;
        }
    }
    
    /**
     * Puts the given value under the given key and returns the old value associated with this key
     * 
     * @param key  the user perspective index
     * @param value  the data that needs to be stored
    */
    
    //a collision occurs when two different keys are in the same bucket
    public V put(K key, V value){
        Entry<K,V> entry = new Entry<K,V>(key, value);
        V oldValue = null;
        int hashCode = key.hashCode();
        int hashIndex = hashCode % capacity;
        int initialHashIndex = hashIndex;
        int firstDeletedIndex = -1;
        //if initially landing in D, look for real entry with specified key and update; else put it in D
        if(array[hashIndex].equals(deleted)){
            firstDeletedIndex = hashIndex;
            hashIndex++;
            while(hashIndex < capacity){
                if(array[hashIndex].equals(empty)){
                    array[firstDeletedIndex] = entry;
                    size++;
                    if(needToRehash()){
                        rehash();
                    }
                    return entry.value;
                }
                else if(array[hashIndex].equals(deleted)){
                    hashIndex++;
                    continue;
                }
                else if(array[hashIndex].key.equals(key)){
                    oldValue = array[hashIndex].value;
                    array[hashIndex].value = value;
                    return oldValue;
                }
                hashIndex++;
            }
            //wrap around
            hashIndex = hashIndex % capacity;
            while(hashIndex < initialHashIndex){
                if(array[hashIndex].equals(empty)){
                    array[firstDeletedIndex] = entry;
                    size++;
                    if(needToRehash()){
                        rehash();
                    }
                    return entry.value;
                }
                else if(array[hashIndex].equals(deleted)){
                    hashIndex++;
                    continue;
                }
                else if(array[hashIndex].key.equals(key)){
                    oldValue = array[hashIndex].value;
                    array[hashIndex].value = value;
                    return oldValue;
                }
                hashIndex++;
            }
            //no empty bucket or existing key in the list, so back to initial hashIndex
            array[firstDeletedIndex] = entry;
            size++;
            if(needToRehash()){
                rehash();
            }
            return entry.value;
        }
        
        //If initially landing in E, insert here
        else if(array[hashIndex].equals(empty)){
            array[hashIndex] = entry;
            size++;
            if(needToRehash()){
                rehash();
            }
            return entry.value;
        }

        //real-entry case: collision
        else if(!(array[hashIndex].key.equals(key))){
            hashIndex++;
            while(hashIndex < capacity){
                if(array[hashIndex].equals(deleted)){
                    if(firstDeletedIndex == -1){
                        firstDeletedIndex = hashIndex;
                    }
                    hashIndex++;
                    continue;
                }
                else if(array[hashIndex].equals(empty)){
                    if(firstDeletedIndex == -1){
                        array[hashIndex] = entry;
                        size++;
                        if(needToRehash()){
                            rehash();
                        }
                        return entry.value;
                    }
                    else{
                        array[firstDeletedIndex] = entry;
                        size++;
                        if(needToRehash()){
                            rehash();
                        }
                        return entry.value;
                    }
                }
                else if(array[hashIndex].key.equals(key)){
                    oldValue = array[hashIndex].value;
                    array[hashIndex].value = value;
                    return oldValue;
                }
                
                hashIndex++;
            }
            //for wrap around
            hashIndex = hashIndex % capacity;
            while(hashIndex <= initialHashIndex){
                if(hashIndex == initialHashIndex){
                    array[firstDeletedIndex] = entry;
                    size++;
                    if(needToRehash()){
                        rehash();
                    }
                    return entry.value;
                }
                else if(array[hashIndex].equals(deleted)){
                    if(firstDeletedIndex == -1){
                        firstDeletedIndex = hashIndex;
                    }
                    hashIndex++;
                    continue;
                }
                else if(array[hashIndex].equals(empty)){
                    if(firstDeletedIndex == -1){
                        array[hashIndex] = entry;
                        size++;
                        if(needToRehash()){
                            rehash();
                        }
                        return entry.value;
                    }
                    else{
                        array[firstDeletedIndex] = entry;
                        size++;
                        if(needToRehash()){ 
                            rehash();
                        }
                        return entry.value;
                    }
                }
                else if(array[hashIndex].key.equals(key)){
                    oldValue = array[hashIndex].value;
                    array[hashIndex].value = value;
                    return oldValue;
                }
                hashIndex++;
            }
        }
        //we know this is a case of replacing
        else{
            oldValue = array[hashIndex].value;
            array[hashIndex].value = value;
        }
        return oldValue ;
    }
    
    /**
     * Returns the value associated with the given key 
     * 
     * @param key  the user perspective index
    */
    public V get(K key){
        int hashCode = key.hashCode();
        int hashIndex = hashCode % capacity;
        int initialHashIndex = hashIndex;
        V data = null;
        //Initially landed at E
        if(array[hashIndex].equals(empty)){
            return null;
        }
        //Initially landed at D
        else if(array[hashIndex].equals(deleted) || !(key.equals(array[hashIndex].key))){
            hashIndex++;
            while(hashIndex < capacity){
                if(array[hashIndex].equals(empty)){
                    return null;
                }
                else if(array[hashIndex].equals(deleted)){
                    hashIndex++;
                    continue;
                }
                else if(array[hashIndex].key.equals(key)){
                    V val = array[hashIndex].value;
                    return val;
                }
                hashIndex++;
            }
            //for wrap around
            hashIndex = hashIndex % capacity;
            while(hashIndex < initialHashIndex){
                if(array[hashIndex].equals(empty)){
                    return null;
                }
                else if(array[hashIndex].equals(deleted)){
                    hashIndex++;
                    continue;
                }
                else if(array[hashIndex].key.equals(key)){
                    V val = array[hashIndex].value;
                    return val;
                }
                hashIndex++;
            }
        }
        //landing on the desired key initially
        else{
            data = array[hashIndex].value;
        }
        return data;
    }
    
    /**
     * Removes/deletes the entry with the given key from the map and returns the entry's value
    */
    public V remove(K key){
        int hashCode = key.hashCode();
        int hashIndex = hashCode % capacity;
        int initialHashIndex = hashIndex;
        //Initially landed on E
        if(array[hashIndex].equals(empty)){
            return null;
        }
        //Landed on deleted or collision happened
        else if(array[hashIndex].equals(deleted) || !(array[hashIndex].key.equals(key))){
            hashIndex++;
            while(hashIndex < capacity){
                if(array[hashIndex].equals(empty)){
                    return null;
                }
                else if(array[hashIndex].equals(deleted)){
                    hashIndex++;
                    continue;
                }
                else if(array[hashIndex].key.equals(key)){
                    V val = array[hashIndex].value;
                    array[hashIndex] = deleted;
                    size--;
                    return val;
                }
                hashIndex++;
            }
            //for wrap around
            hashIndex = hashIndex % capacity;
            while(hashIndex < initialHashIndex){
                if(array[hashIndex].equals(empty)){
                    return null;
                }
                else if(array[hashIndex].equals(deleted)){
                    hashIndex++;
                    continue;
                }
                else if(array[hashIndex].key.equals(key)){
                    V val = array[hashIndex].value;
                    array[hashIndex] = deleted;
                    size--;
                    return val;
                }
                hashIndex++;
            }
        }
        //landed on desired key initially
        else{
            V val = array[hashIndex].value;
            array[hashIndex] = deleted;
            size--;
            return val;
        }
        return null;
    }
    
    /**
     * Determines if the map contains the given value
    */
    public boolean containsValue(V value){
        Iterator<V> iter = this.iterator();
        while(iter.hasNext()){
            V val = iter.next();
            if(val.equals(value)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns a string representation of the map
    */
    public String toString(){
        String str = size + ":[ ";
        for(int i = 0; i < array.length; i++){
            if(array[i].equals(empty)){
                str += "E ";
            }
            else if(array[i].equals(deleted)){
                str += "D ";
            }
            else{
                str += array[i].toString() + " ";
            }
        }
        str += "]";
        return str;
    }
    
    /**
     * Returns an iterator over the values stored in this map from the first bucket to the last
    */
    public Iterator<V> iterator(){
        return new LHashMapIterator();
    }
    
    //method for rehashing
    private void rehash(){
        Entry<K,V>[] tempArray = array;
        int oldCapacity = capacity;
        int newSize = 0;
        int newCapacity = oldCapacity * 2;
        Entry<K,V>[] arr = (Entry<K,V>[]) new Entry[newCapacity];
        for(int i = 0; i < arr.length; i++){
            arr[i] = empty;
        }
        this.array = arr;
        this.size = newSize;
        this.capacity = newCapacity;
        for(int i = 0; i < tempArray.length; i++){
            if(!(tempArray[i].equals(empty) || tempArray[i].equals(deleted))){
                K key = tempArray[i].key;
                V value = tempArray[i].value;
                V forInserting = put(key,value);
            }
        }
    }
    
    //helper method to check if rehash is needed
    private boolean needToRehash(){
        double threshold = loadFactor * capacity;
        return (size >= threshold);
    }
}
