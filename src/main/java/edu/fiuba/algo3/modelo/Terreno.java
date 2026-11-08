package edu.fiuba.algo3.modelo;

public abstract class Terreno {

    public boolean esDelMismoTipoQue(Terreno otro){
        return this.getClass() == otro.getClass();
    }
    public abstract boolean esTransitable();
}
