
import java.util.Iterator;
import java.util.Scanner;

//begin#fragment LinkedBinaryTree
/**
 * An implementation of the BinaryTree interface by means of a linked structure.
 * //end#fragment LinkedBinaryTree This class serves as a superclass for the
 * BinarySearchTree implementation. This design decision was made to emphasize
 * the conceptual relationship that a BinarySearchTree is a specialized
 * LinkedBinaryTree. An unwanted side-effect of this is that the
 * {@link #size() size} method returns the number of total nodes whereas the
 * {@link BinarySearchTree#size() size} method in the
 * {@link BinarySearchTree BinarySearchTree} class returns the number of
 * internal nodes only. For this reason, the the {@link #size
 * size} variable instead of the {@link #size() size} method is used within this
 * class.
 *
 * @author Luca Vismara, Roberto Tamassia, Michael Goodrich, Eric Zamore
 * @see BinaryTree //begin#fragment LinkedBinaryTree
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {

protected BTPosition<E> root;	// reference to the root
    protected int size;		// number of nodes

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
        root = null;  // start with an empty tree
        size = 0;
    }

    /**
     * Returns the number of nodes in the tree.
     */
    public int size() {
        return size;
    }
//end#fragment LinkedBinaryTree

    /**
     * Returns whether the tree is empty.
     */
    public boolean isEmpty() {
        return (size == 0);
    }
//begin#fragment LinkedBinaryTree

    /**
     * Returns whether a node is internal.
     */
    public boolean isInternal(Position<E> v) throws IllegalStateException {
        checkPosition(v);		// auxiliary method
        return (hasLeft(v) || hasRight(v));
    }
//end#fragment LinkedBinaryTree

    /**
     * Returns whether a node is external.
     */
    public boolean isExternal(Position<E> v) throws IllegalStateException {
        return !isInternal(v);
    }
//begin#fragment LinkedBinaryTree

    /**
     * Returns whether a node is the root.
     */
    public boolean isRoot(Position<E> v) throws IllegalStateException {
        checkPosition(v);
        return (v == root());
    }

    /**
     * Returns whether a node has a left child.
     */
    public boolean hasLeft(Position<E> v) throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        return (vv.getLeft() != null);
    }
//end#fragment LinkedBinaryTree

    /**
     * Returns whether a node has a right child.
     */
    public boolean hasRight(Position<E> v) throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        return (vv.getRight() != null);
    }
//begin#fragment LinkedBinaryTree

    /**
     * Returns the root of the tree.
     */
    public Position<E> root() throws IllegalStateException {
        if (root == null) {
            throw new IllegalStateException("The tree is empty");
        }
        return root;
    }

    /**
     * Returns the left child of a node.
     */
    public Position<E> left(Position<E> v)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> leftPos = vv.getLeft();
        if (leftPos == null) {
            throw new IllegalStateException("No left child");
        }
        return leftPos;
    }
//end#fragment LinkedBinaryTree

    /**
     * Returns the right child of a node.
     */
    public Position<E> right(Position<E> v)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> rightPos = vv.getRight();
        if (rightPos == null) {
            throw new IllegalStateException("No right child");
        }
        return rightPos;
    }
//begin#fragment LinkedBinaryTree2

    /**
     * Returns the parent of a node.
     */
    public Position<E> parent(Position<E> v)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> parentPos = vv.getParent();
        if (parentPos == null) {
            throw new IllegalStateException("No parent");
        }
        return parentPos;
    }

    /**
     * Returns an iterable collection of the children of a node.
     */
    public Iterable<Position<E>> children(Position<E> v)
            throws IllegalStateException {
        PositionList<Position<E>> children = new NodePositionList<Position<E>>();
        if (hasLeft(v)) {
            children.addLast(left(v));
        }
        if (hasRight(v)) {
            children.addLast(right(v));
        }
        return children;
    }

    /**
     * Returns an iterable collection of the tree nodes.
     */
    public Iterable<Position<E>> positions() {
        PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
        if (size != 0) {
            preorderPositions(root(), positions);  // assign positions in preorder
        }
        return positions;
    }

    /**
     * Returns an iterator of the elements stored at the nodes
     */
    public Iterator<E> iterator() {
        Iterable<Position<E>> positions = positions();
        PositionList<E> elements = new NodePositionList<E>();
        for (Position<E> pos : positions) {
            elements.addLast(pos.getElement());
        }
        return elements.iterator();  // An iterator of elements
    }

    /**
     * Replaces the element at a node.
     */
    public E replace(Position<E> v, E o)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        E temp = v.getElement();
        vv.setElement(o);
        return temp;
    }
