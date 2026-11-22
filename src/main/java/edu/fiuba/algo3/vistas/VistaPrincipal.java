package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.partida.VisitanteEstadoPartida;
import edu.fiuba.algo3.controllers.ControladorCasilla;
import edu.fiuba.algo3.controllers.EstadoSeleccionVista;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class VistaPrincipal extends BorderPane implements Observador, VisitanteEstadoPartida {
    private final Partida partida;
    private final VistaTablero tableroVisual;
    private final VistaBarraEstado barraEstado;
    private final VistaPanelLateral panelLateral;
    private final EstadoSeleccionVista estadoSeleccion;
    private final ControladorCasilla controladorCasilla;
    private final StackPane centro;
    private final VistaCartelFinPartida cartelFinPartida;

    public VistaPrincipal(
            Partida partida,
            VistaPanelSuperior panelSuperior,
            VistaBarraEstado barraEstado,
            VistaPanelLateral panelLateral
    ) {
        this.partida = partida;
        this.partida.agregarObservador(this);
        this.tableroVisual = new VistaTablero(partida);
        this.barraEstado = barraEstado;
        this.panelLateral = panelLateral;
        this.estadoSeleccion = new EstadoSeleccionVista();
        this.controladorCasilla = new ControladorCasilla(
                partida,
                this.estadoSeleccion,
                this.tableroVisual,
                this.panelLateral,
                this.barraEstado
        );
        this.tableroVisual.agregarListenerSeleccion(this.controladorCasilla::seleccionar);
        this.cartelFinPartida = new VistaCartelFinPartida();
        this.centro = new StackPane(this.tableroVisual, this.cartelFinPartida);

        this.tableroVisual.setPadding(new Insets(10));

        setTop(panelSuperior);
        setCenter(this.centro);
        setRight(this.panelLateral);
        setBottom(this.barraEstado);
        setStyle("-fx-background-color: #263238;");
    }

    @Override
    public void actualizar() {
        this.tableroVisual.refrescarAmenazas();
        this.partida.obtenerEstado().aceptar(this);
    }

    @Override
    public void visitarEnJuego() {
        this.cartelFinPartida.ocultar();
        this.tableroVisual.setDisable(false);
        this.panelLateral.habilitarAcciones();
        this.barraEstado.mostrarMensaje("La partida continua");
    }

    @Override
    public void visitarVictoria() {
        this.cartelFinPartida.mostrarVictoria(this.partida.obtenerVeksEliminados(), this.partida.obtenerTablero().obtenerRedElectrica());
        this.barraEstado.mostrarMensaje("Victoria");
        finalizarInteraccion();
    }

    @Override
    public void visitarDerrota() {
        this.cartelFinPartida.mostrarDerrota(this.partida.obtenerVeksEliminados(), this.partida.obtenerTablero().obtenerRedElectrica());
        this.barraEstado.mostrarError("Derrota");
        finalizarInteraccion();
    }

    private void finalizarInteraccion() {
        this.estadoSeleccion.terminar();
        this.tableroVisual.setDisable(true);
        this.panelLateral.deshabilitarAcciones();
    }
}
