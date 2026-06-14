import java.util.Scanner;
import gestionInventario.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ListaProductos lista = new ListaProductos();

        int opcion;

        do {
            System.out.println("\n===== PRUEBA REPORTE DE COSTOS =====");
            System.out.println("1. Insertar producto");
            System.out.println("2. Mostrar inventario");
            System.out.println("3. Mostrar reporte de costos");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();

                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Categoría: ");
                    String categoria = sc.nextLine();

                    Producto producto = new Producto(
                            nombre,
                            precio,
                            categoria,
                            cantidad
                    );

                    lista.insertarInicio(nombre, precio, categoria, cantidad); //Acá podríamos mandar solo el objeto producto, pero en el método está distinto

                    System.out.println("Producto agregado.");
                    System.out.println(producto);
                    break;

                case 2:
                    lista.mostrarInventario();
                    break;

                case 3:
                    lista.reporteCostos();
                    break;

                case 4:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 3);

        sc.close();
    }
}