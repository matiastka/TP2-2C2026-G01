package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.vistas.VistaPrincipal;

public class ControladorEstadoPartida implements Observador {
    private final Partida partida;
    private final VistaPrincipal vista;

    public ControladorEstadoPartida(Partida partida, VistaPrincipal vista) {
        this.partida = partida;
        this.vista = vista;
        this.partida.agregarObservador(this);
        actualizar();
    }

    @Override
    public void actualizar() {
        this.vista.refrescarAmenazas();
        if (!this.partida.obtenerEstado().estaTerminada()) {
            this.vista.mostrarEnJuego();
        } else if (this.partida.obtenerEstado().ganoElJugador()) {
            this.vista.mostrarVictoria(
                    this.partida.obtenerVeksEliminados(),
                    this.partida.obtenerTablero().obtenerRedElectrica()
            );
        } else {
            this.vista.mostrarDerrota(
                    this.partida.obtenerVeksEliminados(),
                    this.partida.obtenerTablero().obtenerRedElectrica()
            );
        }
    }
}
