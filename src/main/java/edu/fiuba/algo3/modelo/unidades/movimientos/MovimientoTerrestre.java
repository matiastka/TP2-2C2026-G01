
package edu.fiuba.algo3.modelo.unidades.movimientos;

import java.util.List;

import edu.fiuba.algo3.modelo.excepciones.CaminoBloqueadoException;
import edu.fiuba.algo3.modelo.excepciones.DestinoFueraDelTableroException;
import edu.fiuba.algo3.modelo.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Transito;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

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
    public void agregarSiPuedeTerminarEn(Casilla casilla, List<Posicion> destinos) {
        casilla.ofrecerTerrenoComoDestino(new AgregadorDeDestinos(destinos));
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