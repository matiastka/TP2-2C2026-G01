package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.BuscadorDeObjetivos;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.List;

abstract class AtaquePosible implements BuscadorDeObjetivos {
    private final Posicion destino;
    private final Direccion direccion;
    private final List<AtaquePosible> ataquesPosibles;

    AtaquePosible(Posicion destino, Direccion direccion, List<AtaquePosible> ataquesPosibles) {
        this.destino = destino;
        this.direccion = direccion;
        this.ataquesPosibles = ataquesPosibles;
    }

    protected void anotarseComoPosible() {
        this.ataquesPosibles.add(this);
    }

    void ejecutarCon(Vek vek, Tablero tablero) {
        if (!vek.obtenerPosicion().equals(this.destino)) {
            vek.moverA(this.destino, tablero);
        }
        vek.prepararAtaque(this.destino.siguienteEn(this.direccion));
    }
}
