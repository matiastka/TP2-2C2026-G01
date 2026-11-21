package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface EstadoArma {
    void usar(Mech mech, Posicion objetivo, Tablero tablero);
}
