//begin#fragment TNode

/**
 * Class implementing a node of a binary tree by storing references to
 * an element, a parent node, a left node, and a right node.
 * //end#fragment TNode
 *
 *  @author Luca Vismara, Roberto Tamassia, Michael Goodrich
 * //begin#fragment TNode
 */
public class TreeNode<E> implements TreePosition<E> {

    private E element;  // element stored at this node
    private int size;
    private TreePosition<E> parent;  // adjacent node
    private PositionList<Position<E>> children;  // children nodes
//end#fragment TNode

    /**
     * Default constructor
     */
    public TreeNode() {
    }
//begin#fragment TNode

    /**
     * Main constructor
     */
    public TreeNode(E element, int s, TreePosition<E> parent,
            PositionList<Position<E>> children) {
        setElement(element);
        setSize(s);
        setParent(parent);
        setChildren(children);
    }

    /**
     * Returns the element stored at this position
     */
    public E getElement() {
        return element;
    }
    
    /**
     * Returns the size stored at this position
     */
    
  
    public int getSize(){
        return size;
    }

    /**
     * Sets the element stored at this position
     */
    public void setElement(E o) {
        element = o;
    }
    
     /**
     * Sets the size stored at this position
     */

    public void setSize(int s){
        size = s;
    }
    
    
    /**
     * Returns the children of this position
     */
    public PositionList<Position<E>> getChildren() {
        return children;
    }

    /**
     * Sets the right child of this position
     */
    public void setChildren(PositionList<Position<E>> c) {
        children = c;
    }

    /**
     * Returns the parent of this position
     */
    public TreePosition<E> getParent() {
        return parent;
    }

    /**
     * Sets the parent of this position
     */
    public void setParent(TreePosition<E> v) {
        parent = v;
    }
}
//end#fragment TNode
