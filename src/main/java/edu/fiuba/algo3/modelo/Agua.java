package edu.fiuba.algo3.modelo;

public class Agua extends Terreno {

    @Override
    public boolean esTransitable() {
        return true;
    }

    @Override
    public void afectarA(Unidad unidad){
        unidad.caerEnAgua();
    }
}
