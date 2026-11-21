package edu.fiuba.algo3.modelo.unidades.veks.ia;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;

public class ReglaAtacarEdificio extends ReglaAtacar {

    @Override
    protected AtaquePosible crearAtaque(Posicion destino, Direccion direccion, List<AtaquePosible> ataquesPosibles) {
        return new AtaqueAEdificio(destino, direccion, ataquesPosibles);   // o new AtaqueAMech(...)
    }
}