package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Terreno;

public class MovimientoVolador implements Movimiento{

    @Override
    public ResultadoAccion<Void> validarCamino(Posicion origen, Posicion destino, int rango, Tablero tablero){
        if (!origen.estaEnLineaRectaA(destino, 1)) {
            return ResultadoAccion.rechazo(MotivoRechazoAccion.DESTINO_NO_ALCANZABLE);
        }
        if (!tablero.estaEnRango(destino)) {
            return ResultadoAccion.rechazo(MotivoRechazoAccion.DESTINO_FUERA_DEL_TABLERO);
        }
        if (!origen.estaAlAlcanceDe(destino, rango)) {
            return ResultadoAccion.rechazo(MotivoRechazoAccion.DESTINO_NO_ALCANZABLE);
        }
        if (!tablero.enCasilla(destino).estaLibre()) {
            return ResultadoAccion.rechazo(MotivoRechazoAccion.DESTINO_OCUPADO);
        }
        if (!tablero.enCasilla(destino).esTransitable()) {
            return ResultadoAccion.rechazo(MotivoRechazoAccion.CAMINO_BLOQUEADO);
        }
        return ResultadoAccion.exito(null);
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
