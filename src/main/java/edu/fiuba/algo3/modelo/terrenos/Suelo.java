package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Suelo extends Terreno {
    @Override
    public boolean esTransitable() {
        return true;
    }

    @Override
    public void afectarA(Unidad unidad){

    }
}
