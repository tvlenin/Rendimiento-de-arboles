package com.estructuras.datos.arboles;

/**
 *Clase Nodo para un arbol binario
 * @author abrahamon
 */
public class NodoBinario<E extends Comparable> {
    
    protected E data;
    protected NodoBinario<E> hijoDerecho;
    protected NodoBinario<E> hijoIzquierdo;
    
   /**
     * Constructores para la clase nodo
     * @param pData 
     */
    public NodoBinario(E pData){ 
        this(pData, null, null);
    }
    public NodoBinario(E pData, NodoBinario<E> pHijoIzquierdo, NodoBinario<E> pHijoDerecho){
        this.data = pData;
        this.hijoDerecho = pHijoDerecho;
        this.hijoIzquierdo = pHijoIzquierdo;
    }
    
    public NodoBinario<E> getHijoDerecho(){
        return this.hijoDerecho;
    }
    
    public NodoBinario<E> getHijoIzquierdo(){
        return this.hijoIzquierdo;
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
