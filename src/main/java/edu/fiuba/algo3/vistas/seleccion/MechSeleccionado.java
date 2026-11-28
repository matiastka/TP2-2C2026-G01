package edu.fiuba.algo3.vistas.seleccion;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

public class MechSeleccionado implements EstadoSeleccion {
    private final Mech mech;

    public MechSeleccionado(Mech mech) {
        this.mech = mech;
    }

    @Override
    public void ejecutar(AccionSobreMech accion) {
        accion.ejecutar(this.mech);
    }

    @Override
    public void clicEnMech(Mech mech, SeleccionJugador seleccion, ManejadorAccionesSeleccion manejador) {
        manejador.seleccionarMech(mech);
    }

    @Override
    public void clicEnVek(Posicion posicion, SeleccionJugador seleccion, ManejadorAccionesSeleccion manejador) {
        seleccion.atacarSiEsObjetivoValido(this.mech, posicion, manejador);
    }

    @Override
    public void clicEnCasillaVacia(
            Casilla casilla,
            SeleccionJugador seleccion,
            ManejadorAccionesSeleccion manejador
    ) {
        seleccion.procesarClicEnCasillaVacia(this.mech, casilla, manejador);
    }
}
