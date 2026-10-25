public class CircleList {

    protected Node cursor;	// o cursor corrente (corrente e o dado que se esta analisando na lista nauqle determinado momento)
    protected int size;	// a quantidade de nodos da lista

    /**
     * Construtor que cria uma lista vazia
     */
    public CircleList() {
        cursor = null;
        size = 0;
    }

    /**
     * Retorna o tamanho corrente
     */
    public int size() {
        return size;
    }

    /**
     * Retorna o cursor
     */
    public Node getCursor() {
        return cursor;
    }

    /**
     * Move o cursor adiante
     */
    public void advance() {
        cursor = cursor.getNext();
    }

    /**
     * Acrescenta um nodo depois do cursor
     */
    public void add(Node newNode) {
        if (cursor == null) {	// a lista está vazia 
            newNode.setNext(newNode);
            cursor = newNode;
        } else {
            newNode.setNext(cursor.getNext());
            cursor.setNext(newNode);
        }
        size++;
    }

    /**
     * Remove o nodo que segue o cursor
     */
    public Node remove() {
        Node oldNode = cursor.getNext(); // o nodo sendo removido 
        if (oldNode == cursor) {
            cursor = null; // a lista se torna vazia 
        } else {
            cursor.setNext(oldNode.getNext()); // desconecta o nodo antigo
            oldNode.setNext(null);
        }
        size--;
        return oldNode;
    }

    /**
     * Retorna uma representação string da lista, iniciando pelo cursor
     */
    public String toString() {
        if (cursor == null) {
            return "[ ]";
        }
        String s = "[..." + cursor.getElement();
        Node oldCursor = cursor;    //ponteiro auxiliar chamado de old cursor aponta para old cursor 
        for (advance(); oldCursor != cursor; advance()) {
            s += ", " + cursor.getElement();
        }
        return s + "...]";
    }
    
    //Metodo que conta a quantidade de nodos existentes em determinado momento do programa em uma dad lista encadeada circular
    public static int qtdNodos(CircleList L){
        Node oldCursor = L.cursor.getNext();
        int qtdNodos = 1;
        
        //Lista vazia
        if(L.cursor == null){
            return 0;
        }
        
        while(oldCursor != L.cursor){
            qtdNodos++;
            oldCursor = oldCursor.getNext();
        }
        return qtdNodos;
    }
    
    public void  imprimirLista(){
        Node auxCursor = this.cursor.getNext();
        while(auxCursor != this.cursor){
            System.out.println(auxCursor.getElement() + "--->");
            auxCursor = auxCursor.getNext();
        }
        
    }
    
    public static boolean isEqualContents(CircleList listL, CircleList listM){
       if(listL.size != listM.size){
           return false;
       }
       
       if(listL.getCursor() == null && listM.getCursor() == null){
           return true;
       }
        
       Node nodeL = listL.getCursor();
       Node nodeM = listM.getCursor();
        for(int i = 0; i < listL.size; i++){    
            if(!nodeL.getElement().equals(nodeM.getElement())){
           nodeM = nodeM.getNext();
            }
        }
    
       
       for(int i = 0; i < listM.size(); i++){
           nodeM = nodeM.getNext();
           nodeL = nodeL.getNext();
           if(!nodeL.getElement().equals(nodeM.getElement())){
               return false;
           }
       }
       
       return true;
    }
    
    public static void main(String [] args){
        CircleList listacircular1 = new CircleList();
        CircleList listacircular2 = new CircleList();
        
        Node n1 = new Node("BOB", null);
        Node n2 = new Node("GEORGE", null);
        Node n3 = new Node("ABIGAIL", null);
        Node n4 = new Node("JOE", null);
        int qtdNodos = 0;
        
        
        //adiciona os nodos na lista circular
        listacircular1.add(n3);
        listacircular1.add(n4);
        listacircular1.add(n1);
        listacircular1.add(n2);
 
        //imprimi os elementos da lista circular 
        System.out.println("Lista Circular 1:");
        System.out.println(listacircular1.toString());
       
        
        //adiciona os nodos na listacircular2
        listacircular2.add(n1);
        listacircular2.add(n2);
        listacircular2.add(n3);
        listacircular2.add(n4);
        
        //imprime os elementos da listacircular2
        System.out.println("Lista circular 2:");
        System.out.println(listacircular2.toString());
        
        
        if(isEqualContents(listacircular1,listacircular2)){
            System.out.println("Lista 1 e lista 2 sao iguais!");
        }else{
            System.out.println("Lista 1 e lista 2 sao diferentes!!!");
        }

    }            
}