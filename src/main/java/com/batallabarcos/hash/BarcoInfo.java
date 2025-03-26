package com.batallabarcos.hash;

import java.util.List;

class BarcoInfo {
    int numero;
    String nombre;
    List<String> tipos;
    int nivel;

    public BarcoInfo(int numero, String nombre, List<String> tipos, int nivel) {
        this.numero = numero;
        this.nombre = nombre;
        this.tipos = tipos;
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Barco{" + "num=" + numero + ", nombre='" + nombre + '\'' + ", tipos=" + tipos + ", nivel=" + nivel + '}';
    }
}