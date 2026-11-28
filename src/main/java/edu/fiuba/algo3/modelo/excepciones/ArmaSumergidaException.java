package edu.fiuba.algo3.modelo.excepciones;

public class ArmaSumergidaException extends AccionInvalidaException {
    public ArmaSumergidaException() {
        super("Un Mech no puede atacar debajo del agua");
    }
}
