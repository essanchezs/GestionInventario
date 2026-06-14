package gestionInventario;

//Clase ListaProductos: lista enlazada simple de productos
public class ListaProductos {

    //Atributo
    private Producto primero;

    //Metodo constructor
    public ListaProductos() {
        primero = null;
    }

    //Metodo esVacia
    private boolean esVacia() {
        return primero == null;
    }

    //Metodo para insertar un producto al inicio
    public void insertarInicio(String nombre, double precio, String categoria, int cantidad) {
        Producto nuevo = new Producto(nombre, precio, categoria, cantidad);
        nuevo.setSiguiente(primero);
        primero = nuevo;
    }

    //Metodo para buscar un producto
    public Producto buscar(String nombre) {
        if (esVacia()) {
            System.out.println("\nLa lista esta vacia.");
            return null;
        }
        Producto temp = primero;
        while (temp != null) {
            if (temp.getNombre().equalsIgnoreCase(nombre)) {
                return temp;
            }
            temp = temp.getSiguiente();
        }
        System.out.println("\nEl producto no esta en la lista.");
        return null;
    }

    //Metodo para modificar un producto (precio, categoria y cantidad)
    public boolean modificar(String nombre, double nuevoPrecio, String nuevaCategoria, int nuevaCantidad) {
        Producto p = buscar(nombre);
        if (p == null) {
            return false;
        }
        p.setPrecio(nuevoPrecio);
        p.setCategoria(nuevaCategoria);
        p.setCantidad(nuevaCantidad);
        return true;
    }

    //Metodo para agregar una imagen a un producto
    public boolean agregarImagen(String nombre, String ruta) {
        Producto p = buscar(nombre);
        if (p == null) {
            return false;
        }
        p.agregarImagen(ruta);
        return true;
    }

    //Metodo para eliminar un producto
    public Producto eliminar(String nombre) {
        if (esVacia()) {
            System.out.println("\nLa lista esta vacia.");
            return null;
        }
        if (primero.getNombre().equalsIgnoreCase(nombre)) {
            Producto eliminado = primero;
            primero = primero.getSiguiente();
            return eliminado;
        }
        Producto anterior = primero;
        Producto temp = primero.getSiguiente();
        while (temp != null && !temp.getNombre().equalsIgnoreCase(nombre)) {
            anterior = temp;
            temp = temp.getSiguiente();
        }
        if (temp == null) {
            System.out.println("\nEl producto no esta en la lista.");
            return null;
        }
        anterior.setSiguiente(temp.getSiguiente());
        return temp;
    }

    //Metodo para mostrar inventario
    public void mostrarInventario() {

        if (esVacia()) {
            System.out.println("\nLa lista esta vacia.");
            return;
        }

        Producto temp = primero;
        int contador = 1;

        System.out.println("\nINVENTARIO");

        while (temp != null) {
            System.out.println("\n PRODUCTO #" + contador + ": ");
            System.out.println(temp);

            temp = temp.getSiguiente();
            contador++;
        }
    }


    //Metodo para mostrar reporte de costos
    public void reporteCostos(){
        if (esVacia()) {
            System.out.println("\nLa lista esta vacia.");
            return;
        }

        Producto temp = primero;
        double TotalGeneral = 0;

        System.out.println( "REPORTE DE COSTOS \n");
        while (temp != null) {

            double subtotal = temp.getCantidad() * temp.getPrecio();
            System.out.println("Producto: " + temp.getNombre());
            System.out.println("Cantidad: " + temp.getCantidad());
            System.out.println("Precio unitario: ₡" + temp.getPrecio());
            System.out.println("Subtotal: ₡" + subtotal);
            System.out.println("-----------------------------");

            TotalGeneral += subtotal;

            temp = temp.getSiguiente();
        }

        System.out.println("TOTAL GENERAL: ₡" + TotalGeneral);
    }
}
