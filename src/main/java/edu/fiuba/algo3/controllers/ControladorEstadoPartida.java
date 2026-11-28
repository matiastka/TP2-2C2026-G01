package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.partida.estado.VisitanteEstadoPartida;
import edu.fiuba.algo3.vistas.VistaPrincipal;

public class ControladorEstadoPartida implements Observador, VisitanteEstadoPartida {
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
        this.partida.obtenerEstado().aceptar(this);
    }

    @Override
    public void visitarEnJuego() {
        this.vista.mostrarEnJuego();
    }

    @Override
    public void visitarVictoria() {
        this.vista.mostrarVictoria(
                this.partida.obtenerVeksEliminados(),
                this.partida.obtenerTablero().obtenerRedElectrica()
        );
    }

    @Override
    public void visitarDerrota() {
        this.vista.mostrarDerrota(
                this.partida.obtenerVeksEliminados(),
                this.partida.obtenerTablero().obtenerRedElectrica()
        );
    }
}
