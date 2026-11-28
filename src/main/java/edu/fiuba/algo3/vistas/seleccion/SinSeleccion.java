package edu.fiuba.algo3.vistas.seleccion;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

public class SinSeleccion implements EstadoSeleccion {
    @Override
    public void ejecutar(AccionSobreMech accion) {
        accion.ejecutarSinMechSeleccionado();
    }

    @Override
    public void clicEnMech(Mech mech, SeleccionJugador seleccion, ManejadorAccionesSeleccion manejador) {
        manejador.seleccionarMech(mech);
    }

    @Override
    public void clicEnVek(Posicion posicion, SeleccionJugador seleccion, ManejadorAccionesSeleccion manejador) {
    }

    @Override
    public void clicEnCasillaVacia(
            Casilla casilla,
            SeleccionJugador seleccion,
            ManejadorAccionesSeleccion manejador
    ) {
    }
}
