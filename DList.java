public class DList {
    
    protected int size; // quantidade de elementos
    protected DNode header, tail; //apontadores para o inicio e fim da lista

    /**
     * Construtor que cria uma lista vazia
     */
    public DList() {
        size = 0;
        header = new DNode(null, null, null); // cria o cabecalho 
        tail = new DNode(null, header, null); //cria o final element, previus, next   
        header.setNext(tail); // faz o cabecalho e o final apontarem um para o outro
    }

    /**
     * Retorna o número de elementos na lista
     */
    public int size() {
        return size;
    }

    /**
     * Informa se a lista está vazia
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Retorna o primeiro nodo da lista
     */
    public DNode getFirst() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return header.getNext();     //Primeiro nodo efetivo da lista (primeiro nodo de conteudo da lista)
    }

    /**
     * Retorna o último nodo da lista
     */
    public DNode getLast() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return tail.getPrev();
    }

    /**
     * Retorna o nodo que antecede um dado nodo v. Gera erro se v é o cabecalho
     */
    public DNode getPrev(DNode v) throws IllegalArgumentException {
        if (v == header) {
            throw new IllegalArgumentException("Cannot move back past the header of the list");
        }
        return v.getPrev();
    }

    /**
     * Retorna o nodo que segue um dado nodo v. Gera erro se v é o final
     */
    public DNode getNext(DNode v) throws IllegalArgumentException {
        if (v == tail) {
            throw new IllegalArgumentException("Cannot move forward past the trailer of the list");
        }
        return v.getNext();
    }

    /**
     * Insere um dado nodo z antes de um dado nodo v. Gera um erro se v é o
     * 
     */
    public void addBefore(DNode v, DNode z) throws IllegalArgumentException {
        DNode u = getPrev(v); // Deve lancar uma IllegalArgumentException
        z.setPrev(u);
        z.setNext(v);
        v.setPrev(z);
        u.setNext(z);
        size++;
    }

    /**
     * Insere um dado nodo z depois de uma dado nodo v. Gera um erro se v é o
     * final
     */
    public void addAfter(DNode v, DNode z) {
        DNode w = v.getNext(); // Deve lançar uma IllegalArgumentException //o ponteiro w aponta para o elemento da lista proximo de v
        z.setPrev(v);
        z.setNext(w);
        w.setPrev(z);
        v.setNext(z);
        size++;
    }

    /**
     * Insere o nodo fornecido no início da lista
     */
    public void addFirst(DNode v) {
        addAfter(header, v);
    }

    /**
     * Insere o nodo fornecido no fim da lista
     */
    public void addLast(DNode v) {
        addBefore(tail, v);
    }

    /**
     * Remove um dado nodo v da lista. Gera um erro se v é o cabeçalho ou o
     * final
     */
    public void remove(DNode v) {
        DNode u = getPrev(v); // Deve lançar uma IllegalArgumentException 
        DNode w = getNext(v); // Deve lançar uma IllegalArgumentException 

        // Desconecta o nodo da lista 
        w.setPrev(u);
        u.setNext(w);
        v.setPrev(null);
        v.setNext(null);
        size--;
    }

    /**
     * Indica se o nodo indicado possui um antecessor
     */
    public boolean hasPrev(DNode v) {
        return v != header;
    }

    /**
     * Indica se o nodo indicado possui um sucessor
     */
    public boolean hasNext(DNode v) {
        return v != tail;
    }

    /**
     * Retorna uma representação string da lista
     */
    public String toString() {
        String s = "[";
        DNode v = header.getNext();
        while (v != tail) {
            s += v.getElement();
            v = v.getNext();
            if (v != tail) {
                s += ",";
            }
        }
        s += "]";
        return s;
    }
    /**
     * Imprime os elementos da lista duplamente encadeada recebida como parâmetro
     * @param L 
     */
    public static void imprimirLista(DList L){
        DNode ponteiroAux = L.getFirst();
        System.out.print("[");
        while(ponteiroAux != L.tail){
            System.out.print(ponteiroAux.getElement());
            if(ponteiroAux.getNext() != L.tail){
                System.out.print(",");
            }
            ponteiroAux = ponteiroAux.getNext();
        }
        System.out.println("]");
    }
    /**
     * Imprime os elementos da lista duplamente encadeada recebida como parâmetro de trás para frente
     * @param L 
     */
    public static void imprimirListaAoContrario(DList L){
        DNode ponteiroAuxiliar = L.getLast();
        System.out.print("[");
        while(ponteiroAuxiliar != L.header){
            System.out.print(ponteiroAuxiliar.getElement());
            if(ponteiroAuxiliar.getPrev() != L.header){
                System.out.print(",");
            }
            ponteiroAuxiliar = ponteiroAuxiliar.getPrev();
        }
        System.out.println("]");
    }
    
    /**
     * Concatena duas listas duplamente encadeadas L e M
     * @param L
     * @param M
     * @return 
     */
    public static DList concatena(DList L, DList M){   //L lista principal , M lista a ser concatenada
        DList K = new DList();
        DNode Z = M.header.getNext();
        DNode W = L.tail.getPrev();
        
        W.setNext(Z);
        Z.setPrev(W);
        L.tail.setPrev(null);
        M.header.setNext(null);
        K.header = L.header;
        K.tail = M.tail;
        
        return K;
    }
    
    /**
     * Algoritimo de ordenação por inserção aplicada a uma lista duplamente encadeda
     * @param L 
     */
    public static void insertionSort(DList L){
        DNode nodoCorrente;
        DNode ponteiroPosicaoCorreta;
        DNode ponteiroEnd = L.getFirst();
        while(ponteiroEnd != L.getLast()){
            nodoCorrente = ponteiroEnd.getNext();
            L.remove(nodoCorrente);
            ponteiroPosicaoCorreta = ponteiroEnd;
            
            //Se percorre com ponteiros as listas encadeadas nao com variaveis inteiras auxiliares mas sim com ponteiros do tipo de dado do nodo enfim para percorrer as listas encadeadas
            //procura a posicao correta do nodo corrente na sublista duplamente encadeada anterior ao nodo corrente antes de sua remocao a lista e uma estrutura dinamica quando se remove um nodo de uma lista encadeada seu tamanho diminui em 1
            while(L.hasPrev(ponteiroPosicaoCorreta) && (int) nodoCorrente.getElement() < (int) ponteiroPosicaoCorreta.getElement()){
                ponteiroPosicaoCorreta = ponteiroPosicaoCorreta.getPrev();
            }
            
            //Acgou a posicao correta do nodo corrente insere o na frente do ponteiroPosicaoCorreta pois e a posicao correta do nodo e nao se insere o nodo corrente atras do ponteiro auxiliar head no caso em que o nodo corrente armazena o menor elemento
            L.addAfter(ponteiroPosicaoCorreta, nodoCorrente);
            
            //Caso em que o elemento ja esta na posicao correta
            if(ponteiroPosicaoCorreta == ponteiroEnd){
                ponteiroEnd = ponteiroEnd.getNext();
            }        
        }
        
    }
   
}
