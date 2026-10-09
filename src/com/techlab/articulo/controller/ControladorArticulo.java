package com.techlab.articulo.controller;

import com.techlab.articulo.model.*;
import com.techlab.articulo.utils.Validador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Objects;

public class ControladorArticulo {
    private ControladorArticulo(){}

    /**
     * Gestión de la operación para agregar un objeto de la clase <b>Artículo</b> en la lista de artículos pasado por
     * parámetro. Se pedirá dato a dato según corresponda tipo de artículo y <b>Categoría</b>. En caso de éxito en la
     * creación y posterior aprobación del usuario, se agregará el nuevo objeto a la lista y se informará al usuario. En 
     * caso contrario se informará la cancelación o cualquier error generado.
     * @param listaDeArticulos - referencia a la lista de artículos
     * @param listaDeCategorias - referencia a la lista de categorías
     * @param scanner - referencia al scanner
     */
    public static void agregarArticulo(ArrayList<Articulo> listaDeArticulos, ArrayList<Categoria> listaDeCategorias, Scanner scanner){
        String ingresoDeDato = "";
        int codigoArticuloTemp = 0;
        String nombreArticuloTemp = "";
        Double precioArticuloTemp = 0d;
        Articulo articuloACrear;

        subMenuAgregarArticulo();
        scanner.nextLine(); // Limpio el buffer
        ingresoDeDato = scanner.nextLine();
        while(!ingresoDeDato.equalsIgnoreCase("1") 
                && !ingresoDeDato.equalsIgnoreCase("2")
                    && !ingresoDeDato.equalsIgnoreCase("3")){
            System.out.print("""
                    **ERROR** Debe ingresar las opciones '1', '2' o '3', reingrese: 
                    """);
            ingresoDeDato = scanner.nextLine();
        }

        System.out.print("Ingrese el código del artículo a registrar (o -1 para cancelar operación): ");
        ingresoDeDato = scanner.nextLine();
        try{
            int codigoTemp = Integer.parseInt(ingresoDeDato);
            if (evaluarSalida(ingresoDeDato)) return;
            if(listaDeArticulos.stream().anyMatch(p -> p.getCodigo() == codigoTemp)){
                System.out.println("¡El código del artículo ya se encuentra registrado! Operación cancelada.");
                return;
            }
            codigoArticuloTemp = codigoTemp;

            System.out.print("Ingrese el nombre del artículo a registrar (o -1 para cancelar operación): ");
            ingresoDeDato = scanner.nextLine();
            if (evaluarSalida(ingresoDeDato)) return;

            while (!Validador.validarNombreArticulo(ingresoDeDato)){
                System.out.println("""
                        ¡Se ha ingresado un nombre incorrecto!

                        - No debe escribirse sólo números.
                        - El nombre debe tener mínimo 3 caracteres y como máximo 50 caracteres.
                        - Solo se aceptan los caracteres especiales: . : ; , "

                        Reingrese: """);
                ingresoDeDato = scanner.nextLine();
            }
            // Formateo el string para que la primera letra sea mayúscula y el resto minúsculas.
            nombreArticuloTemp = ingresoDeDato.substring(0, 1).toUpperCase() +
                                    ingresoDeDato.substring(1).toLowerCase();
            
            System.out.print("Ingrese el precio del artículo a registrar (o -1 para cancelar operación): ");
            ingresoDeDato = scanner.nextLine();
            if (evaluarSalida(ingresoDeDato)) return;
            while(!Validador.validarPrecioArticulo(Double.parseDouble(ingresoDeDato))){
                System.out.print("""
                        ¡Se ha ingresado un precio incorrecto!

                        - El precio no debe ser negativo.

                        Reingrese: """);
                ingresoDeDato = scanner.nextLine();
            }
            precioArticuloTemp = Double.parseDouble(ingresoDeDato);
            
            listarCategorias(listaDeCategorias);
            System.out.print("Ingrese el número de categoría que corresponderá al artículo (o -1 para cancelar la operación): ");
            ingresoDeDato = scanner.nextLine();
            if (evaluarSalida(ingresoDeDato)) return;

            List<Integer> listaDeOpciones = Arrays.asList(1, 2, 3, 4, 5);

            while(!listaDeOpciones.contains(Integer.parseInt(ingresoDeDato))){
                listarCategorias(listaDeCategorias);
                System.out.print("***ERROR*** Por favor, seleccione una categoría correcta: ");
                ingresoDeDato = scanner.nextLine();
            }
            int codigoCategoriaSeleccionado = Integer.parseInt(ingresoDeDato);

            switch(Integer.parseInt(ingresoDeDato)){
                case 1:
                case 2:
                    int mesesGarantiaArticulo = 1;
                    System.out.print("Ingrese la cantidad de meses de garantía: ");
                    ingresoDeDato = scanner.nextLine();

                    while(Integer.parseInt(ingresoDeDato) <= 0){
                        System.out.print("""
                        ¡Se ha ingresado un valor incorrecto!

                        - La cantidad de meses de garantía no puede ser negativo.

                        Reingrese: """);
                        ingresoDeDato = scanner.nextLine();
                    }
                    mesesGarantiaArticulo = Integer.parseInt(ingresoDeDato);
                    articuloACrear = new ArticuloElectronico(
                            codigoArticuloTemp, 
                            nombreArticuloTemp, 
                            precioArticuloTemp,
                            buscarCategoria(listaDeCategorias, codigoCategoriaSeleccionado),
                            mesesGarantiaArticulo);
                    break;
                case 3:
                    int diasVencimientoArticulo = 1;
                    System.out.print("Ingrese la cantidad de días de vencimiento del artículo: ");
                    ingresoDeDato = scanner.nextLine();

                    while(Integer.parseInt(ingresoDeDato) <= 0){
                    System.out.print("""
                        ¡Se ha ingresado un valor incorrecto!

                        - La cantidad de días de vencimiento no puede ser negativo.

                        Reingrese: """);
                    ingresoDeDato = scanner.nextLine();
                    }
                    diasVencimientoArticulo = Integer.parseInt(ingresoDeDato);
                    articuloACrear = new ArticuloAlimenticio(
                        codigoArticuloTemp,
                        nombreArticuloTemp,
                        precioArticuloTemp,
                        buscarCategoria(listaDeCategorias, codigoCategoriaSeleccionado),
                        diasVencimientoArticulo);
                    break;
                default:
                    Double pesoArticulo = 1d;
                    System.out.print("Ingrese el peso del artículo: ");
                    ingresoDeDato = scanner.nextLine();

                    while(Double.parseDouble(ingresoDeDato) <= 0){
                    System.out.print("""
                        ¡Se ha ingresado un valor incorrecto!

                        - La cantidad del peso no puede ser negativo.

                        Reingrese: """);
                    ingresoDeDato = scanner.nextLine();
                    }
                    pesoArticulo = Double.parseDouble(ingresoDeDato);
                    articuloACrear = new ArticuloGenerico(
                        codigoArticuloTemp,
                        nombreArticuloTemp,
                        precioArticuloTemp,
                        buscarCategoria(listaDeCategorias, codigoCategoriaSeleccionado),
                        pesoArticulo);
                    break;
            }
            System.out.printf("""
                    ¿Confirma ingresar el siguiente artículo?

                    Código: %d
                    Nombre: %s
                    Precio: $%.2f
                    Categoría: %s

                    Ingrese "si" para confirmar o "no" para cancelar: """
                    , articuloACrear.getCodigo(), articuloACrear.getNombre(), articuloACrear.getPrecio(), articuloACrear.getCategoria().getNombre());
            ingresoDeDato = scanner.nextLine();

            while (!Validador.validarSioNo(ingresoDeDato)) {
                System.out.print("Por favor, ingrese únicamente 'si' para confirmar o 'no' para cancelar: ");
                ingresoDeDato = scanner.nextLine();
            }

            if (ingresoDeDato.equals("no")) {
                System.out.println("Operación cancelada. Volviendo al menú principal...");
                return;
            }
            listaDeArticulos.add(articuloACrear);
            System.out.println("¡Artículo creado exitosamente!");
        }catch (NumberFormatException e){
            System.out.println("Se ha ingresado un valor numérico incorrecto. Operación cancelada.");
        } catch (InputMismatchException e) {
            System.out.println("**ERROR**, se ha ingresado incorrectamente el dato solicitado. Operación cancelada.");
        } 
    }

