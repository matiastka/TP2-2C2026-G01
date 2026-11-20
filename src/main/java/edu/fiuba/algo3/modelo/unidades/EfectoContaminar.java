package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.*;

public class EfectoContaminar implements EfectoImpacto {
    @Override
    public void aplicar(Ocupante ocupante, Posicion posicionImpacto, Direccion direccion, Tablero tablero) {
        ocupante.contaminarse();
    }
}