package edu.fiuba.algo3.modelo.tablero;

public interface BuscadorDeObjetivos {
    default void encontrarMech(Casilla casilla) {}
    
    default void encontrarEdificio(Casilla casilla) {}
}
