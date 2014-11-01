
package com.estructuras.datos;

/**
 *
 * @author abrahamon
 */
public class Nodo<E>{
    public E dato;
    private String _indice;
    public Nodo<E> siguiente;
    public Nodo<E> previo;
    
    public Nodo(E dato){
        this(dato,null,null);
    }
    
    public Nodo(E pDato, Nodo<E> pSiguiente, Nodo<E> pPrevio){
        this.dato = pDato;
        this.siguiente = pSiguiente;
        this.previo = pPrevio;
    }
    public Nodo(E dato, String Indice){
        this._indice = Indice;
        this.dato = dato;
        this.previo = null;
        this.siguiente = null;
    }
    
    public void setData(E pData){
        this.dato = pData;
    }
    public void setSiguiente(Nodo<E> pNodo){
        this.siguiente = pNodo;
    }
    
    public E getDato(){
        return dato;
    }
    
    public Nodo<E> getSiguiente(){
        return siguiente;
    }
    public Nodo<E> getPrevio(){
        return previo;
    }

    public boolean tienePrevio(){
        boolean resp = false;
        if(previo != null)
            resp = true;
        return resp;
    }
    public boolean estaEnExtremos(){
        boolean resp= false;
        if (siguiente==null || previo == null)
            resp = true;
        return resp;
    }
    public boolean tieneSiguiente(){
        boolean resp = false;
        if(siguiente != null)
            resp = true;
        return resp;
    }
    
    public String getIndex(){
        return _indice;
    }
    
}