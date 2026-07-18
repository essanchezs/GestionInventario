package gestionInventario;

//Clase ArbolProductos: arbol binario de busqueda (ABB) cuyo nodo es un Producto.
//La llave del arbol es el nombre del Producto (se compara con compareToIgnoreCase).
//Es el inventario que almacena la Tienda.
public class ArbolProductos {

    //Atributo
    private Producto raiz;

    //Metodo constructor
    public ArbolProductos() {
        raiz = null;
    }

    //Getter y setter de la raiz
    public Producto getRaiz() {
        return raiz;
    }

    public void setRaiz(Producto raiz) {
        this.raiz = raiz;
    }

    //Metodo que indica si el arbol esta vacio
    public boolean estaVacia() {
        return raiz == null;
    }

    //Insertar un producto en el arbol segun su nombre (iterativo)
    public void insertar(Producto nuevo) {
        if (estaVacia()) {
            raiz = nuevo;
            return;
        }
        Producto temp = raiz;
        Producto padre = raiz;
        while (temp != null) {
            padre = temp;
            int cmp = temp.getNombre().compareToIgnoreCase(nuevo.getNombre());
            if (cmp < 0) temp = temp.getDerecha();        // el temp es "menor" -> vamos a la derecha
            else if (cmp > 0) temp = temp.getIzquierda(); // el temp es "mayor" -> vamos a la izquierda
            else {
                System.out.println("El producto \"" + nuevo.getNombre() + "\" ya esta en el inventario.");
                return;
            }
        }
        //Colgamos el nuevo producto del padre encontrado, del lado que corresponde
        if (padre.getNombre().compareToIgnoreCase(nuevo.getNombre()) < 0) padre.setDerecha(nuevo);
        else padre.setIzquierda(nuevo);
    }

    //Buscar un producto por su nombre (iterativo)
    public Producto buscar(String nombre) {
        if (estaVacia()) {
            System.out.println("El inventario esta vacio.");
            return null;
        }
        Producto temp = raiz;
        while (temp != null) {
            int cmp = temp.getNombre().compareToIgnoreCase(nombre);
            if (cmp < 0) temp = temp.getDerecha();
            else if (cmp > 0) temp = temp.getIzquierda();
            else return temp;
        }
        System.out.println("El producto \"" + nombre + "\" no esta en el inventario.");
        return null;
    }

    //Buscar el padre de un producto por su nombre (se usa en la eliminacion)
    public Producto buscarPadre(String nombre) {
        Producto temp = raiz;
        Producto padre = temp;
        while (temp != null) {
            int cmp = temp.getNombre().compareToIgnoreCase(nombre);
            if (cmp == 0) return padre;
            padre = temp;
            if (cmp < 0) temp = temp.getDerecha();
            else temp = temp.getIzquierda();
        }
        System.out.println("El producto \"" + nombre + "\" no esta en el inventario.");
        return null;
    }

    //Buscar el sucesor de un nodo (el menor de su subarbol derecho) y desengancharlo
    private Producto buscarSucesor(Producto nodo) {
        Producto temp = nodo.getDerecha();
        Producto sucesor = nodo;
        Producto padreSucesor = sucesor;
        while (temp != null) {
            padreSucesor = sucesor;
            sucesor = temp;
            temp = temp.getIzquierda();
        }
        if (sucesor != nodo.getDerecha()) {
            padreSucesor.setIzquierda(sucesor.getDerecha());
            sucesor.setDerecha(nodo.getDerecha());
        }
        return sucesor;
    }

    //Eliminar un producto por su nombre. Retorna el producto eliminado, o null si no estaba.
    public Producto eliminar(String nombre) {
        //Considerar el caso de que el arbol este vacio
        if (estaVacia()) {
            System.out.println("El inventario esta vacio.");
            return null;
        }
        //Buscar el nodo; si no esta, buscar ya imprime el mensaje y retornamos null
        Producto nodo = buscar(nombre);
        if (nodo == null) return null;

        //Si el nodo por eliminar es la raiz, se consideran sus hijos:
        if (nodo == raiz) {
            //Si no tiene hijos, la raiz queda en null
            if (raiz.getIzquierda() == null && raiz.getDerecha() == null) {
                raiz = null;
            }
            //Si solo tiene hijo izquierdo, ese hijo se vuelve la nueva raiz
            else if (raiz.getDerecha() == null) {
                raiz = raiz.getIzquierda();
            }
            //Si solo tiene hijo derecho, ese hijo se vuelve la nueva raiz
            else if (raiz.getIzquierda() == null) {
                raiz = raiz.getDerecha();
            }
            //Si tiene los dos hijos, su sucesor toma su lugar y hereda el hijo izquierdo
            else {
                Producto sucesor = buscarSucesor(raiz);
                sucesor.setIzquierda(raiz.getIzquierda());
                raiz = sucesor;
            }
            return nodo;
        }

        //Si es cualquier otro nodo, buscamos su padre y de que lado esta colgado
        Producto padre = buscarPadre(nombre);
        boolean esHijoIzq = (padre.getIzquierda() == nodo);

        //Si no tiene hijos, el padre deja de apuntarlo
        if (nodo.getIzquierda() == null && nodo.getDerecha() == null) {
            if (esHijoIzq) padre.setIzquierda(null);
            else padre.setDerecha(null);
        }
        //Si solo tiene hijo izquierdo, ese hijo sube en su lugar
        else if (nodo.getDerecha() == null) {
            if (esHijoIzq) padre.setIzquierda(nodo.getIzquierda());
            else padre.setDerecha(nodo.getIzquierda());
        }
        //Si solo tiene hijo derecho, ese hijo sube en su lugar
        else if (nodo.getIzquierda() == null) {
            if (esHijoIzq) padre.setIzquierda(nodo.getDerecha());
            else padre.setDerecha(nodo.getDerecha());
        }
        //Si tiene los dos hijos, su sucesor toma su lugar y hereda el hijo izquierdo
        else {
            Producto sucesor = buscarSucesor(nodo);
            sucesor.setIzquierda(nodo.getIzquierda());
            if (esHijoIzq) padre.setIzquierda(sucesor);
            else padre.setDerecha(sucesor);
        }
        return nodo;
    }

    //Recorrido en orden (recursivo): imprime los productos ordenados por nombre
    private void enOrdenRec(Producto nodo) {
        if (nodo != null) {
            enOrdenRec(nodo.getIzquierda());
            System.out.println(nodo);
            enOrdenRec(nodo.getDerecha());
        }
    }

    //Mostrar todo el inventario ordenado alfabeticamente por nombre
    public void mostrarInventario() {
        if (estaVacia()) {
            System.out.println("El inventario esta vacio.");
            return;
        }
        System.out.println("\nINVENTARIO DE LA TIENDA (ordenado por nombre)");
        enOrdenRec(raiz);
    }
}
