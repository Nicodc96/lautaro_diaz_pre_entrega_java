package com.techlab.articulo.model;

import java.util.Objects;

public class ArticuloGenerico extends Articulo {
    private Double peso;

    public Double getPeso(){
        return this.peso;
    }

    public boolean setPeso(Double nuevoPeso){
        if (Objects.isNull(nuevoPeso) || nuevoPeso <= 0d) return false;
        this.peso = nuevoPeso;
        return true;
    }

    public ArticuloGenerico(int codigo, String nombre, Double precio, Categoria categoria, Double peso){
        super(codigo, nombre, precio, categoria);
        this.peso = peso;
    }

    @Override
    public String getTipoArticulo(){
        return "Genérico";
    }

    @Override
    public String getDetalleEspecifico(){
        return String.format("Peso: %.2f kg", this.peso);
    }

    @Override
    public String toString(){
        StringBuilder sB = new StringBuilder();
        sB.append(super.toString());
        sB.append("[subtipo genérico]").append(System.lineSeparator());

        return sB.toString();
    }
}
