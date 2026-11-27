package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.tablero.emergencias.EmergenciaVek;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;

public interface VisitanteVisualCasilla {
    void visitarSuelo();
    void visitarAgua();
    void visitarAcido();
    void visitarEdificio();
    void visitarMontana();

    void visitarSinOcupante();
    void visitarCombate(Combate combate);
    void visitarCanon(Canon canon);
    void visitarArtilleria(Artilleria artilleria);
    void visitarLuciernaga(Luciernaga luciernaga);
    void visitarSaltador(Saltador saltador);
    void visitarAvispon(Avispon avispon);

    void visitarSinEmergencia();
    void visitarEmergenciaVek(EmergenciaVek emergencia);
}
