package edu.fiuba.algo3.modelo.unidades.veks;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;

public class ReglaAtacarMech extends ReglaAtacar {

    @Override
    protected AtaquePosible crearAtaque(Posicion destino, Direccion direccion, List<AtaquePosible> ataquesPosibles) {
        return new AtaqueAMech(destino, direccion, ataquesPosibles);   // o new AtaqueAMech(...)
    }
}
