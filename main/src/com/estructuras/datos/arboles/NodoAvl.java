package com.estructuras.datos.arboles;

/** Here is the AVL-Node class for Completenesse **/
public class NodoAvl<E extends Comparable> {
 public NodoAvl left;
 public NodoAvl right;
 public NodoAvl parent;
 public E data;
 public int factorDeEquilibrio;

 public NodoAvl(E k) {
  left = right = parent = null;
  factorDeEquilibrio = 0;
  data = k;
 }
 
 public String toString() {
  return "" + data;
 }

 public E getData(){
     return this.data;
 }
 
}
