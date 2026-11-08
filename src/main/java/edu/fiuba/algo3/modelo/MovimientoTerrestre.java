
package edu.fiuba.algo3.modelo;

public class MovimientoTerrestre {
    
    public boolean puedeLlegar(Posicion origen, Posicion destino, int rango, Tablero tablero) {
        if (origen.distanciaA(destino) > rango) {
            return false;
        }

        Casilla casillaDestino = tablero.enCasilla(destino.getFila(), destino.getColumna());
        if (!casillaDestino.estaLibre()) {
            return false;
        }

        return caminoDespejado(origen, destino, tablero);
    }

    private boolean caminoDespejado(Posicion origen, Posicion destino, Tablero tablero) {
        int filaDir = Integer.compare(destino.getFila(), origen.getFila());
        int colDir = Integer.compare(destino.getColumna(), origen.getColumna());
        
        int f = origen.getFila() + filaDir;
        int c = origen.getColumna() + colDir;
        
        while (f != destino.getFila() || c != destino.getColumna()) {
            Casilla actual = tablero.enCasilla(f, c);
            if (!actual.esTransitable() || !actual.estaLibre()) {
                return false;
            }
            f += filaDir;
            c += colDir;
        }
        return true;
    }
}