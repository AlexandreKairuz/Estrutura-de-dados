import java.util.Scanner;

public class linkedList {
    public Node head; 
    public int size;

    //Construtor default
    public linkedList(){
        head = new Node();    //head é instânciada com com parametro nulo para ponteiro next
        head.setNext(null);
        int size = 0;
    }

    /**
     * Métodos de operação na lista simplesmente encadeada
     */
    
    
    /**
     * Adiciona um novo elemento na cabeça da lista simplesmente encadeada
     * @param newNode 
     */
    public void addFirst(Node newNode){
        newNode.setNext(head.getNext());
        head.setNext(newNode);
        size++;
    }
    
    
    /**
     * Adiciona um novo elemento no final(na cauda) de uma lista simplesmente encadeda
     * @param newNode 
     */
    public void addFinal(Node newNode){
        Node aux = head;
        while(aux.getNext() != null){
            aux = aux.getNext();
        }
        
        newNode.setNext(aux.getNext());
        aux.setNext(newNode);
        size++;
    }
    
    /**
     * Remove o primeiro elemento de uma lista simplesmente encadeada
     * @return 
     */
    public Node removeFirst(){
        Node primeiro = this.head.getNext();
        Node novoPrimeiro = primeiro.getNext();
        this.head.setNext(novoPrimeiro);
        primeiro.setNext(null);
        return primeiro;
    }
    
    /**
     * Remove um elemento da lista simplesmente encadeada pelo nome, no caso em que a classe Node possui uma variavel de instancia do tipo String
     */
    /*
    public Node removeByName(String nome){
        Node corrente = this.head.getNext();
        Node anterior = this.head;
        while(corrente.getNext() != null && !corrente.getElement().equals(nome)){
            corrente = corrente.getNext();
            anterior = anterior.getNext();
        }
        
        if(corrente.getElement().equals(nome)){
            anterior.setNext(corrente.getNext());
            corrente.setNext(null);
            return corrente;
        }else{
            return null;
        }   
    }
    */
    
    /**
     * Inseri um novo nodo de maneira ordenada na lista, no caso em que a classe Node possui uma variavel de instancia do tipo String
     */
    /*
    public static void inserirOrdenado(linkedList L, Node newNode){
        if(L.head.getNext() == null){
           L.head.setNext(newNode);
           newNode.setNext(null);
           L.size++;
           
        }else{
            Node aux = L.head;
            while(aux.getNext() != null && newNode.getElement().compareTo(aux.getNext().getElement()) > 0 ){
                aux = aux.getNext();
            }
        
            //achou
            //Lincou
            newNode.setNext(aux.getNext());
            aux.setNext(newNode);
            L.size++;
        }
    }
    */
    
    /**
     * Ordena a lista em ordem crescente de numeros inteiros, ponto flutuante e double
     */
    public void ordenarLista(){
        //aagora como estou ordenando uma lista simplesmente encadeada devo verificar se ela ja tem pelo menos 1 elemento ou nao
        if(this.size > 1){
            Node auxHead = new Node();   //Nova cabeça
            Node curNode = this.head;  //Ponteiro que salva o nodo corrente
            //Percorre a lista desordenada
            while(curNode != null){
                Node proxx = curNode.getNext(); //Ponteiro que salva o endereço de memóia do elemento prox de cur antes do elemento cur ser retirado da memória   
                Node prevNode = auxHead;  //Ponteiro que salva a referencia do objeto anterior ao cur
                //percorre a lista ordenada procurando a posicao correta do elemento curnode na lista a se formar ordenada
                while(prevNode.getNext() != null &&  curNode.getElement() > prevNode.getNext().getElement() ){
                    prevNode = prevNode.getNext();
                }    
                curNode.setNext(prevNode.getNext());
                prevNode.setNext(curNode);
                curNode = proxx;
            }
            this.head = auxHead;
        }
    }
    
    /**
     * Método recurssivo que conta o número de nodos de uma lista simplesmente encadeda
     * @param L
     * @param aux
     * @param n
     * @return 
     */
    public static int contarNumeroNodos(linkedList L, Node aux, int n){
        if(aux == null){
            return n;
        }
        n += 1;
        return contarNumeroNodos(L, aux.getNext(), n);
    }
    
    /**
     * Metodo iterativo que inverte uma lista simplesmente encadeada
     * @param tamanhoLista
     * @return 
     */
    public linkedList inverteListaSemRecurssao(int tamanhoLista){
        linkedList listaInvertida = new linkedList();
        for(int i = 0; i < tamanhoLista; i ++){
            Node temporario = this.removeFirst();
            listaInvertida.addFirst(temporario);
        }
        
        return listaInvertida;
    }
    /**
     * Método recurssivo que inverte uma lista simplesmente encadeada
     * @param L
     * @param Li
     * @param tamanhoLista
     * @param contador
     * @return 
     */
    public static linkedList inverterListaRecurssivo(linkedList L, linkedList Li, int tamanhoLista, int contador){
        //Caso base 
        if(contador == tamanhoLista){
            return Li;
        }
        
        Node temporario = L.removeFirst();
        Li.addFirst(temporario);
        
        //Caso Recursivo
        
        return inverterListaRecurssivo(L, Li, tamanhoLista, (contador + 1));
    }
    
    /**
     * Metodo que concatena 2 listas simplesmente encadeadas L e M sem usar uma subrrotina recurssiva
     * @param L
     * @param M
     * @return 
     */
    public static linkedList concatenarListas1(linkedList L, linkedList M){
        Node aux = L.head.getNext();
        
        //Procura o ultimo nodo da lista L
        while(aux.getNext() != null){
            aux = aux.getNext();
        }
        
        //Concatena as listas L e M
        aux.setNext(M.head.getNext());
        
        return L;
    }
    
    /**
     * Subrrotina usadada para procurarar o ultimo nodo de uma lista simplesmente encadeada
     * @param L
     * @param aux
     * @return 
     */
    public static Node procurarUltimoNodoRecursivo(linkedList L,Node aux){
        //Caso base do problema recursivo
        if(aux.getNext() == null){
            return aux;
        }
        
        //Caso recurssivo(chamada para resolver a proxima instancia do problema de carater recursivo)
        return procurarUltimoNodoRecursivo(L, aux.getNext());
    }
    
    /**
     * Método que contatena 2 listas simplesmente encadeadas L e M usando uma subrrotina recurssiva
     * @param L
     * @param M
     * @return 
     */
    public static linkedList concatenarListas2(linkedList L, linkedList M){
        Node ultimo = procurarUltimoNodoRecursivo(L, L.head.getNext());
        
        //concatena as listas L e M
        ultimo.setNext(M.head.getNext());
        return L;
    }
    
    /**
     * Método que imprimie os elementos de uma lista simplesmente encadeada
     */
    public void imprimirLista(){
        Node aux = this.head.getNext();
        System.out.print("[");
        while(aux != null){
            System.out.print(aux.getElement());
            if(aux.getNext() != null){
                System.out.print(",");
            }
            aux = aux.getNext();
        }
        System.out.print("]");
        System.out.println(" ");
    }
}
