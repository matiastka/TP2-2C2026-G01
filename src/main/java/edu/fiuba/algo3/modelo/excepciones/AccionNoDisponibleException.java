package edu.fiuba.algo3.modelo.excepciones;

public class AccionNoDisponibleException extends AccionInvalidaException {
    public AccionNoDisponibleException() {
        super("La acción no está disponible en este momento");
    }
}
