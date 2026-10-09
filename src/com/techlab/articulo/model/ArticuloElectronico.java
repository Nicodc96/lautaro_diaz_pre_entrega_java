package com.techlab.articulo.model;

import java.util.Objects;

public class ArticuloElectronico extends Articulo{
    private int garantiaMeses;

    public int getGarantiaMeses(){
        return this.garantiaMeses;
    }

    public boolean setGarantiaMeses(int nuevaGarantia){
        if (Objects.isNull(nuevaGarantia) || nuevaGarantia < 0){
            return false;
        }
        this.garantiaMeses = nuevaGarantia;
        return true;
    }

    public ArticuloElectronico(int codigo, String nombre, Double precio, Categoria categoria, int garantiaMeses){
        super(codigo, nombre, precio, categoria);
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String getTipoArticulo(){
        return "Electrónico";
    }

    @Override
    public String getDetalleEspecifico(){
        return String.format("%d mes(es) de garantía", this.garantiaMeses);
    }

    public String nroTelMesaDeAyudaParaReclamos(){
        return "0800-123-4567";
    }

    @Override
    public String toString(){
        StringBuilder sB = new StringBuilder();
        sB.append(super.toString());
        sB.append("[subtipo electrónico]").append(System.lineSeparator());

        return sB.toString();
    }
}
