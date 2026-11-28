package edu.fiuba.algo3.vistas.seleccion;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

public interface EstadoSeleccion {
    void ejecutar(AccionSobreMech accion);
    void clicEnMech(Mech mech, SeleccionJugador seleccion, ManejadorAccionesSeleccion manejador);
    void clicEnVek(Posicion posicion, SeleccionJugador seleccion, ManejadorAccionesSeleccion manejador);
    void clicEnCasillaVacia(Casilla casilla, SeleccionJugador seleccion, ManejadorAccionesSeleccion manejador);
}
