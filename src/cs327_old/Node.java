package cs327_old;

public class Node {
    
    private String value; // string value of node
    private Node next;    // node object pointed to by current node. 
    
    /**
     * Constructor for object of type Node
     * @param String s string value of node
     */
    public Node(String s){
            value = s;
            next = null;
    }
    
    /**
     * getter method for the string value of the node
     * @return string value of the node
     */
    public String getValue(){
                    return value;
    }
    /**
     *  getter method to get at the next attribute
     *  @return next, node pointed to by this node
     */
    public Node getNext(){
            return next;
    }
    
    /**
     * setter method to set the value of the node that a given node will point to
     * @param n the node that this node will point to
     */
    public void setNext(Node n){
            next = n;
    }
    
    
}
