package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.tablero.Casilla;

class CasillaSeleccionada implements EstadoSeleccionCasilla {
    private final Casilla casilla;

    CasillaSeleccionada(Casilla casilla) {
        this.casilla = casilla;
    }

    @Override
    public void actualizar(VistaPanelLateral vista) {
        vista.mostrarDetalles(this.casilla);
    }
}
