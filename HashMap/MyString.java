/**
 * Implementation of a String wrapper to be used for testing 
 * the HashTable. Changes the default hash code implementation
*/
public class MyString{
    /**
     * the string representative
     */
    private String str;
    
    public MyString(String s){
        str = s;
    }
    
    @Override
    public int hashCode(){
        char firstChar = str.charAt(0);
        if(Character.isDigit(firstChar)){
            int value = Character.getNumericValue(firstChar);
            return 10*value + (str.length() - 1);
        }
        else{
            return str.length();
        }
    }
    
    @Override
    public String toString(){
        return str;
    }
    
    @Override
    public boolean equals(Object other){
        if(other == this){
            return true;
        }
        else if(!(other instanceof MyString)){
            return false;
        }
        else{
            MyString otherStr = (MyString) other;
            return this.str.equals(otherStr.str);
        }
    }
}