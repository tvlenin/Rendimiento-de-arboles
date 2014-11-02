package com.estructuras.datos.arboles;

/**
 *
 * @author abrahamon
 */
public class ArbolBinario <E extends Comparable>{
    
    private NodoBinario<E> raiz;
    
    
    public ArbolBinario(){
        this.raiz = null;
    }
    
    public boolean estaVacio(){
        return raiz == null;
    }
    
    /**
     * para insertar alguna informacion se verifica que no se inserte data ==null
     * que no este vacio el arbol
     * y como ultima opcion se busca el nodo donde debe ir esta info
     * @param pData informacion a insertar
     * @return si se logra la accion retorna true
     */
    public boolean insertar(E pData){
        if(pData == null){
            System.out.println("No inserto data == null");
            return false;}
        if(this.estaVacio())
            return this.insertarPrimerElemento(pData);
        else{
            return this.insertar(this.buscar(raiz, pData), pData);
        }
    }
    
    private boolean insertar(NodoBinario<E> nodoIterador, E pData){
        if(nodoIterador == null){
            return false;
        }
        else if(nodoIterador.getData().compareTo(pData) >= 0)
            nodoIterador.hijoIzquierdo = new NodoBinario<>(pData);
        else
            nodoIterador.hijoDerecho = new NodoBinario<>(pData);
        return true;
    }
    
    private boolean insertarPrimerElemento(E pData){
        NodoBinario<E> nuevoNodo = new NodoBinario<>(pData);
        this.raiz = nuevoNodo;
        return true;
    }
    
    public boolean buscar(E pData){
        NodoBinario<E> iterador = this.buscar(raiz, pData);
        if(iterador.getData().compareTo(pData) == 0)
            return true;
        else 
            return false;
    }
    
    private NodoBinario<E> buscar(NodoBinario<E> iterador,E pData){
        if(this.estaVacio()){
            System.out.println("No puedo buscar a: "+pData+". El arbol esta vacio");
            return null;
        }
        else if (iterador.getData().compareTo(pData) == 0)
            return iterador;
        
        else if (iterador.getData().compareTo(pData) >= 0 ){ //buscar en el sub arbol izq
            if (iterador.tieneHijoIzquierdo())
                return this.buscar(iterador.getHijoIzquierdo(), pData);
            else
                return iterador;
        }
        else{
            if(iterador.tieneHijoDerecho())
                return this.buscar(iterador.getHijoDerecho(), pData);
            else
                return iterador;
        }
    }
    
    public void eliminar(E pData){
      raiz = eliminar(raiz, pData);
    }
    
    private NodoBinario<E> eliminar(NodoBinario<E> iterador, E pData){
        if (iterador == null) throw new RuntimeException("No se puede eliminar");
        else
            if (pData.compareTo(iterador.getData()) < 0)
                iterador.hijoIzquierdo = eliminar (iterador.getHijoIzquierdo(), pData);
            else
                if (pData.compareTo(iterador.getData()) > 0)
                    iterador.hijoDerecho = eliminar (iterador.getHijoDerecho(), pData);
                else{
                    if (iterador.getHijoIzquierdo() == null) return iterador.getHijoDerecho();
                    else
                        if (iterador.getHijoDerecho() == null) return iterador.getHijoIzquierdo();
                        else{// get data from the rightmost node in the left subtree
                            iterador.data = eliminarAux(iterador.getHijoIzquierdo());// eliminar the rightmost node in the left subtree
                            iterador.hijoIzquierdo =  eliminar(iterador.getHijoIzquierdo(), iterador.data) ;
                        }
                }
        return iterador;
    }
   
   private E eliminarAux(NodoBinario<E> p){
      while (p.getHijoDerecho() != null) 
          p = p.getHijoDerecho();
      return p.data;
   }
   
   
   public void printPreOrder(){
      printPreOrderAux(raiz);
   }
   private void printPreOrderAux(NodoBinario<E> r){
      if (r != null){
         System.out.println(r.getData()+" ");
         printPreOrderAux(r.getHijoIzquierdo());
         printPreOrderAux(r.getHijoDerecho());
      }
   }

   public void printInOrder(){
      printInOrderAux(raiz);
   }
   private void printInOrderAux(NodoBinario<E> r){
      if (r != null){
         printInOrderAux(r.getHijoIzquierdo());
         System.out.println(r.getData()+" ");
         printInOrderAux(r.getHijoDerecho());
      }
   } 
}
