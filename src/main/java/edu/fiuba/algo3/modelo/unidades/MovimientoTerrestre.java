
package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Terreno;

public class MovimientoTerrestre implements Movimiento {

    @Override
    public ResultadoAccion<Void> validarCamino(Posicion origen, Posicion destino, int rango, Tablero tablero) {
        if (!origen.estaEnLineaRectaA(destino, 1)) {
            return ResultadoAccion.rechazo(MotivoRechazoAccion.DESTINO_NO_ALCANZABLE);
        }
        if (!tablero.estaEnRango(destino)) {
            return ResultadoAccion.rechazo(MotivoRechazoAccion.DESTINO_FUERA_DEL_TABLERO);
        }
        if (!origen.estaAlAlcanceDe(destino, rango)) {
            return ResultadoAccion.rechazo(MotivoRechazoAccion.DESTINO_NO_ALCANZABLE);
        }
        if (!caminoDespejado(origen, destino, tablero)) {
            return ResultadoAccion.rechazo(MotivoRechazoAccion.CAMINO_BLOQUEADO);
        }
        return ResultadoAccion.exito(null);
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
    public boolean esSeguroTerminarEn(Terreno terreno) {
        return !terreno.esPeligrosoParaTerrestres();
    }

    @Override
    public void caerEnAgua(Unidad unidad){
        unidad.hundirse();
    }
}