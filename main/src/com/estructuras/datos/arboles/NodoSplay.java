package com.estructuras.datos.arboles;

/**
 *
 * @author abrahamon
 */
public class NodoSplay{
    
    protected Comparable data;          // The data in the node
    protected NodoSplay left;         // Left child
    protected NodoSplay right;        // Right child
        
    public NodoSplay(Comparable theKey) {
        data = theKey;
        left = right = null;
    }

    public Comparable getData(){
        return this.data;
    }
}
    