    /**
     * Muestra una a una y de manera formateada los artículos de la lista pasada por parámetro. En caso de no poseer 
     * ningún <b>Articulo</b> se informará al usuario de la lista vacía.
     * @param listaDeArticulos - referencia a la lista de artículos
     */
    public static void listarArticulos(ArrayList<Articulo> listaDeArticulos){
        if (listaDeArticulos.size() == 0) {
            System.out.println("¡No hay artículos registrados actualmente!");
            return;
        }
        System.out.println("---- Lista de Artículos actualmente ----\n");
        for (Articulo Articulo : listaDeArticulos) {
            System.out.println(Articulo);
        }
    }

    /**
     * Busca un <b>Articulo</b> en la lista pasada por parámetro según el código. Se muestra al usuario únicamente
     * una lista de códigos, uno debajo del otro, de artículos registrados.
     * En caso de no haber ningún artículo en la lista, se informará al usuario.
     * En caso de no encontrar el artículo en la lista no vacía con el código provisto, se informará al usuario.
     * @param listaDeArticulos - referencia a la lista de artículos
     * @param articuloParam - articulo encontrado por su código para re-utilizar
     * @param scanner - referencia al scanner
     * @return El <b>Articulo</b> encontrado o <b>null</b> si no se encuentra o la lista está vacía.
     */
    public static Articulo consultarDevolverArticulo(ArrayList<Articulo> listaDeArticulos, Scanner scanner){
        if (listaDeArticulos != null && listaDeArticulos.size() == 0){
            System.out.println("¡No existen artículos registrados actualmente!");
            return null;
        }
        try{
            System.out.println("---- Códigos de artículos actuales ----");
            for(int i = 0; i < listaDeArticulos.size(); i++){
                System.out.printf("%d) - %d\n", i+1, listaDeArticulos.get(i).getCodigo());
            }
            scanner.nextLine(); // Limpiar el buffer

            Articulo articuloABuscar = buscarArticulo(scanner, listaDeArticulos, "buscar");

            if (Objects.isNull(articuloABuscar)) return null; // Si no existe el artículo termino la función

            System.out.println(articuloABuscar);
            return articuloABuscar;
        }catch(InputMismatchException e){
            System.out.println("El código a registrar DEBE ser numérico. Consulta cancelada.");
            return null;
        }
    }

