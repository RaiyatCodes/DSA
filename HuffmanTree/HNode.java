/**
 * Implementation of Huffman Node for Huffman Tree Data Structure 
 * to support the Huffman Encoding Algorithm
 * 
 * @author Raiyat Haque
*/
public class HNode{
    HNode right;
    HNode left;
    String characters = "";
    int frequency;
   
    /**
     * Creates a leaf node representing the given character and its frequency
     * @param c   the character that is passed
     * @param f   the number of occurences of that character  
    */
    public HNode(char c,int f){
        this.characters += c;
        this.frequency = f;
    }
    
    /**
     * Creates a node with the given left and right children.
     * @param left    the left child of HNode
     * @param right   the right child of HNode
    */
    public HNode(HNode left, HNode right){
        this.characters = left.characters + right.characters;
        this.frequency = left.frequency + right.frequency;
        this.left = left;
        this.right = right;
    }
    
    /**
     * Returns true if the node is a leaf
     * @return True if both children of the node are null, False otherwise
    */
    public boolean isLeaf(){
        return (this.right == null && this.left == null);
    }
    
    /**
     * Returns true if the node contains the given character
     * @param ch    the character to look for
     * @return True if node contains the character, False otherwise
    */
    public boolean contains(char ch){
        String str = "" + ch;
        return (characters.contains(str));
    }
    
    /**
     * Returns the symbol stored in the node.
     * @return The characters stored in the node, '\0' if node is a leaf
    */
    public char getSymbol(){
        if((isLeaf())){
            return characters.charAt(0);
        }
        return '\0';
    }
    
    /**
     * Returns a string representation of the node
     * @return a String format of HNode in the format symbols:frequency
    */
    public String toString(){
        return this.characters + ":" + this.frequency; 
    }
}
