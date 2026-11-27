package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;

public class Montana extends Terreno {

    @Override
    public void ofrecerParaEmergencia(BuscadorDeEmergencias buscador, Casilla casilla) {
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
