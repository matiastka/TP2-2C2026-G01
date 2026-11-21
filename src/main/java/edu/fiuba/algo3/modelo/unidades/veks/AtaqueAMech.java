package edu.fiuba.algo3.modelo.unidades.veks;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;

class AtaqueAMech extends AtaquePosible {

    AtaqueAMech(Posicion destino, Direccion direccion, List<AtaquePosible> ataquesPosibles) {
        super(destino, direccion, ataquesPosibles);
    }

    @Override
    public void encontrarMech(Casilla casilla) {
        anotarseComoPosible();
    }
}
