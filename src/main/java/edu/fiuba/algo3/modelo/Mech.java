package edu.fiuba.algo3.modelo;

public abstract class Mech extends Unidad{
    protected Mech(int vida){
        super(vida);
    }

    public abstract void atacarA(Posicion posicion, Tablero tablero);

}
