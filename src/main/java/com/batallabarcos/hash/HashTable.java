package com.batallabarcos.hash;

class HashTable<T> {
    private Object[] table;
    private int size;

    public HashTable(int size) {
        this.size = size;
        this.table = new Object[size];
    }

    public void put(Object key, T value) {
        int index = Math.abs(key.hashCode()) % size;
        int originalIndex = index;

        while (table[index] != null) {
            index = (index + 1) % size;
            if (index == originalIndex) {
                System.out.println("Tabla llena, no se pudo insertar: " + value);
                return;
            }
        }
        table[index] = value;
    }

    public T get(Object key) {
        int index = Math.abs(key.hashCode()) % size;
        int originalIndex = index;

        while (table[index] != null) {
            T value = (T) table[index];
            if (value != null && value.equals(key)) {
                return value;
            }
            index = (index + 1) % size;
            if (index == originalIndex) break;
        }
        return null;
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ": " + table[i]);
        }
    }
}