    /**
     * Busca un <b>Articulo</b> en la lista pasada por parámetro según el código. Se muestra al usuario únicamente
     * una lista de códigos, uno debajo del otro, de artículos registrados.
     * En caso de no haber ningún artículo en la lista, se informará al usuario.
     * En caso de no encontrar el artículo en la lista no vacía con el código provisto, se informará al usuario.
     * @param listaDeArticulos - referencia a la lista de artículos
     * @param scanner - referencia al scanner
     */
    public static void consultarArticulo(ArrayList<Articulo> listaDeArticulos, Scanner scanner){
        consultarDevolverArticulo(listaDeArticulos, scanner);
    }

    public static void modificarArticulo(ArrayList<Articulo> listaDeArticulos, ArrayList<Categoria> listaDeCategorias, Scanner scanner){
        if (listaDeArticulos.size() == 0) {
            System.out.println("¡No hay artículos registrados actualmente!");
            return;
        }

        Articulo articuloABuscar = consultarDevolverArticulo(listaDeArticulos, scanner);
        String ingresoDeDato = "";

        if (Objects.isNull(articuloABuscar)) return;

        try{
            String nuevoNombre = "";
            Double nuevoPrecio = 0d;

            System.out.print("Ingrese el nuevo nombre para el artículo (o -1 para cancelar operación): ");
            scanner.nextLine(); // Limpiar el buffer
            ingresoDeDato = scanner.nextLine();
            if (evaluarSalida(ingresoDeDato)) return;

            while (!Validador.validarNombreArticulo(ingresoDeDato)){
                System.out.println("""
                        ¡Se ha ingresado un nombre incorrecto!

                        - No debe escribirse sólo números.
                        - El nombre debe tener mínimo 3 caracteres y como máximo 50 caracteres.
                        - Solo se aceptan los caracteres especiales: . : ; , "

                        Reingrese: """);
                ingresoDeDato = scanner.nextLine();
            }
            // Formateo el string para que la primera letra sea mayúscula y el resto minúsculas.
            nuevoNombre = ingresoDeDato.substring(0, 1).toUpperCase() +
                                    ingresoDeDato.substring(1).toLowerCase();

            System.out.print("Ingrese el nuevo precio para el artículo (o -1 para cancelar operación): ");
            ingresoDeDato = scanner.nextLine();
            if (evaluarSalida(ingresoDeDato)) return;
            while(!Validador.validarPrecioArticulo(Double.parseDouble(ingresoDeDato))){
                System.out.print("""
                        ¡Se ha ingresado un precio incorrecto!

                        - El precio no debe ser negativo.

                        Reingrese: """);
                ingresoDeDato = scanner.nextLine();
            }
            nuevoPrecio = Double.parseDouble(ingresoDeDato);

            listarCategorias(listaDeCategorias);
            System.out.print("Ingrese el número de la nueva categoría que corresponderá al artículo: ");
            ingresoDeDato = scanner.nextLine();
            
            List<Integer> listaDeOpciones = Arrays.asList(1, 2, 3, 4, 5);

            while(!listaDeOpciones.contains(Integer.parseInt(ingresoDeDato))){
                listarCategorias(listaDeCategorias);
                System.out.print("***ERROR*** Por favor, seleccione una categoría correcta: ");
                ingresoDeDato = scanner.nextLine();
            }
            int codigoCategoriaSeleccionado = Integer.parseInt(ingresoDeDato);

            switch(codigoCategoriaSeleccionado){
                case 1:
                case 2:
                    int mesesGarantiaArticulo = 0;
                    System.out.print("Ingrese la nueva cantidad de meses de garantía: ");
                    ingresoDeDato = scanner.nextLine();

                    while(Integer.parseInt(ingresoDeDato) <= 0){
                        System.out.print("""
                        ¡Se ha ingresado un valor incorrecto!

                        - La cantidad de meses de garantía no puede ser negativo.

                        Reingrese: """);
                        ingresoDeDato = scanner.nextLine();
                    }
                    mesesGarantiaArticulo = Integer.parseInt(ingresoDeDato);
                    System.out.println(mostrarArticuloModificar(articuloABuscar, 
                                nuevoNombre, 
                                nuevoPrecio, 
                                buscarCategoria(listaDeCategorias, codigoCategoriaSeleccionado), 
                                null, 
                                mesesGarantiaArticulo, 
                                null));
                    ingresoDeDato = scanner.nextLine();

                    while (!Validador.validarSioNo(ingresoDeDato)) {
                        System.out.print("Por favor, ingrese únicamente 'si' para confirmar o 'no' para cancelar: ");
                        ingresoDeDato = scanner.nextLine();
                    }

                    if (ingresoDeDato.equals("no")) {
                        System.out.println("Operación cancelada. Volviendo al menú principal...");
                        return;
                    }

                    articuloABuscar.setNombre(nuevoNombre);
                    articuloABuscar.setPrecio(nuevoPrecio);
                    buscarCategoria(listaDeCategorias, codigoCategoriaSeleccionado);
                    ((ArticuloElectronico)articuloABuscar).setGarantiaMeses(mesesGarantiaArticulo);
                    break;
                case 3:
                    int nuevosDiasVencimiento = 0;
                    System.out.print("Ingrese la nueva cantidad de días de vencimiento del artículo: ");
                    ingresoDeDato = scanner.nextLine();

                    while(Integer.parseInt(ingresoDeDato) <= 0){
                        System.out.print("""
                            ¡Se ha ingresado un valor incorrecto!

                            - La cantidad de días de vencimiento no puede ser negativo.

                            Reingrese: """);
                        ingresoDeDato = scanner.nextLine();
                    }
                    nuevosDiasVencimiento = Integer.parseInt(ingresoDeDato);
                    
                    System.out.println(mostrarArticuloModificar(articuloABuscar, 
                                nuevoNombre, 
                                nuevoPrecio, 
                                buscarCategoria(listaDeCategorias, codigoCategoriaSeleccionado), 
                                nuevosDiasVencimiento, 
                                null, 
                                null));
                    ingresoDeDato = scanner.nextLine();

                    while (!Validador.validarSioNo(ingresoDeDato)) {
                        System.out.print("Por favor, ingrese únicamente 'si' para confirmar o 'no' para cancelar: ");
                        ingresoDeDato = scanner.nextLine();
                    }

                    if (ingresoDeDato.equals("no")) {
                        System.out.println("Operación cancelada. Volviendo al menú principal...");
                        return;
                    }

                    articuloABuscar.setNombre(nuevoNombre);
                    articuloABuscar.setPrecio(nuevoPrecio);
                    buscarCategoria(listaDeCategorias, codigoCategoriaSeleccionado);
                    ((ArticuloAlimenticio)articuloABuscar).setDiasParaVencimiento(nuevosDiasVencimiento);
                    break;
                default:
                    Double nuevoPeso = 1d;
                    System.out.print("Ingrese el nuevo peso del artículo: ");
                    ingresoDeDato = scanner.nextLine();

                    while(Double.parseDouble(ingresoDeDato) <= 0d){
                        System.out.print("""
                            ¡Se ha ingresado un valor incorrecto!

                            - La cantidad del peso no puede ser negativo.

                            Reingrese: """);
                        ingresoDeDato = scanner.nextLine();
                    }
                    nuevoPeso = Double.parseDouble(ingresoDeDato);
                    System.out.println(mostrarArticuloModificar(articuloABuscar, 
                                nuevoNombre, 
                                nuevoPrecio, 
                                buscarCategoria(listaDeCategorias, codigoCategoriaSeleccionado), 
                                null, 
                                null, 
                                nuevoPeso));
                    ingresoDeDato = scanner.nextLine();

                    while (!Validador.validarSioNo(ingresoDeDato)) {
                        System.out.print("Por favor, ingrese únicamente 'si' para confirmar o 'no' para cancelar: ");
                        ingresoDeDato = scanner.nextLine();
                    }

                    if (ingresoDeDato.equals("no")) {
                        System.out.println("Operación cancelada. Volviendo al menú principal...");
                        return;
                    }

                    articuloABuscar.setNombre(nuevoNombre);
                    articuloABuscar.setPrecio(nuevoPrecio);
                    articuloABuscar.setCategoria(buscarCategoria(listaDeCategorias, codigoCategoriaSeleccionado));
                    ((ArticuloGenerico)articuloABuscar).setPeso(nuevoPeso);
                    break;
            }
            System.out.println("¡Artículo modificado correctamente!");
        }catch(NumberFormatException e){
            System.out.println("Se ha ingresado un valor numérico incorrecto. Operación cancelada.");
        }
    }

