package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.unidades.mechs.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Saltador;
import edu.fiuba.algo3.modelo.tablero.emergencias.EmergenciaVek;

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
