package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Edificio extends Terreno {
    @Override
    public boolean esTransitable() {
        return false;
    }

    @Override
    public void afectarA(Unidad unidad){

    }
}
