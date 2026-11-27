package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;

public class CreadorDeAvispon implements CreadorDeVek {
    @Override
    public Vek crear() {
        return new Avispon();
    }
}
