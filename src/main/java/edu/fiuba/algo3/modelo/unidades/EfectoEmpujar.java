package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.*;

public class EfectoEmpujar implements EfectoImpacto {

    @Override
    public void aplicar(Ocupante ocupante, Posicion posicionImpacto, Direccion direccion, Tablero tablero) {
        Posicion destino = posicionImpacto.siguienteEn(direccion);
        if (!tablero.estaEnRango(destino)) {
            return;
        }
        tablero.enCasilla(destino).recibirTransito(new Empujon(ocupante, tablero));
    }
}