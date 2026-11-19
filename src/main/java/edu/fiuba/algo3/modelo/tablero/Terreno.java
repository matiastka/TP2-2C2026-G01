package edu.fiuba.algo3.modelo.tablero;

public abstract class Terreno {
    public abstract boolean esTransitable();

    public abstract void afectarA(Ocupante ocupante);

    public void abandonar(Ocupante ocupante) {
    }

    public void recibirDanio(int danio) {
        // Por defecto, los terrenos no reciben daño.
    }

    public boolean esEdificio() {
        return false;
    }

    public boolean esPeligrosoParaTerrestres() {
        return false;
    }

    public void recibirDanio(int danio, Casilla casilla) {
        this.recibirDanio(danio);
    }
}
