import java.io.*;
import java.util.Scanner;
/**
 * Implementation of the stack ADT by means of a singly linked list.
 *
 * @author Natasha Gelfand
 * @author Roberto Tamassia
 * @see Node
 */
//begin#fragment NodeStack
public class NodeStack<E> implements Stack<E> {

    protected NodeG<E> top;	// reference to the head node
    protected int size;		// number of elements in the stack
//end#fragment NodeStack

    /**
     * Creates an empty stack.
     */
//begin#fragment NodeStack
    public NodeStack() {	// constructs an empty stack
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }
   
    //Verifica se a pilha esta vazia
    public boolean isEmpty() {
        if (top == null) {
            return true;
        }
        return false;
    }
    
    //insere um elemento no topo da pilha
    public void push(E elem) {
        NodeG<E> v = new NodeG<E>(elem, top);	// cria e encadeia um novo nodo
        top = v;
        size++;
    }
    
    //reotrna o elemento no topo da pilha genérica
    public E top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        }
        return top.getElement();
    }
    
    //Remove o elemento do topo da pilha genérica  
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        }
        E temp = top.getElement();
        top = top.getNext();	// link-out the former top node
        size--;
        return temp;
    }
//end#fragment NodeStack

    /**
     * Returns a string representation of the stack as a list of elements, with
     * the top element at the end: [ ... , prev, top ]. This method runs in O(n)
     * time, where n is the size of the stack.
     *
     * @return textual representation of the stack.
     */
    public String toString() {
        String s;
        NodeG<E> cur = null;
        s = "[";
        int n = size();
        if (n > 0) {
            cur = top;
            s += cur.getElement();
        }
        if (n > 1) {
            for (int i = 1; i <= n - 1; i++) {
                cur = cur.getNext();
                s += ", " + cur.getElement();
            }
        }
        s += "]";
        return s;
    }

    /**
     * Prints information about an operation and the stack.
     *
     * @param op operation performed
     * @param element element returned by the operation
     * @return information about the operation performed, the element returned
     * by the operation and the content of the stack after the operation.
     */
    public static void status(Stack S, String op, Object element) {
        System.out.println("---------------------------------");
        System.out.println(op);
        System.out.println("Returned: " + element);
        String emptyStatus;
        if (S.isEmpty()) {
            emptyStatus = "empty";
        } else {
            emptyStatus = "not empty";
        }
        System.out.println("size = " + S.size() + ", " + emptyStatus);
        System.out.println("Stack: " + S);
    }

    /**
     * Test program that performs a series of operations on on a stack and
     * prints the operation performed, the returned element and the content of
     * the stack after each operation.
     * @return 
     */
    
    
    /**
     * Verifica se os parenteses de uma expressão aritimética casam, verificando se uma expressão aritimética está correta
     * @param pilhaRecebida
     * @return 
     */
    public static boolean parenMatch(NodeStack pilhaRecebida){
        NodeStack <Character> openSymbol = new NodeStack();
        NodeG <Character> aux =  (NodeG <Character>) pilhaRecebida.top;
        while(aux != null){
            if(aux.getElement() == '(' || aux.getElement() == '[' || aux.getElement() == '{'){
                Character element = aux.getElement();
                openSymbol.push(element);
            }else if(aux.getElement().equals(')') || aux.getElement().equals(']') || aux.getElement().equals('}')){
                if(openSymbol.isEmpty()){
                    return false;
                } 
                
                Character topElement = openSymbol.pop();
                if(topElement.equals('(') && !aux.getElement().equals(')') || topElement.equals('[') && !aux.getElement().equals(']') || topElement.equals('{') && !aux.getElement().equals('}') ){
                    return false;
                }
            }
            aux = aux.getNext();
            
        }
        
        return openSymbol.isEmpty();
    }
        
        
        
    
    public static void main(String[] args) {
        
        //Cria pilha e empilha os nodos da arvore que armazena a expressao aritimetica
        NodeStack E = new NodeStack();
        NodeStack inverseE = new NodeStack();
        E.push('(');
        E.push('(');
        E.push('5');
        E.push('+');
        E.push('4');
        E.push(')');
        E.push('*');
        E.push('(');
        E.push('3');
        E.push('-');
        E.push('2');
        E.push(')');
        E.push(')');
        int n = E.size;
        for(int i = 0; i < n; i++){
            inverseE.push(E.pop());
        }
        
        System.out.println("Inverse Stack: " + inverseE.toString());
        
       if(parenMatch(inverseE)){
           System.out.println("Verdadeira");
       }else{
           System.out.println("False");
       }
    }
}
