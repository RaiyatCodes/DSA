/**
 * Unit test cases for the implementation of Linear-HashMap;
 * 
 * @author Raiyat Haque
*/
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LHashMapTest{
    //Short-cut for making MyString Objects
    private static MyString ms(String str){
        return new MyString(str);
    }
    
    LHashMap<MyString, Integer> map = new LHashMap<MyString, Integer>(3, 0.5);
    
    @Test
    public void test(){
        assertEquals(map.toString(), "0:[ E E E ]");
        
        assertTrue( map.get(ms("Lu")) == null);
        assertEquals( map.toString(), "0:[ E E E ]");
        
        assertFalse(map.containsValue(100));
        assertEquals( map.toString(), "0:[ E E E ]");
        
        assertTrue( map.remove( ms("Lia")) == null);
        assertEquals( map.toString(), "0:[ E E E ]");
        
        assertTrue( map.put( ms("Meg"), 31) == 31);
        assertEquals( map.toString(), "1:[ Meg:31 E E ]");
        
        assertTrue( map.get( ms("Meg")) == 31);
        assertEquals( map.toString(), "1:[ Meg:31 E E ]");
        
        assertTrue( map.remove( ms("Meg")) == 31);
        assertEquals( map.toString(), "0:[ D E E ]");
        
        assertTrue( map.put( ms("Ci"), 3) == 3);
        assertEquals( map.toString(), "1:[ D E Ci:3 ]");
        
        assertTrue( map.get( ms("Ci")) == 3);
        assertEquals( map.toString(), "1:[ D E Ci:3 ]");
        
        assertTrue( map.remove( ms("Ci")) == 3);
        assertEquals( map.toString(), "0:[ D E D ]");
        
        assertTrue( map.put( ms("Hi"), 3) == 3);
        assertEquals( map.toString(), "1:[ D E Hi:3 ]");
        
        assertTrue( map.get( ms("Hi")) == 3);
        assertEquals( map.toString(), "1:[ D E Hi:3 ]");
        
        assertTrue( map.remove( ms("Hi")) == 3);
        assertEquals( map.toString(), "0:[ D E D ]");
        
        assertTrue( map.put( ms("H"), 4) == 4);
        assertEquals( map.toString(), "1:[ D H:4 D ]");
        
        assertTrue( map.get( ms("H")) == 4);
        assertEquals( map.toString(), "1:[ D H:4 D ]");
        
        assertTrue( map.remove( ms("H")) == 4);
        assertEquals( map.toString(), "0:[ D D D ]");
        
        assertTrue( map.put( ms("Hol"), 10) == 10);
        assertEquals( map.toString(), "1:[ Hol:10 D D ]");
        
        assertTrue( map.get( ms("Hol")) == 10);
        assertEquals( map.toString(), "1:[ Hol:10 D D ]");
        
        assertTrue( map.put( ms("Jol"), 14) == 14);
        assertEquals( map.toString(), "2:[ E E E Hol:10 Jol:14 E ]");
        
        assertTrue( map.put( ms("Lory"), 15) == 15);
        assertEquals( map.toString(), "3:[ E E E Hol:10 Jol:14 Lory:15 E E E E E E ]");
        
        assertTrue( map.put( ms("Re"), 150) == 150);
        assertEquals( map.toString(), "4:[ E E Re:150 Hol:10 Jol:14 Lory:15 E E E E E E ]");
        
        assertTrue( map.put( ms("X"), 1) == 1);
        assertEquals( map.toString(), "5:[ E X:1 Re:150 Hol:10 Jol:14 Lory:15 E E E E E E ]");
        
        assertTrue( map.put( ms("D"), 90) == 90);
        assertEquals( map.toString(), "6:[ E X:1 Re:150 Hol:10 Jol:14 Lory:15 D:90 E E E E E E E E E E E E E E E E E ]");
        
        assertTrue( map.put( ms("WD"), 100) == 100);
        assertEquals( map.toString(), "7:[ E X:1 Re:150 Hol:10 Jol:14 Lory:15 D:90 WD:100 E E E E E E E E E E E E E E E E ]");
        
        assertTrue( map.remove( ms("D")) == 90);
        assertEquals( map.toString(), "6:[ E X:1 Re:150 Hol:10 Jol:14 Lory:15 D WD:100 E E E E E E E E E E E E E E E E ]");
        
        assertTrue( map.remove( ms("Jol")) == 14);
        assertEquals( map.toString(), "5:[ E X:1 Re:150 Hol:10 D Lory:15 D WD:100 E E E E E E E E E E E E E E E E ]");
        
        assertTrue( map.remove( ms("Hol")) == 10);
        assertEquals( map.toString(), "4:[ E X:1 Re:150 D D Lory:15 D WD:100 E E E E E E E E E E E E E E E E ]");
        
        assertTrue( map.put( ms("M"), 1000) == 1000);
        assertEquals( map.toString(), "5:[ E X:1 Re:150 M:1000 D Lory:15 D WD:100 E E E E E E E E E E E E E E E E ]");
        
        assertTrue( map.put( ms("Bangladesh"), 99) == 99);
        assertEquals( map.toString(), "6:[ E X:1 Re:150 M:1000 D Lory:15 D WD:100 E E Bangladesh:99 E E E E E E E E E E E E E ]");
        
        assertTrue( map.containsValue(99));
        assertEquals( map.toString(), "6:[ E X:1 Re:150 M:1000 D Lory:15 D WD:100 E E Bangladesh:99 E E E E E E E E E E E E E ]");
        
        assertFalse( map.containsValue(2000));
        assertEquals( map.toString(), "6:[ E X:1 Re:150 M:1000 D Lory:15 D WD:100 E E Bangladesh:99 E E E E E E E E E E E E E ]");
        
        assertTrue( map.get( ms("Bangladesh")) == 99);
        assertEquals( map.toString(), "6:[ E X:1 Re:150 M:1000 D Lory:15 D WD:100 E E Bangladesh:99 E E E E E E E E E E E E E ]");
        
        assertTrue( map.put( ms("GettysburgCollege"), 1988) == 1988);
        assertEquals( map.toString(), "7:[ E X:1 Re:150 M:1000 D Lory:15 D WD:100 E E Bangladesh:99 E E E E E E GettysburgCollege:1988 E E E E E E ]");
        
        assertTrue( map.put( ms("M"), 1990) == 1000);
        assertEquals( map.toString(), "7:[ E X:1 Re:150 M:1990 D Lory:15 D WD:100 E E Bangladesh:99 E E E E E E GettysburgCollege:1988 E E E E E E ]");
        
        assertTrue( map.remove( ms("Lory")) == 15);
        assertEquals( map.toString(), "6:[ E X:1 Re:150 M:1990 D D D WD:100 E E Bangladesh:99 E E E E E E GettysburgCollege:1988 E E E E E E ]");
        
        assertTrue( map.put( ms("Mory"), 44) ==  44);
        assertEquals( map.toString(), "7:[ E X:1 Re:150 M:1990 Mory:44 D D WD:100 E E Bangladesh:99 E E E E E E GettysburgCollege:1988 E E E E E E ]");
        
        assertTrue( map.put( ms("AVeryLongTextToGetLastI"), 1) ==  1);
        assertEquals( map.toString(), "8:[ E X:1 Re:150 M:1990 Mory:44 D D WD:100 E E Bangladesh:99 E E E E E E GettysburgCollege:1988 E E E E E AVeryLongTextToGetLastI:1 ]");
        
        assertTrue( map.remove( ms("AVeryLongTextToGetLastI")) == 1);
        assertEquals( map.toString(), "7:[ E X:1 Re:150 M:1990 Mory:44 D D WD:100 E E Bangladesh:99 E E E E E E GettysburgCollege:1988 E E E E E D ]");
        
        assertTrue( map.put( ms("AVeryLongTextToGetLastI"), 1) ==  1);
        assertEquals( map.toString(), "8:[ E X:1 Re:150 M:1990 Mory:44 D D WD:100 E E Bangladesh:99 E E E E E E GettysburgCollege:1988 E E E E E AVeryLongTextToGetLastI:1 ]");
        
        assertTrue( map.remove( ms("Re")) == 150);
        assertEquals( map.toString(), "7:[ E X:1 D M:1990 Mory:44 D D WD:100 E E Bangladesh:99 E E E E E E GettysburgCollege:1988 E E E E E AVeryLongTextToGetLastI:1 ]");
        
        assertTrue( map.get( ms("WD")) == 100);
        assertEquals( map.toString(), "7:[ E X:1 D M:1990 Mory:44 D D WD:100 E E Bangladesh:99 E E E E E E GettysburgCollege:1988 E E E E E AVeryLongTextToGetLastI:1 ]");
        
        assertTrue( map.put( ms("BVeryLongTextToGetLastI"), 2) ==  2);
        assertEquals( map.toString(), "8:[ BVeryLongTextToGetLastI:2 X:1 D M:1990 Mory:44 D D WD:100 E E Bangladesh:99 E E E E E E GettysburgCollege:1988 E E E E E AVeryLongTextToGetLastI:1 ]");
        
        assertTrue( map.remove( ms("BVeryLongTextToGetLastI")) == 2);
        assertEquals( map.toString(), "7:[ D X:1 D M:1990 Mory:44 D D WD:100 E E Bangladesh:99 E E E E E E GettysburgCollege:1988 E E E E E AVeryLongTextToGetLastI:1 ]");
    }
}