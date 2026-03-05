/**
 * Implementation of Binary Search Tree
 * 
 * @author Raiyat Haque
*/
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BSTree<E>{
    /**
     * Node Class of which the tree is made of
    */
    public class Node{
        Node parent;
        Node left;
        Node right;
        E data;
        
        public Node(E data){
            this.data = data;
        }
    }
    
    private Node root; // data member that points to the root of the tree
    private Comparator<E> comp; //data member to compare Nodes within the tree
    
    /**
     * Creates an empty tree that uses the given comparator to compare the values in the tree
     * 
     * @param comp   The comparator passed to use for comparing the Nodes
     * @return A tree is created with zero nodes
    */ 
    public BSTree(Comparator<E> comp){
        this.comp = comp;
        root = null;
    }
    
    /**
     * Adds the given item to the tree
     * 
     * @param item   The data given by the user that is to be added in the tree
    */
    public void addLoop(E item){
        Node newNode = new Node(item);
        if(isEmpty()){
            root = newNode;
        }
        Node current = root;
        while(current != null){
            if(comp.compare(item, current.data) < 0){
                if(current.left != null){
                    current = current.left;
                }
                else{
                    Node temp = current;
                    current.left = newNode;
                    current.left.parent = temp;
                    break;
                }
            }
            else if(comp.compare(item, current.data) > 0){
                if(current.right != null){
                    current = current.right;
                }
                else{
                    Node temp = current;
                    current.right = newNode;
                    current.right.parent = temp;
                    break;
                }
            }
            else if(comp.compare(current.data, item) == 0){
                break;
            }
        }
    }
    
    /**
     * Determines if the tree is empty
     * 
     * @return  True if there no root, otherwise returns false if there is at least one proper node
    */
    public boolean isEmpty(){
        return (root == null);
    }
    
    /**
     * Returns the largest value in the tree
     *
     * @return  The largest value in the tree 
    */
    public E maxValueLoop(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Node maxValueNode = findMaxNodeLoop(root);
        return maxValueNode.data;
    }
    
    /* Helper method to find the node containing the largest value
     * Returns The node with the largest value in the sub-tree rooted at the given node
    */
    private Node findMaxNodeLoop(Node node){
        Node current = node;
        while(current.right != null){
            current = current.right;
        }
        return current;
    }
    
    /**
     * Determines if the tree contains the given item
     * 
     * @param item   The data given by the user that is to be found
     * @return  True if the item given by the user is in the tree, otherwise false
    */
    public boolean containsLoop(E item){
        Node givenItemNode = findNodeLoop(root, item);
        if(givenItemNode == null){
            return false;
        }
        return true;
    }
    
    /*Helper method to find the node that contains the item in containsLoop method
     * Finds the node in the sub-tree rooted at the given node Node that contains the given item.
    */
    private Node findNodeLoop(Node node, E item){
        Node current = node;
        while(current != null){
            if(comp.compare(item, current.data) == 0){
                return current;
            }
            else if(comp.compare(item, current.data) < 0){
                current = current.left;
            }
            else if(comp.compare(item, current.data) > 0){
                current = current.right;
            }
        }
        return current;
    }
    
    /**
     * Adds the given item to the tree
     * 
     * @param item   The data given by the user that is to be added in the tree
    */
    public void add(E item){
        if(isEmpty()){
            root = new Node(item);
        }
        add(root, item);
    }
    
    /**Helper Method
     * 
     * Recursive version of addLoop. Adds the given item to the sub-tree rooted at the given curr node.
     * 
    */
    private void add(Node curr, E item){
        Node newNode = new Node(item);
        Node current = curr;
        if(comp.compare(item, current.data) < 0){
            if(curr.left == null){ //case where I will fall from the tree
                Node temp = current;
                current.left = newNode;
                current.left.parent = temp;
                return;
            }
            add(current.left, item);
        }
        else if(comp.compare(item, current.data) > 0){
            if(curr.right == null){ //case where I will fall from the tree
                Node temp = current;
                current.right = newNode;
                current.right.parent = temp;
                return;
            }
            add(current.right, item);
        }
        else if(comp.compare(item, current.data) == 0){
            return;
        }
    }
    
    /**
     * Returns the largest value in the tree
     * 
     * @return  The item that has the max value
    */
    public E maxValue(){
        Node maxValueNode = findMaxNode(root);
        if(maxValueNode == null){
            throw new NoSuchElementException();
        }
        return maxValueNode.data;
    }
    
    /*Helper method to find the node that contains the max value
     * Recursive version of findMaxNodeLoop
    */
    private Node findMaxNode(Node curr){
        if(curr == null){
            return curr;
        }
        else if(curr.right == null){
            return curr;
        }
        return findMaxNode(curr.right);
    }
    
    /**
     * Determines if the tree contains the given item
     * 
     * @param item   The data given by the user that is to be found
     * @return  True if the item exists, otherwise false
    */
    public boolean contains(E item){
        Node givenItemNode = findNode(root, item);
        if(givenItemNode == null){
            return false;
        }
        return true;
    }
    
    /*Helper method to find the node with a specified item
     * Recursive version of findNodeLoop
    */
    private Node findNode(Node curr, E item){
        if(curr == null){
            return curr;
        }
        else if(comp.compare(item, curr.data) < 0){
            return findNode(curr.left, item);
        }
        else if(comp.compare(item, curr.data) > 0){
            return findNode(curr.right, item);
        }
        return curr; 
    }
    
    /**
     * Removes the given item from the tree
     * 
     * @param item   The data given by the user that is to be removed
     * @return True if the item is succesfully removed, otherwise false
    */
    public boolean remove(E item){
        Node nodeRemoved = findNode(root, item);
        if(nodeRemoved == null){ //if the tree is empty
            return false;
        }
        else if(nodeRemoved.right == null || nodeRemoved.left == null){ //could be missing one/both child
            removeMissing(nodeRemoved);
            return true;
        }
        //has both child
        removeHasBoth(nodeRemoved);
        return true;
    }
    
    /**Helper Method
     * 
     * Removes the given node assuming it is missing one or both children
     * 
    */
    private void removeMissing(Node node){
        Node curr = node; //node to remove
        if(curr == root){
            if(root.right == null && root.left == null){
                root = null;
            }
            else if(root.right == null){
                Node temp = root.left;
                root.left.parent = null;
                root = temp;
            }
            else if(root.left == null){
                Node temp = root.right;
                root.right.parent = null;
                root = temp;
            }
        }
        else if(curr.left == null && curr.right == null){ //doesnt have any child
            if(curr.parent.left == curr){
                curr.parent.left = null;
            }
            else if(curr.parent.right == curr){ //mirror
                curr.parent.right = null;
            }
        }
        else if(curr.right == null){ //doesnt have right child
            curr.left.parent = curr.parent; 
            if(curr.parent.right == curr){
                curr.parent.right = curr.left;
            }
            else if(curr.parent.left == curr){ //mirror
                curr.parent.left = curr.left;
            }
        }
        else if(curr.left == null){ //doesnt have a left child
            curr.right.parent = curr.parent;
            if(curr.parent.left == curr){ 
                curr.parent.left = curr.right;
            }
            else if(curr.parent.right == curr){ //mirror
                curr.parent.right = curr.right;
            }
        }
    }
    
    /**Helper Method
     * 
     * Removes the given node assuming it has exactly two children
     *
    */
    private void removeHasBoth(Node node){
        Node curr = node;
        Node maxNode = findMaxNodeLoop(curr.left);
        curr.data = maxNode.data;
        removeMissing(maxNode);
    }
    
    /**
     * Returns a string representation of the tree
    */
    public String toString(){
        return new BSTreeUtils<E>().toString(root);
    }
}

