package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EfectoEmpujar implements EfectoImpacto {

    @Override
    public void aplicar(Ocupante ocupante, Posicion posicionImpacto, Direccion direccion, Tablero tablero) {
        Posicion destino = posicionImpacto.siguienteEn(direccion);

        if (!tablero.estaEnRango(destino)) {
            return;
        }

        Casilla casillaDestino = tablero.enCasilla(destino);

        if (!casillaDestino.estaLibre() || !casillaDestino.esTransitable()) {
            ocupante.recibirDanio(1);
            casillaDestino.recibirDanio(1);
            return;
        }

        ocupante.serEmpujadoA(destino, tablero);
    }
}