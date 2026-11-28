package edu.fiuba.algo3.vistas;

class SinCasillaSeleccionada implements EstadoSeleccionCasilla {
    @Override
    public void actualizar(VistaPanelLateral vista) {
        vista.mostrarSinSeleccion();
    }
}
