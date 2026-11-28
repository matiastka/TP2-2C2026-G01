package edu.fiuba.algo3.modelo.unidades.mechs.estado.arma;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

public interface EstadoArma {
    void usar(Mech mech, Posicion objetivo, Tablero tablero);
    String obtenerDescripcion();
}
