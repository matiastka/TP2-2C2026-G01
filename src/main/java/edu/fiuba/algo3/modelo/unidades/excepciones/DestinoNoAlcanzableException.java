package edu.fiuba.algo3.modelo.unidades.excepciones;

public class DestinoNoAlcanzableException extends AccionInvalidaException {
    public DestinoNoAlcanzableException() {
        super("El destino no está al alcance");
    }
}
