package edu.fiuba.algo3.modelo.excepciones;

public class AtaqueYaPreparadoException extends AccionInvalidaException{
    public AtaqueYaPreparadoException() {
        super("El Vek ya tiene un ataque preparado");
    }
}
