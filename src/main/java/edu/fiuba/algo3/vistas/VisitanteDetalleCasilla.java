package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.tablero.emergencias.EmergenciaVek;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;

class VisitanteDetalleCasilla implements VisitanteVisualCasilla {
    private final VistaPanelLateral vista;

    VisitanteDetalleCasilla(VistaPanelLateral vista) {
        this.vista = vista;
    }

    @Override
    public void visitarSuelo() {
        this.vista.mostrarTerreno("Suelo");
    }

    @Override
    public void visitarAgua() {
        this.vista.mostrarTerreno("Agua");
    }

    @Override
    public void visitarAcido() {
        this.vista.mostrarTerreno("Acido");
    }

    @Override
    public void visitarEdificio() {
        this.vista.mostrarTerreno("Edificio");
    }

    @Override
    public void visitarMontana() {
        this.vista.mostrarTerreno("Montana");
    }

    @Override
    public void visitarSinOcupante() {
    }

    @Override
    public void visitarCombate(Combate combate) {
        this.vista.mostrarMech(combate, "Mech Combate");
    }

    @Override
    public void visitarCanon(Canon canon) {
        this.vista.mostrarMech(canon, "Mech Canon");
    }

    @Override
    public void visitarArtilleria(Artilleria artilleria) {
        this.vista.mostrarMech(artilleria, "Mech Artilleria");
    }

    @Override
    public void visitarLuciernaga(Luciernaga luciernaga) {
        this.vista.mostrarVek(luciernaga, "Vek Luciernaga");
    }

    @Override
    public void visitarSaltador(Saltador saltador) {
        this.vista.mostrarVek(saltador, "Vek Saltador");
    }

    @Override
    public void visitarAvispon(Avispon avispon) {
        this.vista.mostrarVek(avispon, "Vek Avispon");
    }

    @Override
    public void visitarSinEmergencia() {
    }

    @Override
    public void visitarEmergenciaVek(EmergenciaVek emergencia) {
        this.vista.mostrarEmergencia();
    }
}
