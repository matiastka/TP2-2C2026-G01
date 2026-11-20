package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Terreno;

public class MovimientoVolador implements Movimiento{

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
        if (!tablero.enCasilla(destino).estaLibre()) {
            throw new DestinoOcupadoException();
        }
        if (!tablero.enCasilla(destino).esTransitable()) {
            throw new CaminoBloqueadoException();
        }
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
}
