package Recursos;

public class Arbol {

    int grado;
    Nodo raiz;
    int minimo;

    public Arbol(int grado) {
        if (grado % 2 == 0) {
            minimo = grado / 2;
        } else {
            minimo = (grado - 1) / 2;
        }
        this.grado = grado;
        raiz = new Nodo(grado);
    }

    public int buscarClaveMayor() {
        int claveMaxima = getClaveMayor(this.raiz);

        return claveMaxima;
    }

    private int getClaveMayor(Nodo a) {
        if (a == null) {
            return 0;
        }
        while (!a.esHoja) {
            a = a.hijos[a.numclaves];
        }
        return claveMayorPorNodo(a);
    }

    private int claveMayorPorNodo(Nodo a) {
        return a.claves[a.numclaves - 1];
    }

    public void mostrarClavesNodoMinimo() {
        Nodo temp = buscarNodoMinimo(raiz);

        if (temp == null) {
            System.out.println("Sin minimo");
        } else {
            temp.imprimir();
        }
    }

    private Nodo buscarNodoMinimo(Nodo raiz) {
        if (raiz == null) {
            return null;
        }
        Nodo aux = raiz;

        while (!aux.esHoja) {

            aux = aux.hijos[0];
        }

        return aux;
    }

    public void buscarNodoPorClave(int num) {
        Nodo temp = buscar(raiz, num);

        if (temp == null) {
            System.out.println("No se ha encontrado un nodo con el valor ingresado");
        } else {
            imprimir(temp);
        }
    }

    private Nodo buscar(Nodo actual, int key) {
        int i = 0;//se empieza a buscar siempre en la primera posicion

        //Incrementa el indice mientras el valor de la clave del nodo sea menor
        while (i < actual.numclaves && key > actual.claves[i]) {
            i++;
        }

        //Si la clave es igual, entonces retornamos el nodo
        if (i < actual.numclaves && key == actual.claves[i]) {
            return actual;
        }

        //Si llegamos hasta aqui, entonces hay que buscar los hijos
        //Se revisa primero si tiene hijos
        if (actual.esHoja) {
            return null;
        } else {
            //Si tiene hijos, hace una llamada recursiva
            return buscar(actual.hijos[i], key);
        }
    }

    public void insertar(int key) {
        Nodo r = raiz;

        //Si el nodo esta lleno lo debe separar antes de insertar
        if (r.numclaves == grado - 1) {
            Nodo s = new Nodo(grado);
            raiz = s;
            s.esHoja = false;
            s.numclaves = 0;
            s.hijos[0] = r;
            separar(s, 0, r);
            fullInsert(s, key);
        } else {
            fullInsert(r, key);
        }
    }


    private void separar(Nodo x, int i, Nodo y) {
        //Nodo temporal que sera el hijo i + 1 de x
        Nodo z = new Nodo(grado);
        z.esHoja = y.esHoja;
        z.numclaves = (minimo - 1);

        //Copia las ultimas (t - 1) claves del nodo y al inicio del nodo z      // z = |40|50| | | |
        for (int j = 0; j < (minimo- 1); j++) {
            z.claves[j] = y.claves[(j + minimo)];
        }

        //Si no es hoja hay que reasignar los nodos hijos
        if (!y.esHoja) {
            for (int k = 0; k < minimo; k++) {
                z.hijos[k] = y.hijos[(k + minimo)];
            }
        }

        //nuevo tamanio de y                                                    // x =            | | | | | |
        y.numclaves = (minimo - 1);                                             //               /   \
        //  |10|20| | | |
        //Mueve los hijos de x para darle espacio a z
        for (int j = x.numclaves; j > i; j--) {
            x.hijos[(j + 1)] = x.hijos[j];
        }
        //Reasigna el hijo (i+1) de x                                           // x =            | | | | | |
        x.hijos[(i + 1)] = z;                                                   //               /   \
        //  |10|20| | | |     |40|50| | | |
        //Mueve las claves de x
        for (int j = x.numclaves; j > i; j--) {
            x.claves[(j + 1)] = x.claves[j];
        }

        //Agrega la clave situada en la mediana                                 // x =            |30| | | | |
        x.claves[i] = y.claves[(minimo - 1)];                                   //               /    \
        x.numclaves++;
    }

    private void fullInsert(Nodo x, int key) {
          //Si es una hoja
        if (x.esHoja) {
            int i = x.numclaves; //cantidad de valores del nodo
            //busca la posicion i donde asignar el valor
            while (i >= 1 && key < x.claves[i - 1]) {
                x.claves[i] = x.claves[i - 1];//Desplaza los valores mayores a key
                i--;
            }

            x.claves[i] = key;//asigna el valor al nodo
            x.numclaves++; //aumenta la cantidad de elementos del nodo
        } else {
            int j = 0;
            //Busca la posicion del hijo
            while (j < x.numclaves && key > x.claves[j]) {
                j++;
            }

            //Si el nodo hijo esta lleno lo separa
            if (x.hijos[j].numclaves == (grado-1)) {
                separar(x, j, x.hijos[j]);

                if (key > x.claves[j]) {
                    j++;
                }
            }

            fullInsert(x.hijos[j], key);
        }
    }
    public void showBTree() {
        imprimir(raiz);
    }
    
    
    private void imprimir(Nodo n) {
        n.imprimir();

        //Si no es hoja
        if (!n.esHoja) {
            //recorre los nodos para saber si tiene hijos
            for (int j = 0; j <= n.numclaves; j++) {
                if (n.hijos[j] != null) {
                    System.out.println();
                    imprimir(n.hijos[j]);
                }
            }
        }
    }

}
