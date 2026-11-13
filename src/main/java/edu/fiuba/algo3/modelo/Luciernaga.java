package edu.fiuba.algo3.modelo;

public class Luciernaga extends Vek{
    public Luciernaga(Posicion posicion){
        super(3, posicion);
        this.rango = 2;
    }
    @Override
    public void caerEnAgua() {
        recibirDanio(obtenerVida());
    }
}
