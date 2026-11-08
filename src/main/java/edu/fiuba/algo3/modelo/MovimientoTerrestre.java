
package edu.fiuba.algo3.modelo;

public class MovimientoTerrestre {

    public boolean puedeLlegar(Posicion origen, Posicion destino, int rango, Tablero tablero) {
        boolean mismaFila = origen.getFila() == destino.getFila();
        boolean mismaColumna = origen.getColumna() == destino.getColumna();
        if (!mismaFila && !mismaColumna) return false;
        if (!tablero.estaEnRango(destino.getFila(), destino.getColumna())) return false;
        if (origen.distanciaA(destino) > rango) return false;
        return caminoDespejado(origen, destino, tablero);
    }

    private boolean caminoDespejado(Posicion origen, Posicion destino, Tablero tablero) {
        int filaDir = Integer.compare(destino.getFila(), origen.getFila());
        int colDir = Integer.compare(destino.getColumna(), origen.getColumna());
        int f = origen.getFila();
        int c = origen.getColumna();
        do {
            f += filaDir;
            c += colDir;
            Casilla actual = tablero.enCasilla(f, c);
            if (!actual.esTransitable() || !actual.estaLibre()) return false;
        } while (f != destino.getFila() || c != destino.getColumna());
        return true;
    }
}