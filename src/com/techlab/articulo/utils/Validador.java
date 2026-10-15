package com.techlab.articulo.utils;

public class Validador {
    private Validador() {
    }

    /**
     * Verifica que el <b>String</b> pasado por parámetro contenga "si" o "no" indenpendientemente
     * de las máyusculas/minúsculas
     * @param input - referencia al <b>String</b>
     * @return <b>true</b> Si cumple estas condiciones, de lo contrario <b>false</b>
     */
    public static boolean validarSioNo(String input){
        return input.equalsIgnoreCase("si") || input.equalsIgnoreCase("no");
    }

    /**
     * Verifica que se cumplan las condiciones de <b>validar()<b>, que tenga un mínimo de 3 caracteres
     * y un máximo de 50 caracteres.
     * @param descAValidar - referencia al String a validar
     * @return <b>true</b> Si cumple estas condiciones, de lo contrario <b>false</b>
     */
    public static boolean validarNombreArticulo(String nombreAValidar) {
        return validar(nombreAValidar, 3, 50);
    }

    // Si el dato de tipo double ingresado es menor a 0, se considera inválido. De lo contrario, es válido.
    /**
     * Verifica que valor del <b>Double</b> pasado por parámetro no sea negativo, ni exceda los límites de Double.
     * @param precioAValidar- referencia al <b>Double</b> a validar
     * @return <b>true</b> Si cumple estas condiciones, de lo contrario <b>false</b>
     */
    public static boolean validarPrecioArticulo(Double precioAValidar) {
        if (precioAValidar <= 0d) return false;
        if (!Double.isFinite(precioAValidar)) return false;
        return true;
    }

    /**
     * Verifica que se cumplan las condiciones de <b>validar()<b>, que tenga un mínimo de 0 caracteres
     * y un máximo de 100 caracteres.
     * @param descAValidar - referencia al <b>String</b> a validar
     * @return <b>true</b> Si cumple estas condiciones, de lo contrario <b>false</b>
     */
    public static boolean validarDescripcionCategoria(String descAValidar) {
        return validar(descAValidar, 0, 100);
    }

    /**
     * Función interna de validación que verifica:
        - El texto ingresado puede tener números, espacios, letras y acentos españoles.
        - El texto ingresado NO pueda tener sólo números
        - El texto ingresado debe respetar un mínimo y un máximo de longitud
        - Acepta los siguientes caracteres especiales: . , : ; -"
     * @param strAValidar - referencia al String a validar
     * @param minimo - mínimo de caracteres que debe tener la cadena de texto
     * @param maximo - máximo de caracteres que debe tener la cadena de texto
     * @return <b>true</b> Si cumple estas condiciones, de lo contrario <b>false</b>
     */
    private static boolean validar(String strAValidar, int minimo, int maximo) {
        if (!strAValidar.matches("^(?=.*[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ])[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ0-9 .:,\";-]+$"))
            return false;
        if (strAValidar.length() < minimo || strAValidar.length() > maximo)
            return false;
        return true;
    }
}