package edu.fiuba.algo3.modelo.unidades.veks.ia.reglas;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.unidades.veks.ia.ataque.AtaqueAEdificio;
import edu.fiuba.algo3.modelo.unidades.veks.ia.ataque.AtaquePosible;

public class ReglaAtacarEdificio extends ReglaAtacar {

    @Override
    protected AtaquePosible crearAtaque(Posicion destino, Direccion direccion, List<AtaquePosible> ataquesPosibles) {
        return new AtaqueAEdificio(destino, direccion, ataquesPosibles);   // o new AtaqueAMech(...)
    }
}