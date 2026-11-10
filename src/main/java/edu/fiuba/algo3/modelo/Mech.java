package edu.fiuba.algo3.modelo;

public abstract class Mech extends Unidad {
    protected Mech(int vida) {
        super(vida);
    }

    public void atacarA(Posicion posicion, Tablero tablero) {
        if (this.yaAtaco) {
            System.out.println("Ataque inválido: el Mech ya atacó en este turno.");
            return;
        }
        ejecutarAtaque(posicion, tablero);
        this.yaAtaco = true;
    }

    protected abstract void ejecutarAtaque(Posicion posicion, Tablero tablero);
}