//end#fragment LinkedBinaryTree2
//begin#fragment LinkedBinaryTree3
    // Additional accessor method

    /**
     * Return the sibling of a node
     */
    public Position<E> sibling(Position<E> v)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        BTPosition<E> parentPos = vv.getParent();
        if (parentPos != null) {
            BTPosition<E> sibPos;
            BTPosition<E> leftPos = parentPos.getLeft();
            if (leftPos == vv) {
                sibPos = parentPos.getRight();
            } else {
                sibPos = parentPos.getLeft();
            }
            if (sibPos != null) {
                return sibPos;
            }
        }
        throw new IllegalStateException("No sibling");
    }
    // Additional update methods

    /**
     * Adds a root node to an empty tree
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) {
            throw new IllegalStateException("Tree already has a root");
        }
        size = 1;
        root = createNode(e, null, null, null);
        return root;
    }

    /**
     * Inserts a left child at a given node.
     */
    public Position<E> insertLeft(Position<E> v, E e)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> leftPos = vv.getLeft();
        
        //Verifica se ja existe um filho da direita no nodo v
        if (leftPos != null) {
            throw new IllegalStateException("Node already has a left child");
        }
        BTPosition<E> ww = createNode(e, vv, null, null);
        vv.setLeft(ww);
        size++;
        return ww;
    }
//end#fragment LinkedBinaryTree3

    /**
     * Inserts a right child at a given node.
     */
    public Position<E> insertRight(Position<E> v, E e)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> rightPos = vv.getRight();
        
        //Verifica se ja existe um filho da direita do nodo v
        if (rightPos != null) {
            throw new IllegalStateException("Node already has a right child");
        }
        BTPosition<E> w = createNode(e, vv, null, null);
        vv.setRight(w);
        size++;
        return w;
    }
//begin#fragment LinkedBinaryTree4

    /**
     * Removes a node with zero or one child.
     */
    public E remove(Position<E> v)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        BTPosition<E> leftPos = vv.getLeft();
        BTPosition<E> rightPos = vv.getRight();
        if (leftPos != null && rightPos != null) {
            throw new IllegalStateException("Cannot remove node with two children");
        }
        BTPosition<E> ww; 	// the only child of v, if any
        if (leftPos != null) {
            ww = leftPos;
        } else if (rightPos != null) {
            ww = rightPos;
        } else // v is a leaf
        {
            ww = null;
        }
        if (vv == root) { 	// v is the root
            if (ww != null) {
                ww.setParent(null);
            }
            root = ww;
        } else { 		// v is not the root
            BTPosition<E> uu = vv.getParent();
            if (vv == uu.getLeft()) {
                uu.setLeft(ww);
            } else {
                uu.setRight(ww);
            }
            if (ww != null) {
                ww.setParent(uu);
            }
        }
        size--;
        return v.getElement();
    }

//end#fragment LinkedBinaryTree4
//begin#fragment LinkedBinaryTree5
    /**
     * Attaches two trees to be subtrees of an external node.
     */
    public void attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        if (isInternal(v)) {
            throw new IllegalStateException("Cannot attach from internal node");
        }
        int newSize = size + T1.size() + T2.size();
        if (!T1.isEmpty()) {
            BTPosition<E> r1 = checkPosition(T1.root());
            vv.setLeft(r1);
            r1.setParent(vv);		// T1 should be invalidated
        }
        if (!T2.isEmpty()) {
            BTPosition<E> r2 = checkPosition(T2.root());
            vv.setRight(r2);
            r2.setParent(vv);		// T2 should be invalidated
        }
        size = newSize;
    }
//end#fragment LinkedBinaryTree5

    /**
     * Swap the elements at two nodes
     */
    public void swapElements(Position<E> v, Position<E> w)
            throws IllegalStateException {
        BTPosition<E> vv = checkPosition(v);
        BTPosition<E> ww = checkPosition(w);
        E temp = w.getElement();
        ww.setElement(v.getElement());
        vv.setElement(temp);
    }

    /**
     * Expand an external node into an internal node with two external node
     * children
     */
    public void expandExternal(Position<E> v, E l, E r)
            throws IllegalStateException {
        if (!isExternal(v)) {
            throw new IllegalStateException("Node is not external");
        }
        insertLeft(v, l);
        insertRight(v, r);
    }

    /**
     * Remove an external node v and replace its parent with v's sibling
     */
    public void removeAboveExternal(Position<E> v)
            throws IllegalStateException {
        if (!isExternal(v)) {
            throw new IllegalStateException("Node is not external");
        }
        if (isRoot(v)) {
            remove(v);
        } else {
            Position<E> u = parent(v);
            remove(v);
            remove(u);
        }
    }
    // Auxiliary methods
