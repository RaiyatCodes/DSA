/**
 * Implementation of the generic Node for B-Tree
 * 
 * @author Raiyat Haque
*/
import java.util.ArrayList;
import java.util.Comparator;

public class Node<E>{
    Node<E> parent;
    int order;
    Comparator<E> comp;
    ArrayList<E> data;
    ArrayList<Node<E>> children;
    
    /**
     * Creates an empty node of the given order and the given comparator
     * @param theOrder    The order of the Node
     * @param theComp     The comparator for the Node
    */
    public Node(int theOrder, Comparator<E> theComp){
        this.order = theOrder;
        this.comp = theComp;
        this.data = new ArrayList<E>();
        this.children = new ArrayList<Node<E>>();
    }
    
    /**
     * Creates a node with the given left and right children and the single separator data item for the children
     * @param theOrder    The order of the Node
     * @param theComp     The comparator for the Node
     * @param left        The left child of the Node
     * @param right       The right child of the Node
     * @param item        The data item for the children
     */
    public Node(int theOrder, Comparator<E> theComp, Node<E> left, Node<E> right, E item){
        this.order = theOrder;
        this.comp = theComp;
        this.data  = new ArrayList<E>();
        this.children = new ArrayList<Node<E>>();
        data.add(item);
        children.add(left);
        children.add(right);
        left.parent = this;
        right.parent = this;
    }
    
    /**
     * Creates a node with the given parent p, data items, and children
     * @param theOrder    The order of the Node
     * @param theComp     The comparator for the Node
     * @param theParent   The parent of the Node
     * @param theData     The data items of the Node
     * @param theChildren   The children of the Node
    */
    public Node(int theOrder, Comparator<E> theComp, Node<E> theParent, ArrayList<E> theData, ArrayList<Node<E>> theChildren){
        this.parent = theParent;
        this.comp = theComp;
        this.order = theOrder;
        this.data = theData;
        this.children = theChildren;
    }
    
    /**
     * Determines whether the node is filled beyond capacity (and needs to be split)
    */
    public boolean hasOverflow(){
        return (data.size() > order);
    }
    
    /**
     * Determines if this node is leaf
    */
    public boolean isLeaf(){
        return (children.size() == 0);
    }
    
    /**
     * Returns the next child to follow down the tree in order to locate the given item
     * @param item     The item to look for
    */
    public Node<E> childToFollow(E item){
        for(int i = 0; i < this.data.size(); i++){
            if(comp.compare(item,data.get(i)) < 0){
                return this.children.get(i);
            }
        }
        return children.get(children.size() - 1);
    }
    
    /**
     * Inserts the given item in the correct position among this node's data item
     * @param item   The item that needs to be added
    */
    public void leafAdd(E item){
        if(data.isEmpty()){
            data.add(item);
        }
        else if(data.contains(item)){
            return;
        }
        else{
            data.add(item);
            data.sort(comp);
        }
    }
    
    /**
     * Splits this node by creating a new right sibling node
    */
    public void split(){
        //case where parent is null
        if(this.parent == null){ 
            Node<E> rightSibling = new Node(this.order, this.comp);
            int middle = data.size() / 2;
            E middleItem = data.get(middle);
            ArrayList<E> data_list = new ArrayList<E>(data.subList(middle + 1, data.size()));
            ArrayList<Node<E>> children_list = new ArrayList<>();
            
            if(!children.isEmpty()){
                children_list = new ArrayList<>(children.subList(middle + 1,children.size()));
            }
            rightSibling.data = data_list;
            rightSibling.children = children_list;
            
            for(Node<E> child: rightSibling.children){
                child.parent = rightSibling;
            }
            
            data = new ArrayList<>(data.subList(0,middle));
            if(!children.isEmpty()){
                children = new ArrayList<>(children.subList(0, middle + 1));
            }
            Node<E> newParent = new Node<>(this.order, this.comp, this, rightSibling, middleItem);
            this.parent = newParent;
            rightSibling.parent = newParent;
            
            return;
        }
        
        //case where parent is not null
        Node<E> rightSibling = new Node<>(this.order, this.comp);
        int middle = data.size() / 2;
        E middleItem = data.get(middle);
        
        ArrayList<E> data_list = new ArrayList<E>(data.subList(middle+1, data.size()));
        ArrayList<Node<E>> children_list = new ArrayList<>();
        
        if(!children.isEmpty()){
            children_list = new ArrayList<>(children.subList(middle + 1, children.size()));
        }
        
        rightSibling.data = data_list;
        rightSibling.children = children_list;
        
        for(Node<E> child: rightSibling.children){
            child.parent = rightSibling;
        }
        
        data = new ArrayList<>(data.subList(0,middle));
        
        if(!children.isEmpty()){
            children = new ArrayList<>(children.subList(0, middle + 1));
        }
        
        parent.data.add(middleItem);
        parent.data.sort(comp);
        
        int index = parent.children.indexOf(this);
        parent.children.add(index+1, rightSibling);
        
        rightSibling.parent = this.parent;
        
        //overflow in parent
        if(parent.hasOverflow()){
            parent.split();
        }
    }
    
    /**
     * Determines if this node contains the given item
    */
    public boolean contains(E item){
        return (this.data.contains(item));
    }
}
