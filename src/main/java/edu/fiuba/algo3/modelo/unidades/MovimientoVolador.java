package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class MovimientoVolador implements Movimiento{

    @Override
    public boolean puedeLlegar(Posicion origen, Posicion destino, int rango, Tablero tablero){
        return origen.direccionHacia(destino) != Direccion.NINGUNA &&
                tablero.estaEnRango(destino)
                && origen.estaAlAlcanceDe(destino, rango)
                && tablero.enCasilla(destino).estaLibre()
                && tablero.enCasilla(destino).esTransitable();
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
