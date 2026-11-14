
package edu.fiuba.algo3.modelo;

public class MovimientoTerrestre {

    public boolean puedeLlegar(Posicion origen, Posicion destino, int rango, Tablero tablero) {
        if (origen.direccionHacia(destino) == Direccion.NINGUNA) return false;
        if (!tablero.estaEnRango(destino)) return false;
        if (!origen.estaAlAlcanceDe(destino, rango)) return false;
        return caminoDespejado(origen, destino, tablero);
    }

    private boolean caminoDespejado(Posicion origen, Posicion destino, Tablero tablero) {
        Direccion direccion = origen.direccionHacia(destino);
        Posicion actual = origen;
        do {
            actual = actual.siguienteEn(direccion);
            Casilla casilla = tablero.enCasilla(actual);
            if (!casilla.esTransitable() || !casilla.estaLibre()) return false;
        } while (!actual.equals(destino));
        return true;
    }
}