import gestionInventario.*;

import java.util.Scanner;

public class Main {

    //Estas las agregamos como globales para que todos los menus accedan al mismo inventario y a la misma cola de clientes
    private static ColaClientes colaClientes = new ColaClientes();
    private static ArbolProductos inventario = new ArbolProductos();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Productos iniciales de prueba
        inventario.insertar(new Producto("Laptop", 750000, "Electronica", 10));
        inventario.insertar(new Producto("Celular", 450000.0, "Electronica", 15));
        inventario.insertar(new Producto("Audifonos", 35000.0, "Audio", 20));

        //ListaProductos inventario = new ListaProductos();

        //Mostrar menu principal
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE INVENTARIO Y VENTAS ===");
            System.out.println("1. Gestión del Inventario (Administrador)");
            System.out.println("2. Registrar Cliente y Llenar Carrito (Encolar)");
            System.out.println("3. Atender Siguiente Cliente (Procesar Factura)");
            System.out.println("4. Ver Cola de Clientes en Espera");
            System.out.println("0. Salir del Programa");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    menuAdministrador(scanner);
                    break;
                case 2:
                    registrarClienteYCarrito(scanner);
                    break;
                case 3:
                    atenderSiguienteCliente();
                    break;
                case 4:
                    colaClientes.mostrarCola();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }

        scanner.close();
    }

    //Menu del administrador
    // Gestión del Inventario de la Tienda desde el admin (Árbol Binario de Búsqueda)
    private static void menuAdministrador(Scanner scanner) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== Menú de Administración del Inventario ===");
            System.out.println("1. Insertar Producto al Inventario");
            System.out.println("2. Modificar/Eliminar un Producto");
            System.out.println("3. Agregar Imagen a un Producto");
            System.out.println("4. Buscar un Producto en el Inventario");
            System.out.println("5. Mostrar Catálogo Completo (Orden Alfabético)");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el Enter

                switch (opcion) {
                    case 1:
                        System.out.print("Nombre del producto: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Precio: ₡");
                        double precio = scanner.nextDouble();
                        scanner.nextLine(); // Limpiar Enter
                        System.out.print("Categoría: ");
                        String categoria = scanner.nextLine();
                        System.out.print("Cantidad en Stock: ");
                        int cantidad = scanner.nextInt();
                        scanner.nextLine();

                        Producto nuevoProd = new Producto(nombre, precio, categoria, cantidad);

                        // Preguntar opcionalmente por una imagen al crear el producto
                        System.out.print("¿Desea agregar la ruta de una imagen ahora? (s/n): ");
                        String opcImg = scanner.nextLine();
                        if (opcImg.equalsIgnoreCase("s")) {
                            System.out.print("Ruta de la imagen (ej: imagenes/laptop.png): ");
                            String rutaImg = scanner.nextLine();
                            nuevoProd.agregarImagen(rutaImg);
                        }

                        inventario.insertar(nuevoProd);
                        System.out.println("¡Producto guardado exitosamente en el inventario!");
                        break;

                    case 2:
                        System.out.print("Nombre del producto a eliminar: ");
                        String nombreEli = scanner.nextLine();
                        Producto eliminado = inventario.eliminar(nombreEli);
                        if (eliminado != null) {
                            System.out.println("Producto " + eliminado.getNombre() + " eliminado con éxito.");
                        }
                        break;

                    case 3:
                        // OPCIÓN SOLICITADA: Agregar imagen a un producto existente
                        System.out.print("Nombre del producto al que desea agregar imagen: ");
                        String nombreImg = scanner.nextLine();
                        System.out.print("Ruta de la imagen (ej: imagenes/laptop.png): ");
                        String ruta = scanner.nextLine();

                        if (inventario.agregarImagen(nombreImg, ruta)) {
                            System.out.println("¡Imagen agregada con éxito al producto!");
                        }
                        break;

                    case 4:
                        System.out.print("Nombre del producto a buscar: ");
                        String nombreBusc = scanner.nextLine();
                        Producto encontrado = inventario.buscar(nombreBusc);
                        if (encontrado != null) {
                            System.out.println(encontrado);
                        }
                        break;

                    case 5:
                        inventario.mostrarInventario();
                        break;

                    case 0:
                        System.out.println("Volviendo al menú principal.");
                        break;

                    default:
                        System.out.println("Opción inválida, intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error en la entrada de datos. Operación cancelada.");
                scanner.nextLine();
            }
        }
    }

    //Menu para el cliente cliente
    // ACCIÓN REQUERIDA 2: Registro de Cliente con Carrito Modificable y Encolado
    private static void registrarClienteYCarrito(Scanner scanner) {
        System.out.println("\n=== REGISTRO DE NUEVO CLIENTE ===");

        // Limpieza de buffer por si venía un Enter colgado
        scanner.nextLine();

        System.out.print("Nombre del cliente: ");
        String nombreCliente = scanner.nextLine();

        System.out.print("Asigne prioridad (1 = Básico, 2 = Afiliado, 3 = Premium): ");
        int prioridad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (prioridad < 1 || prioridad > 3) {
            System.out.println("Prioridad inválida. Registro cancelado.");
            return;
        }

        // Crear el objeto cliente
        Cliente nuevoCliente = new Cliente(nombreCliente, prioridad);

        // Submenú interactivo para gestionar el Carrito de Compras (ListaProductos)
        int opcCarrito = -1;
        do {
            System.out.println("\n--- GESTIÓN DEL CARRITO DE COMPRAS ---");
            System.out.println("1. Agregar producto al carrito");
            System.out.println("2. Modificar cantidad de un producto en el carrito");
            System.out.println("3. Eliminar producto del carrito");
            System.out.println("4. Ver estado actual del carrito");
            System.out.println("5. Finalizar compra e ingresar a la cola de atención");
            System.out.print("Seleccione una opción: ");

            try {
                opcCarrito = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcCarrito) {
                    case 1: // AGREGAR
                        System.out.println("\n--- INVENTARIO DISPONIBLE EN TIENDA ---");
                        inventario.mostrarInventario();

                        System.out.print("\nIngrese el nombre del producto a añadir: ");
                        String nombreBuscar = scanner.nextLine();
                        Producto prodTienda = inventario.buscar(nombreBuscar);

                        if (prodTienda != null) {
                            System.out.print("Ingrese la cantidad que desea llevar: ");
                            int cantLlevar = scanner.nextInt();
                            scanner.nextLine();

                            if (cantLlevar <= 0) {
                                System.out.println("La cantidad debe ser mayor a 0.");
                            } else if (cantLlevar > prodTienda.getCantidad()) {
                                System.out.println("Stock insuficiente en tienda. Disponibles: " + prodTienda.getCantidad());
                            } else {
                                // Descontamos del stock general de la tienda (Árbol)
                                prodTienda.setCantidad(prodTienda.getCantidad() - cantLlevar);

                                // Agregamos a la ListaProductos usando el método de Cliente
                                nuevoCliente.agregarAlCarrito(prodTienda.getNombre(), prodTienda.getPrecio(), prodTienda.getCategoria(), cantLlevar);
                                System.out.println("¡Producto añadido con éxito al carrito!");
                            }
                        }
                        break;

                    case 2: // MODIFICAR
                        System.out.print("Nombre del producto en el carrito a modificar: ");
                        String nombreMod = scanner.nextLine();

                        // Verificamos si el producto existe en el carrito del cliente
                        Producto prodCarrito = nuevoCliente.getCarrito().buscar(nombreMod);

                        if (prodCarrito != null) {
                            System.out.print("Cantidad actual en carrito: " + prodCarrito.getCantidad());
                            System.out.print("\nIngrese la NUEVA cantidad deseada: ");
                            int nuevaCant = scanner.nextInt();
                            scanner.nextLine();

                            if (nuevaCant <= 0) {
                                System.out.println("Para dejar la cantidad en 0, utilice la opción de Eliminar.");
                            } else {
                                Producto prodStock = inventario.buscar(nombreMod);
                                int diferencia = nuevaCant - prodCarrito.getCantidad();

                                // Si pide más de lo que tenía, validamos que el árbol tenga stock suficiente
                                if (diferencia > 0 && prodStock != null && diferencia > prodStock.getCantidad()) {
                                    System.out.println("No hay suficiente stock en tienda para aumentar la cantidad. Stock disponible: " + prodStock.getCantidad());
                                } else {
                                    // Ajustamos el stock del Árbol (si la diferencia es negativa, devuelve stock)
                                    if (prodStock != null) {
                                        prodStock.setCantidad(prodStock.getCantidad() - diferencia);
                                    }

                                    // Usamos la función modificar() de la clase ListaProductos
                                    nuevoCliente.getCarrito().modificar(nombreMod, prodCarrito.getPrecio(), prodCarrito.getCategoria(), nuevaCant);
                                    System.out.println("¡Cantidad modificada con éxito en el carrito!");
                                }
                            }
                        }
                        break;

                    case 3: // ELIMINAR
                        System.out.print("Nombre del producto que desea quitar del carrito: ");
                        String nombreEli = scanner.nextLine();

                        // Usamos la función eliminar() de la clase ListaProductos
                        Producto devuelto = nuevoCliente.getCarrito().eliminar(nombreEli);

                        if (devuelto != null) {
                            // Importante: Devolvemos las unidades liberadas al inventario de la tienda (Árbol)
                            Producto prodStock = inventario.buscar(nombreEli);
                            if (prodStock != null) {
                                prodStock.setCantidad(prodStock.getCantidad() + devuelto.getCantidad());
                            }
                            System.out.println("¡Producto eliminado del carrito y devuelto al stock de la tienda!");
                        }
                        break;

                    case 4: // VER CARRITO
                        System.out.println("\n--- CARRITO DE " + nuevoCliente.getNombre().toUpperCase() + " ---");
                        nuevoCliente.getCarrito().mostrarInventario();
                        break;

                    case 5: // FINALIZAR
                        System.out.println("Finalizando la selección de productos...");
                        break;

                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error en el ingreso de datos. Intente de nuevo.");
                scanner.nextLine();
            }
        } while (opcCarrito != 5);

        // Al salir del menú del carrito, encolamos al cliente en la Cola de Prioridad
        colaClientes.encolar(nuevoCliente);
        System.out.println("\n¡Cliente " + nuevoCliente.getNombre() + " ingresado a la cola de prioridad (" + nuevoCliente.toString() + ")!");
    }

    // ACCIÓN REQUERIDA 3: Atender al cliente con mayor prioridad e imprimir reporte/factura
    private static void atenderSiguienteCliente() {
        System.out.println("\n=== DESENCOLANDO Y ATENDIENDO CLIENTE ===");

        // Sacamos al cliente correspondiente
        Cliente atendido = colaClientes.atender();

        if (atendido != null) {
            System.out.println("========================================");
            System.out.println("           FACTURA DE VENTA             ");
            System.out.println("========================================");
            // El toString() de Cliente ya maneja el formato de Nombre y Tipo de prioridad
            System.out.println(atendido);
            System.out.println("----------------------------------------");

            // Invocamos el reporte de costos de la estructura ListaProductos propia del cliente
            atendido.getCarrito().reporteCostos();
            System.out.println("========================================");
        }
    }
}
