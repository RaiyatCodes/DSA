/**
 * JUnit Test Cases for the Chained-HashMap
 * 
 * @author Raiyat Haque
*/
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CHashMapTest{
    //shortcut for making MyString Objects
    private static MyString ms(String str){
        return new MyString(str);
    }
    
    CHashMap<MyString, Integer> map = new CHashMap<MyString, Integer>(3,0.5);
    
    @Test
    public void test(){
        assertEquals( map.toString(), "0:[ [] [] [] ]");
        
        assertTrue( map.get(ms("Lu")) == null);
        assertEquals( map.toString(), "0:[ [] [] [] ]");
        
        assertFalse(map.containsValue(100));
        assertEquals( map.toString(), "0:[ [] [] [] ]");
        
        assertTrue( map.remove( ms("Lia")) == null );
        assertEquals( map.toString(), "0:[ [] [] [] ]");
        
        assertTrue( map.put( ms("Meg"), 31) == 31);
        assertEquals( map.toString(), "1:[ [Meg:31] [] [] ]");
        
        assertTrue( map.get(ms("Meg")) == 31);
        assertEquals( map.toString(), "1:[ [Meg:31] [] [] ]");
        
        assertTrue( map.put( ms("Ci"), 3) == 3);
        assertEquals( map.toString(), "2:[ [Meg:31] [] [Ci:3] ]");
        
        assertTrue( map.get(ms("Ci")) == 3);
        assertEquals( map.toString(), "2:[ [Meg:31] [] [Ci:3] ]");
        
        assertTrue( map.put( ms("Lu"), 4) == 4);
        assertEquals( map.toString(), "3:[ [Meg:31] [] [Lu:4 Ci:3] ]");
        
        assertTrue( map.get(ms("Lu")) == 4);
        assertEquals( map.toString(), "3:[ [Meg:31] [] [Lu:4 Ci:3] ]");
        
        assertTrue( map.put( ms("H"), 5) == 5);
        assertEquals( map.toString(), "4:[ [Meg:31] [H:5] [Lu:4 Ci:3] ]");
        
        assertTrue( map.get(ms("H")) == 5);
        assertEquals( map.toString(), "4:[ [Meg:31] [H:5] [Lu:4 Ci:3] ]");
        
        assertTrue( map.put( ms("G"), 10) == 10);
        assertEquals( map.toString(), "5:[ [Meg:31] [G:10 H:5] [Lu:4 Ci:3] ]");
        
        assertTrue( map.get(ms("G")) == 10);
        assertEquals( map.toString(), "5:[ [Meg:31] [G:10 H:5] [Lu:4 Ci:3] ]");
        
        assertTrue( map.containsValue(3));
        assertEquals( map.toString(), "5:[ [Meg:31] [G:10 H:5] [Lu:4 Ci:3] ]");
        
        assertTrue( map.remove( ms("Meg")) == 31 );
        assertEquals( map.toString(), "4:[ [] [G:10 H:5] [Lu:4 Ci:3] ]");
        
        assertTrue( map.remove( ms("G")) == 10 );
        assertEquals( map.toString(), "3:[ [] [H:5] [Lu:4 Ci:3] ]");
        
        assertTrue( map.remove( ms("Ci")) == 3 );
        assertEquals( map.toString(), "2:[ [] [H:5] [Lu:4] ]");
    }
}