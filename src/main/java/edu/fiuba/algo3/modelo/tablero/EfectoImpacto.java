package edu.fiuba.algo3.modelo.tablero;

public interface EfectoImpacto {
    void aplicar(Ocupante ocupante, Posicion posicionImpacto, Direccion direccion, Tablero tablero);
}
