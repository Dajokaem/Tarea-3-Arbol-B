/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Recursos;

/**
 *
 * @author jos56
 */
public class Nodo {
    boolean esHoja;
    int numclaves;
    int claves[];
    Nodo hijos[];
   

    public Nodo(int grado) {

        this.claves = new int[grado - 1];
        this.hijos = new Nodo[grado];
        this.esHoja = true;
        
    }

    public void imprimir() {
        System.out.print("[");
        for (int i = 0; i < numclaves; i++) {
            if (i < numclaves - 1) {
                System.out.print(claves[i] + " | ");
            } else {
                System.out.print(claves[i]);
            }
        }
        System.out.print("]");
    }
    
       public int find(int k) {
        for (int i = 0; i < numclaves; i++) {
            if (claves[i] == k) {
                return i;
            }
        }
        return -1;
    }
}
