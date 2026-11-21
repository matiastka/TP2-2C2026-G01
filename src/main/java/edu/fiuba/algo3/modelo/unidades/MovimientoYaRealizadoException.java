package edu.fiuba.algo3.modelo.unidades;

public class MovimientoYaRealizadoException extends AccionInvalidaException {
    public MovimientoYaRealizadoException() {
        super("La unidad ya se movió en este turno");
    }
}
