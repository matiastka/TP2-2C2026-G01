package edu.fiuba.algo3.modelo.excepciones;

public class DestinoOcupadoException extends AccionInvalidaException {
    public DestinoOcupadoException() {
        super("El destino está ocupado");
    }
}
