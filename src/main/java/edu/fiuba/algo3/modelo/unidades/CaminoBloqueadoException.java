package edu.fiuba.algo3.modelo.unidades;

public class CaminoBloqueadoException extends AccionInvalidaException {
    public CaminoBloqueadoException() {
        super("El camino esta bloqueado");
    }
}
