package com.estructuras.datos.arboles;

import java.util.ArrayList;

 public class ArbolAvl<E extends Comparable>{
 
 protected NodoAvl root;  
 /**
  * Add a new element with data "k" into the tree.
  * 
  * @param pData
  *            The data of the new node.
  */
 public void insert(E pData) {
  // create new node
  NodoAvl n = new NodoAvl(pData);
  // start recursive procedure for inserting the node
  insertAVL(this.root,n);
 }
 
 /**
  * Recursive method to insert a node into a tree.
  * 
  * @param p The node currently compared, usually you start with the root.
  * @param q The node to be inserted.
  */
 public void insertAVL(NodoAvl p, NodoAvl q) {
  // If  node to compare is null, the node is inserted. If the root is null, it is the root of the tree.
  if(p==null) {
   this.root=q;
  } else {
   
   // If compare node is smaller, continue with the left node
   if(q.getData().compareTo(p.getData()) < 0) {
    if(p.left==null) {
     p.left = q;
     q.parent = p;
     
     // Node is inserted now, continue checking the factorDeEquilibrio
     recursiveBalance(p);
    } else {
     insertAVL(p.left,q);
    }
    
   } else if(q.getData().compareTo(p.getData()) > 0) {
    if(p.right==null) {
     p.right = q;
     q.parent = p;
     
     // Node is inserted now, continue checking the factorDeEquilibrio
     recursiveBalance(p);
    } else {
     insertAVL(p.right,q);
    }
   } else {
    // do nothing: This node already exists
   }
  }
 }
 
 /**
  * Check the factorDeEquilibrio for each node recursivly and call required methods for balancing the tree until the root is reached.
  * 
  * @param cur : The node to check the factorDeEquilibrio for, usually you start with the parent of a leaf.
  */
 public void recursiveBalance(NodoAvl cur) {
  
  // we do not use the factorDeEquilibrio in this class, but the store it anyway
  setBalance(cur);
  int balance = cur.factorDeEquilibrio;
  
  // check the factorDeEquilibrio
  if(balance==-2) {
   
   if(altura(cur.left.left)>=altura(cur.left.right)) {
    cur = rotarDerecha(cur);
   } else {
    cur = rotarDobleIzquierdaDerecha(cur);
   }
  } else if(balance==2) {
   if(altura(cur.right.right)>=altura(cur.right.left)) {
    cur = rotarIzquierda(cur);
   } else {
    cur = rotarDoblederechaIzquierda(cur);
   }
  }
  
  // we did not reach the root yet
  if(cur.parent!=null) {
   recursiveBalance(cur.parent);
  } else {
   this.root = cur;
  // System.out.println("------------ Balancing finished ----------------");
  }
 }

 /**
  * Removes a node from the tree, if it is existent.
  */
 public void remove(E k) {
  // First we must find the node, after this we can delete it.
  eliminar(this.root,k);
 }
 
 /**
  * Finds a node and calls a method to remove the node.
  * 
  * @param p The node to start the search.
  * @param q The KEY of node to remove.
  */
 public void eliminar(NodoAvl p,E q) {
  if(p==null) {
   // der Wert existiert nicht in diesem Baum, daher ist nichts zu tun
   return;
  } else {
   if(p.getData().compareTo(q) > 0)  {
    eliminar(p.left,q);
   } else if(p.getData().compareTo(q) < 0) {
    eliminar(p.right,q);
   } else if(p.data==q) {
    // we found the node in the tree.. now lets go on!
    eliminarAux(p);
   }
  }
 }
 
 /**
  * Removes a node from a AVL-Tree, while balancing will be done if necessary.
  * 
  * @param q The node to be removed.
  */
 private void eliminarAux(NodoAvl q) {
  NodoAvl r;
  // at least one child of q, q will be removed directly
  if(q.left==null || q.right==null) {
   // the root is deleted
   if(q.parent==null) {
    this.root=null;
    q=null;
    return;
   }
   r = q;
  } else {
   // q has two children --> will be replaced by successor
   r = successor(q);
   q.data = r.data;
  }
  
  NodoAvl p;
  if(r.left!=null) {
   p = r.left;
  } else {
   p = r.right;
  }
  
  if(p!=null) {
   p.parent = r.parent;
  }
  
  if(r.parent==null) {
   this.root = p;
  } else {
   if(r==r.parent.left) {
    r.parent.left=p;
   } else {
    r.parent.right = p;
   }
   // balancing must be done until the root is reached.
   recursiveBalance(r.parent);
  }
  r = null;
 }
 
 /**
  * Left rotation using the given node.
  * 
  * 
  * @param n
  *            The node for the rotation.
  * 
  * @return The root of the rotated tree.
  */
 public NodoAvl rotarIzquierda(NodoAvl n) {
  
  NodoAvl v = n.right;
  v.parent = n.parent;
  
  n.right = v.left;
  
  if(n.right!=null) {
   n.right.parent=n;
  }
  
  v.left = n;
  n.parent = v;
  
  if(v.parent!=null) {
   if(v.parent.right==n) {
    v.parent.right = v;
   } else if(v.parent.left==n) {
    v.parent.left = v;
   }
  }
  
  setBalance(n);
  setBalance(v);
  
  return v;
 }
 
 /**
  * Right rotation using the given node.
  * 
  * @param n
  *            The node for the rotation
  * 
  * @return The root of the new rotated tree.
  */
 public NodoAvl rotarDerecha(NodoAvl n) {
  
  NodoAvl v = n.left;
  v.parent = n.parent;
  
  n.left = v.right;
  
  if(n.left!=null) {
   n.left.parent=n;
  }
  
  v.right = n;
  n.parent = v;
  
  
  if(v.parent!=null) {
   if(v.parent.right==n) {
    v.parent.right = v;
   } else if(v.parent.left==n) {
    v.parent.left = v;
   }
  }
  
  setBalance(n);
  setBalance(v);
  
  return v;
 }
 /**
  * 
  * @param u The node for the rotation.
  * @return The root after the double rotation.
  */
 public NodoAvl rotarDobleIzquierdaDerecha(NodoAvl u) {
  u.left = rotarIzquierda(u.left);
  return rotarDerecha(u);
 }
 
 /**
  * 
  * @param u The node for the rotation.
  * @return The root after the double rotation.
  */
 public NodoAvl rotarDoblederechaIzquierda(NodoAvl u) {
  u.right = rotarDerecha(u.right);
  return rotarIzquierda(u);
 }
 
/***************************** Helper Functions ************************************/
 
 /**
  * Returns the successor of a given node in the tree (search recursivly).
  * 
  * @param q The predecessor.
  * @return The successor of node q.
  */
 public NodoAvl successor(NodoAvl q) {
  if(q.right!=null) {
   NodoAvl r = q.right;
   while(r.left!=null) {
    r = r.left;
   }
   return r;
  } else {
   NodoAvl p = q.parent;
   while(p!=null && q==p.right) {
    q = p;
    p = q.parent;
   }
   return p;
  }
 }
 
 /**
  * Calculating the "height" of a node.
  * 
  * @param cur
  * @return The height of a node (-1, if node is not existent eg. NULL).
  */
 private int altura(NodoAvl cur) {
  if(cur==null) {
   return -1;
  }
  if(cur.left==null && cur.right==null) {
   return 0;
  } else if(cur.left==null) {
   return 1+altura(cur.right);
  } else if(cur.right==null) {
   return 1+altura(cur.left);
  } else {
   return 1+maximo(altura(cur.left),altura(cur.right));
  }
 }
 
 /**
  * Return the maximum of two integers.
  */
 private int maximo(int a, int b) {
  if(a>=b) {
   return a;
  } else {
   return b;
  }
 }

 /**
  * Only for debugging purposes. Gives all information about a node.
  
  * @param n The node to write information about.
  */
 /**public void debug(NodoAvl n) {
  E l = 0;
  int r = 0;
  int p = 0;
  if(n.left!=null) {
   l = n.left.data;
  }
  if(n.right!=null) {
   r = n.right.data;
  }
  if(n.parent!=null) {
   p = n.parent.data;
  }
  
  System.out.println("Left: "+l+" Key: "+n+" Right: "+r+" Parent: "+p+" Balance: "+n.factorDeEquilibrio);
  
  if(n.left!=null) {
   debug(n.left);
  }
  if(n.right!=null) {
   debug(n.right);
  }
 }*/
 
 private void setBalance(NodoAvl cur) {
  cur.factorDeEquilibrio = altura(cur.right)-altura(cur.left);
 }
 
 /**
  * Calculates the Inorder traversal of this tree.
  * 
  * @return A Array-List of the tree in inorder traversal.
  */
 final protected ArrayList<NodoAvl> inorder() {
  ArrayList<NodoAvl> ret = new ArrayList<NodoAvl>();
  inorder(root, ret);
  return ret;
 }
 
 /**
  * Function to calculate inorder recursivly.
  * 
  * @param n
  *            The current node.
  * @param io
  *            The list to save the inorder traversal.
  */
 final protected void inorder(NodoAvl n, ArrayList<NodoAvl> io) {
  if (n == null) {
   return;
  }
  inorder(n.left, io);
  io.add(n);
  inorder(n.right, io);
 }

 public NodoAvl buscar(E pData){
     if (pData != null)
         return this.buscarAux(root, pData);
     else 
         return null;
 }
 
 private NodoAvl buscarAux(NodoAvl nodoIterador, E pData){
     if(nodoIterador.getData().compareTo(pData) == 0)
         return nodoIterador;
     else if(nodoIterador.getData().compareTo(pData) > 0)
         if(nodoIterador.left != null){
             return this.buscarAux(nodoIterador.left, pData);
         }
         else 
             return null;
     else 
         if (nodoIterador.right != null)
             return this.buscarAux(nodoIterador.right, pData);
         else 
             return null;
 }
 
 public NodoAvl getRaiz(){
     return this.root;
 }
 
 }

