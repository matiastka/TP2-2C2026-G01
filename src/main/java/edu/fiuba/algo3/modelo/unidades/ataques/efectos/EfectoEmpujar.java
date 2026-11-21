package edu.fiuba.algo3.modelo.unidades.ataques.efectos;

import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;

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