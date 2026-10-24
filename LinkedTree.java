
import java.util.Iterator;

//begin#fragment Tree
/**
 * A linked class for a tree where nodes have an arbitrary number of children.
 * //end#fragment Tree
 *
 * @author Luca Vismara, Roberto Tamassia, Michael Goodrich, Eric Zamore
 * //begin#fragment Tree
 */
public class LinkedTree<E> implements Tree<E> {

    protected TreePosition<E> root; // reference to the root
    protected int size;		  // number of nodes

    /**
     * Creates an empty tree.
     */
    public LinkedTree() {
        root = null;  // start with an empty tree
        size = 0;
    }

    /**
     * Returns the number of nodes in the tree.
     */
    public int size() {
        return size;
    }
//end#fragment LinkedTree

    /**
     * Returns whether the tree is empty.
     */
    public boolean isEmpty() {
        return (size == 0);
    }
//begin#fragment LinkedTree

    /**
     * Returns whether a node is internal.
     */
    public boolean isInternal(Position<E> v) throws IllegalArgumentException {
        return !isExternal(v);
    }
//end#fragment LinkedTree

    /**
     * Returns whether a node is external.
     */
    public boolean isExternal(Position<E> v) throws IllegalArgumentException {
        TreePosition<E> vv = checkPosition(v);	// auxiliary method
        return (vv.getChildren() == null) || vv.getChildren().isEmpty();
    }
//begin#fragment LinkedTree

    /**
     * Returns whether a node is the root.
     */
    public boolean isRoot(Position<E> v) throws IllegalArgumentException {
        checkPosition(v);
        return (v == root());
    }
//begin#fragment LinkedTree

    /**
     * Returns the root of the tree.
     */
    public Position<E> root() throws IllegalArgumentException {
        if (root == null) {
            throw new IllegalArgumentException("The tree is empty");
        }
        return root;
    }
//end#fragment LinkedTree
//begin#fragment LinkedTree2

    /**
     * Returns the parent of a node.
     */
    public Position<E> parent(Position<E> v)
            throws IllegalArgumentException {
        TreePosition<E> vv = checkPosition(v);
        Position<E> parentPos = vv.getParent();
        if (parentPos == null) {
            throw new IllegalArgumentException("No parent");
        }
        return parentPos;
    }

