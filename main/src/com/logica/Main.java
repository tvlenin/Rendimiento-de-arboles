package com.logica;

import com.estructuras.datos.arboles.ArbolBinario;
import com.estructuras.datos.arboles.ArbolSplay;
import com.estructuras.datos.arboles.NodoAvl;
import com.estructuras.datos.arboles.ArbolAvl;

/**
 *
 * @author abrahamon
 */
public class Main {
    public static void main(String[] args){   

        ArbolAvl a = new ArbolAvl();
        
        
        a.insert(1);
       // System.out.println(a.getRaiz().getData());
        a.insert(231);
        a.insert(4);
        
        a.remove(1);
        
       // System.out.println(a.getRaiz().getData());
        
        
    } 
}
