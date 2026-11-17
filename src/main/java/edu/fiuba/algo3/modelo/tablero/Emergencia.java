package edu.fiuba.algo3.modelo.tablero;

public interface Emergencia {
    boolean estaActiva();
    void resolver(Casilla casilla, Tablero tablero);
}
