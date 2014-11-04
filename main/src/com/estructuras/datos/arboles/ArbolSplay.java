 package com.estructuras.datos.arboles;
 /**
 * Implements a top-down splay tree.
 * Available at http://www.link.cs.cmu.edu/splay/
 * Author: Danny Sleator <sleator@cs.cmu.edu>
 * This code is in the public domain.
 */



public class ArbolSplay{
    
    private NodoSplay root;

    public NodoSplay getRaiz(){
        return this.root;
    }
    
    public ArbolSplay() {
        root = null;
    }

    /**
     * Insert into the tree.
     * @param x the item to insert.
     * @throws DuplicateItemException if x is already present.
     */
    public void insert(Comparable key) {
	NodoSplay n;
	int c;
	if (root == null) {
	    root = new NodoSplay(key);
	    return;
	}
	splay(key);
	if ((c = key.compareTo(root.data)) == 0) {
	    //	    throw new DuplicateItemException(x.toString());	    
	    return;
	}
	n = new NodoSplay(key);
	if (c < 0) {
	    n.left = root.left;
	    n.right = root;
	    root.left = null;
	} else {
	    n.right = root.right;
	    n.left = root;
	    root.right = null;
	}
	root = n;
    }

    /**
     * Remove from the tree.
     * @param x the item to borrar.
     * @throws ItemNotFoundException if x is not found.
     */
    public void borrar(Comparable key) {
	NodoSplay x;
	splay(key);
	if (key.compareTo(root.data) != 0) {
	    //            throw new ItemNotFoundException(x.toString());
	    return;
	}
	// Now delete the root
	if (root.left == null) {
	    root = root.right;
	} else {
	    x = root.right;
	    root = root.left;
	    splay(key);
	    root.right = x;
	}
    }

    /**
     * Find the smallest item in the tree.
     */
    public Comparable findMin() {
        NodoSplay x = root;
        if(root == null) return null;
        while(x.left != null) x = x.left;
        splay(x.data);
        return x.data;
    }

    /**
     * Find the largest item in the tree.
     */
    public Comparable findMax() {
        NodoSplay x = root;
        if(root == null) return null;
        while(x.right != null) x = x.right;
        splay(x.data);
        return x.data;
    }

    /**
     * Find an item in the tree.
     */
    public Comparable buscar(Comparable key) {
	if (root == null) return null;
	splay(key);
        if(root.data.compareTo(key) != 0) return null;
        return root.data;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /** this method just illustrates the top-down method of
     * implementing the move-to-root operation 
     */
    private void moveToRoot(Comparable key) {
	NodoSplay l, r, t, y;
	l = r = header;
	t = root;
	header.left = header.right = null;
	for (;;) {
	    if (key.compareTo(t.data) < 0) {
		if (t.left == null) break;
		r.left = t;                                 /* link right */
		r = t;
		t = t.left;
	    } else if (key.compareTo(t.data) > 0) {
		if (t.right == null) break;
		l.right = t;                                /* link left */
		l = t;
		t = t.right;
	    } else {
		break;
	    }
	}
	l.right = t.left;                                   /* assemble */
	r.left = t.right;
	t.left = header.right;
	t.right = header.left;
	root = t;
    }

    private static NodoSplay header = new NodoSplay(null);     
    /**
     * Internal method to perform a top-down splay.
     * 
     *   splay(key) does the splay operation on the given key.
   If key is in the tree, then the NodoSplay containing
   that key becomes the root.  If key is not in the tree,
   then after the splay, key.root is either the greatest key
   < key in the tree, or the lest key > key in the tree.
     *
     *   This means, among other things, that if you splay with
     *   a key that's larger than any in the tree, the rightmost
     *   node of the tree becomes the root.  This property is used
     *   in the delete() method.
     */

    /**
     * Internal method to perform a top-down splay.splay(data) does the splay operation on the given data.
   If data is in the tree, then the NodoSplay containing
   that data becomes the root.  If data is not in the tree,
   then after the splay, data.root is either the greatest data
   < data in the tree, or the lest data > data in the tree.

   This means, among other things, that if you splay with
   a data that's larger than any in the tree, the rightmost
   node of the tree becomes the root.  This property is used
   in the delete() method.
     */
    private void splay(Comparable key) {
        NodoSplay l;
	NodoSplay r, t, y;
	l = r = header;
	t = root;
	header.left = header.right = null;
	for (;;) {
	    if (key.compareTo(t.data) < 0) {
		if (t.left == null) break;
		if (key.compareTo(t.left.data) < 0) {
		    y = t.left;                            /* rotate right */
		    t.left = y.right;
		    y.right = t;
		    t = y;
		    if (t.left == null) break;
		}
		r.left = t;                                 /* link right */
		r = t;
		t = t.left;
	    } else if (key.compareTo(t.data) > 0) {
		if (t.right == null) break;
		if (key.compareTo(t.right.data) > 0) {
		    y = t.right;                            /* rotate left */
		    t.right = y.left;
		    y.left = t;
		    t = y;
		    if (t.right == null) break;
		}
		l.right = t;                                /* link left */
		l = t;
		t = t.right;
	    } else {
		break;
	    }
	}
	l.right = t.left;                                   /* assemble */
	r.left = t.right;
	t.left = header.right;
	t.right = header.left;
	root = t;
    }
}