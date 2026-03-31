/**
 * Implementation of Huffman Tree Data Structure
 * 
 * @author Raiyat Haque
*/
import java.io.IOException;
import java.util.TreeMap;
import java.util.PriorityQueue;
import java.util.Map;

public class HuffmanTree{
    HNode root;
    
    /**
     * Builds a HuffmanTree from the given characters and their corresponding frequencies
     * @param frequencies   A TreeMap of Characters and Integers
    */
    public HuffmanTree(TreeMap<Character, Integer> frequencies){
        PriorityQueue<HNode> queue = new PriorityQueue<>(new HNodeComparator());
        for(Map.Entry<Character, Integer> entry: frequencies.entrySet()){
            HNode node = new HNode(entry.getKey(), entry.getValue());
            queue.add(node);
        }
        while(queue.size() != 1){
            HNode left = queue.poll();
            HNode right = queue.poll();
            HNode newNode = new HNode(left, right);
            queue.add(newNode);
        }
        root = queue.poll();
    }
    
    /**
     * Returns the binary encoding of the given symbol as a string of '0' and '1' characters
     * @param symbol   the character that needs to be encoded
     * @return A String of 0s and 1s corresponding to the given character
    */
    public String encodeLoop(char symbol){
        String binaryCode = "";
        HNode curr = root;
        while(!(curr.isLeaf())){
            if(curr.left.contains(symbol)){
                binaryCode += "0";
                curr = curr.left;
            }
            else if(curr.right.contains(symbol)){
                binaryCode += "1";
                curr = curr.right;
            }
        }
        return binaryCode;
    }
  
    /**
     * Returns the binary encoding of the given symbol as a string of '0' and '1' characters
     * @param symbol    the character that needs to be encoded
     * @return A String of 0s and 1s corresponding to the given character
     */
    public String encode(char symbol){
        HNode curr = root;
        String binaryCode = encode(symbol,curr);
        return binaryCode;
    }
    
    /**
     * Helper method for encode
    */
    public String encode(char symbol, HNode curr){
        String binaryCode = "";
        if(curr.isLeaf()){
            return "";
        }
        else if(curr.left.contains(symbol)){
            binaryCode += "0";
            return binaryCode + encode(symbol, curr.left);
        }
        else if(curr.right.contains(symbol)){
            binaryCode += "1";
            return binaryCode + encode(symbol, curr.right);
        }
        return binaryCode;
    }
    
    /**
     * Returns the symbol that corresponds to the given code
     * @param code   the code whose corresponding symbol needs to be returned
     * @return The symbol corresponding to the code, '\0' if the code is invalid
    */
    public char decode(String code){
        HNode curr = root;
        for(int i = 0; i < code.length(); i++){
            if(code.charAt(i) == '0'){
                curr = curr.left;
                if(curr == null){
                    return '\0';
                }
            }
            else if(code.charAt(i) == '1'){
                curr = curr.right;
                if(curr == null){
                    return '\0';
                }
            }
            else{
                return '\0';
            }
        }
        char symbol = curr.getSymbol();
        return symbol;
    }
    
    /**
     * Write the individual bits of the binary encoding of the given symbol to the given stream
     * 
     * @param symbol    the charactor whose bits are required to be written
     * @param stream    the BitOutputStream instance that will collect the bits
    */
    public void writeCode (char symbol, BitOutputStream stream) throws IOException{
        String binaryCode = encodeLoop(symbol);
        for(int i = 0; i < binaryCode.length(); i++){
            if(binaryCode.charAt(i) == '1'){
                stream.writeBit(1);
            }
            else{
                stream.writeBit(0);
            }
        }
    }
    
    /**
     * Reads from the given stream the individual bits of the binary encoding of the next symbol and returns the corresponding character
     * @param stream    The BitOutputStream instance where the bits will be
     * @return The character corresponding to those bits, '\0' if the bits in the stream do not produce a symbol
    */
    public char readCode(BitInputStream stream) throws Exception{
        HNode curr = root;
        int bit;
        while(true){
            try{
                bit = stream.readBit();
            } catch (Exception e){
                return '\0';
            }
            if(bit == 1){
                curr = curr.right;
            }
            else{
                curr = curr.left;
            }
            if(curr.isLeaf()){
                return curr.getSymbol();
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        HuffmanZip huffZip = new HuffmanZip();
        huffZip.encode("/Users/raiyat/Desktop/CS216-Spring HW/Assignment 7/HuffmanTree/res/tlc-logic.txt");
        huffZip.decode("/Users/raiyat/Desktop/CS216-Spring HW/Assignment 7/HuffmanTree/res/tlc-logic.txt.hz");
        
        System.out.println("Done.");
    }
}
