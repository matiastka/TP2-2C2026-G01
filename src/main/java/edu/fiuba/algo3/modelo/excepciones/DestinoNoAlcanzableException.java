package edu.fiuba.algo3.modelo.excepciones;

public class DestinoNoAlcanzableException extends AccionInvalidaException {
    public DestinoNoAlcanzableException() {
        super("El destino no está al alcance");
    }
}
