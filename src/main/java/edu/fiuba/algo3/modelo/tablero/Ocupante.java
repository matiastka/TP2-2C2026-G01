package edu.fiuba.algo3.modelo.tablero;

public interface Ocupante {
    boolean ocupaCasilla();
    void recibirDanio(int danio);
    void caerEnAgua();
    void caerEnAcido();
    void serEmpujadoA(Posicion destino, Tablero tablero);
    void contaminarse();

    default void salirDelAgua() {
    }

    default boolean esMech() {
        return false;
    }
}