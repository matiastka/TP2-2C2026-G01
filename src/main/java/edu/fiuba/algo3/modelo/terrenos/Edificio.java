package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Terreno;

public class Edificio extends Terreno {
    private final RedElectrica redElectrica = RedElectrica.getInstancia();

    @Override
    public boolean esTransitable() {
        return false;
    }

    @Override
    public void afectarA(Ocupante ocupante) {
    }

    @Override
    public void recibirDanio(int danio) {
        if (danio > 0) {
            this.redElectrica.reducir();
        }
    }

    @Override
    public void recibirDanio(int danio, Casilla casilla) {
        if (danio > 0) {
            this.redElectrica.reducir();
            casilla.cambiarTerreno(new Suelo());
        }
    }

    @Override
    public boolean esEdificio() {
        return true;
    }
}