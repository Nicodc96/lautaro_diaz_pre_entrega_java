package com.techlab.articulo.model;

import java.util.Objects;

public class Categoria {
    private int codigo;
    private String nombre;
    private String descripcion;

    public int getCodigo(){
        return this.codigo;
    }

    public void setCodigo(int nuevoCodigo){
        if (Objects.isNull(nuevoCodigo) || nuevoCodigo < 0){
            return;
        }
        this.codigo = nuevoCodigo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nuevoNombre){
        if (Objects.isNull(nuevoNombre) || nuevoNombre.length() <= 2){
            return;
        }
        this.nombre = nuevoNombre;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public void setDescripcion(String nuevaDescripcion){
        if (Objects.isNull(nuevaDescripcion) || nuevaDescripcion.length() <= 0){
            return;
        }
        this.descripcion = nuevaDescripcion;
    }

    public Categoria(int codigo, String nombre, String descripcion){
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();
        sB.append("Código: ").append(this.codigo).append(System.lineSeparator());
        sB.append("Nombre: ").append(this.nombre).append(System.lineSeparator());
        sB.append("Descripción: ").append(this.descripcion).append(System.lineSeparator());

        return sB.toString();
    }
}
