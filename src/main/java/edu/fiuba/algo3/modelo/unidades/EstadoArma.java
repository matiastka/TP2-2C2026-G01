package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface EstadoArma {
    ResultadoAccion<Void> usar(Mech mech, Posicion objetivo, Tablero tablero);
}
