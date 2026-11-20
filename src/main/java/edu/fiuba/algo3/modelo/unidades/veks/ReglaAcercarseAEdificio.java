package edu.fiuba.algo3.modelo.unidades.veks;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.ResultadoAccion;

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
            ResultadoAccion<Void> resultadoMovimiento = vek.moverA(mejorDestino, tablero);
            if (!resultadoMovimiento.fold(ignorado -> true, motivo -> false)) {
                return false;
            }
        }
        // Prioridad 3: se acerca sin anunciar ataque.
        return true;
    }

    private List<Posicion> obtenerPosicionesEdificios(Tablero tablero) {
        List<Posicion> edificios = new ArrayList<>();
        for (int fila = 0; fila < tablero.cantidadFilas(); fila++) {
            for (int col = 0; col < tablero.cantidadColumnas(); col++) {
                Posicion pos = new Posicion(fila, col);
                if (tablero.enCasilla(pos).obtenerTerreno().esEdificio()) {
                    edificios.add(pos);
                }
            }
        }
        return edificios;
    }
}