package edu.fiuba.algo3.modelo.unidades;

public class DestinoOcupadoException extends AccionInvalidaException {
    public DestinoOcupadoException() {
        super("El destino está ocupado");
    }
}
