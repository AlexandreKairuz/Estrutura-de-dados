
/**
 * A simple node class for a doubly-linked list.  Each DNode has a
 * reference to a stored element, a previous node, and a next node.
 *
 * @author Roberto Tamassia
 */
//begin#fragment DNode
public class DNode<E> implements Position<E> {

    DNode<E> prev;
    protected DNode<E> next; // References to the nodes before and after
    protected E element;	// Element stored in this position

    /**
     * Constructor
     */
    public DNode(DNode<E> newPrev, DNode<E> newNext, E elem) {
        prev = newPrev;
        next = newNext;
        element = elem;
    }
    // Method from interface Position

    public E getElement() throws IllegalArgumentException {
        /*if ((prev == null) && (next == null)) {
            throw new IllegalArgumentException("Position is not in a list!");
        }*/
        return element;
    }
    // Accessor methods

    public DNode<E> getNext() {
        return next;
    }

    public DNode<E> getPrev() {
        return prev;
    }
    // Update methods

    public void setNext(DNode<E> newNext) {
        next = newNext;
    }

    public void setPrev(DNode<E> newPrev) {
        prev = newPrev;
    }

    public void setElement(E newElement) {
        element = newElement;
    }
}
//end#fragment DNode
