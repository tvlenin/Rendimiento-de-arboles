 package com.estructuras.datos.arboles;
 
 public class ArbolSplay<E extends Comparable>{
     
    private NodoSplay root;
    private int count = 0;

    public ArbolSplay(){}

    public boolean isEmpty(){
        return root == null;
    }

    public void clear(){
        root = null;
    }


    public void insert(E ele){
        NodoSplay z = root;
        NodoSplay p = null;
        while (z != null){
            p = z;
            if (ele.compareTo(p.getData()) < 0)
                z = z.getHijoDerecho();
            else
                z = z.getHijoIzquierdo();
        }
        z = new NodoSplay(ele);
        z.padre = p;
        if (p == null)
            root = z;
        else if (ele.compareTo(p.getData()) < 0)
            p.hijoDerecho = z;
        else
            p.hijoIzquierdo = z;
        Splay(z);
        count++;
    }

    public void makeLeftChildParent(NodoSplay c, NodoSplay p){
        if ((c == null) || (p == null) || (p.hijoIzquierdo != c) || (c.padre != p))
            throw new RuntimeException("WRONG");

        if (p.padre != null){
            if (p == p.padre.getHijoIzquierdo())
                p.padre.hijoIzquierdo = c;
            else 
                p.padre.hijoDerecho = c;
        }
        if (c.getHijoDerecho() != null)
            c.hijoDerecho.padre = p;

        c.padre = p.getPadre();
        p.padre = c;
        p.hijoIzquierdo = c.hijoDerecho;
        c.hijoDerecho = p;
    }

    public void makeRightChildParent(NodoSplay<E> c, NodoSplay<E> p){
        if ((c == null) || (p == null) || (p.hijoDerecho != c) || (c.padre != p))
            throw new RuntimeException("WRONG");
        if (p.padre != null){
            if (p == p.padre.hijoIzquierdo)
                p.padre.hijoIzquierdo = c;
            else
                p.padre.hijoDerecho = c;
        }
        if (c.hijoIzquierdo != null)
            c.hijoIzquierdo.padre = p;
        c.padre = p.padre;
        p.padre = c;
        p.hijoDerecho = c.hijoIzquierdo;
        c.hijoIzquierdo = p;
    }

    /** function splay **/
    private void Splay(NodoSplay x)
    {
        while (x.padre != null)
        {
            NodoSplay Parent = x.padre;
            NodoSplay GrandParent = Parent.padre;
            if (GrandParent == null)
            {
                if (x == Parent.hijoIzquierdo)
                    makeLeftChildParent(x, Parent);
                else
                    makeRightChildParent(x, Parent);                 
            } 
            else
            {
                if (x == Parent.hijoIzquierdo)
                {
                    if (Parent == GrandParent.hijoIzquierdo)
                    {
                        makeLeftChildParent(Parent, GrandParent);
                        makeLeftChildParent(x, Parent);
                    }
                    else 
                    {
                        makeLeftChildParent(x, x.padre);
                        makeRightChildParent(x, x.padre);
                    }
                }
                else 
                {
                    if (Parent == GrandParent.hijoIzquierdo)
                    {
                        makeRightChildParent(x, x.padre);
                        makeLeftChildParent(x, x.padre);
                    } 
                    else 
                    {
                        makeRightChildParent(Parent, GrandParent);
                        makeRightChildParent(x, Parent);
                    }
                }
            }
        }
        root = x;
    }

    /** function to remove data **/
    public void remove(E ele)
    {
        NodoSplay node = findNode(ele);
       remove(node);
    }

    /** function to remove node **/
    private void remove(NodoSplay node)
    {
        if (node == null)
            return;

        Splay(node);
        if( (node.hijoIzquierdo != null) && (node.hijoDerecho !=null))
        { 
            NodoSplay min = node.hijoIzquierdo;
            while(min.hijoDerecho!=null)
                min = min.hijoDerecho;

            min.hijoDerecho = node.hijoDerecho;
            node.hijoDerecho.padre = min;
            node.hijoIzquierdo.padre = null;
            root = node.hijoIzquierdo;
        }
        else if (node.hijoDerecho != null)
        {
            node.hijoDerecho.padre = null;
            root = node.hijoDerecho;
        } 
        else if( node.hijoIzquierdo !=null)
        {
            node.hijoIzquierdo.padre = null;
            root = node.hijoIzquierdo;
        }
        else
        {
            root = null;
        }
        node.padre = null;
        node.hijoIzquierdo = null;
        node.hijoDerecho = null;
        node = null;
        count--;
    }

    /** Functions to count number of nodes **/
    public int countNodes(){
        return count;
    }

    /** Functions to search for an data **/
    public boolean search(E val){
        return findNode(val) != null;
    }

    private NodoSplay findNode(E ele){
        NodoSplay z = root;
        while (z != null)
        {
            if (ele.compareTo(z.getData()) < 0)
                z = z.hijoDerecho;
            else if (ele.compareTo(z.getData()) > 0)
                z = z.hijoIzquierdo;
            else
                return z;
        }
        return null;
    }

    /** Function for inorder traversal **/ 
    public void inorder(){
        inorder(root);
    }

    private void inorder(NodoSplay r){
        if (r != null){
            inorder(r.hijoIzquierdo);
            System.out.print(r.data +" ");
            inorder(r.hijoDerecho);
        }
    }

    /** Function for preorder traversal **/
    public void preorder(){
        preorder(root);
    }

    private void preorder(NodoSplay r){
        if (r != null){
            System.out.print(r.data +" ");
            preorder(r.hijoIzquierdo);             
            preorder(r.hijoDerecho);
        }
    }

    /** Function for postorder traversal **/
    public void postorder(){
        postorder(root);
    }
    private void postorder(NodoSplay r){
        if (r != null){
            postorder(r.hijoIzquierdo);             
            postorder(r.hijoDerecho);
            System.out.print(r.data +" ");
        }
    }
 }
 
