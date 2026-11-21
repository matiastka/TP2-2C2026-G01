package edu.fiuba.algo3.modelo.partida;

public class PartidaTerminadaException extends RuntimeException {
    public PartidaTerminadaException() {
        super("La partida ya terminó");
    }
}