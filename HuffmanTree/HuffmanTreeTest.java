import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import java.io.OutputStream;
import java.io.IOException;

public class HuffmanTreeTest{
    @Test
    public void test_HuffmanTree() throws Exception{
        TreeMap<Character,Integer> frequencies = new TreeMap<Character,Integer>();
        frequencies.put('a', 10);
        frequencies.put('b', 20);
        frequencies.put('d', 4);
        frequencies.put('h', 30);
        frequencies.put('e', 9);
        HuffmanTree tree = new HuffmanTree(frequencies);
        
        //testing encode
        assertEquals( tree.encode('h'), "0");
        assertEquals( tree.encode('a'), "110");
        assertEquals( tree.encode('b'), "10");
        assertEquals( tree.encode('e'), "1111");
        
        //testing encodeLoop
        assertEquals( tree.encodeLoop('h'), "0");
        assertEquals( tree.encodeLoop('a'), "110");
        assertEquals( tree.encodeLoop('b'), "10");
        assertEquals( tree.encodeLoop('e'), "1111");
        
        //testing decode
        assertEquals( tree.decode("0"), 'h');
        assertEquals( tree.decode("110"), 'a');
        assertEquals( tree.decode("10"), 'b');
        assertEquals( tree.decode("1111"), 'e');
        
        TreeMap<Character,Integer> frequencies2 = new TreeMap<Character,Integer>();
        frequencies2.put('a', 5);
        frequencies2.put('b', 9);
        frequencies2.put('c', 12);
        frequencies2.put('d', 13);
        frequencies2.put('e', 16);
        frequencies2.put('f', 45);
        HuffmanTree tree2 = new HuffmanTree(frequencies2);
        
        //testing encode
        assertEquals( tree2.encode('a'), "1100");
        assertEquals( tree2.encode('b'), "1101");
        assertEquals( tree2.encode('c'), "100");
        assertEquals( tree2.encode('d'), "101");
        assertEquals( tree2.encode('e'), "111");
        assertEquals( tree2.encode('f'), "0");
        
        //testing encodeLoop
        assertEquals( tree2.encodeLoop('a'), "1100");
        assertEquals( tree2.encodeLoop('b'), "1101");
        assertEquals( tree2.encodeLoop('c'), "100");
        assertEquals( tree2.encodeLoop('d'), "101");
        assertEquals( tree2.encodeLoop('e'), "111");
        assertEquals( tree2.encodeLoop('f'), "0");
        
        //testing decode
        assertEquals( tree2.decode("1100"), 'a');
        assertEquals( tree2.decode("1101"), 'b');
        assertEquals( tree2.decode("100"), 'c');
        assertEquals( tree2.decode("101"), 'd');
        assertEquals( tree2.decode("111"), 'e');
        assertEquals( tree2.decode("0"), 'f');
        //testing invalid code
        assertEquals( tree2.decode("000000"), '\0');
        assertEquals( tree2.decode("101001"), '\0');
        assertEquals( tree2.decode("1110"), '\0');
        assertEquals( tree2.decode("3110"), '\0');
        assertEquals( tree2.decode("111111"), '\0');
        
        //testing writeCode and readCode for 1 symbol
        BitOutputStream streamOut = new BitOutputStream("test.hz");
        tree2.writeCode('a',streamOut);
        streamOut.close();
        
        BitInputStream streamIn = new BitInputStream("test.hz");
        char result = tree2.readCode(streamIn);
        streamIn.close();
        
        assertEquals('a', result);
        
        //testing writeCode and readCode for 2 symbols
        BitOutputStream stream = new BitOutputStream("test2.hz");
        tree.writeCode('a', stream);
        tree.writeCode('e', stream);
        stream.close();
        
        BitInputStream in = new BitInputStream("test2.hz");
        char result1 = tree.readCode(in);
        char result2 = tree.readCode(in);
        in.close();
        
        assertEquals('a', result1);
        assertEquals('e', result2);
        
        //testing writeCode and readCode for more than 2 symbols
        BitOutputStream stream2 = new BitOutputStream("test3.hz");
        tree2.writeCode('a',stream2);
        tree2.writeCode('f',stream2);
        tree2.writeCode('b',stream2);
        tree2.writeCode('e',stream2);
        stream2.close();
        
        BitInputStream streamIn2 = new BitInputStream("test3.hz");
        char c = tree2.readCode(streamIn2);
        char c2 = tree2.readCode(streamIn2);
        char c3 = tree2.readCode(streamIn2);
        char c4 = tree2.readCode(streamIn2);
        streamIn2.close();
        
        assertEquals('a', c);
        assertEquals('f', c2);
        assertEquals('b', c3);
        assertEquals('e', c4);
    }
    
    @Test
    public void test_HNode(){
       //testing leaf node
        HNode node = new HNode('c',10);
        assertTrue( node.isLeaf());
        assertTrue( node.contains('c'));
        assertFalse( node.contains('a'));
        assertEquals( node.toString(), "c:10");
        assertTrue( node.getSymbol() == 'c');
        
        //testing multiple nodes
        HNode n1 = new HNode('a',5);
        HNode n2 = new HNode('b',4);
        HNode n3 = new HNode('c',9);
        HNode n4 = new HNode('d',13);
        
        HNode newNode = new HNode(n1, n2);
        
        assertFalse( newNode.isLeaf());
        assertTrue( newNode.contains('b'));
        assertEquals( newNode.toString(), "ab:9");
        assertTrue( newNode.getSymbol() == '\0');
        
        HNode newNode2 = new HNode(newNode, n3);
        assertFalse( newNode2.isLeaf());
        assertTrue( newNode2.contains('b'));
        assertEquals( newNode2.toString(), "abc:18");
        assertTrue( newNode2.getSymbol() == '\0');
    }
}
