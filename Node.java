/**
 * @author livro - trecho de codigo 3.12
 * Nodo de uma lista simplesmente encadeada de strings */

public class Node {

    private  int element;	// assumimos que os elementos sao strings 
    private Node next;
    
    
    /**
     * Cria um nodo vazio 
     */
    
    public Node(){
        element = 0;
        next = null;
    }
    
    
    /**
     * Cria um nodo com um dado elemento e o próximo nodo
     */
    public Node(int s, Node n) {
        element = s; //O elemento de um objeto nodo e uma string
        next = n; //o node aqui e a referencia (endereco de memoria) do objeto
    }

    /**
     * Retorna o elemento deste nodo
     */
    public int getElement() {
        return element;
    }

    /**
     * Retorna o próximo elemento deste nodo
     */
    public Node getNext() {
        return next;
    }

    // Métodos modificadores: 
    /**
     * Define o elemento deste nodo
     */
    public void setElement(int newElem) {
        element = newElem;
    }

    /**
     * Define o próximo elemento deste nodo
     */
    public void setNext(Node newNext) {     //Este metedo recebe como parametro a referencia do proximo nodo
        next = newNext;                     // e adiciona essa refencia no campo next do nodo
    }
}