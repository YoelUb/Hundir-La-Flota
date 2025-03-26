package com.batallabarcos.hash;

import java.util.Arrays;

public class BarcoHashManager {
    private HashTable<HashTable<BarcoInfo>> tipoTabla;
    private final int TIPO_TABLA_SIZE = 10;
    private final int SECUNDARIA_SIZE = 15;

    public BarcoHashManager() {
        tipoTabla = new HashTable<>(TIPO_TABLA_SIZE);
    }

    public void cargarBarco(BarcoInfo barco) {
        for (String tipo : barco.tipos) {
            HashTable<BarcoInfo> subTabla = getOrCreateTipoTabla(tipo);
            subTabla.put(barco.numero, barco);
            subTabla.put(barco.nombre, barco);
        }
    }

    private HashTable<BarcoInfo> getOrCreateTipoTabla(String tipo) {
        int index = Math.abs(tipo.hashCode()) % TIPO_TABLA_SIZE;
        int originalIndex = index;

        while (tipoTabla.get(index) != null) {
            String tipoExistente = ((HashTable<BarcoInfo>) tipoTabla.get(index)).toString();
            if (tipoExistente.equals(tipo)) break;
            index = (index + 1) % TIPO_TABLA_SIZE;
            if (index == originalIndex) break;
        }

        if (tipoTabla.get(index) == null) {
            tipoTabla.put(tipo, new HashTable<>(SECUNDARIA_SIZE));
        }
        return (HashTable<BarcoInfo>) tipoTabla.get(index);
    }

    public void imprimirTablas() {
        System.out.println("\nTabla principal por tipo te puedes ir al puto bar a tomarte un acerveza a mi salud:");
        tipoTabla.printTable();
    }

    public static void main(String[] args) {
        BarcoHashManager manager = new BarcoHashManager();

        BarcoInfo b1 = new BarcoInfo(1, "Kraken", Arrays.asList("Battleship"), 5);
        BarcoInfo b2 = new BarcoInfo(2, "Ghost", Arrays.asList("Frigate", "Battleship"), 4);
        BarcoInfo b3 = new BarcoInfo(3, "Dot", Arrays.asList("Canoe"), 1);

        manager.cargarBarco(b1);
        manager.cargarBarco(b2);
        manager.cargarBarco(b3);

        manager.imprimirTablas();
    }
}