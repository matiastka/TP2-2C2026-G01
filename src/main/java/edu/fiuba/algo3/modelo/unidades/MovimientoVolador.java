package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.*;

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
        tablero.enCasilla(destino).recibirTransito(this);
    }

    @Override
    public boolean esSeguroTerminarEn(Terreno terreno) {
        return true;
    }

    /* Duda de Mati: Es correcto que MovimientoVolador no haga nada?
        Me quise basar como el Principio Strategy que vimos en la teorica, particularmente el ejemplo:
        Volador --> -VolarConAlas
                    -NoVolar
        En este caso seria:
        Movimiento --> MovimientoTerrestre
                       MovimientoVolador
     */
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
