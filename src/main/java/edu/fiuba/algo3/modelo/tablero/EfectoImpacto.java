package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;

public interface EfectoImpacto {
    void aplicar(Ocupante ocupante, Posicion posicionImpacto, Direccion direccion, Tablero tablero);
}
