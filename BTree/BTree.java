/**
 * Implementation of B-Tree
 * 
 * @author Raiyat Haque
*/
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.Queue;
import java.util.LinkedList;

public class BTree<E>{
    private Node<E> root;
    private Comparator<E> comp;
    private int order;
    
    /**
     * Creates an empty tree of the given order and the given comparator
    */
    public BTree(int theOrder, Comparator<E> theComp){
        this.order = theOrder;
        this.comp = theComp;
        root = new Node<E>(this.order, this.comp);
    }
    
    /**
     * Adds the given item to the tree.
     * @param item    The data that needs to be added
    */
    public void add(E item){
        Node<E> curr = findLeaf(root, item);
        curr.leafAdd(item);
        if(curr.hasOverflow()){
            curr.split();
            while(root.parent != null){
                root = root.parent;
            }
        }
    }
    
    /**
     * Finds the leaf node in the tree rooted at the given node curr where the given item should be inserted
     * non-recursive
    */
    private Node<E> findLeaf(Node<E> curr, E item){
        Node<E> temp = curr;
        while(!(temp.children.isEmpty())){
            temp = temp.childToFollow(item);
        }
        return temp;
    }
    
    /**
     * Determines if the tree contains the given item
     * @param item    The data that needs to be checked
    */
    public boolean contains(E item){
        Node<E> curr = findNode(root, item);
        if(curr == null){
            return false;
        }
        return curr.data.contains(item);
    }
    
    /**
     * Finds the node that contains the given item in the tree rooted at the given node curr
    */
    private Node<E> findNode(Node<E> curr, E item){
        if(curr.data.contains(item)){
            return curr;
        }
        else if(curr.children.isEmpty()){
            return null;
        }
        return findNode(curr.childToFollow(item),item);
    }
    
    /**
     * Performs inorder traversal of this tree
     * @param consumer     An instance of Consumer that takes in values
    */
    public void inorder(Consumer<E> consumer){
        inorder(consumer,root);
    }
    
    /**
     * Performs inorder traversal of the tree rooted at the given node curr
    */
    private void inorder(Consumer<E> consumer, Node<E> curr){
        if(curr.children.isEmpty()){
            for(E items: curr.data){
                consumer.accept(items);
            }
            return;
        }
        for(int i = 0; i < curr.data.size(); i++){
            Node<E> temp = curr.children.get(i);
            inorder(consumer, temp);
            consumer.accept(curr.data.get(i));
        }
        Node<E> temp = curr.children.get(curr.children.size()-1);
        inorder(consumer,temp);
    }
    
    /**
     * Returns a string representation of this tree in sorted order
    */
    public String toStringSorted(){
        StringConsumer<E> consumer = new StringConsumer<>();
        this.inorder(consumer);
        return consumer.getValue();
    }
    
    /**
     * Returns a string representaion of this tree in level-order traversal in this format
    */
    public String toString(){
        String result = "[";
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        while(!(queue.isEmpty())){
            Node<E> curr = queue.poll();
            String str = curr.data.toString();
            str = str.replaceAll(",", "");
            result += str + " ";
            for(Node<E> child: curr.children){
                queue.add(child);
            }
        }
        result = result.trim();
        result += "]";
        return result;
    }
}