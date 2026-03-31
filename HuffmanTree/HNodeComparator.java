/**
 * Implementation of a comparator that compares two HNode objects based on frequencies
 * If frequencies are same, comparison is made based on symbols lexicographically
 * 
 * @author Raiyat Haque
*/
import java.util.Comparator;

public class HNodeComparator implements Comparator<HNode> {
    public int compare(HNode node1, HNode node2){
        //if node1 frequency is bigger than node2 frequency
        if(node1.frequency > node2.frequency){
            return 1;
        }
        //if node1 frequenct is less than node2 frequency
        else if(node1.frequency < node2.frequency){
            return -1;
        }
        //if both frequencies are the same, compare lexicographically
        else if(node1.frequency == node2.frequency){
            if(node1.characters.compareTo(node2.characters) > 0){ // node2 comes before node1
                return 1;
            }
            else if(node1.characters.compareTo(node2.characters) < 0){ // node1 comes before node2
                return -1;
            }
        }
        return 0;
    }
}