    /**
     * Función interna que utilizará únicamente la función <b>modificarArticulo()</b>. Según el tipo de
     * artículo que sea, separará por instancias y mostrará un mensaje personalizado con los viejos datos
     * del artículo y los nuevos para modificar, con el consecuente pedido de confirmación.
     * @param articulo - referencia del <b>Articulo</b>
     * @param nuevoNombre - nuevo nombre para el artículo
     * @param nuevoPrecio - nuevo precio para el artículo
     * @param nuevaCategoria - nueva <b>Categoria</b> para el artículo
     * @param nuevosDiasVencimiento - nuevos días de vencimiento, en caso de ser <b>ArticuloAlimenticio</b>
     * @param nuevosMesesGarantia - nuevos meses de garantía, en caso de ser <b>ArticuloElectronico</b>
     * @param nuevoPeso - nuevo peso para el artículo, en caso de ser <b>ArticuloGenerico</b>
     * @return El mensaje personalizado según la instancia y solicitando una confirmación de modificación.
     */
    private static String mostrarArticuloModificar(Articulo articulo, String nuevoNombre, 
        Double nuevoPrecio, Categoria nuevaCategoria, Integer nuevosDiasVencimiento, Integer nuevosMesesGarantia, Double nuevoPeso){
        StringBuilder sB = new StringBuilder();
        if (articulo instanceof ArticuloElectronico){
            sB.append(String.format("""

                    Tipo de artículo: %s

                    Nombre anterior: %s | Nuevo nombre: %s
                    Precio anterior: %.2f | Nuevo precio: %.2f
                    Categoría anterior: %s | Nueva categoría: %s
                    Meses de garantía anterior: %d | Nueva cantidad: %d

                    """,articulo.getClass().getSimpleName(),
                        articulo.getNombre(), nuevoNombre,
                        articulo.getPrecio(), nuevoPrecio,
                        articulo.getCategoria().getNombre(), nuevaCategoria.getNombre(),
                        ((ArticuloElectronico)articulo).getGarantiaMeses(), nuevosMesesGarantia))
                        .append(System.lineSeparator());
        }
        if (articulo instanceof ArticuloAlimenticio){
            sB.append(String.format("""

                    Tipo de artículo: %s

                    Nombre anterior: %s | Nuevo nombre: %s
                    Precio anterior: %.2f | Nuevo precio: %.2f
                    Categoría anterior: %s | Nueva categoría: %s
                    Días de vencimiento anterior: %d | Nueva cantidad de días: %d

                    """,articulo.getClass().getSimpleName(),
                        articulo.getNombre(), nuevoNombre,
                        articulo.getPrecio(), nuevoPrecio,
                        articulo.getCategoria().getNombre(), nuevaCategoria.getNombre(),
                        ((ArticuloAlimenticio)articulo).getDiasParaVencimiento(), (int)nuevosDiasVencimiento))
                        .append(System.lineSeparator());
        }
        sB.append(String.format("""

                    Tipo de artículo: %s

                    Nombre anterior: %s | Nuevo nombre: %s
                    Precio anterior: %.2f | Nuevo precio: %.2f
                    Categoría anterior: %s | Nueva categoría: %s
                    Peso anterior: %.2f | Nuevo peso: %.2f

                    """,articulo.getClass().getSimpleName(),
                        articulo.getNombre(), nuevoNombre,
                        articulo.getPrecio(), nuevoPrecio,
                        articulo.getCategoria().getNombre(), nuevaCategoria.getNombre(),
                        ((ArticuloGenerico)articulo).getPeso(), nuevoPeso))
                        .append(System.lineSeparator());
        // Asumo que es ArticuloGenérico
        sB.append("¿Confirma la modificación del artículo? Escriba 'si' o 'no': ");
        return sB.toString();
    }

