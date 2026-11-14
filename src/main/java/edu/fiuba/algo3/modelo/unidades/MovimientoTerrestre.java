
package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class MovimientoTerrestre implements Movimiento {

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

    @Override
    public void caerEnAcido(Unidad unidad){
        unidad.contaminarse();
    }

    @Override
    public void caerEnAgua(Unidad unidad){
        unidad.hundirse();
    }
}