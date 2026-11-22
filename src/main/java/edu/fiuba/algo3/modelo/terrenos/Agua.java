package edu.fiuba.algo3.modelo.terrenos;


import edu.fiuba.algo3.modelo.tablero.BuscadorDeDestinos;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Terreno;
import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;

public class Agua extends Terreno {
    @Override
    public void aceptar(VisitanteVisualCasilla visitante) {
        visitante.visitarAgua();
    }

    @Override
    public void afectarA(Ocupante ocupante){
        ocupante.caerEnAgua();
    }

    @Override
    public void abandonar(Ocupante ocupante) {
        ocupante.salirDelAgua();
    }

    @Override
    public void ofrecerComoDestino(BuscadorDeDestinos buscador, Casilla casilla) {
    }

    @Override
    public boolean esTransitableParaEmergencia() {
        return false;
    }

}
