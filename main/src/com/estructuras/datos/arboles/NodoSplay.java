
package com.estructuras.datos.arboles;

/**
 *
 * @author abrahamon
 */
public class NodoSplay<E extends Comparable> {
    
    protected E data;
    protected NodoSplay<E> padre;
    protected NodoSplay<E> hijoDerecho;
    protected NodoSplay<E> hijoIzquierdo;
    
   /**
     * Constructores para la clase nodo
     * @param pData 
     */
    public NodoSplay(E pData){ 
        this(pData, null, null, null);
    }
    public NodoSplay(E pData, NodoSplay pPadre, NodoSplay<E> pHijoIzquierdo, NodoSplay<E> pHijoDerecho){
        this.data = pData;
        this.padre = pPadre;
        this.hijoDerecho = pHijoDerecho;
        this.hijoIzquierdo = pHijoIzquierdo;
    }
    
    public NodoSplay<E> getPadre(){
        return this.padre;
    }
    
    public NodoSplay<E> getHijoDerecho(){
        return this.hijoDerecho;
    }
    
    public NodoSplay<E> getHijoIzquierdo(){
        return this.hijoIzquierdo;
    }
    
    public boolean tienePadre(){
        return this.padre != null;
    }
    public boolean tieneHijoDerecho(){
        return this.hijoDerecho != null;
    }
    
    public boolean tieneHijoIzquierdo(){
        return this.hijoIzquierdo != null;
    }
    
    /**
     * obtener la informacion que almacena este nodo
     * @return 
     */
    public E getData(){
        return data;
    }
    
}