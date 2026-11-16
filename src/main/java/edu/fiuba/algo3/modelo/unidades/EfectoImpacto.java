package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface EfectoImpacto {
    void aplicar(Ocupante ocupante, Posicion posicionImpacto, Direccion direccion, Tablero tablero);
}
