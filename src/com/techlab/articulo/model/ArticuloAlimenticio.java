package com.techlab.articulo.model;

import java.util.Objects;
public class ArticuloAlimenticio extends Articulo {
    private int diasParaVencimiento;

    public int getDiasParaVencimiento(){
        return this.diasParaVencimiento;
    }

    public void setDiasParaVencimiento(int nuevaCantidad){
        if (Objects.isNull(nuevaCantidad) || nuevaCantidad < 0){
            return;
        }
        this.diasParaVencimiento = nuevaCantidad;
    }
    
    public ArticuloAlimenticio(int codigo, String nombre, Double precio, Categoria categoria, int diasParaVencimiento){
        super(codigo, nombre, precio, categoria);
        this.diasParaVencimiento = diasParaVencimiento;
    }

    @Override
    public String getTipoArticulo(){
        return "Alimenticio";
    }

    @Override
    public String getDetalleEspecifico(){
        return String.format("%d día(s) restante(s) de vencimiento", this.diasParaVencimiento);
    }

    @Override
    public String toString(){
        StringBuilder sB = new StringBuilder();
        sB.append(super.toString());
        sB.append("[subtipo alimenticio]").append(System.lineSeparator());

        return sB.toString();
    }
}
