package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Terreno;
import edu.fiuba.algo3.modelo.tablero.Transito;
import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;

public class Montana extends Terreno {
    @Override
    public boolean esTransitableParaEmergencia() {
        return false;
    }
    @Override
    public void aceptar(VisitanteVisualCasilla visitante) {
        visitante.visitarMontana();
    }

    @Override
    public void afectarA(Ocupante ocupante){

    }

    @Override
    public void recibirTransito(Transito transito, Casilla casilla) {
        transito.chocarConTerreno(casilla);
    }
}
