package edu.fiuba.algo3.vistas.seleccion;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

public interface ManejadorAccionesSeleccion {
    void seleccionarMech(Mech mech);
    void mover(Mech mech, Posicion destino);
    void atacar(Mech mech, Posicion objetivo);
}
