package edu.fiuba.algo3.modelo.tablero;

public interface Transito {
    void atravesar(Casilla casilla);
    void chocarConOcupante(Casilla casilla);
    void chocarConTerreno(Casilla casilla);
}
