import gestionInventario.ListaProductos;
import gestionInventario.Producto;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ListaProductos inventario = new ListaProductos();

        //Mostrar menu principal
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== Tienda de Electronica ===");
            System.out.println("1. Ingresar como Administrador");
            System.out.println("2. Ingresar como Cliente");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    menuAdministrador(scanner, inventario);
                    break;
                case 2:
                    menuCliente(scanner, inventario);
                    break;
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion invalida, intente de nuevo.");
            }
        }

        scanner.close();
    }

    //Menu del administrador
    private static void menuAdministrador(Scanner scanner, ListaProductos inventario) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== Menu Administrador ===");
            System.out.println("1. Agregar producto");
            System.out.println("2. Modificar un producto");
            System.out.println("3. Agregar imagen a un producto");
            System.out.println("4. Eliminar un producto");
            System.out.println("5. Mostrar inventario");
            System.out.println("6. Reporte de costos");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    scanner.nextLine();
                    System.out.print("Nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine();
                    System.out.print("Cantidad: ");
                    int cantidad = scanner.nextInt();
                    inventario.insertarInicio(nombre, precio, categoria, cantidad);
                    System.out.println("Producto agregado.");
                    break;
                case 2:
                    scanner.nextLine(); // limpiar el Enter pendiente
                    System.out.print("Nombre del producto a modificar: ");
                    String nombreMod = scanner.nextLine();
                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = scanner.nextDouble();
                    scanner.nextLine(); // limpiar el Enter pendiente
                    System.out.print("Nueva categoria: ");
                    String nuevaCategoria = scanner.nextLine();
                    System.out.print("Nueva cantidad: ");
                    int nuevaCantidad = scanner.nextInt();
                    if (inventario.modificar(nombreMod, nuevoPrecio, nuevaCategoria, nuevaCantidad)) {
                        System.out.println("Producto modificado.");
                    }
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.print("Nombre del producto: ");
                    String nombreImg = scanner.nextLine();
                    System.out.print("Ruta de la imagen (ej: imagenes/laptop.png): ");
                    String ruta = scanner.nextLine();
                    if (inventario.agregarImagen(nombreImg, ruta)) {
                        System.out.println("Imagen agregada.");
                    }
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.print("Nombre del producto a eliminar: ");
                    String nombreEli = scanner.nextLine();
                    Producto eliminado = inventario.eliminar(nombreEli);
                    if (eliminado != null) {
                        System.out.println("Producto eliminado: " + eliminado.getNombre());
                    }
                    break;
                case 5:
                    inventario.mostrarInventario();
                    break;
                case 6:
                    inventario.reporteCostos();
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal.");
                    break;
                default:
                    System.out.println("Opcion invalida, intente de nuevo.");
            }
        }
    }

    //Menu del cliente
    private static void menuCliente(Scanner scanner, ListaProductos inventario) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== Menu Cliente ===");
            System.out.println("1. Ver productos disponibles");
            System.out.println("2. Buscar un producto");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    inventario.mostrarInventario();
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.print("Nombre del producto a buscar: ");
                    String nombre = scanner.nextLine();
                    Producto p = inventario.buscar(nombre);
                    if (p != null) {
                        System.out.println(p);
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal.");
                    break;
                default:
                    System.out.println("Opcion invalida, intente de nuevo.");
            }
        }
    }
}