    /**
     * Elimina un <b>Articulo</b> de la lista pasada por parámetro. Se muestra al usuario
     * una lista de códigos, uno debajo del otro, de artículos registrados para seleccionar. Una
     * vez seleccionado un código válido, se pedirá confirmación y notificará según caso positivo o negativo.
     * En caso de no encontrar el artículo con el código provisto, se informará al usuario.
     * En caso de que la lista esté vacía, se informará al usuario.
     * @param listaDeArticulos - referencia a la lista de artículos
     * @param scanner - referencia al scanner
     */
    public static void eliminarArticulo(ArrayList<Articulo> listaDeArticulos, Scanner scanner){
        // Reciclo el método consultarArticulo y obtengo la referencia del artículo.
        Articulo articuloABuscar = consultarDevolverArticulo(listaDeArticulos, scanner);
        String datoIngresado = "";

        if (Objects.isNull(articuloABuscar)) return;

        System.out.printf("¿Confirma eliminar el artículo de código %d? Ingrese 'si' o 'no': ", articuloABuscar.getCodigo());
        scanner.nextLine(); // Limpiar el buffer
        datoIngresado = scanner.nextLine();
        while (!Validador.validarSioNo(datoIngresado)) {
            System.out.print("Por favor, ingrese únicamente 'si' para confirmar o 'no' para cancelar: ");
            datoIngresado = scanner.nextLine();
        }

        if (datoIngresado.equals("no")) {
            System.out.println("Operación cancelada.");
            return;
        }

        System.out.println(listaDeArticulos.remove(articuloABuscar) ? "Artículo eliminado correctamente." : "No se ha podido eliminar el artículo");
    }

