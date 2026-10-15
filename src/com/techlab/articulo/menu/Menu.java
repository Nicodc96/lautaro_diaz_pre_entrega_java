package com.techlab.articulo.menu;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Menu(){}

    /**
     * Muestra un menú de opciones y solicitará el número correspondiente a la opción seleccionada.
     * En caso de ingresar un valor numérico no válido, informará al usuario.
     * En caso de ingresar un valor NO numérico, informará del error al usuario.
     * @param scanner - referencia al scanner
     * @return <b>int</b> de la opción seleccionada o '-1' en caso de valor numérico no válido.
     */
    public static int menuDeOpciones(Scanner scanner){
        try{
            System.out.print("""
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
                Ingrese una opción: """);
            int opcionSeleccionada = scanner.nextInt();
            List<Integer> listaDeOpciones = Arrays.asList(0,1,2,3,4,5,6);
            while(!listaDeOpciones.contains(opcionSeleccionada)){
                System.out.print("**ERROR** Por favor, seleccione una opción correcta: ");
                opcionSeleccionada = scanner.nextInt();
            }
            return opcionSeleccionada;
        } catch(InputMismatchException e){
            System.out.println("**ERROR**, ¡Se debe ingresar un número como opción! Vuelva a intentar.");
            return -1;
        }
    }

    /**
     * Función con el único propósito de simular una 'pausa' entre selecciones del menú o
     * luego de finalizar alguna operación.
     * @param scanner - referencia al scanner
     */
    public static void enterParaContinuar(Scanner scanner){ 
        System.out.println("Presione 'Enter' para continuar...");
        try{
            System.in.read();
            scanner.nextLine();
        }
        catch(Exception e){}  
    }

    public static String salirDelPrograma(){
        System.out.println("Saliendo del programa...");
        return "no";
    }
}
