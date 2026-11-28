package edu.fiuba.algo3.modelo.unidades.veks.ia.ataque;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;

public class AtaqueAMech extends AtaquePosible {

    public AtaqueAMech(Posicion destino, Direccion direccion, List<AtaquePosible> ataquesPosibles) {
        super(destino, direccion, ataquesPosibles);
    }

    @Override
    public void encontrarMech(Casilla casilla) {
        anotarseComoPosible();
    }
}
