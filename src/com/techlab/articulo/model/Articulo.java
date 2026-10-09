package com.techlab.articulo.model;

import java.util.Objects;

public abstract class Articulo {
    private int codigo;
    private String nombre;
    private Double precio;
    private Categoria categoria;

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

    public Double getPrecio(){
        return this.precio;
    }

    public void setPrecio(Double nuevoPrecio){
        if (Objects.isNull(nuevoPrecio) || nuevoPrecio < 0d){
            return;
        }
        this.precio = nuevoPrecio;
    }

    public Categoria getCategoria(){
        return this.categoria;
    }

    public void setCategoria(Categoria nuevaCategoria){
        if (Objects.isNull(nuevaCategoria) || nuevaCategoria.equals(this.categoria)){
            return;
        }
        this.categoria = nuevaCategoria;
    }

    public Articulo(int codigo, String nombre, Double precio, Categoria categoria){
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public abstract String getTipoArticulo();
    public abstract String getDetalleEspecifico();

    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();
        sB.append("Código: ").append(this.codigo).append(System.lineSeparator());
        sB.append("Nombre: ").append(this.nombre).append(System.lineSeparator());
        sB.append(String.format("Precio: $%.2f", this.precio)).append(System.lineSeparator());
        sB.append("Nombre categoría: ").append(this.categoria.getNombre()).append(System.lineSeparator());
        sB.append("Tipo artículo: ").append(this.getTipoArticulo()).append(System.lineSeparator());
        sB.append("Detalle específico: ").append(this.getDetalleEspecifico()).append(System.lineSeparator());

        return sB.toString();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (Objects.isNull(obj) || obj.getClass() != this.getClass()) return false;
        
        Articulo articuloAComparar = (Articulo) obj;
        return this.getCodigo() == articuloAComparar.getCodigo();
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(this.getCodigo());
    }
}
