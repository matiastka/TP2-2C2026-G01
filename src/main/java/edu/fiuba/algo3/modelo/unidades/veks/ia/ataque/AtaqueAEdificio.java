package edu.fiuba.algo3.modelo.unidades.veks.ia.ataque;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;

public class AtaqueAEdificio extends AtaquePosible {

    public AtaqueAEdificio(Posicion destino, Direccion direccion, List<AtaquePosible> ataquesPosibles) {
        super(destino, direccion, ataquesPosibles);
    }

    @Override
    public void encontrarEdificio(Casilla casilla) {
        anotarseComoPosible();
    }
}
