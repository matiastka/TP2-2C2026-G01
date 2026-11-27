package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.tablero.BuscadorDeDestinos;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Terreno;
import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;

public class Acido extends Terreno {
    @Override
    public void aceptar(VisitanteVisualCasilla visitante) {
        visitante.visitarAcido();
    }

    @Override
    public void afectarA(Ocupante ocupante) {
        ocupante.caerEnAcido();
    }

    @Override
    public void ofrecerComoDestino(BuscadorDeDestinos buscador, Casilla casilla) {
    }

}