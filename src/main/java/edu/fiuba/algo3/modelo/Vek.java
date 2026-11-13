package edu.fiuba.algo3.modelo;

public abstract class Vek extends Unidad{
    protected Vek(int vida, Posicion posicion){
        super(vida, posicion);
    }

    @Override
    public void caerEnAgua(){
        recibirDanio(obtenerVida());
    }
}
