package edu.fiuba.algo3.modelo.unidades.excepciones;

public class DestinoFueraDelTableroException extends AccionInvalidaException {
    public DestinoFueraDelTableroException() {
        super("El destino está fuera del tablero");
    }
}
