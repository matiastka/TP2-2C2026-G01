package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;

public class CreadorDeLuciernaga  implements CreadorDeVek {
    @Override
    public Vek crear() {
        return new Luciernaga();
    }
}
