package edu.fiuba.algo3.modelo.terrenos;


import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Terreno;

public class Agua extends Terreno {

    @Override
    public boolean esTransitable() {
        return true;
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
    public boolean esPeligrosoParaTerrestres() {
        return true;
    }
}
