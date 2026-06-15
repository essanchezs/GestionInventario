# Sistema de Gestión de Inventarios

## Descripción

Aplicación desarrollada en Java para la gestión de inventarios de una tienda en línea.

Este proyecto corresponde al **Primer Avance del Proyecto de Estructuras de Datos (SOFT-10)** de la Universidad CENFOTEC.

La aplicación implementa una **Lista Enlazada Simple** para administrar productos dentro del inventario, permitiendo registrar, modificar, eliminar y consultar información de los productos almacenados.

---

## Objetivos del Proyecto

- Implementar estructuras de datos dinámicas.
- Aplicar listas enlazadas simples para la gestión de inventarios.
- Desarrollar un menú interactivo mediante consola (CLI).
- Gestionar productos utilizando operaciones CRUD.
- Aplicar buenas prácticas de programación orientada a objetos.

---

## Funcionalidades Implementadas

### Gestión de Productos

- Agregar productos al inicio de la lista.
- Agregar productos al final de la lista.
- Buscar productos.
- Modificar productos existentes.
- Eliminar productos.
- Mostrar inventario completo.

### Gestión de Imágenes

Cada producto puede almacenar múltiples imágenes mediante una lista dinámica (`ArrayList<String>`).

- Agregar rutas de imágenes.
- Consultar imágenes asociadas a un producto.

### Reportes

- Mostrar información detallada de cada producto.
- Calcular costo total por producto según su cantidad.
- Calcular costo acumulado total del inventario.

### Menú de Consola

La aplicación cuenta con un menú interactivo que permite acceder a todas las funcionalidades disponibles desde la terminal.

---

## Estructura del Proyecto

```text
src/
│
├── Main.java
│
├── gestionInventario/
│   ├── Producto.java
│   ├── ListaProductos.java

```

---

## Datos del Producto

Cada producto almacena:

| Atributo | Descripción |
|-----------|------------|
| Nombre | Nombre del producto |
| Precio | Precio unitario |
| Categoría | Categoría del producto |
| Fecha de Registro / Vencimiento | Fecha asociada al producto |
| Cantidad | Existencias disponibles |
| Lista de Imágenes | Rutas de imágenes asociadas |

---

## Integrantes

- **Andrés Gabriel Meléndez Carvajal**
- **Esteban Sánchez Sánchez**
- **Azarela Fernández Sibaja**

---

## Curso

**SOFT-10 – Estructuras de Datos**  
Universidad CENFOTEC  
Docente: **Romario Salas Cerdas**

---

## Estado del Proyecto

✅ Primer avance completado

Implementación de:

- Clase Producto
- Nodo de lista enlazada
- Lista enlazada simple
- Operaciones CRUD
- Menú principal
- Reporte de costos
