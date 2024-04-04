package arbol_b;

import Recursos.Arbol;
import java.util.Scanner;

/**
 *
 * @author jos56
 */
public class Arbol_B {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int op = 99, grado, valor;
        System.out.println("Este arbol de tipo B solo puede aceptar valores, mas puedes indicar el grado");
        System.out.println("Cual sera el grado de este arbol?");
        grado = leer.nextInt();
        Arbol arbol = new Arbol(grado);
        do{
            menu();
            op = leer.nextInt();
            switch(op){
                case 1:
                    System.out.println("Ok, Dame el valor entero");
                    valor = leer.nextInt();
                    System.out.println("Gracias, el valor ha sido insertado");
                    arbol.insertar(valor);
                    break;
                case 2:
                    System.out.println("Esta bien, ahora te lo muestro");
                    arbol.showBTree();
                    break;
                case 0: 
                    System.out.println("Esta bien, Adios");
                    break;
            }
        }while(op != 0);
    }
    public static void menu(){
        System.out.println("");
        System.out.println("Que quieres hacer?");
        System.out.println("1. Insertar");
        System.out.println("2. Mostrar");
        System.out.println("0. Salir");
        System.out.println("");
    }
}
