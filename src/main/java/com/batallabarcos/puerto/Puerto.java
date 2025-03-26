package com.batallabarcos.puerto;

class Puerto {
    String nombre;
    public Puerto(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}