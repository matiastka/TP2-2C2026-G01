package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Terreno;

public class Acido extends Terreno {

    @Override
    public void afectarA(Ocupante ocupante) {
        ocupante.caerEnAcido();
    }

    @Override
    public boolean esPeligrosoParaTerrestres() {
        return true;
    }
}
