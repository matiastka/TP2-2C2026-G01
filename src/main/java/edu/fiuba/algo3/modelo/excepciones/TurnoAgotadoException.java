package edu.fiuba.algo3.modelo.excepciones;

public class TurnoAgotadoException extends AccionInvalidaException {
    public TurnoAgotadoException() {
        super("La unidad ya agotó sus acciones en este turno");
    }
}
