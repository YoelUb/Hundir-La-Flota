package com.batallabarcos.puerto;

import java.util.*;

public class GrafoPuertos {
    private Map<Puerto, Map<Puerto, Integer>> adyacencia = new HashMap<>();

    public void agregarPuerto(String nombre) {
        adyacencia.putIfAbsent(new Puerto(nombre), new HashMap<>());
    }

    public void conectar(String nombre1, String nombre2, int distancia) {
        Puerto p1 = buscarPuerto(nombre1);
        Puerto p2 = buscarPuerto(nombre2);
        if (p1 != null && p2 != null && distancia > 0) {
            adyacencia.get(p1).put(p2, distancia);
            adyacencia.get(p2).put(p1, distancia);
        }
    }

    private Puerto buscarPuerto(String nombre) {
        return adyacencia.keySet().stream()
                .filter(p -> p.nombre.equals(nombre))
                .findFirst().orElse(null);
    }

    public void dfs(String inicio) {
        Puerto origen = buscarPuerto(inicio);
        if (origen == null) return;
        Set<Puerto> visitados = new HashSet<>();
        System.out.print("DFS desde " + inicio + ": ");
        dfsRecursivo(origen, visitados);
        System.out.println();
    }

    private void dfsRecursivo(Puerto actual, Set<Puerto> visitados) {
        visitados.add(actual);
        System.out.print(actual + " ");
        for (Puerto vecino : adyacencia.get(actual).keySet()) {
            if (!visitados.contains(vecino)) {
                dfsRecursivo(vecino, visitados);
            }
        }
    }

    public void caminoMasCorto(String inicio, String destino) {
        Puerto origen = buscarPuerto(inicio);
        Puerto fin = buscarPuerto(destino);
        if (origen == null || fin == null) return;

        Map<Puerto, Integer> dist = new HashMap<>();
        Map<Puerto, Puerto> anterior = new HashMap<>();
        Set<Puerto> visitados = new HashSet<>();
        PriorityQueue<Puerto> cola = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        for (Puerto p : adyacencia.keySet()) {
            dist.put(p, Integer.MAX_VALUE);
            anterior.put(p, null);
        }
        dist.put(origen, 0);
        cola.add(origen);

        while (!cola.isEmpty()) {
            Puerto actual = cola.poll();
            if (!visitados.add(actual)) continue;

            for (Map.Entry<Puerto, Integer> vecino : adyacencia.get(actual).entrySet()) {
                int nuevaDist = dist.get(actual) + vecino.getValue();
                if (nuevaDist < dist.get(vecino.getKey())) {
                    dist.put(vecino.getKey(), nuevaDist);
                    anterior.put(vecino.getKey(), actual);
                    cola.add(vecino.getKey());
                }
            }
        }

        if (dist.get(fin) == Integer.MAX_VALUE) {
            System.out.println("No hay camino entre " + inicio + " y " + destino);
            return;
        }

        List<Puerto> camino = new ArrayList<>();
        for (Puerto at = fin; at != null; at = anterior.get(at)) {
            camino.add(at);
        }
        Collections.reverse(camino);
        System.out.println("Camino más corto de " + inicio + " a " + destino + ": " + camino + " (distancia: " + dist.get(fin) + ")");
    }

    public void eliminarPuertoMayorGrado() {
        Puerto max = null;
        int maxGrado = -1;
        for (Puerto p : adyacencia.keySet()) {
            int grado = adyacencia.get(p).size();
            if (grado > maxGrado) {
                max = p;
                maxGrado = grado;
            }
        }
        if (max != null) {
            for (Puerto p : adyacencia.keySet()) {
                adyacencia.get(p).remove(max);
            }
            adyacencia.remove(max);
            System.out.println("Se eliminó el puerto con mayor conexiones: " + max);
        }
    }

    public static void main(String[] args) {
        GrafoPuertos grafo = new GrafoPuertos();

        grafo.agregarPuerto("Madero");
        grafo.agregarPuerto("Rodas");
        grafo.agregarPuerto("Genova");
        grafo.agregarPuerto("Marsella");
        grafo.agregarPuerto("Lisboa");

        grafo.conectar("Madero", "Genova", 7);
        grafo.conectar("Madero", "Marsella", 5);
        grafo.conectar("Genova", "Rodas", 10);
        grafo.conectar("Marsella", "Lisboa", 3);
        grafo.conectar("Lisboa", "Rodas", 6);

        grafo.dfs("Madero");
        grafo.caminoMasCorto("Madero", "Rodas");
        grafo.eliminarPuertoMayorGrado();
    }
}