    /**
     * Returns an iterable collection of the children of a node.
     */
    public Iterable<Position<E>> children(Position<E> v)
            throws IllegalArgumentException {
        TreePosition<E> vv = checkPosition(v);
        if (isExternal(v)) {
            //throw new IllegalArgumentException("External nodes have no children");
            return new NodePositionList<Position<E>>();
        }
        return vv.getChildren();    //retorna um node position list
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
            throws IllegalArgumentException {
        TreePosition<E> vv = checkPosition(v);
        E temp = v.getElement();
        vv.setElement(o);
        return temp;
    }
//end#fragment LinkedTree2
//begin#fragment LinkedTree3
    // Additional update methods

    /**
     * Adds a root node to an empty tree
     */
    public TreePosition<E> addRoot(E e) throws IllegalArgumentException {
        if (!isEmpty()) {
            throw new IllegalArgumentException("Tree already has a root");
        }
        size = 1;
        root = createNode(e, null, null);
        return root;
    }

    /**
     * Swap the elements at two nodes
     */
    public void swapElements(Position<E> v, Position<E> w)
            throws IllegalArgumentException {
        TreePosition<E> vv = checkPosition(v);
        TreePosition<E> ww = checkPosition(w);
        E temp = w.getElement();
        ww.setElement(v.getElement());
        vv.setElement(temp);
    }
    // Auxiliary methods
//begin#fragment LinkedTree5

    /**
     * If v is a good tree node, cast to TreePosition, else throw exception
     */
    protected TreePosition<E> checkPosition(Position<E> v)
            throws IllegalArgumentException {
        if (v == null || !(v instanceof TreePosition)) {
            throw new IllegalArgumentException("The position is invalid");
        }
        return (TreePosition<E>) v;
    }

    /**
     * Creates a new tree node
     */
    protected TreePosition<E> createNode(E element, TreePosition<E> parent,
            PositionList<Position<E>> children) {
        return new TreeNode<E>  (element, parent, children);
    }

    /**
     * Creates a list storing the the nodes in the subtree of a node, ordered
     * according to the preorder traversal of the subtree.
     */
    protected void preorderPositions(Position<E> v, PositionList<Position<E>> pos)
            throws IllegalArgumentException {
        System.out.println(v.getElement());
        pos.addLast(v);
        if (children(v) != null) {
            for (Position<E> w : children(v)) {
                preorderPositions(w, pos);	// recurse on each child
            }
        }
    }
    
    //algoritimo recursivo que computa a profundidade de uma nodo v de uma arvore T
    public int depht(TreePosition<E> v){
        //Pela definição de profundidade, temos o caso base
        if(this.isRoot(v)){
            return 0;
        }
        
        //caso recursivo    pela definição, a profundidade de um nodo é definida como 1 + mais a profundidade de seu ancestral mais próximo(nodo pai de v)
        return 1 + depht(v.getParent());
    }
    
    //algoritimo recursivo que computa a altura de um dado nodo v em uma arvore T
    public  int hight(TreePosition v){
        int h = 0;
        //caso base
        if(this.isExternal(v)){
            return 0;
        }
        
        h = Math.max(h, depht(v));
        return h;
    }
    
    
    public static <E> String toStringPreOrder(Tree<E> T, Position <E> v){
        String s = v.getElement().toString();
        if(T.children(v) != null);
            for(Position<E> w: T.children(v)){  //T.children(v) retorna um iterable (iteravel) onde cada iteravel é um objeto da classe Position<E>
                s += "," + "\n" + toStringPreOrder(T,w);
            }
            return s;
        }
    
    //Pre order -> a visita no nodo acontece antes da recursao
    public static <E> String parentheticRepresentation (Tree<E> t,Position<E> v){
        String s = v.getElement().toString();
        if(t.isInternal(v)){
            Boolean firstTime = true;   //flag falando que e a primeira vez que o nodo e percorrido
            for(Position<E> w: t.children(v)){
                if(firstTime){
                    s += "(" + "\n"  +  parentheticRepresentation(t,w);
                    firstTime = false;  //flag dizendo que o nodo ja foi percorrido uma vez, flag necessaria para  a as chamadas da volta da recursao
                }else{
                    s += "," + parentheticRepresentation(t,w);
                }
            }
            s += ") " + "\n";
        }
        return s;
    }
    
    public static <E> String toStringPosOrder(Tree<E> T, Position<E> v){
        String s = " ";
        for(Position<E> w: T.children(v)){
            s += toStringPosOrder(T, w) + "\n ";       //Percorre recurssivamente as subarvores de V
        }
        s += v.getElement();    //Acao principal de visitacao
        return s;
    }
    
   
    public static void main(String [] args){
        //Declara uma variavel da classe Linked tree
        LinkedTree firstTree = new LinkedTree();
        TreePosition<String> raiz =  firstTree.addRoot("Vendas");      
        
        //Criando os filhos da raiz(no 0), a raiz da arvore e o nodo cujo nao tem pai, o ponteiro parent do nodo raiz aponta para null
        TreePosition<String> nacional = new TreeNode("Nacional", raiz, null);
        TreePosition<String> internacional = new TreeNode("Internacional", raiz, null);
        
        //cria o container(Lista duplamente/simplesmente) encadeada dos filhos da raiz primeira geracao de filhos que e um objeto da classe NodePositionList
        NodePositionList c1 = new NodePositionList();
        c1.addFirst(internacional);  //nacional -> passa o endereco de memoria para nacional
        c1.addFirst(nacional);     //internacional -> endereco de memoria internacional
        //nenhuma passagem  de parametro em java é por copia EM JAVA todas as passagens por parametro são por referencia ou seja quando passa o parametro e altera alguma coisa lá a alteração acontece direto no endereço de memoria passado como parametro
        //passando por referencia o valor da memoria é alterado direto na memoria no local de origem não precisa reotrnar valor de retorno para alterar
        
        raiz.setChildren(c1);
      
        //Cria os nodos filho do nodo internacional
        TreePosition<String> canada = new TreeNode("Canada", internacional, null);
        TreePosition<String> americaDoSul = new TreeNode("America do sul", internacional, null);
        TreePosition<String> ultramar = new TreeNode("Ultramar", internacional, null);
        
        //cria a lista duplamente encadeada dos nodos filhos do nodo internalcinal
        PositionList c2 = new NodePositionList();
        c2.addFirst(ultramar);
        c2.addFirst(americaDoSul);
        c2.addFirst(canada);
        
        //Seta o container de filhos do nodo internacional
        internacional.setChildren(c2);
        
        //Cria os filhos do nodo ultramar
        TreePosition<String> africa = new TreeNode("Africa", ultramar, null);
        TreePosition<String> europa = new TreeNode("Europa",ultramar , null);
        TreePosition<String> asia = new TreeNode("Asia", ultramar, null);
        TreePosition<String> australia = new TreeNode("Australia", ultramar, null);
       
        //Cria o container de filhos do nodo ultramar
        PositionList c3 = new NodePositionList();
        c3.addFirst(australia);
        c3.addFirst(asia);
        c3.addFirst(europa);
        c3.addFirst(africa);
        
        //seta o container de filhos do nodo ultramar
        System.out.println("Caminhamento Pre Fixado:");
        ultramar.setChildren(c3);
        String preFixado = toStringPreOrder(firstTree, raiz);
        System.out.println(preFixado);
        System.out.println("-----------------------------------------");
        System.out.println("Caminhamento Pos Fixado:");
        String posFixado = toStringPosOrder(firstTree, raiz);
        System.out.println(posFixado);
        System.out.println("-----------------------------------------");
        String parenteses = parentheticRepresentation(firstTree,raiz);
        System.out.println("Representacao com parenteses:");
        System.out.println(parenteses);
        System.out.println("-----------------------------------------");
    }  
    
   
}
    
       
    

       