//begin#fragment LinkedBinaryTree5

    /**
     * If v is a good binary tree node, cast to BTPosition, else throw exception
     */
    protected BTPosition<E> checkPosition(Position<E> v)
            throws IllegalStateException {
        if (v == null || !(v instanceof BTPosition)) {
            throw new IllegalStateException("The position is invalid");
        }
        return (BTPosition<E>) v;
    }

    /**
     * Creates a new binary tree node
     */
    protected BTPosition<E> createNode(E element, BTPosition<E> parent,
            BTPosition<E> left, BTPosition<E> right) {
        return new BTNode<E>(element, parent, left, right);
    }

    /**
     * Creates a list storing the the nodes in the subtree of a node, ordered
     * according to the preorder traversal of the subtree.
     */
    protected void preorderPositions(Position<E> v, PositionList<Position<E>> pos)
            throws IllegalStateException {
        
        System.out.println(v.getElement() + "(  ");
        
        pos.addLast(v);
        if (hasLeft(v)) {
            preorderPositions(left(v), pos);	// recurse on left child
        }
        if (hasRight(v)) {
            preorderPositions(right(v), pos);	// recurse on right child
        }
    }
//end#fragment LinkedBinaryTree5

    /**
     * Creates a list storing the the nodes in the subtree of a node, ordered
     * according to the inorder traversal of the subtree.
     */
    protected void inorderPositions(Position<E> v, PositionList<Position<E>> pos)
            throws IllegalStateException {
        if (hasLeft(v)) {
            inorderPositions(left(v), pos);  // recurse on left child
        }
        pos.addLast(v);
        if (hasRight(v)) {
            inorderPositions(right(v), pos); // recurse on right child
        }
    }
    
    public static void toStringPreOrder(LinkedBinaryTree T,BTPosition v){
        System.out.println(v.getElement());
        if(T.hasLeft(v)){
            T.toStringPreOrder(T,v.getLeft());
        }
        
        if(T.hasRight(v)){
            T.toStringPreOrder(T,v.getRight());
        }
        
        return;
    }
    
    public static void toStringPosOrder(LinkedBinaryTree T, BTPosition v){
        if(T.hasLeft(v)){
            toStringPosOrder(T, v.getLeft());
        }
        
        if(T.hasRight(v)){
            toStringPosOrder(T, v.getRight());
        }
        
        System.out.println((char) v.getElement());
    }
    
    public static void toStringInOrder(LinkedBinaryTree T, BTPosition v){
        if(T.hasLeft(v)){
            toStringInOrder(T,v.getLeft());
        }
        
        System.out.println(v.getElement());
        
        if(T.hasRight(v)){
            toStringInOrder(T, v.getRight());
        }
        
    }
    
        public static LinkedBinaryTree buildExpression(NodeStack E){
            NodeStack S = new NodeStack();  //Pilha nova de arvores vazia
            NodeStack operadores = new NodeStack(); //Pilha nova de operadores vazia
            NodeG aux = E.top;
            
            //perccore a pilha
            while(aux != null){
                Character c = (Character) aux.getElement();
                
                if(c >= '0' && c <= '9'){
                    LinkedBinaryTree T = new LinkedBinaryTree();
                    T.addRoot(c);
                    S.push(T);
                }
                
                if(c.equals('+') || c .equals('-') || c.equals('*') || c.equals('/')){
                    operadores.push(c);
                }
                
                if(c.equals('(')){
                    
                }
                
                if(c.equals(')')){
                    LinkedBinaryTree T1 = new LinkedBinaryTree();   //Arvore da direita
                    LinkedBinaryTree T2 = new LinkedBinaryTree();  //Arvore da esquerda
                    LinkedBinaryTree T = new LinkedBinaryTree();  //raiz da subarvore que armazena um operador e dois operandos 
                    
                    Character operador = (Character) operadores.pop();
                    T.addRoot(operador);
                    T1 = (LinkedBinaryTree) S.pop();
                    T2= (LinkedBinaryTree) S.pop();
                    T.attach(T.root(), T2, T1);
                    
                    S.push(T);
                    }
                
                    aux = aux.getNext();
            }
        return  (LinkedBinaryTree) S.pop();
    }
    
    public static void toStringEulerTour(LinkedBinaryTree T, BTNode v){
        System.out.println(v.getElement()); //Executa a acao previta para o nodo v quando encontrado pela esquerda
        if(T.hasLeft(v)){
            toStringEulerTour(T,(BTNode) v.getLeft());
        }
        
        System.out.println(v.getElement());//Executa a acao prevista para o nodo v vindo de baixo
        
        if(T.hasRight(v)){
            toStringEulerTour(T, (BTNode) v.getRight());
        }
        
        System.out.println(v.getElement()); // executa a acao prevista para o nodo v pela direita
    }
    
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
     
     
     public static int evaluateExpression(LinkedBinaryTree T, BTNode v){
        if(T.isInternal(v)){
            Character operacao = (Character) v.getElement();
            int op = 0;
            if(operacao.equals('+')){op = 1;}
            if(operacao.equals('-')){op = 2;}
            if(operacao.equals('*')){op = 3;}
            if(operacao.equals('/')){op = 4;}
            
            int x = evaluateExpression(T, (BTNode) v.getLeft());
            int y = evaluateExpression(T, (BTNode) v.getRight());
            
            if(op == 1){return x + y;}
            if(op == 2){return x - y;}
            if(op == 3){return x * y;}
            if(op == 4){return x / y;}
        }else{
            Character num = (Character) v.getElement();
            int numero = Character.getNumericValue(num);
            return numero;
        } 
        throw new IllegalArgumentException("Operador inválido");
     }
  
    public static NodeStack reverseStack(NodeStack L, int n){
        NodeStack e = new NodeStack();
        for(int i = 0; i < n; i++){
            e.push(L.pop());
        }
        return e;
    }
    
    
    /*Calcula recursivamente a profundidade de um nodo v de umar Arvore T
        se v e o nodo raiz entao
            retorna 0
        se nao
            retorna 1 + depth1(T, w) com w sendo o pai de v
    
    */
    public static <E> int depth1(LinkedBinaryTree T, BTNode v){
        //Caso base
        if(T.isRoot(v)){
            return 0;
        }
        
        //Caso Recursivo
        return 1 + depth1(T, (BTNode) T.parent(v));       
    }
    
    public static <E> int height1(LinkedBinaryTree T){
        int h = 0;
        for(Object v : T.positions()){
            if(T.isInternal((Position) v)){
                h = Math.max(h,depth1(T, (BTNode) v));
            }
        }
        return h;
    }
    
    //Metodo recursivo que conta/calcula/computa quantos nodos esquerdos externos existem na arvore t recebida como parametro/ como inpuit de dados
    public static int contarNodosEsquerdosExternos(LinkedBinaryTree T, BTNode v){
        int contagem = 0;
        
        //Verifica se o nodo e externo, se ele for externo nao tera filhos nem da esquerda nem da direita para contar entao retorna 0
        if(T.isExternal(v)){
            return 0;
        }
        
        //Verifica se o nodo v possui um nodo filho da esquerda
        if(T.hasLeft(v)){
            BTNode u = (BTNode) v.getLeft();
            if(T.isExternal(u)){
            //se o nodo filho da esquerda de v for externo entao  foi encontrado 1 nodo esquerdo esterno e se adiciona um na variavel contador
            contagem += 1;
            }else{  //Senao passa como parametro da funcao recursiva o nodo interno filho de v e faz a mesma verificacao novamente
                contagem += contarNodosEsquerdosExternos(T, u);
            }
        }    
            
        //Verifica se o nodo v possui um nodo filho da direita
        if(T.hasRight(v)){
            //se tiver atribui o nodo filho da direita na variavel w
            BTNode w = (BTNode) v.getRight();
            //e passa como parametro para a a funcao recuriscva contarNodosEsquerdosExternos para percorrer os nodos da subarvore da direta enraizada em root que pode conter nodos externos filhos da esquerda tambem
            contagem += contarNodosEsquerdosExternos(T, w);
        }
        
        return contagem;
    }
            
    public static void main(String [] args){
        
        Scanner s = new Scanner(System.in);
        NodeStack expressao = new NodeStack();
        Character flag = 'S';
        NodeStack reverseExpression = new NodeStack();
        do{
            System.out.println("Digite um caracter:");
            expressao.push(s.next().charAt(0));
            System.out.println("Outro caracter? [S/N]");
            flag = s.next().charAt(0);
        }while(flag == 's' || flag == 'S');
        
        reverseExpression = reverseStack(expressao, expressao.size);
    
        if(parenMatch(reverseExpression)){
            System.out.println("Expressão verdadeira");
            LinkedBinaryTree exp = buildExpression(reverseExpression);
            toStringInOrder(exp, (BTPosition) exp.root());
            System.out.print("Resultado da expressão:" + evaluateExpression(exp, (BTNode) exp.root()));
        }else{
            System.out.println("Expressão inválida");
        }

       
    }
}