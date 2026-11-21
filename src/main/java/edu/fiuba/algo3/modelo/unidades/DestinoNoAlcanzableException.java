package edu.fiuba.algo3.modelo.unidades;

public class DestinoNoAlcanzableException extends AccionInvalidaException {
    public DestinoNoAlcanzableException() {
        super("El destino no está al alcance");
    }
}
