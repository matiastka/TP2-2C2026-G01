package edu.fiuba.algo3.modelo.terrenos;


import edu.fiuba.algo3.modelo.tablero.Ocupante;

public class Agua extends Terreno {

    @Override
    public boolean esTransitable() {
        return true;
    }

    @Override
    public void afectarA(Ocupante ocupante){
        ocupante.caerEnAgua();
    }
}
