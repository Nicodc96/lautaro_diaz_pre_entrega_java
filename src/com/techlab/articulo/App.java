package com.techlab.articulo;

import com.techlab.articulo.menu.*;
import com.techlab.articulo.model.*;
import com.techlab.articulo.controller.*;

import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        String continuar = "si";
        Scanner scanner = new Scanner(System.in);
        ArrayList<Articulo> listaDeArticulos = new ArrayList<>();
        ArrayList<Categoria> listaDeCategorias = new ArrayList<>();

        // Pre-cargado de categorias
        listaDeCategorias = precargarCategorias();

        do{ 
            switch(Menu.menuDeOpciones(scanner)){
                case 0 -> continuar = Menu.salirDelPrograma();
                case 1 -> ControladorArticulo.agregarArticulo(listaDeArticulos, listaDeCategorias, scanner);
                case 2 -> ControladorArticulo.listarArticulos(listaDeArticulos);
                case 3 -> ControladorArticulo.consultarArticulo(listaDeArticulos, scanner);
                case 4 -> ControladorArticulo.modificarArticulo(listaDeArticulos, listaDeCategorias, scanner);
                case 5 -> ControladorArticulo.eliminarArticulo(listaDeArticulos, scanner);
                case 6 -> ControladorArticulo.listarCategorias(listaDeCategorias);
            }
            Menu.enterParaContinuar(scanner);
        }while(continuar.equalsIgnoreCase("si"));
    }

    private static ArrayList<Categoria> precargarCategorias(){
        ArrayList<Categoria> categoriasACargar = new ArrayList<>();
        categoriasACargar.add(new Categoria(1, "Electrónica", "Productos tecnológicos y electrónicos"));
        categoriasACargar.add(new Categoria(2, "Periféricos", "Accesorios para computadora"));
        categoriasACargar.add(new Categoria(3, "Alimentos", "Productos alimenticios"));
        categoriasACargar.add(new Categoria(4, "Limpieza", "Artículos de limpieza para el hogar"));
        categoriasACargar.add(new Categoria(5, "Otros", "Variados"));
        return categoriasACargar;
    }
}
