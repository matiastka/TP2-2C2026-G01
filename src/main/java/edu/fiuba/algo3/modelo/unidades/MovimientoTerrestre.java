
package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.*;

public class MovimientoTerrestre implements Movimiento, Transito {

    @Override
    public void validarCamino(Posicion origen, Posicion destino, int rango, Tablero tablero) {
        if (!origen.estaEnLineaRectaA(destino, 1)) {
            throw new DestinoNoAlcanzableException();
        }
        if (!tablero.estaEnRango(destino)) {
            throw new DestinoFueraDelTableroException();
        }
        if (!origen.estaAlAlcanceDe(destino, rango)) {
            throw new DestinoNoAlcanzableException();
        }
        recorrerCamino(origen, destino, tablero);
    }

    private void recorrerCamino(Posicion origen, Posicion destino, Tablero tablero) {
        Direccion direccion = origen.direccionHacia(destino);
        Posicion actual = origen;
        do {
            actual = actual.siguienteEn(direccion);
            tablero.enCasilla(actual).recibirTransito(this);
        } while (!actual.equals(destino));
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

    @Override
    public void atravesar(Casilla casilla) {
    }

    @Override
    public void chocarConOcupante(Casilla casilla) {
        throw new CaminoBloqueadoException();
    }

    @Override
    public void chocarConTerreno(Casilla casilla) {
        throw new CaminoBloqueadoException();
    }
}