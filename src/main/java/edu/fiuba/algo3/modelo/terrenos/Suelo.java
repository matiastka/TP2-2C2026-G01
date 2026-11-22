package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Terreno;
import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;

public class Suelo extends Terreno {
    @Override
    public void aceptar(VisitanteVisualCasilla visitante) {
        visitante.visitarSuelo();
    }

    @Override
    public void afectarA(Ocupante ocupante){
    }
}
