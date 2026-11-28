package edu.fiuba.algo3.modelo.unidades.veks.ia.reglas;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.ia.BuscadorDeEdificios;

public class ReglaAcercarseAEdificio implements ReglaPrioridadVek {

    @Override
    public boolean intentarEjecutar(Vek vek, List<Posicion> posicionesAlcanzables, Tablero tablero) {
        List<Posicion> edificios = obtenerPosicionesEdificios(tablero);
        if (edificios.isEmpty() || posicionesAlcanzables.isEmpty()) {
            return false;
        }

        Posicion edificioObjetivo = edificios.get(0);
        int menorDistanciaInicial = Integer.MAX_VALUE;
        for (Posicion edificio : edificios) {
            int dist = vek.obtenerPosicion().distanciaA(edificio);
            if (dist < menorDistanciaInicial) {
                menorDistanciaInicial = dist;
                edificioObjetivo = edificio;
            }
        }

        Posicion mejorDestino = vek.obtenerPosicion();
        int mejorDistancia = Integer.MAX_VALUE;
        for (Posicion candidata : posicionesAlcanzables) {
            int dist = candidata.distanciaA(edificioObjetivo);
            if (dist < mejorDistancia) {
                mejorDistancia = dist;
                mejorDestino = candidata;
            }
        }

        if (!vek.obtenerPosicion().equals(mejorDestino)) {
            vek.moverA(mejorDestino, tablero);
        }
        // Prioridad 3: se acerca sin anunciar ataque.
        return true;
    }

    private List<Posicion> obtenerPosicionesEdificios(Tablero tablero) {
        BuscadorDeEdificios buscador = new BuscadorDeEdificios();
        tablero.recorrerTerrenos(buscador);
        return buscador.obtenerEdificios();
    }
}