    /**
     * Muestra una a una y de manera formateada las categorías de la lista pasada por parámetro. En caso de no poseer 
     * ninguna <b>Categoria</b> se informará al usuario de la lista vacía.
     * @param listaDeCategorias - referencia a la lista de categorías
     */
    public static void listarCategorias(ArrayList<Categoria> listaDeCategorias){
        if (listaDeCategorias.size() == 0) {
            System.out.println("¡No hay categorías registradas actualmente!");
            return;
        }
        System.out.println("---- Lista de categorías registradas ----\n");
        for (Categoria categoria : listaDeCategorias) {
            System.out.println(categoria);
        }
    }

    /**
     * Función interna con la única finalidad de buscar un articulo en la lista de articulos según su código.
     * Si no se encuentra el articulo, se retorna null.
     * @param scanner - referencia al scanner
     * @param listaDeArticulos - referencia a la lista de artículos
     * @param operacion - el tipo de operación para mostrar en mensaje (ej: 'eliminar', 'modificar')
     * @return El <b>Artículo</b> encontrado o <b>null</b> si no se encuentra.
     * @throws InputMismatchException En caso de que el código ingresado no sea un número.
     */
    private static Articulo buscarArticulo(Scanner scanner, ArrayList<Articulo> listaDeArticulos, String operacion) throws InputMismatchException {
        System.out.printf("Ingrese el código del artículo a %s: ", operacion);
        int codigoIngresado = scanner.nextInt();
        if (!listaDeArticulos.stream().anyMatch(p -> p.getCodigo() == codigoIngresado)) {
            System.out.println("¡No se ha encontrado ningún artículo con el ID ingresado!");
            return null;
        }
        return listaDeArticulos.stream().filter(p -> p.getCodigo() == codigoIngresado).collect(Collectors.toList()).get(0);
    }

    private static Categoria buscarCategoria(ArrayList<Categoria> listaDeCategorias, int codigo){
        return listaDeCategorias.stream().filter((c) -> c.getCodigo() == codigo).findFirst().orElse(null);
    }
    /**
     * Función interna con la única finalidad de evaluar si el usuario desea salir de la operación actual.
     * @param datoEntrada
     * @return true si el usuario ha ingresado -1, false en caso contrario.
     */
    private static boolean evaluarSalida(String datoEntrada) {
        if (datoEntrada.equals("-1")) {
            System.out.println("Se ha cancelado la operación.");
            return true;
        }
        return false;
    }

    /**
     * Función interna que sólo muestra un sub-menú de tipos de artículos a registrar
     * mediante el uso de un System.out.print()
     */
    private static void subMenuAgregarArticulo(){
        System.out.print("""
                --- INGRESAR ARTÍCULO ---
                1 - Artículo electrónico
                2 - Artículo alimenticio
                3 - Artículo genérico

                Seleccione el tipo de artículo: 
                """);
    }
}