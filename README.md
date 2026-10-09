# Proyecto CRUD (educativo)

<div align='center'>
<img src="https://i.ibb.co/jkH9gBsC/Back-Java-Banner.jpg" alt="logo talento-tech">
</div>

## Talento Tech (Pre-entrega)

- Autor del proyecto: **Díaz, Lautaro Nicolás**
- Duración del curso: **4 meses**
- Año: **2026**

### Acerca del proyecto

Se solicita una aplicación de consola utilizando una entidad **Articulo** que tenga su correspondiente CRUD con validaciones de tipos de datos, y validación del dato de entrada del usuario. Además, incorporé una entidad extra **Categoria** donde tendré una lista de categorías pre-cargadas que utilizaré para
el testeo del equipo docente.

```java
        ======================================================
        SISTEMA DE ARTÍCULOS ------- PRE-ENTREGA TALENTO TECH
        ======================================================
        1 - Ingresar artículo
        2 - Listar artículos
        3 - Consultar un artículo
        4 - Modificar un artículo
        5 - Eliminar un artículo
        6 - Listar categorías
        0 - Salir
        ======================================================
        Ingrese una opción:
```

### ¿Qué se puede hacer en el proyecto de consola?

- El usuario puede registrar un **Articulo** que contiene: código, nombre, precio, una **Categoria** y un atributo extra, dependiendo de que sea un **ArticuloElectronico**, **ArticuloAlimenticio** o **ArticuloGenerico**.

- El usuario puede acceder a la información de todos los artículos registrados.

- El usuario puede eliminar y/o modificar un artículo registrado **(no podrá acceder a este sub-menú si no existen productos registrados)**.

- Adicionalmente, puede consultar un artículo mediante su *Código*, el cual ingresará a mano.

```java
// Ejemplo
---- Códigos de artículos actuales ----
1) - 100
2) - 642
3) - 413
4) - 6
5) - 19064
```

- Posteriormente verá la información de cada uno:

```java
// Ejemplo
Código: 541
Nombre: Auriculares audiotechnica ath m30x
Precio: $215999,75
Nombre categoría: Periféricos
Tipo artículo: Electrónico
Detalle específico: 12 mes(es) de garantía
[subtipo electrónico] // si es del tipo ArticuloElectronico
```

## Importante

- El proyecto posee 5 **categorías** pre-cargadas a pedido de las consignas brindadas, estas se pueden visualizar en el menú:

```java
---- Lista de categorías registradas ----

Código: 1
Nombre: Electrónica
Descripción: Productos tecnológicos y electrónicos

Código: 2
Nombre: Periféricos
Descripción: Accesorios para computadora

Código: 3
Nombre: Alimentos
Descripción: Productos alimenticios

Código: 4
Nombre: Limpieza
Descripción: Artículos de limpieza para el hogar

Código: 5
Nombre: Otros
Descripción: Variados
```

## ¿Cómo utilizar este proyecto?

- Descargar **Visual Studio Code** y abrir este repositorio como carpeta de proyecto.
- Utilizar Java JDK 17 o superior.
- Iniciar el proyecto desde el archivo Main.java
