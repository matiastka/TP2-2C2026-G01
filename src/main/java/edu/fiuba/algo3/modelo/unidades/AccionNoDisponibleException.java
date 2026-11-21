package edu.fiuba.algo3.modelo.unidades;

public class AccionNoDisponibleException extends AccionInvalidaException {
    public AccionNoDisponibleException() {
        super("La acción no está disponible en este momento");
    }
}
