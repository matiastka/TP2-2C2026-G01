package edu.fiuba.algo3.modelo.excepciones;

public class PartidaTerminadaException extends RuntimeException {
    public PartidaTerminadaException() {
        super("La partida ya terminó");
    }
}