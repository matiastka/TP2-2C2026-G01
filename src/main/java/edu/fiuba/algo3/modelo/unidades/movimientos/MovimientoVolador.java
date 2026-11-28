package edu.fiuba.algo3.modelo.unidades.movimientos;

import java.util.List;

import edu.fiuba.algo3.modelo.excepciones.CaminoBloqueadoException;
import edu.fiuba.algo3.modelo.excepciones.DestinoFueraDelTableroException;
import edu.fiuba.algo3.modelo.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.excepciones.DestinoOcupadoException;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Transito;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class MovimientoVolador implements Movimiento, Transito {

    @Override
    public void validarCamino(Posicion origen, Posicion destino, int rango, Tablero tablero){
        if (!origen.estaEnLineaRectaA(destino, 1)) {
            throw new DestinoNoAlcanzableException();
        }
        if (!tablero.estaEnRango(destino)) {
            throw new DestinoFueraDelTableroException();
        }
        if (!origen.estaAlAlcanceDe(destino, rango)) {
            throw new DestinoNoAlcanzableException();
        }
        aterrizarEn(destino, tablero);
    }

    private void aterrizarEn(Posicion destino, Tablero tablero) {
        tablero.recibirTransitoEn(destino, this);
    }

    @Override
    public void agregarSiPuedeTerminarEn(Casilla casilla, List<Posicion> destinos) {
        destinos.add(casilla.obtenerPosicion());
    }

    @Override
    public void caerEnAcido(Unidad unidad){
    }

    @Override
    public void caerEnAgua(Unidad unidad){
    }

    @Override
    public void atravesar(Casilla casilla) {
    }

    @Override
    public void chocarConOcupante(Casilla casilla) {
        throw new DestinoOcupadoException();
    }

    @Override
    public void chocarConTerreno(Casilla casilla) {
        throw new CaminoBloqueadoException();
    }
}
