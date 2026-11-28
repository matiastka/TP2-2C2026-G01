package edu.fiuba.algo3.vistas.seleccion;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.ReceptorDeOcupante;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;

class ClicSobreOcupante implements ReceptorDeOcupante {
    private final SeleccionJugador seleccion;
    private final Casilla casilla;
    private final ManejadorAccionesSeleccion manejador;

    ClicSobreOcupante(SeleccionJugador seleccion, Casilla casilla, ManejadorAccionesSeleccion manejador) {
        this.seleccion = seleccion;
        this.casilla = casilla;
        this.manejador = manejador;
    }

    @Override
    public void recibirMech(Mech mech) {
        this.seleccion.clicEnMech(mech, this.manejador);
    }

    @Override
    public void recibirVek(Vek vek) {
        this.seleccion.clicEnVek(this.casilla.obtenerPosicion(), this.manejador);
    }

    @Override
    public void recibirSinOcupante() {
        this.seleccion.clicEnCasillaVacia(this.casilla, this.manejador);
    }
}