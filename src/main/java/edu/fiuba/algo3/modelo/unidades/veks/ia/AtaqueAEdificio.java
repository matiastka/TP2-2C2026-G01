package edu.fiuba.algo3.modelo.unidades.veks.ia;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;

class AtaqueAEdificio extends AtaquePosible {

    AtaqueAEdificio(Posicion destino, Direccion direccion, List<AtaquePosible> ataquesPosibles) {
        super(destino, direccion, ataquesPosibles);
    }

    @Override
    public void encontrarEdificio(Casilla casilla) {
        anotarseComoPosible();
    }
}
