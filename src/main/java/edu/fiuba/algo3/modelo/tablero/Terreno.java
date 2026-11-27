package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;

public abstract class Terreno {
    public abstract void aceptar(VisitanteVisualCasilla visitante);

    public boolean esTransitableParaEmergencia() {
        return true;
    }

    public abstract void afectarA(Ocupante ocupante);

    public void abandonar(Ocupante ocupante) {
    }

    public void ofrecerComoObjetivo(BuscadorDeObjetivos buscador, Casilla casilla) {
    }

    public void recibirDanio(int danio, Casilla casilla) {
    }

    public void recibirTransito(Transito transito, Casilla casilla) {
        transito.atravesar(casilla);
    }

    public void ofrecerComoDestino(BuscadorDeDestinos buscador, Casilla casilla) {
        buscador.encontrarTerrenoSeguro(casilla);
    }

}
