package edu.fiuba.algo3.modelo.unidades.excepciones;

public class ArmaSumergidaException extends AccionInvalidaException {
    public ArmaSumergidaException() {
        super("Un Mech no puede atacar debajo del agua");
    }
}
