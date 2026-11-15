package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Terreno;

public class Montana extends Terreno {

@Override
    public boolean esTransitable() {
        return false;
    }

    @Override
    public void afectarA(Ocupante ocupante){

    }
}
