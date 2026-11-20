package edu.fiuba.algo3.modelo.tablero;

public abstract class Terreno {

    public abstract void afectarA(Ocupante ocupante);

    public void abandonar(Ocupante ocupante) {
    }

    public boolean esEdificio() {
        return false;
    }

    public boolean esPeligrosoParaTerrestres() {
        return false;
    }

    public void recibirDanio(int danio, Casilla casilla) {
    }

    public void recibirTransito(Transito transito, Casilla casilla) {
        transito.atravesar(casilla);
    }
}
