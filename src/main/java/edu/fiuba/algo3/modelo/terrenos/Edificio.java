package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.tablero.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;

public class Edificio extends Terreno {
    @Override
    public boolean esTransitableParaEmergencia() {
        return false;
    }
    @Override
    public void aceptar(VisitanteVisualCasilla visitante) {
        visitante.visitarEdificio();
    }
    private final RedElectrica redElectrica;

    public Edificio(RedElectrica redElectrica) {
        this.redElectrica = redElectrica;
    }

    @Override
    public void afectarA(Ocupante ocupante) {
    }

    @Override
    public void recibirDanio(int danio, Casilla casilla) {
        if (danio > 0) {
            this.redElectrica.reducir();
            casilla.cambiarTerreno(new Suelo());
        }
    }

    @Override
    public void ofrecerComoObjetivo(BuscadorDeObjetivos buscador, Casilla casilla) {
        buscador.encontrarEdificio(casilla);
    }

    @Override
    public void recibirTransito(Transito transito, Casilla casilla) {
        transito.chocarConTerreno(casilla);
    }
}