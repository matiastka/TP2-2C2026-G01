package edu.fiuba.algo3.modelo.unidades;

public class DestinoFueraDelTableroException extends AccionInvalidaException {
    public DestinoFueraDelTableroException() {
        super("El destino está fuera del tablero");
    }
}
