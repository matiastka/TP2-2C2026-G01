package edu.fiuba.algo3.vistas.seleccion;

import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.emergencias.EmergenciaVek;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;

class VisitanteClicSeleccion implements VisitanteVisualCasilla {
    private final SeleccionJugador seleccion;
    private final EstadoSeleccion estado;
    private final Casilla casilla;
    private final ManejadorAccionesSeleccion manejador;

    VisitanteClicSeleccion(
            SeleccionJugador seleccion,
            EstadoSeleccion estado,
            Casilla casilla,
            ManejadorAccionesSeleccion manejador
    ) {
        this.seleccion = seleccion;
        this.estado = estado;
        this.casilla = casilla;
        this.manejador = manejador;
    }

    @Override
    public void visitarCombate(Combate combate) {
        this.estado.clicEnMech(combate, this.seleccion, this.manejador);
    }

    @Override
    public void visitarCanon(Canon canon) {
        this.estado.clicEnMech(canon, this.seleccion, this.manejador);
    }

    @Override
    public void visitarArtilleria(Artilleria artilleria) {
        this.estado.clicEnMech(artilleria, this.seleccion, this.manejador);
    }

    @Override
    public void visitarLuciernaga(Luciernaga luciernaga) {
        procesarVek();
    }

    @Override
    public void visitarSaltador(Saltador saltador) {
        procesarVek();
    }

    @Override
    public void visitarAvispon(Avispon avispon) {
        procesarVek();
    }

    @Override
    public void visitarSinOcupante() {
        this.estado.clicEnCasillaVacia(this.casilla, this.seleccion, this.manejador);
    }

    private void procesarVek() {
        Posicion posicion = this.casilla.obtenerPosicion();
        this.estado.clicEnVek(posicion, this.seleccion, this.manejador);
    }

    @Override
    public void visitarSuelo() {
    }

    @Override
    public void visitarAgua() {
    }

    @Override
    public void visitarAcido() {
    }

    @Override
    public void visitarEdificio() {
    }

    @Override
    public void visitarMontana() {
    }

    @Override
    public void visitarSinEmergencia() {
    }

    @Override
    public void visitarEmergenciaVek(EmergenciaVek emergencia) {
    }
}
