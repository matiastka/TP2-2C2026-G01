package edu.fiuba.algo3.vistas.seleccion;

import java.util.List;

import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.emergencias.EmergenciaVek;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;

class VisitanteObjetivoVek implements VisitanteVisualCasilla {
    private final Posicion posicion;
    private final Mech mech;
    private final List<Posicion> objetivos;

    VisitanteObjetivoVek(Posicion posicion, Mech mech, List<Posicion> objetivos) {
        this.posicion = posicion;
        this.mech = mech;
        this.objetivos = objetivos;
    }

    private void agregarSiEstaAlAlcance() {
        if (this.posicion.distanciaA(this.mech.obtenerPosicion()) <= this.mech.obtenerRango()
                && !this.posicion.equals(this.mech.obtenerPosicion())) {
            this.objetivos.add(this.posicion);
        }
    }

    @Override
    public void visitarLuciernaga(Luciernaga luciernaga) {
        agregarSiEstaAlAlcance();
    }

    @Override
    public void visitarSaltador(Saltador saltador) {
        agregarSiEstaAlAlcance();
    }

    @Override
    public void visitarAvispon(Avispon avispon) {
        agregarSiEstaAlAlcance();
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
    public void visitarSinOcupante() {
    }

    @Override
    public void visitarCombate(Combate combate) {
    }

    @Override
    public void visitarCanon(Canon canon) {
    }

    @Override
    public void visitarArtilleria(Artilleria artilleria) {
    }

    @Override
    public void visitarSinEmergencia() {
    }

    @Override
    public void visitarEmergenciaVek(EmergenciaVek emergencia) {
    }
}
